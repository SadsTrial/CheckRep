package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateVisit extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for verifying audit logs for update Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForUpdateVisit(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib visitTestData = new AuditLib();
		visitTestData.workSheetName = "UpdateVisit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchAuditTestData();
		verifyAuditLogForUpdateVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForUpdateVisit
	 * @Description : Function to Verify Audit Log For Update Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Anil Sannareddy
	 * @Created on 	: July 26, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForUpdateVisit(String seleniumHost, int seleniumPort,String browser, String webSite,AuditLib visitTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Visit and enter details                    //
			//--------------------------------------------------------------------//

			click(selenium, "visits");
			waitForPageLoad(selenium);

			if(!isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){
				visitTestData.workSheetName = "Visit";
				visitTestData.testCaseId = "TC_VIS_001";		
				visitTestData.fetchAuditTestData();
				createVisit(selenium, visitTestData);
			}

			visitTestData = null;
			visitTestData = new AuditLib();
			visitTestData.workSheetName = "UpdateVisit";
			visitTestData.testCaseId = "TC_VIS_001";		
			visitTestData.fetchAuditTestData();
			click(selenium, "visits");
			waitForPageLoad(selenium);

			String uniqueID = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id").split("visit")[1];
			Assert.assertTrue(click(selenium,"//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a"),"Could not find First Visit link in the list; More Details :" + visitTestData.toString());
			waitForPageLoad(selenium);

			if(getValue(selenium,"visitdateInput").trim().equals(visitTestData.visitDate)){
				visitTestData.workSheetName = "UpdateVisit";
				visitTestData.testCaseId = "TC_VIS_002";		
				visitTestData.fetchAuditTestData();
			}
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);

			Assert.assertTrue(enterDate(selenium, "visitdateInput", visitTestData.visitDate),"Could not enter the test data for Visit Date; More Details :" + visitTestData.toString());
			Assert.assertTrue(select(selenium, "providersInput", visitTestData.providerName),"Could not select the test data for Provider Name; More Details :" + visitTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", visitTestData.providerLocation),"Could not select the test data for Provider Location; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"subjectiveInput", visitTestData.patientSubjective),"Could not enter the test data for Patient Subjective; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"objectiveInput", visitTestData.patientObjective),"Could not enter the test data for Patient Objective; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"assessmentInput", visitTestData.patientAssessment),"Could not enter the test data for Patient Assessment; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"planInput", visitTestData.patientPlan),"Could not enter the test data for Patient Plan; More Details :" + visitTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", visitTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", visitTestData.taskNotes),"Could not enter the test data for Task Notes; More Details :" + visitTestData.toString());
			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + visitTestData.toString());

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Visit not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+ "; More Details :" + visitTestData.toString());
			}

			click(selenium, "visits");
			waitForPageLoad(selenium);

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Date currentDate1 = new Date(); 
			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + visitTestData.toString());
			String recordID = "Visit("+uniqueID+")";

			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,  visitTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, visitTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + visitTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + visitTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + visitTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(selenium != null){
				if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
					click(selenium, "errorCloseButton");

					waitForPageLoad(selenium);	
				}
				if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
					click(selenium, "headerClinicalMenu");

			}
		}
	}

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 10){
				break;
			}

			counter++;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value, AuditLib visitTestData){
		if(value.trim().equals("") || value.trim().equals("")){ return true;}
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("visitdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(visitTestData.visitDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				Assert.fail("Visit Date is not Logged in the audit section  \nExpected Date : " + expectedStartdate+" \nActual   Date : " + actualStartdate);
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("location")){
			isValueChecked = true;

			if(value.trim().equalsIgnoreCase(visitTestData.providerLocation)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"location,";
				return false;
			}

		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("objectivenote")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(visitTestData.patientObjective)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"objectivenote,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("plannote")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(visitTestData.patientPlan)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"plannote,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("subjectivenote")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(visitTestData.patientSubjective)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"subjectivenote,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("assessmentnote")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(visitTestData.patientAssessment)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"assessmentnote,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lastupdated")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date currDate = new Date();
			currDate.setHours(0);
			currDate.setMinutes(0);
			currDate.setSeconds(0);
			if(actualStartdate.toString().trim().toLowerCase(new java.util.Locale("en", "US")).equals(currDate.toString().trim().toLowerCase(new java.util.Locale("en", "US"))) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lastupdated,";
				return false;
			}
		}else{
			return true;
		}
	}
}

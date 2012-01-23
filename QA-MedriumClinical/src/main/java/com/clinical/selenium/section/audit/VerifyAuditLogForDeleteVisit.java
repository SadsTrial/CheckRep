package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForDeleteVisit extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Audit Log for  Delete Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void veriyAuditLogForDeleteVisit(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib visitTestData = new AuditLib();
		visitTestData.workSheetName = "DeleteVisit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchAuditTestData();
		veriyAuditLogForDeleteVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: veriyAuditLogForDeleteVisit
	 * @Description : Function to veriy Audit log For Delete Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 18, 2010
	 */
	@SuppressWarnings("deprecation")
	public void veriyAuditLogForDeleteVisit(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib visitTestData){

		Selenium selenium = null;
		int counter = 1;
		int recordCounter = 0;
		boolean isVisitExisit = false;
		boolean isVisitDeleted = false;
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
			//  Step-2: Check for Existing Records and Delete                     //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);

			Date currentDate = new Date();
			String idOfTheRecord = null;
			if (isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){
				counter =1;
				while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isVisitExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						click(selenium, idOfTheRecord);
						waitForPageLoad(selenium);
						visitTestData.providerName = getSelectedValue(selenium, "providersInput");
						visitTestData.providerLocation = getSelectedValue(selenium, "locationsInput");
						visitTestData.patientSubjective = getValue(selenium, "subjectiveInput");
						visitTestData.patientObjective = getValue(selenium, "objectiveInput");
						visitTestData.patientAssessment = getValue(selenium, "assessmentInput");
						visitTestData.patientPlan = getValue(selenium, "planInput");
						visitTestData.taskName = getSelectedValue(selenium, "workStatusInput");
						visitTestData.sendTaskTo = getSelectedValue(selenium, "taskUsersInput");
						visitTestData.taskNotes = getValue(selenium, "taskNotesInput");

						currentDate.setSeconds(currentDate.getSeconds()-10);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().contains("Are you sure you want to delete this visit")){
								isVisitDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Visit not Deleted successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + visitTestData.toString());
						}
						isVisitDeleted = true;
						counter = counter -1;
						click(selenium, "visits");
						waitForPageLoad(selenium);
					}
					counter++;
				}
			}

			//---------------------------------------------------------------------------------------//
			//  Step-3: function call to create Visit if visit is not available                     //
			//--------------------------------------------------------------------------------------//

			if ((!isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")  && !isVisitDeleted) || !isVisitExisit){
				createVisit(selenium, visitTestData);

			}
			if (isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){
				counter =1;
				while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]")){
					if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isVisitExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						click(selenium, idOfTheRecord );
						waitForPageLoad(selenium);
						visitTestData.providerName = getSelectedValue(selenium, "providersInput");
						visitTestData.providerLocation = getSelectedValue(selenium, "locationsInput");
						visitTestData.patientSubjective = getValue(selenium, "subjectiveInput");
						visitTestData.patientObjective = getValue(selenium, "objectiveInput");
						visitTestData.patientAssessment = getValue(selenium, "assessmentInput");
						visitTestData.patientPlan = getValue(selenium, "planInput");
						visitTestData.taskName = getSelectedValue(selenium, "workStatusInput");
						visitTestData.sendTaskTo = getSelectedValue(selenium, "taskUsersInput");
						visitTestData.taskNotes = getValue(selenium, "taskNotesInput");
						currentDate = new Date();
						currentDate.setSeconds(currentDate.getSeconds()-10);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");

						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().contains("Are you sure you want to delete this visit")){
								isVisitDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);

						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Visit not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + visitTestData.toString());
						}
						isVisitDeleted = true;
						counter = counter -1;

					}
					counter++;
				}
			}

			Date currentDate1 = new Date();
			
			//-----------------------------------------------------------------------------//
			//  Step-4:  Verify the Deleted details are logged properly  in the Audit page //
			//-----------------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			counter = 1;
			int counter1 =0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String uniqueID = idOfTheRecord.split("visit")[1];
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Deleted Record; more Details :-" + visitTestData.toString());
			String recordID = "Visit("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Delete", recordID, visitTestData).split("\n");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" Old value was ")){
					String ColName =auditValue[counter1].split(" was deleted. ")[0];
					String value = 	auditValue[counter1].split(" Old value was ")[1];
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
		if(value.trim().equals("") || value.trim().equals("")){
			return true;
		}
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("visitdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(visitTestData.visitDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"visitdate,";
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
		}else{
			return true;
		}
	}
}

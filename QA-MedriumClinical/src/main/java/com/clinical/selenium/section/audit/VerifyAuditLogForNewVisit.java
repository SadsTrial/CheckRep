package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewVisit extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verifying audit Losgs for New Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewVisit(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib visitTestData = new AuditLib();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchAuditTestData();
		verifyAuditLogForNewVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForNewVisit
	 * @Description : Function to verify Audit Log For New Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: July 08, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewVisit(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib visitTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;

		//--------------------------------------------------------------------//
		//  Step-1: Login to the application and search for the given patient //
		//--------------------------------------------------------------------//

		try{	
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Visit and enter details                    //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientVisitListMoreLink") && selenium.isVisible("patientVisitListMoreLink") ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			Collection<String> firstList = getDataBaseIDs(selenium, "visit"); 	
			createVisit(selenium, visitTestData);
			Date currentDate1 = new Date();
			while(selenium.isElementPresent("patientVisitListMoreLink") && selenium.isVisible("patientVisitListMoreLink") ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}
			waitForPageLoad(selenium);
			Collection<String> secondList = getDataBaseIDs(selenium, "visit");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ visitTestData.toString());
			}
			
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			int counter1 =0;
			

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String uniqueID = idOfTheNewlyAddedRecord.split("visit")[1];
			String recordID = "Visit("+uniqueID+")";
			String tmpValue = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,  visitTestData);
			String auditValue[] = tmpValue.split("Changed ");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" from ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, visitTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + visitTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + visitTestData.toString());
		}catch (RuntimeException e) {
			e.printStackTrace();
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

			}		}
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

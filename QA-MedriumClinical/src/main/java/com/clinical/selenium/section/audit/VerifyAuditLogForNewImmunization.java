package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewImmunization extends AbstractAuditTest {

	boolean isValueChecked = false;
	String immunizationManuacturerCode = null;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verifying audit Logs for New Immunization")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForNewImmunization(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib immunizationTestData = new AuditLib();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "TC_IMM_001";
		immunizationTestData.fetchAuditTestData();
		verifyAuditLogsForNewImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForNewImmunization
	 * @Description : Function to verifying AuditLogs For New Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 12, 2010
	 */	
	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForNewImmunization(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib immunizationTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + immunizationTestData.toString());
			loginFromPublicSite(selenium, immunizationTestData.userAccount, immunizationTestData.userName, immunizationTestData.userPassword);
			searchPatient(selenium,immunizationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Immunization and enter details             //
			//--------------------------------------------------------------------//

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			click(selenium,"immunizations");
			waitForPageLoad(selenium);

			while(isElementPresent(selenium, "patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
				click(selenium, "patientImmunizationListMoreLink");
				waitForPageLoad(selenium);
			}

			Collection<String> firstList = getDataBaseIDs(selenium, "immunization"); 	
			createImmunization(selenium, immunizationTestData);
			Date currentDate1 = new Date(); 
			while(isElementPresent(selenium, "patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
				click(selenium, "patientImmunizationListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "immunization");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ immunizationTestData.toString());
			}
			
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);						
			immunizationManuacturerCode = getText(selenium,"manufacturerCode");
			
			int counter1= 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String uniqueID = idOfTheNewlyAddedRecord.split("immunization")[1];
			String recordID = "Immunization("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID, immunizationTestData).split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains("to")){
					String ColName =auditValue[counter1].split("to")[0];
					String value = 	auditValue[counter1].split("to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, immunizationTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + immunizationTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + immunizationTestData.toString());

		}catch (RuntimeException e) {

			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + immunizationTestData.toString());
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){
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

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName,String value, AuditLib immunizationTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("administrationdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(immunizationTestData.administrationDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"administrationdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.immunityNotes)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("manufacturercode")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationManuacturerCode.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"manufacturercode,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("administeredamount")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.administrationAmount)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"administeredamount,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lotnumber")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.lotInput)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lotnumber,";
				return false;
			}
		}else{
			return true;
		}
	}
}
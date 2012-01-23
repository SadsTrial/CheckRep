package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewAllergy extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying audit logs for New Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib allergyTestData = new AuditLib();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "TC_ALL_001";		
		allergyTestData.fetchAuditTestData();
		verifyAuditLogForNewAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForNewAllergy
	 * @Description : Function to Verify Audit Log for New Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 11, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib allergyTestData){

		Selenium selenium = null;
		int counter = 1;
		boolean isAuditResultVerified = false;
		String content = null;
		allergyTestData.endDate = allergyTestData.endDate != null ? allergyTestData.endDate.trim() : "";
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Removing existing Allergies with same name                //
			//--------------------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);	
			while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
				content = null;				
				content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "";
				if(content != null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US"))) && ! content.toLowerCase(new java.util.Locale("en", "US")).contains("inactive")){
						String uniqueID = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("allergy")[1];
						click(selenium, "//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						select(selenium, "itemStatusInput", "Inactive");
						click(selenium, "validateButton");
						waitForPageLoad(selenium);
					}						
				}
				counter++;
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Create a New Allergy									  //
			//--------------------------------------------------------------------//
			
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			while(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
				selenium.click("patientAllergyListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "allergy"); 
			createAllergy(selenium,allergyTestData);
			Date currentDate1 = new Date();
			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
				selenium.click("patientAllergyListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "allergy");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ allergyTestData.toString());
			}

			//----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			counter = 1;
			int counter1 =0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			String uniqueID = idOfTheNewlyAddedRecord.split("allergy")[1];
			String recordID = "Allergy("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID, allergyTestData).split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, allergyTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			org.testng.Assert.fail("some error has occured during execution; Detailed data:" + allergyTestData.toString());
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
	public boolean verifyAuditValues(String ColName, String value, AuditLib allergyTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(allergyTestData.startDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.before(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"startdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("enddate")){
			isValueChecked = true;
			Date actualEnddate = new Date(value.replace("-","/"));
			Date expectedEnddate = new Date(allergyTestData.endDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(allergyTestData.allergyNotes)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(allergyTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else{
			return true;
		}
	}
}
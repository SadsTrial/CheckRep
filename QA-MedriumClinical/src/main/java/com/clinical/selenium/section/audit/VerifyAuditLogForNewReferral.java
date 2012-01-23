package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewReferral extends AbstractAuditTest {

	String state = null;
	String NPI = null;
	String name = null;
	String speciality = null;
	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying audit Logs for New Referral")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewReferral(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib referralTestData = new AuditLib();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "TC_REF_001";
		referralTestData.fetchAuditTestData();
		verifyAuditLogForNewReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForNewReferral
	 * @Description : Function to verify Audit Log For NewReferral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 16, 2010
	 */	
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewReferral(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib referralTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + referralTestData.toString());
			loginFromPublicSite(selenium, referralTestData.userAccount, referralTestData.userName, referralTestData.userPassword);
			searchPatient(selenium,referralTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Referral and enter details                 //
			//--------------------------------------------------------------------//

			click(selenium,"referrals");
			waitForPageLoad(selenium);
			Collection<String> firstList = getDataBaseIDs(selenium, "referral"); 	
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			createReferral(selenium, referralTestData);
			Date currentDate1 = new Date();
			waitForPageLoad(selenium);
			Collection<String> secondList = getDataBaseIDs(selenium, "referral");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ referralTestData.toString());
			}
			
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			while(!(isElementPresent(selenium, idOfTheNewlyAddedRecord)&& isElementPresent(selenium, "patientReferralListMoreLink"))){
				click(selenium, "patientReferralListMoreLink");
				waitForPageLoad(selenium);
				waitForElement(selenium, "patientReferralListMoreLink", 10000);
			}

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Assert.assertTrue(click(selenium, idOfTheNewlyAddedRecord), "Record of the Referral not found; More details :" + referralTestData.toString());
			waitForPageLoad(selenium);
			
			state =getText(selenium, "state"); 
			NPI = getText(selenium, "npi");
			name = getText(selenium, "name");
			speciality = getText(selenium, "specialty");
			String uniqueID = idOfTheNewlyAddedRecord.split("referral")[1];
			
			String recordID = "Referral("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID,  referralTestData).split("Set");
			int counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, referralTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + referralTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + referralTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + referralTestData.toString());
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

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value, AuditLib referralTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("referraldate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(referralTestData.startDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"referraldate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(referralTestData.providerNotes)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("name")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(name.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"name,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("specialty")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(speciality.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"specialty,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("state")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(state.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"state,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("npi")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(NPI.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"npi,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(referralTestData.userName)){
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

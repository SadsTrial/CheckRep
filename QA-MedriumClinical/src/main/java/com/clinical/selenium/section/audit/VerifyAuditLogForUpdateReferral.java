package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateReferral extends AbstractAuditTest {
	boolean isValueChecked = false;
	String state = null;
	String NPI = null;
	String name = null;
	String speciality = null;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Verifying Audit Log for  Update Referral")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForUpdateReferral(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib referralTestData = new AuditLib();
		referralTestData.workSheetName = "UpdateReferral";
		referralTestData.testCaseId = "TC_URE_001";
		referralTestData.fetchAuditTestData();
		verifyAuditLogForUpdateReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForUpdateReferral
	 * @Description : Function to verify audit Logs for update Referral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 27, 2010
	 */	
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForUpdateReferral(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib referralTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		String referralCount = null;
		String uniqueID = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + referralTestData.toString());
			loginFromPublicSite(selenium, referralTestData.userAccount, referralTestData.userName, referralTestData.userPassword);
			searchPatient(selenium,referralTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Navigating to Referral Page and Capturing Referral Count //
			//--------------------------------------------------------------------//

			click(selenium, "referrals");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture existing Referrals Count; More Details :" + referralTestData.toString());
			referralCount = getListCount(selenium.getText("Referrals"));
			if(referralCount != null && !referralCount.trim().equals("")){
				if (Integer.parseInt(referralCount)== 0){
					referralTestData = null;
					referralTestData = new AuditLib();
					referralTestData.workSheetName = "Referral";
					referralTestData.testCaseId = "TC_REF_001";		
					referralTestData.fetchAuditTestData();
					createReferral(selenium, referralTestData);
					referralCount = "1";

				}
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Opening an existing Referral and updating it             //
			//--------------------------------------------------------------------//

			referralTestData = null;
			referralTestData = new AuditLib();
			referralTestData.workSheetName = "UpdateReferral";
			referralTestData.testCaseId = "TC_URE_001";		
			referralTestData.fetchAuditTestData();
			String idOfTheRecord = "";
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientReferralList']/table/tbody[1]/tr[1]/td[1]"),"Could Not open the existing Referrel to Update; More Details :" + referralTestData.toString());		
			idOfTheRecord = selenium.getAttribute("//div[@id='patientReferralList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
			uniqueID = idOfTheRecord.split("referral")[1];
			click(selenium, idOfTheRecord);				
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);
			if(getValue(selenium, "referraldateInput").trim().equalsIgnoreCase(referralTestData.startDate.trim())){
				referralTestData.workSheetName = "UpdateReferral";
				referralTestData.testCaseId = "TC_URE_002";		
				referralTestData.fetchAuditTestData();
			}

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"providersBoxBox", referralTestData.providerName),"Could not enter Provider Name; More Details :" + referralTestData.toString());

			state =getText(selenium, "//ol[@class='referralFormList']/li[4]/div[2]/div"); 
			NPI = getText(selenium, "//ol[@class='referralFormList']/li[2]/div[2]/div");
			name = getText(selenium, "//ol[@class='referralFormList']/li[3]/div[2]/div");
			speciality = getText(selenium, "//ol[@class='referralFormList']/li[5]/div[2]/div");

			Assert.assertTrue(enterDate(selenium,"referraldateInput", referralTestData.startDate),"Could not enter Date; More Details :" + referralTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",referralTestData.providerNotes),"Could not enter Notes; More Details :" + referralTestData.toString());

			if(!isElementPresent(selenium,"workStatusInput")){
				Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  ; More Details :" + referralTestData.toString());
			}
			Assert.assertTrue(select(selenium,"workStatusInput", referralTestData.taskName),"Could not select Work Status; More Details :" + referralTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(select(selenium,"taskUsersInput", referralTestData.sendTaskTo),"Could not select Send Task To; More Details :" + referralTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", referralTestData.taskNotes),"Could not enter Task Notes; More Details :" + referralTestData.toString());

			Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details :" + referralTestData.toString());			
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Referral not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + referralTestData.toString());
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + referralTestData.toString());
			Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture Referral Count after updating a Referral; More Details :" + referralTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("Referrals"))) , Integer.parseInt(referralCount),"Could not capture Referral Count after updating a Referral; More Details :" + referralTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Date currentDate1 = new Date();

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//


			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + referralTestData.toString());
			String recordID = "Referral("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,  referralTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
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
			e.printStackTrace();
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

	public  void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			counter++;
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
package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewSocialHistory extends AbstractAuditTest {

	boolean isValueChecked = false;
	boolean isAuditResultVerifiedForSmoking = false;
	boolean isAuditResultVerifiedForPreganacy = false;
	boolean isAuditResultVerifiedForLactating = false;

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Audit Log For New Social Historye")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewSocialHistory(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib socialTestData = new AuditLib();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "TC_SH_002";
		socialTestData.fetchAuditTestData();
		verifyAuditLogForNewSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForNewSocialHistory
	 * @Description : Function to verify Audit Log For New Social History
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 16, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewSocialHistory(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib socialTestData){

		Selenium selenium = null;
		String altDate = "January 20, 2011";
		String patientGender = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + socialTestData.toString());
			loginFromPublicSite(selenium, socialTestData.userAccount, socialTestData.userName, socialTestData.userPassword);
			searchPatient(selenium,socialTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Social History and enter details           //
			//--------------------------------------------------------------------//

			Thread.sleep(3000);
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientinformation']/div/div/div[2]"),"Patient Personal details are not displayed; More Details :" + socialTestData.toString());
			waitForElement(selenium,"//div[@id='patientinformation']/div/div/div[2]",20000);
			if(selenium.getText( "//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("female")){
				patientGender = "Female";	
			}else if(selenium.getText("//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("male")){
				patientGender = "Male";
			}else{
				Assert.fail("Patient Gender is not displayed; More Details :" + socialTestData.toString());
			}
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			click(selenium, "socialHistory");
			waitForPageLoad(selenium);
			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details :" + socialTestData.toString());
			waitForPageLoad(selenium);

			if(patientGender.equalsIgnoreCase("male")){
				if(getValue(selenium, "smokingStartDateInput").trim().equalsIgnoreCase(socialTestData.smokingStartDate.trim())){
					socialTestData.smokingStartDate = altDate;
				}
			}

			if(patientGender.equalsIgnoreCase("female")){
				if(getValue(selenium, "smokingStartDateInput").trim().equalsIgnoreCase(socialTestData.smokingStartDate.trim())){
					socialTestData.smokingStartDate = altDate;
				}

				if(getValue(selenium, "lactatingStartDateInput").trim().equalsIgnoreCase(socialTestData.lactatingStartDate.trim())){
					socialTestData.lactatingStartDate = altDate;
				}

				if(getValue(selenium, "pregnancyStartDateInput").trim().equalsIgnoreCase(socialTestData.pregnancyStartDate.trim())){
					socialTestData.pregnancyStartDate = altDate;
				}
			}

			Assert.assertTrue(select(selenium,"smokingTypeCodeInput", socialTestData.smokingType),"Could not select Smoking Type; More Details :" + socialTestData.toString());
			Assert.assertTrue(enterDate(selenium,"smokingStartDateInput",socialTestData.smokingStartDate),"Could not enter Smoking Start Date; More Details :" + socialTestData.toString());
			Assert.assertTrue(type(selenium,"smokingNotesInput", socialTestData.smokingNote),"Could not enter Smoking Notes; More Details :" + socialTestData.toString());

			if(patientGender.equalsIgnoreCase("Female")){
				if(socialTestData.pregnancyType.equalsIgnoreCase("yes")){
					Assert.assertTrue(isElementPresent(selenium, "//span[@id='yesPregnancyTypeCodeInput']/input"),"Could not select pregnancy Type; More Details :" + socialTestData.toString());
					selenium.check("//span[@id='yesPregnancyTypeCodeInput']/input");
				}else{
					Assert.assertTrue(isElementPresent(selenium, "//span[@id='noPregnancyTypeCodeInput']/input"),"Could not select pregnancy Type; More Details :" + socialTestData.toString());
					selenium.check("//span[@id='noPregnancyTypeCodeInput']/input");
				}
			}else{
				if(selenium.isElementPresent("pregnancyTypeCodeInput")){
					Assert.assertFalse(selenium.isVisible("pregnancyTypeCodeInput"),"Pregnancy Type is displayed for Male Patient; More Details :" + socialTestData.toString());
				}
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(enterDate(selenium,"pregnancyStartDateInput", socialTestData.pregnancyStartDate),"Could not enter Pregnancy Start Date; More Details :" + socialTestData.toString());

			}else{
				Assert.assertFalse(selenium.isVisible("pregnancyStartDateInput"),"Pregnancy Start Date is displayed for Male Patient; More Details :" + socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(type(selenium,"pregnancyNotesInput", socialTestData.pregnancyNote),"Could not enter Pregnancy Notes; More Details :" + socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("pregnancyNotesInput"),"Pregnancy Notes is displayed for Male Patient; More Details :" + socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){
				if(socialTestData.lactatingType.trim().equalsIgnoreCase("Yes")){
					Assert.assertTrue(isElementPresent(selenium,"//span[@id='yesLactatingTypeCodeInput']/input"),"Could not select Lactating Type; More Details :" + socialTestData.toString());
					selenium.check("//span[@id='yesLactatingTypeCodeInput']/input");
				}else{
					Assert.assertTrue(isElementPresent(selenium,"//span[@id='noLactatingTypeCodeInput']/input"),"Could not select Lactating Type; More Details :" + socialTestData.toString());
					selenium.check("//span[@id='noLactatingTypeCodeInput']/input");
				}
			}else{
				if(selenium.isElementPresent("//span[@id='yesLactatingTypeCodeInput']/input")){
					Assert.assertFalse(selenium.isVisible("//span[@id='yesLactatingTypeCodeInput']/input"),"Lactating Type is displayed for Male Patient; More Details :" + socialTestData.toString());
				}
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(enterDate(selenium,"lactatingStartDateInput", socialTestData.lactatingStartDate),"Could not enter Lactating Start Date; More Details :" + socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("lactatingStartDateInput"),"Lactating Start Date is displayed for Male Patient; More Details :" + socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(type(selenium,"lactatingNotesInput", socialTestData.lactatingNote),"Could not enter Lactating Notes; More Details :" + socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("lactatingNotesInput"),"Lactating Notes is displayed for Male Patient; More Details :" + socialTestData.toString());
			}

			Assert.assertTrue(click(selenium,"validateButton"),"Clicked the Save Button; More Details :" + socialTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Social History not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + socialTestData.toString());
			}
			Date currentDate1 = new Date(); 

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			int counter1 =0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String[] searchResults = searchRecordAlternative(selenium, currentDate, currentDate1, "Update", "SocialHistory",  socialTestData).split("Set");
			Assert.assertTrue(searchResults.length != 0,"No records displyed; Search Record failed; More Details : "+ socialTestData.toString());
			String results = selenium.getText("//descendant::div[starts-with(@id,'detail-Add-SocialHistory')][1]")+"Set"+ selenium.getText("//descendant::div[starts-with(@id,'detail-Add-SocialHistory')][2]")+"Set"+selenium.getText("//descendant::div[starts-with(@id,'detail-Add-SocialHistory')][3]");
			String auditValue[] = results.split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isValueChecked = verifyAuditValues(ColName, value, socialTestData);
					if(!isValueChecked){
						break;
					}
				}
				counter1 ++;
			}
			
			 Assert.assertTrue(isValueChecked && isAuditResultVerifiedForSmoking,"Audit Log is not Reflected for Smoking Entires and Verification Failed; More Details :" + socialTestData.toString());
			 Assert.assertTrue(isValueChecked && isAuditResultVerifiedForPreganacy,"Audit Log is not Reflected for Pregnancy Entires and Verification Failed; More Details :" + socialTestData.toString());
			 Assert.assertTrue(isValueChecked && isAuditResultVerifiedForLactating,"Audit Log is Not Reflected for Lactating Entires and Verification Failed; More Details :" + socialTestData.toString());
		}
		catch (RuntimeException e) {

			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + socialTestData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {

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
			if(counter == 5){
				break;
			}
			counter++;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value,AuditLib socialTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(socialTestData.pregnancyStartDate);
			Date expectedStartdate1 = new Date(socialTestData.smokingStartDate);
			Date expectedStartdate2 = new Date(socialTestData.lactatingStartDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				isAuditResultVerifiedForPreganacy = true;
				return true;
			}else if(actualStartdate.equals(expectedStartdate1) || actualStartdate.equals(currDate) ){
				isAuditResultVerifiedForSmoking = true;
				return true;
			}else if(actualStartdate.equals(expectedStartdate2) || actualStartdate.equals(currDate) ){
				isAuditResultVerifiedForLactating  = true;
				return true;
			}else{
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			if(value.trim().equalsIgnoreCase(socialTestData.pregnancyNote)){
				isAuditResultVerifiedForPreganacy = true;
				return true;
			}else if(value.trim().equalsIgnoreCase(socialTestData.smokingNote)){
				isAuditResultVerifiedForSmoking = true;
				return true;
			}else if(value.trim().equalsIgnoreCase(socialTestData.lactatingNote)){
				isAuditResultVerifiedForLactating  = true;
				return true;
			}else{
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(socialTestData.userName)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}

	}
}

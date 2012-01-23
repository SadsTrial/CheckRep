package com.clinical.selenium.section.charts.socialHistory;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForSocialHistory extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Save button and Data for Social History ")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedSocialHistory(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "VerifySaveButtonSocialHistory";
		socialTestData.testCaseId = "TC_SH_001";
		socialTestData.fetchChartsTestData();
		verifySaveButtonForSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	/**
	 * @Function 	: createNewSocialHistory
	 * @Description : Function to create a New SocialHistory
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 26, 2011
	 */
	public void verifySaveButtonForSocialHistory(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib socialTestData){

		Selenium selenium = null;
		String patientGender = null;
		String altDate = "January 20, 2011";

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

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientinformation']/div/div/div[2]"),"Patient Personal details are not displayed; More Details" +socialTestData.toString());
			waitForElement(selenium,"//div[@id='patientinformation']/div/div/div[2]",20000);
			if(selenium.getText( "//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("female")){
				patientGender = "Female";	
			}else if(selenium.getText("//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("male")){
				patientGender = "Male";
			}else{
				Assert.fail("Patient Gender is not displayed; More Details" +socialTestData.toString());
			}

			click(selenium,"socialHistory");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details" +socialTestData.toString());
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
				if(getValue(selenium, "lactatingStartDateInput").trim().equalsIgnoreCase(socialTestData.smokingStartDate.trim())){
					socialTestData.lactatingStartDate = altDate;
				}
				if(getValue(selenium, "pregnancyStartDateInput").trim().equalsIgnoreCase(socialTestData.smokingStartDate.trim())){
					socialTestData.pregnancyStartDate = altDate;
				}
			}

			Assert.assertTrue(select(selenium,"smokingTypeCodeInput", socialTestData.smokingType),"Could not select Smoking Type; More Details" +socialTestData.toString());
			Assert.assertTrue(enterDate(selenium,"smokingStartDateInput",socialTestData.smokingStartDate),"Could not enter Smoking Start Date; More Details" +socialTestData.toString());
			Assert.assertTrue(type(selenium,"smokingNotesInput", socialTestData.smokingNote),"Could not enter Smoking Notes; More Details" +socialTestData.toString());

			if(patientGender.equalsIgnoreCase("Female")){
				if(socialTestData.pregnancyType.equalsIgnoreCase("yes")){
					Assert.assertTrue(isElementPresent(selenium, "//span[@id='yesPregnancyTypeCodeInput']/input"),"Could not select pregnancy Type; More Details" +socialTestData.toString());
					selenium.check("//span[@id='yesPregnancyTypeCodeInput']/input");
				}else{
					Assert.assertTrue(isElementPresent(selenium, "//span[@id='noPregnancyTypeCodeInput']/input"),"Could not select pregnancy Type; More Details" +socialTestData.toString());
					selenium.check("//span[@id='noPregnancyTypeCodeInput']/input");
				}
			}else{
				if(selenium.isElementPresent("pregnancyTypeCodeInput")){
					Assert.assertFalse(selenium.isVisible("pregnancyTypeCodeInput"),"Pregnancy Type is displayed for Male Patient; More Details" +socialTestData.toString());
				}
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(enterDate(selenium,"pregnancyStartDateInput", socialTestData.pregnancyStartDate),"Could not enter Pregnancy Start Date; More Details" +socialTestData.toString());

			}else{
				Assert.assertFalse(selenium.isVisible("pregnancyStartDateInput"),"Pregnancy Start Date is displayed for Male Patient; More Details" +socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(type(selenium,"pregnancyNotesInput", socialTestData.pregnancyNote),"Could not enter Pregnancy Notes; More Details" +socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("pregnancyNotesInput"),"Pregnancy Notes is displayed for Male Patient; More Details" +socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){

				if(socialTestData.lactatingType.trim().equalsIgnoreCase("Yes")){
					Assert.assertTrue(isElementPresent(selenium,"//span[@id='yesLactatingTypeCodeInput']/input"),"Could not select Lactating Type; More Details" +socialTestData.toString());
					selenium.check("//span[@id='yesLactatingTypeCodeInput']/input");
				}else{
					Assert.assertTrue(isElementPresent(selenium,"//span[@id='noLactatingTypeCodeInput']/input"),"Could not select Lactating Type");
					selenium.check("//span[@id='noLactatingTypeCodeInput']/input");
				}
			}else{
				if(selenium.isElementPresent("//span[@id='yesLactatingTypeCodeInput']/input")){
					Assert.assertFalse(selenium.isVisible("//span[@id='yesLactatingTypeCodeInput']/input"),"Lactating Type is displayed for Male Patient; More Details" +socialTestData.toString());
				}
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(enterDate(selenium,"lactatingStartDateInput", socialTestData.lactatingStartDate),"Could not enter Lactating Start Date; More Details" +socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("lactatingStartDateInput"),"Lactating Start Date is displayed for Male Patient; More Details" +socialTestData.toString());
			}

			if(patientGender.equalsIgnoreCase("Female")){
				Assert.assertTrue(type(selenium,"lactatingNotesInput", socialTestData.lactatingNote),"Could not enter Lactating Notes; More Details" +socialTestData.toString());
			}else{
				Assert.assertFalse(selenium.isVisible("lactatingNotesInput"),"Lactating Notes is displayed for Male Patient; More Details" +socialTestData.toString());
			}

			//-----------------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Social History section  //
			//-----------------------------------------------------------------------------------//
		
			click(selenium,"socialHistory");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("socialHistoryAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Social History Add(+)  button is OFF and not backgrounded by Orange; More Details" +socialTestData.toString());  
			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details" +socialTestData.toString());
			waitForPageLoad(selenium);
			
			verifyStoredValues(selenium, socialTestData, patientGender);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +socialTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + socialTestData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				if(selenium != null){
					if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
						click(selenium, "errorCloseButton");
						waitForPageLoad(selenium);	
					}
					if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
					click(selenium, "headerClinicalMenu");
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	public void verifyStoredValues(Selenium selenium, ChartsLib socialTestData, String gender){

		if(!(socialTestData.smokingType == null || socialTestData.smokingType.equals(""))){
			Assert.assertEquals(getSelectedValue(selenium,"smokingTypeCodeInput"),socialTestData.smokingType,"Un saved Smoking Type Code Value is not available; It is modified; More Details" +socialTestData.toString());

		}

		Assert.assertEquals(getValue(selenium, "smokingStartDateInput").trim(),socialTestData.smokingStartDate.trim(),"Un saved Smoking Start Date Value is not available; It is modified; More Details" +socialTestData.toString());


		Assert.assertEquals(getValue(selenium,"smokingNotesInput").trim(),socialTestData.smokingNote.trim(),"Un saved Smoking Notes Value is not available; It is modified; More Details" +socialTestData.toString());

		if(gender.equalsIgnoreCase("Female")){
			Assert.assertEquals(getValue(selenium, "pregnancyStartDateInput").trim(),socialTestData.pregnancyStartDate.trim(),"Un saved Pregnancy Start Date Value is not available; It is modified; More Details" +socialTestData.toString());

		}else{
			Assert.assertFalse(selenium.isElementPresent("pregnancyStartDateInput") && selenium.isVisible("pregnancyStartDateInput"),"Pregnancy Start Date is displayed for Male Patient; More Details" +socialTestData.toString());
		}

		if(gender.equalsIgnoreCase("Female")){	
			Assert.assertEquals(getValue(selenium,"pregnancyNotesInput").trim(),socialTestData.pregnancyNote.trim(),"Un saved Pregnancy Notes Input Value is not available; It is modified; More Details" +socialTestData.toString());
		}else{
			Assert.assertFalse(selenium.isElementPresent("pregnancyNotesInput")&&selenium.isVisible("pregnancyNotesInput"),"Pregnancy Notes is displayed for Male Patient; More Details" +socialTestData.toString());
		}


		if(gender.equalsIgnoreCase("Female")){
			Assert.assertEquals(getValue(selenium, "lactatingStartDateInput").trim(),socialTestData.lactatingStartDate.trim(),"Un saved Lactating Start Date Input Value is not available; It is modified; More Details" +socialTestData.toString());
		}else{
			Assert.assertFalse(selenium.isElementPresent("lactatingStartDateInput") && selenium.isVisible("lactatingStartDateInput"),"Lactating Start Date is displayed for Male Patient; More Details" +socialTestData.toString());
		}

		if(gender.equalsIgnoreCase("Female")){
			Assert.assertEquals(getValue(selenium,"lactatingNotesInput"),socialTestData.lactatingNote.trim(),"Un saved Lactating Notes Input Value is not available; It is modified; More Details" +socialTestData.toString());
		}else{
			Assert.assertFalse(selenium.isElementPresent("lactatingNotesInput") && selenium.isVisible("lactatingNotesInput"),"Lactating Notes is displayed for Male Patient; More Details" +socialTestData.toString());
		}
	}


}

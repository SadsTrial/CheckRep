package com.clinical.selenium.section.charts.socialHistory;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewSocialHistory extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New SocialHistory for Male")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewSocialHistory001(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "TC_SH_001";
		socialTestData.fetchChartsTestData();
		createNewSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New SocialHistory for Female")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewSocialHistory002(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "TC_SH_002";
		socialTestData.fetchChartsTestData();
		createNewSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	/**
	 * @Function 	: createNewSocialHistory
	 * @Description : Function to create a New SocialHistory
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 15, 2010
	 */
	public void createNewSocialHistory(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib socialTestData){
		
		Selenium selenium = null;
		String patientGender = null;
		int counter = 1;
		boolean isRecordFoundInSocialHistories = false;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity = false;
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
			//  Step-2:  Step to Find the Patient Gender                          //
			//--------------------------------------------------------------------//

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientinformation']/div/div/div[2]"),"Patient Personal details are not displayed");
			waitForElement(selenium,"//div[@id='patientinformation']/div/div/div[2]",20000);
			if(selenium.getText( "//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("female")){
				patientGender = "Female";	
			}else if(selenium.getText("//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("male")){
				patientGender = "Male";
			}else{
				Assert.fail("Patient Gender is not displayed");
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Click Add New Social History and enter details           //
			//--------------------------------------------------------------------//

			click(selenium,"socialHistory");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link");
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

			//-----------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Social History section  //
			//-----------------------------------------------------------------------------------//

			click(selenium,"socialHistory");
			waitForPageLoad(selenium);
			
			waitForElement(selenium, "smoking", 120000);
			isRecordFoundInSocialHistories  = verifyStoredValuesInSocialHistory(selenium, socialTestData, patientGender);

			//----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			counter = 1;
			Assert.assertTrue(click(selenium,"socialHistoryTitle"),"Could not find SocialHistory Title Link; More Details :" + socialTestData.toString());
			waitForPageLoad(selenium);

			isRecordFoundInSummary = verifyStoredValuesInSocialHistory(selenium, socialTestData, patientGender);

			//----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in Activity section //
			//----------------------------------------------------------------------------//		

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent("//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				String category = selenium.getText("//div[@id='patActivity']/table/tbody[1]/tr["+ counter +"]/td[2]/div");
				String content = getText(selenium, "//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[3]/div");
				if(category.toLowerCase(new java.util.Locale("en", "US")).trim().contains("social history") && content.toLowerCase(new java.util.Locale("en", "US")).contains("breast-feeding") || content.toLowerCase(new java.util.Locale("en", "US")).contains("pregnant") || content.toLowerCase(new java.util.Locale("en", "US")).contains("smoking") ){
					click(selenium,"//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[3]/div/strong/a");
					waitForPageLoad(selenium);
					if(verifyStoredValuesInSocialHistory(selenium, socialTestData, patientGender)){
						isRecordFoundInActivity= true;
						break;
					}
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}
				counter++;
			}

			Assert.assertTrue(isRecordFoundInSocialHistories,"No matching records found in patient Social History section; Social History Creation Failed; More Details : "+socialTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"No matching records found in Patient Activity section; Social History CreationFailed; More Details : "+socialTestData.toString());
			Assert.assertTrue(isRecordFoundInSummary,"No matching records found in Patient summary section; Social History Creation Failed; More Details : "+socialTestData.toString());

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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			
			counter++;
		}
	}

	public boolean verifyStoredValuesInSocialHistory(Selenium selenium, ChartsLib socialTestData, String gender){

		if(!(socialTestData.smokingType == null || socialTestData.smokingType.equals(""))){
			if(!getText(selenium,"smoking").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.smokingType.trim().toLowerCase(new java.util.Locale("en", "US")))){
				return false;
			}
		}

		if(!getText(selenium,"smoking").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.smokingStartDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"smokingNotesInput").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.smokingNote.toLowerCase(new java.util.Locale("en", "US")).trim())){
			return false;
		}

		if(gender.equalsIgnoreCase("Female")){
			if(!(socialTestData.pregnancyType == null || socialTestData.pregnancyType.equals(""))){
				if(!getText(selenium,"pregnancy").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.pregnancyType.trim().toLowerCase(new java.util.Locale("en", "US")))){
					return false;
				}
			}
		}else{
			if(selenium.isElementPresent("pregnancyTypeCodeInput") && selenium.isVisible("pregnancyTypeCodeInput")){
				Assert.fail("Pregnancy Type is displayed for Male Patient; More Details :" + socialTestData.toString());
			}
		}

		if(gender.equalsIgnoreCase("Female")){
			if(!getText(selenium,"pregnancy").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.pregnancyStartDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
				return false;
			}
		}else{
			if(selenium.isElementPresent("pregnancy") && selenium.isVisible("pregnancy")){
				Assert.fail("Pregnancy Start Date is displayed for Male Patient; More Details :" + socialTestData.toString());
			}
		}

		if(gender.equalsIgnoreCase("Female")){	
			if(!getText(selenium,"pregnancyNotesInput").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.pregnancyNote.toLowerCase(new java.util.Locale("en", "US")).trim())){
				return false;
			}
		}else{
			if(selenium.isElementPresent("pregnancyNotesInput")&&selenium.isVisible("pregnancyNotesInput")){
				Assert.fail("Pregnancy Notes is displayed for Male Patient; More Details :" + socialTestData.toString());
			}
		}

		if(gender.equalsIgnoreCase("Female")){
			if(!(socialTestData.lactatingType == null || socialTestData.lactatingType.equals(""))){

				if(!getText(selenium,"lactating").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.lactatingType.trim().toLowerCase(new java.util.Locale("en", "US")))){
					return false;
				}
			}


		}else{
			if(selenium.isElementPresent("lactating")&& selenium.isVisible("lactating")){
				Assert.fail("Lactating Type is displayed for Male Patient");
			}
		}

		if(gender.equalsIgnoreCase("Female")){
			if(!getText(selenium,"lactating").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.lactatingStartDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
				return false;
			}
		}else{
			if(selenium.isElementPresent("lactating") && selenium.isVisible("lactating")){
				Assert.fail("Lactating Start Date is displayed for Male Patient; More Details :" + socialTestData.toString());
			}
		}

		if(gender.equalsIgnoreCase("Female")){
			if(!getText(selenium,"lactatingNotesInput").trim().toLowerCase(new java.util.Locale("en", "US")).contains(socialTestData.lactatingNote.toLowerCase(new java.util.Locale("en", "US")).trim())){
				return false;
			}
		}else{
			if(selenium.isElementPresent("lactatingNotesInput") && selenium.isVisible("lactatingNotesInput")){
				Assert.fail("Lactating Notes is displayed for Male Patient; More Details :" + socialTestData.toString());
			}
		}
		return true;
	}
}

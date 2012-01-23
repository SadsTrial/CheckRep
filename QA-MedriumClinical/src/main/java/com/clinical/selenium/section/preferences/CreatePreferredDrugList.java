package com.clinical.selenium.section.preferences;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.clinical.selenium.genericlibrary.preferences.PreferencesLib;
import com.thoughtworks.selenium.Selenium;

public class CreatePreferredDrugList extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for create a preferred drug list")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyCreatePreferredDrugList(String seleniumHost, int seleniumPort,String browser, String webSite){
		PreferencesLib drugListTestData = new PreferencesLib();
		drugListTestData.workSheetName = "PreferredDrugList";
		drugListTestData.testCaseId = "TC_DRU_001";		
		drugListTestData. fetchPreferencesTestData();
		verifyCreatePreferredDrugList(seleniumHost, seleniumPort, browser, webSite, drugListTestData);
	}
	/**
	 * @Function 	:verifyCreatePreferredDrugList
	 * @Description : Function to Create preferred drug list
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */
	public void verifyCreatePreferredDrugList(String seleniumHost, int seleniumPort,String browser, String webSite, PreferencesLib drugListTestData){

		int counter = 1;
		int preferenceCounter = 1;
		Selenium selenium = null;
		
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + drugListTestData.toString());
			loginFromPublicSite(selenium, drugListTestData.userAccount, drugListTestData.userName, drugListTestData.userPassword);
			searchPatient(selenium,drugListTestData.patientID);
			click(selenium, "preferences");
			waitForPageLoad(selenium);
			click(selenium, "PreferredDrugList");
			waitForPageLoad(selenium);
		
			while(selenium.isElementPresent( "productTargetBox"+counter)){ 
				if(getText(selenium, "productTargetBox"+counter).contains(drugListTestData.medicationName)){
					click(selenium,"//div[@Class='formSection']/div[2]/div/div["+counter+"]/table/tbody/tr/td[3]/a");
								
					waitForPageLoad(selenium);
				}
				counter++; 
			}
			Assert.assertTrue(isElementPresent(selenium, "link=Add New"),"Could not Click the Add New Button; More Details" +drugListTestData.toString());
			click(selenium, "link=Add New");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent( "productTargetBox"+preferenceCounter)){ 
				if(getText(selenium, "productTargetBox"+preferenceCounter).equals("") ||  getText(selenium, "productTargetBox"+preferenceCounter).equals("")){
					break;
				}
				preferenceCounter++; 
			}
			waitForPageLoad(selenium);
			if(!waitForElement(selenium, "productTargetBox"+preferenceCounter+"Box", 7000)){
				preferenceCounter++;
			}
			waitForElement(selenium, "productTargetBox"+preferenceCounter+"Box", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"productTargetBox"+preferenceCounter+"Box",drugListTestData.medicationName ),"Could not enter Medication Name; More Details" +drugListTestData.toString());
			waitForElement(selenium, "productReplacementSuggestBox"+preferenceCounter+"Box", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"productReplacementSuggestBox"+preferenceCounter+"Box", drugListTestData.altMedicationName),"Could not enter Alt Medication Name; More Details" +drugListTestData.toString());
			waitForPageLoad(selenium);
			searchPatient(selenium,drugListTestData.patientID);
			click(selenium,"medications");
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details" +drugListTestData.toString());
			click(selenium,"addMedication");
						
			waitForPageLoad(selenium);
			waitForElement(selenium, "medicationBoxBox", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", drugListTestData.medicationName),"Could not enter Medication Name; More Details" +drugListTestData.toString());
			Assert.assertTrue(checkForAlternativeMedication(selenium, drugListTestData),"Preferred list Verification Failed in Medication Section; More Details" +drugListTestData.toString());
			click(selenium,"cancelButton");
			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" +drugListTestData.toString());
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", drugListTestData.medicationName),"Could not enter Medication Name; More Details" +drugListTestData.toString());
			Assert.assertTrue(checkForAlternativeMedication(selenium, drugListTestData),"Preferred list Verification Failed in Medication Section; More Details" +drugListTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + drugListTestData.toString());
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){				
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

	public boolean checkForAlternativeMedication(Selenium selenium,PreferencesLib drugListTestData){
		waitForElement(selenium, "//div[@class='popupContent']/div", 7000);
		if(isElementPresent(selenium, "//div[@class='popupContent']/div")){
			String preferredValue = getText(selenium, "//div[@class='popupContent']/div");
			if(preferredValue.trim().toLowerCase(new java.util.Locale("en", "US")).contains(drugListTestData.altMedicationName.trim().toLowerCase(new java.util.Locale("en", "US"))) && preferredValue.trim().toLowerCase(new java.util.Locale("en", "US")).contains("prefered alternatives")){
				return true;
			}else{
				return false;	
			}
		}else{
			return false;
		}
	}
}

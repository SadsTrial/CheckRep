package com.clinical.selenium.section.preferences;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.clinical.selenium.genericlibrary.preferences.PreferencesLib;
import com.thoughtworks.selenium.Selenium;

public class DeleteInteractionWarnings extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for delete interaction warning ")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void deleteInteractionWarnings(String seleniumHost, int seleniumPort,String browser, String webSite){
		PreferencesLib drugListTestData = new PreferencesLib();
		drugListTestData.workSheetName = "DeleteWarnings";
		drugListTestData.testCaseId = "TC_IWR_001";		
		drugListTestData. fetchPreferencesTestData();
		deleteInteractionWarnings(seleniumHost, seleniumPort, browser, webSite, drugListTestData);
	}

	/**
	 * @Function 	: deleteInteractionWarnings
	 * @Description : Function to Delete Interaction Warnings
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */

	public void deleteInteractionWarnings(String seleniumHost, int seleniumPort,String browser, String webSite, PreferencesLib interactionTestData){

		int counter = 1;
		boolean isRecordFoundInAll = false;
		boolean isRecordFound = false;
		boolean isDeleted = false;
		boolean isMedicationAvailable  = false;
		Selenium selenium = null;

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + interactionTestData.toString());
			loginFromPublicSite(selenium, interactionTestData.userAccount, interactionTestData.userName, interactionTestData.userPassword);
			searchPatient(selenium,interactionTestData.patientID);
			
			click(selenium,"medications");
			waitForPageLoad(selenium);
			isMedicationAvailable = deactiveMedication(selenium, interactionTestData);
			if(!isMedicationAvailable){
				createMedication(selenium, interactionTestData);
			}
			
			click(selenium, "preferences");
			waitForPageLoad(selenium);
			isDeleted = deleteRecords(selenium,interactionTestData);
			if(!isDeleted){
				Assert.assertTrue(isElementPresent(selenium, "filter"),"Could not Click the Add Filter Button; More Details" +interactionTestData.toString());
				click(selenium, "filter");
							
				waitForPageLoad(selenium);
				Assert.assertTrue(select(selenium,"addApplyToList", interactionTestData.applyTo),"Could not enter the addApplyToList Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(select(selenium,"addProductType", interactionTestData.productType),"Could not enter Alt Medication Name; More Details" +interactionTestData.toString());
				waitForElement(selenium, "productNameBoxBox", 7000);
				Assert.assertTrue(selectValueFromAjaxList(selenium,"productNameBoxBox", interactionTestData.medicationName.toUpperCase(new java.util.Locale("en", "US"))),"Could not enter Product Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(select(selenium,"addSeverity", interactionTestData.severity),"Could not enter Alt Medication Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(click(selenium,"addValidateButton"),"Could not click Save Button; More Details" +interactionTestData.toString());
											
				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Warning  not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
				searchPatient(selenium,interactionTestData.patientID);
				click(selenium,"medications");
				waitForPageLoad(selenium);
				click(selenium,"addMedication");
				waitForPageLoad(selenium);
				waitForElement(selenium, "medicationboxBox", 7000);
				Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", interactionTestData.altMedicationName),"Could not enter Medication Name; More Details" +interactionTestData.toString());
				click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[1]/div/div");
				waitForPageLoad(selenium);
							
				Assert.assertFalse(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning displayed in the Medication(Moderate) section; Filter Interaction Warnings Failed; More Details" +interactionTestData.toString());
				click(selenium,"cancelButton");
				waitForPageLoad(selenium);
							
			}
			click(selenium, "preferences");
						
			waitForPageLoad(selenium);
			if(!isDeleted)
				isDeleted = deleteRecords(selenium,interactionTestData);
			counter = 1;
			while(selenium.isElementPresent( "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div")){ 
				if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div").contains(interactionTestData.medicationName) && getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[1]/div").contains(interactionTestData.productType) ){
					if(interactionTestData.applyTo.equalsIgnoreCase("all")){
						if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[3]/div").toLowerCase(new java.util.Locale("en", "US")).contains(interactionTestData.severity.toLowerCase(new java.util.Locale("en", "US")))){
							isRecordFoundInAll = true;
							break;
						}
					}else{
						if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[4]/div").toLowerCase(new java.util.Locale("en", "US")).contains(interactionTestData.severity.toLowerCase(new java.util.Locale("en", "US")))){
							isRecordFound = true;
							break;
						}	
					}
				}
				counter++;

			}

			Assert.assertTrue(isDeleted,"Unable to delete the Interanction; More Details" +interactionTestData.toString());
			Assert.assertFalse(isRecordFoundInAll,"Interaction Warning is Found in Filter (applies to all);Delete Interaction Failed; More Details" +interactionTestData.toString());
			Assert.assertFalse(isRecordFound,"Interaction Warning is  Found in Filter (applies to You); Delete Interaction Passed; More Details" +interactionTestData.toString());
			searchPatient(selenium,interactionTestData.patientID);
			click(selenium,"medications");
			waitForPageLoad(selenium);
			click(selenium,"addMedication");
			waitForPageLoad(selenium);
			waitForElement(selenium, "medicationBoxBox", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", interactionTestData.altMedicationName),"Could not enter Medication Name; More Details" +interactionTestData.toString());
			click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[1]/div/div");
			waitForPageLoad(selenium);
						
			Assert.assertTrue(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning not displayed in the Medication(Major) section; Delete Filter Interaction Warnings Failed; More Details" +interactionTestData.toString());
			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			click(selenium,"addPrescription");
			waitForPageLoad(selenium);
			
			waitForElement(selenium, "medicationboxBox", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", interactionTestData.altMedicationName),"Could not enter Medication Name; More Details" +interactionTestData.toString());
			click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[1]/div/div");
			waitForPageLoad(selenium);
						
			Assert.assertTrue(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning not displayed in the Prescription(Moderate) section; Delete Filter Interaction Warnings Failed; More Details" +interactionTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + interactionTestData.toString());	
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

	public boolean deactiveMedication(Selenium selenium,PreferencesLib medicationTestData){

		int counter = 1;
		boolean isAvailable = false;
		while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
			String content = null;
			content = selenium.getText("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
			content = content != null ? content.trim() : "" ;
			if(content != null && !content.equals("")){		
				if(content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					isAvailable = true;
					break;
				}
			}
			counter++;
		}
		return isAvailable;
	}

	public boolean deleteRecords(Selenium selenium,PreferencesLib interactionTestData ){
		click(selenium, "FilterInteractionWarnings");
					
		waitForPageLoad(selenium);
		boolean isDeleted = false;
		int counter =1;
		while(selenium.isElementPresent( "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div")){ 
			if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div").contains(interactionTestData.medicationName)){
				click(selenium,"//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[3]/div/div/a[2]");
							
				click(selenium,"//div[4]/div/div/div/div/button[2]");
				waitForPageLoad(selenium);
				isDeleted =  true;
				counter --;

			}
			counter++; 
		}
		return isDeleted;	
	}
}

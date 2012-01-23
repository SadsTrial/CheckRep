package com.clinical.selenium.section.preferences;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.clinical.selenium.genericlibrary.preferences.PreferencesLib;
import com.thoughtworks.selenium.Selenium;

public class EditInteractionWarnings extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for edit the interaction Warnings")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void editInteractionWarnings(String seleniumHost, int seleniumPort,String browser, String webSite){
		PreferencesLib drugListTestData = new PreferencesLib();
		drugListTestData.workSheetName = "EditInteractionWarnings";
		drugListTestData.testCaseId = "TC_IWR_001";		
		drugListTestData. fetchPreferencesTestData();
		editInteractionWarnings(seleniumHost, seleniumPort, browser, webSite, drugListTestData);
	}

	/**
	 * @Function 	: editInteractionWarnings
	 * @Description : Function to Edit the Interaction Warnings and Verify 
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */
	public void editInteractionWarnings(String seleniumHost, int seleniumPort,String browser, String webSite, PreferencesLib interactionTestData){

		int counter = 1;
		boolean isRecordFound = false;
		boolean isRecordFoundToEdit = false;
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

			boolean	isMedicationAvailable = deactiveMedication(selenium, interactionTestData);
			if(!isMedicationAvailable){
				createMedication(selenium, interactionTestData);
			}

			click(selenium, "preferences");
			waitForPageLoad(selenium);
			click(selenium, "FilterInteractionWarnings");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent( "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div")){ 
				if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div").contains(interactionTestData.medicationName)){
					isRecordFoundToEdit = true;
					break;
				}
				counter++; 
			}

			if(!isRecordFoundToEdit ){
				Assert.assertTrue(isElementPresent(selenium, "filter"),"Could not Click the Add Filter Button; More Details" +interactionTestData.toString());
				click(selenium, "filter");
				waitForPageLoad(selenium);
				Assert.assertTrue(select(selenium,"addApplyToList", interactionTestData.applyTo),"Could not enter the addApplyToList Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(select(selenium,"addProductType", interactionTestData.productType),"Could not enter Alt Medication Name; More Details" +interactionTestData.toString());
				waitForElement(selenium, "productNameBoxBox", 7000);
				Assert.assertTrue(selectValueFromAjaxList(selenium,"productNameBoxBox", interactionTestData.medicationName.toUpperCase(new java.util.Locale("en", "US"))),"Could not enter Product Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(select(selenium,"addSeverity", interactionTestData.severity),"Could not enter Alt Medication Name; More Details" +interactionTestData.toString());
				Assert.assertTrue(click(selenium,"addValidateButton"),"Could not click Save Button; More Details" +interactionTestData.toString());
				waitForPageLoad(selenium);
				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
			}

			click(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[4]/div/div/a[1]");
			waitForPageLoad(selenium);

			if(selenium.getSelectedLabel("addSeverity").trim().toLowerCase(new java.util.Locale("en", "US")).equals(interactionTestData.severity.trim().toLowerCase(new java.util.Locale("en", "US")))){
				interactionTestData.severity = interactionTestData.severityToUpdate;
			}
			Assert.assertTrue(select(selenium,"editSeverity", interactionTestData.severity),"Could not enter severity Name; More Details" +interactionTestData.toString());
			Assert.assertTrue(click(selenium,"editValidateButton"),"Could not click Save Button; More Details" +interactionTestData.toString());
			waitForPageLoad(selenium);
										

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
			}

			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
			counter = 1;

			while(selenium.isElementPresent( "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div")){ 
				if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[2]/div").contains(interactionTestData.medicationName) && getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[1]/div").contains(interactionTestData.productType) ){
					if(interactionTestData.applyTo.equalsIgnoreCase("all")){
						if(getText(selenium, "//table[@id='filterPreferenceTable']/tbody[1]/tr["+counter+"]/td[3]/div").toLowerCase(new java.util.Locale("en", "US")).contains(interactionTestData.severity.toLowerCase(new java.util.Locale("en", "US")))){
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
			if(!interactionTestData.applyTo.equalsIgnoreCase("all")){

				Assert.assertTrue(isRecordFound,"Interaction Warning is Not Found in Filter (applies to You); Create Interaction Failed; Detailed data:" + interactionTestData.toString());
			}

			searchPatient(selenium,interactionTestData.patientID);
			click(selenium,"medications");
			waitForPageLoad(selenium);
			click(selenium,"addMedication");
			waitForPageLoad(selenium);
			waitForElement(selenium, "medicationBoxBox", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", interactionTestData.altMedicationName.toUpperCase(new java.util.Locale("en", "US"))),"Could not enter Medication Name; More Details" +interactionTestData.toString());
			click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[1]/div/div");
			waitForPageLoad(selenium);
						
			if(interactionTestData.severity.trim().equalsIgnoreCase("Show no warnings")){
				Assert.assertFalse(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning displayed in the Medication(Moderate) section; Filter Interaction Warnings Failed; More Details" +interactionTestData.toString());
			}else{
				Assert.assertTrue(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning not displayed in the Medication section After Edit; Edit Interaction Failed; More Details" +interactionTestData.toString());
			}
			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			click(selenium,"addPrescription");
			waitForPageLoad(selenium);
			
			waitForElement(selenium, "medicationboxBox", 7000);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", interactionTestData.altMedicationName.toUpperCase(new java.util.Locale("en", "US"))),"Could not enter Medication Name; More Details" +interactionTestData.toString());
			click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[1]/div/div");
			waitForPageLoad(selenium);
						
			if(interactionTestData.severity.trim().equalsIgnoreCase("Show no warnings")){
				Assert.assertFalse(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning displayed in the Prescription(Moderate) section; Filter Interaction Warnings Failed; Detailed data:" + interactionTestData.toString());
			}else{
				Assert.assertTrue(getText(selenium, "//div[@id='interactionstabpanel']/div[3]/div/div[2]/div/div/div/ul").trim().contains(interactionTestData.interactionWarnings.trim()),"Filterd Interaction Warning not displayed in the Prescription section After Edit; Edit Interaction Failed; Detailed data:" + interactionTestData.toString());
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
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

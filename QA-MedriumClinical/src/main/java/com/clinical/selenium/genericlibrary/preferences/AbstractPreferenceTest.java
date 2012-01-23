package com.clinical.selenium.genericlibrary.preferences;

import org.testng.Assert;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class AbstractPreferenceTest extends AbstractTest {
	public void createMedication(Selenium selenium, PreferencesLib medicationTestData ){
		
		String allMedicationCount = null;
		String activeMedicationCount = null;

		Assert.assertTrue(click(selenium,"medications"),"Could Not click the New Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);
		
		if(selenium.isElementPresent("AllMedications"))
			Assert.assertTrue(click(selenium,"AllMedications"),"Could Not Click All Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
		allMedicationCount = getListCount(getText(selenium,"AllMedications"));

		Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
		activeMedicationCount = getListCount(getText(selenium,"CurrentMedications"));

		Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details" + medicationTestData.toString());
		Assert.assertTrue(click(selenium,"addMedication"),"Could not Click Add Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForElement(selenium, "medicationBoxBox", 7000),"Medication Box is not loaded; More Details" + medicationTestData.toString());
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationTestData.medicationName),"Could not enter Medication Name; More Details" + medicationTestData.toString());

		Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details" + medicationTestData.toString());

		if (medicationTestData.endDate != null && !medicationTestData.endDate.equals("")){
			Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details" + medicationTestData.toString());
		}

		Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details" + medicationTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", medicationTestData.status),"Could not select Medication Status; More Details" + medicationTestData.toString());

		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

		if(!selenium.isVisible("workStatusInput")){
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + medicationTestData.toString());
			waitForPageLoad(selenium);
		}

		Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details" + medicationTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details" + medicationTestData.toString());

		if(!waitForElementToEnable(selenium, "saveButton")){
			Assert.assertFalse(selenium.getText("medicationBoxLabel").trim().equals("")|| selenium.getText("medicationBoxLabel").trim() == null,"Medication Name Not properly loaded; Please Check the Medication Name" );
			Assert.fail("Save button is not enabled; More Details" + medicationTestData.toString());
		}

		Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}
		Assert.assertFalse(isElementPresent(selenium,"//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		click(selenium,"AllMedications");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture Medication Count[All] after saving a Medication; More Details" + medicationTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"AllMedications"))) == Integer.parseInt(allMedicationCount)+1,"The Medication is not Saved Successfully, Medication[All] count has no change after adding a new Medication; More Details" + medicationTestData.toString());
		Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture Medication Count[Active] after saving a Medication; More Details" + medicationTestData.toString());
		if(!medicationTestData.status.trim().equalsIgnoreCase("inactive")){
			Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"CurrentMedications"))) == Integer.parseInt(activeMedicationCount)+1,"The Medication is not Saved Successfully, Medication count[Active] has no change after adding a new Medication; More Details" + medicationTestData.toString());
		}else{
			Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"CurrentMedications"))) == Integer.parseInt(activeMedicationCount),"The Medication is not Saved Successfully, Medication count[Active] has no change after adding a new Medication; More Details" + medicationTestData.toString());
		}
	}
}

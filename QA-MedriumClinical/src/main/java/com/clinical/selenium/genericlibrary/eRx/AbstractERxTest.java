package com.clinical.selenium.genericlibrary.eRx;

import org.testng.Assert;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class AbstractERxTest extends AbstractTest {

	

	public void createPrescription(Selenium selenium, ERxLib prescriptionTestData ){
	
		click(selenium,"CurrentPrescriptions");
		waitForPageLoad(selenium);

		click(selenium,"AllPrescriptions");
		waitForPageLoad(selenium);
		
		Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" + prescriptionTestData.toString());
		waitForPageLoad(selenium);
		
		if(!isElementPresent(selenium, "providersInput") || !selenium.isVisible("providersInput") ){
			click(selenium,"editProvider");
			waitForPageLoad(selenium);
		}
					
		Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", prescriptionTestData.medicationName),"Could not enter Medication Name; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(select(selenium, "quantityTypeInput", prescriptionTestData.quantityTypeInput),"Could not select Quantity type input; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details" + prescriptionTestData.toString());
		
		Assert.assertTrue(type(selenium,"ss_quantityInput", prescriptionTestData.quantity),"Could not enter the Quantity input; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details" + prescriptionTestData.toString());
		
		Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details" + prescriptionTestData.toString());

		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details" + prescriptionTestData.toString());
					
		Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details" + prescriptionTestData.toString());

		Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details" + prescriptionTestData.toString());
		waitForPageLoad(selenium);				

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}
		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

	}

	}

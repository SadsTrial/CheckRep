package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForPrescription extends AbstractChartsTest {
	
	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test for Verifying Save button and Data for Un saved Prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "VerifySaveButtonForPrescription";
		prescriptionTestData.testCaseId = "TC_PRES_001";
		prescriptionTestData.fetchChartsTestData();
		createNewPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: createNewPrescription
	 * @Description : Function to create a New Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 25, 2011
	 */
	public void createNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib prescriptionTestData){
		
		Selenium selenium = null;
		
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Prescription and enter details             //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" +prescriptionTestData.toString());
			waitForPageLoad(selenium);
						

			if(!isElementPresent(selenium, "providersInput") || !selenium.isVisible("providersInput") ){
				click(selenium,"editProvider");
				waitForPageLoad(selenium);
			}
						
			Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", prescriptionTestData.medicationName),"Could not enter Medication Name; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "quantityTypeInput", prescriptionTestData.quantityTypeInput),"Could not select Quantity type input; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details" +prescriptionTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details" +prescriptionTestData.toString());
						
			Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details" +prescriptionTestData.toString());

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(selenium.getAttribute("prescriptionsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Prescriptions Add(+)  button is OFF and not backgrounded by Orange; More Details" +prescriptionTestData.toString());  
			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" +prescriptionTestData.toString());
			waitForPageLoad(selenium);
						
			verifyStoredValue(selenium, prescriptionTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +prescriptionTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
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

	public void verifyStoredValue(Selenium selenium, ChartsLib prescriptionTestData){

		Assert.assertEquals(getSelectedValue(selenium, "providersInput").trim(),prescriptionTestData.providerName,"Un saved Providers Input Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "locationsInput").trim(),prescriptionTestData.providerLocation,"Un saved Location Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertTrue(getText(selenium,"medicationboxLabel").trim().contains(prescriptionTestData.medicationName),"Un saved Madication Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getValue(selenium,"unitQtyInput").trim(),prescriptionTestData.unitQuantityInput,"Un saved UnitQty Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "quantityTypeInput").trim(),prescriptionTestData.quantityTypeInput,"Un saved Quantity Type Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "frequencyInput").trim(),prescriptionTestData.frequencyInput,"Un saved Frequency input Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "instructionInput").trim(),prescriptionTestData.instructionInput,"Un saved Instructions input Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getValue(selenium,"ss_refillsInput").trim(),prescriptionTestData.refills,"Un saved Refill input Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"itemStatusInput").trim(),prescriptionTestData.status,"Un saved Item Status input Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getValue(selenium,"commentsInput").trim(),prescriptionTestData.comments,"Un saved Comments Value is not available; It is modified; More Details" +prescriptionTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"workStatusInput").trim(),prescriptionTestData.taskName,"Un saved Work Status Value is not available; It is modified; More Details" +prescriptionTestData.toString());
	}
}
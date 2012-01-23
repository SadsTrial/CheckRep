package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyElectronicPrescription extends AbstractChartsTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to Verify Prescription With Valid Conditions")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyPrescriptionWithValidConditions(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "VerifyPrescription(E-Rx)";
		prescriptionTestData.testCaseId = "TC_EPRES_001";
		prescriptionTestData.fetchChartsTestData();
		createPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to Verify Prescription With InValid Conditions")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyPrescriptionWithInValidConditions(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "VerifyPrescription(E-Rx)";
		prescriptionTestData.testCaseId = "TC_EPRES_002";
		prescriptionTestData.fetchChartsTestData();
		createPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: createPrescription
	 * @Description : Function to create a New Signed Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Aug 11, 2011
	 */
	public void createPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib prescriptionTestData){

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

			//Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(type(selenium,"ss_quantityInput", prescriptionTestData.quantity),"Could not enter the Quantity input; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details" + prescriptionTestData.toString());

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			selenium.click("link=Search");
			waitForPageLoad(selenium);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"pharmacyBox", prescriptionTestData.pharmacyName),"Could not enter Pharmacy Name; More Details" +prescriptionTestData.toString());

			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details" + prescriptionTestData.toString());


			Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(waitForElementToEnable(selenium, "signButton"),"Unable to click 'Save' - Save button is not enabled; More Details" + prescriptionTestData.toString());

			Assert.assertTrue(click(selenium,"signButton"),"Could not click Sign Button; More Details" + prescriptionTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

			Assert.assertTrue(click(selenium,"actionButton"),"Could not click the Action Button");
			waitForPageLoad(selenium);

			if(prescriptionTestData.description.trim().equalsIgnoreCase("Verify the ERX with Valid conditions")){
				Assert.assertTrue(selenium.isElementPresent("//descendant::div[starts-with(@id, 'eprescribe')][1]"),"EPrescription  button is not present; E-Prescription with Valid Conditions Verification failed; More Details  " + prescriptionTestData.toString());
				Assert.assertTrue(selenium.getText("//descendant::div[starts-with(@id, 'eprescribe')][1]").trim().contains("Send Electronically"),"Send Electronically button is not present; E-Prescription with Valid Conditions Verification failed; More Details  " + prescriptionTestData.toString());
			}else{
				Assert.assertFalse(selenium.isElementPresent("//descendant::div[starts-with(@id, 'eprescribe')][1]"),"EPrescription  button is not present; E-Prescription with In Valid Conditions Verification failed; More Details  " + prescriptionTestData.toString());
				Assert.assertTrue(selenium.isElementPresent("//descendant::div[starts-with(@id, 'print')][1]"),"Print  button is not present; E-Prescription with Valid Conditions Verification failed; More Details  " + prescriptionTestData.toString());
			}

		}
		catch (RuntimeException e) {
			e.printStackTrace();
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib prescriptionTestData){

		if(!getText(selenium, "providerInput").trim().equalsIgnoreCase(prescriptionTestData.providerName)){
			return false;
		}

		if(!getText(selenium, "providerlocation").trim().equalsIgnoreCase(prescriptionTestData.providerLocation)){
			return false;
		}

		if(!getText(selenium,"//div[@id='fixedcontent']/div/div[3]/div/div/table/tbody/tr/td[2]/div/div/form/div/div[2]/div[9]/ol/li[3]/div[2]/span").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"quantityInput").trim().equalsIgnoreCase(prescriptionTestData.quantity)){
			return false;
		}

		if(!getText(selenium,"daysSupplyInput").trim().equalsIgnoreCase(prescriptionTestData.daySupplyInput)){
			return false;
		}

		if(!getText(selenium,"refillsInput").trim().equalsIgnoreCase(prescriptionTestData.refills)){
			return false;
		}

		if(!getText(selenium,"commentsInput").trim().equalsIgnoreCase(prescriptionTestData.comments)){
			return false;
		}

		if(!getText(selenium,"workStatus").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.taskName.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"itemstatus").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.status.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"taskUsers").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.sendTaskTo.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}
		return true;
	}
}
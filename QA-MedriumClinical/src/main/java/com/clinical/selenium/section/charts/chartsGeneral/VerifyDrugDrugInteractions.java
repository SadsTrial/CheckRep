package com.clinical.selenium.section.charts.chartsGeneral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyDrugDrugInteractions extends AbstractChartsTest {
	
	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to Verify Drug Drug Interactions")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyDrugDrugInteraction(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib drugDrugInteractionTestData = new ChartsLib();
		drugDrugInteractionTestData.workSheetName = "VerifyDrugDrugInteractions";
		drugDrugInteractionTestData.testCaseId = "TC_DDN_001";
		drugDrugInteractionTestData.fetchChartsTestData();
		verifyDrugDrugInteraction(seleniumHost, seleniumPort, browser, webSite, drugDrugInteractionTestData);	
	}

	/**
	 * @Function 	: verifyDrugDrugInteraction
	 * @Description : Function to verify the Drug Drug Interactions
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 03, 2010
	 */
	public void verifyDrugDrugInteraction(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib drugDrugInteractionTestData){

		Selenium selenium = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + drugDrugInteractionTestData.toString());
			loginFromPublicSite(selenium, drugDrugInteractionTestData.userAccount, drugDrugInteractionTestData.userName, drugDrugInteractionTestData.userPassword);
			searchPatient(selenium,drugDrugInteractionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Prescription and enter details             //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
						
			waitForPageLoad(selenium);

			drugDrugInteractionTestData.medicationName = drugDrugInteractionTestData.medicationName1; 

			createPrescription(selenium, drugDrugInteractionTestData);

			drugDrugInteractionTestData.medicationName = drugDrugInteractionTestData.medicationName2;

			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" + drugDrugInteractionTestData.toString());
			waitForPageLoad(selenium);
						

			click(selenium, "editProvider");
			waitForPageLoad(selenium);

			Assert.assertTrue(select(selenium, "providersInput", drugDrugInteractionTestData.providerName),"Could not select Provider Name; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", drugDrugInteractionTestData.providerLocation),"Could not select Provider Location; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", drugDrugInteractionTestData.medicationName),"Could not enter Medication Name; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(type(selenium,"unitQtyInput", drugDrugInteractionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium, "quantityTypeInput", drugDrugInteractionTestData.quantityTypeInput),"Could not select Quantity type input; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium, "frequencyInput", drugDrugInteractionTestData.frequencyInput),"Could not select Frequency Input; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium, "instructionInput", drugDrugInteractionTestData.instructionInput),"Could not select Frequency Input; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_daysSupplyInput", drugDrugInteractionTestData.daySupplyInput),"Could not enter Day supply input; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_refillsInput", drugDrugInteractionTestData.refills),"Could not enter Refills; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", drugDrugInteractionTestData.status),"Could not enter status; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(type(selenium,"commentsInput", drugDrugInteractionTestData.comments),"Could not enter Comments; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details" + drugDrugInteractionTestData.toString());
						
			Assert.assertTrue(select(selenium,"workStatusInput", drugDrugInteractionTestData.taskName),"Could not enter work status; More Details" + drugDrugInteractionTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", drugDrugInteractionTestData.sendTaskTo),"Could not select Medication send task to; More Details" + drugDrugInteractionTestData.toString());

			Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details" + drugDrugInteractionTestData.toString());

			click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[2]/div/div");
						
			String txtInteraction = getText(selenium,"//div[@id='interactionstabpanel']/div[3]/div");
			Assert.assertTrue(txtInteraction.trim().toLowerCase(new java.util.Locale("en", "US")).contains(drugDrugInteractionTestData.interactionMessage.trim().toLowerCase(new java.util.Locale("en", "US"))),"Expected Interaction is Not displayed; More Details "+drugDrugInteractionTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + drugDrugInteractionTestData.toString());
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

}
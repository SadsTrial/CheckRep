package com.clinical.selenium.section.charts.medication;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyDrugInteractionMedication extends AbstractChartsTest {
	

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to Verify Drug Drug Interactions")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyDrugDrugInteraction(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib drugDrugInteractionTestData = new ChartsLib();
		drugDrugInteractionTestData.workSheetName = "VerifyDrugInteractionMedication";
		drugDrugInteractionTestData.testCaseId = "TC_DIM_001";
		drugDrugInteractionTestData.fetchChartsTestData();
		verifyDrugDrugInteraction(seleniumHost, seleniumPort, browser, webSite, drugDrugInteractionTestData);	
	}

	/**
	 * @Function 	: verifyDrugInteractionMedication
	 * @Description : Function to verify the Drug Drug Interactions
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: June 6, 2011
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

			//------------------------------------------------------------------------------------------------------------//
			//  Step-2:  Check whether the drug drug interaction displayed when new medication is added medication exists-//
			//------------------------------------------------------------------------------------------------------------//

			Assert.assertTrue(click(selenium,"medications"),"Could Not click the New Medication Link; More Details" + drugDrugInteractionTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isElementPresent("AllMedications"))
				Assert.assertTrue(click(selenium,"AllMedications"),"Could Not Click All Medication Link; More Details" + drugDrugInteractionTestData.toString());
			waitForPageLoad(selenium);

			Boolean isRecordFoundInAllMedications=false;
			int counter = 1;
			String content = null;
			while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim() ;
				if(content.toLowerCase(new java.util.Locale("en", "US")).contains(drugDrugInteractionTestData.medicationName1.trim().toLowerCase(new java.util.Locale("en", "US")))){
					isRecordFoundInAllMedications = true;
					break;
				}
				counter++;
			}

			if(!isRecordFoundInAllMedications)
			{
				drugDrugInteractionTestData.medicationName= drugDrugInteractionTestData.medicationName1;
				createMedication(selenium,drugDrugInteractionTestData);
			}

			verifyInteraction(selenium,drugDrugInteractionTestData.medicationName2,drugDrugInteractionTestData.interactionMessage,drugDrugInteractionTestData.toString());

		}
		catch (RuntimeException e) {
			e.printStackTrace();
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

	public void verifyInteraction(Selenium selenium,String medicationName, String interactionMessage, String errorMessage)
	{
		Assert.assertTrue(click(selenium,"medications"),"Could Not click the New Medication Link");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link");
		Assert.assertTrue(click(selenium,"addMedication"),"Could not Click Add Medication Link");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForElement(selenium, "medicationBoxBox", 7000),"Medication Box is not loaded");
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationName),"Could not enter Medication Name");
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
		
		click(selenium,"//div[@id='interactionstabpanel']/div[2]/div/div[2]/div/div");
		waitForPageLoad(selenium);

		String txtInteraction = getText(selenium,"//div[@id='interactionstabpanel']/div[3]/div");
		Assert.assertTrue(txtInteraction.toLowerCase(new java.util.Locale("en", "US")).contains(interactionMessage.toLowerCase(new java.util.Locale("en", "US"))),"Expected Interaction is Not displayed; More Details " + errorMessage);
	}

}
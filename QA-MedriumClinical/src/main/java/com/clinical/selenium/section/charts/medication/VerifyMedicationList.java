package com.clinical.selenium.section.charts.medication;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyMedicationList extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for display various details in charts section")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyMedicationList(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "VerifyMedicationList";
		medicationTestData.testCaseId = "TC_VML_001";
		medicationTestData.fetchChartsTestData();
		verifyMedicationList(seleniumHost, seleniumPort, browser, webSite, medicationTestData);

	}

	/**
	 * @Function 	: clinicalCharts
	 * @Description : Function to display various details available in Charts
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 28, 2011
	 */
	public void verifyMedicationList(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){

		Selenium selenium = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			click(selenium,"medications");
			waitForPageLoad(selenium);
			verifyInMedications(selenium,medicationTestData.medicationName,medicationTestData.brandName,medicationTestData.genericName);
			click(selenium,"prescriptions");
						
			waitForPageLoad(selenium);
			verifyInPrescriptions(selenium,medicationTestData.medicationName,medicationTestData.brandName,medicationTestData.genericName);

			click(selenium,"medications");
			waitForPageLoad(selenium);
			verifyInMedications(selenium,medicationTestData.medicationName1,medicationTestData.brandName1,medicationTestData.genericName1);
			click(selenium,"prescriptions");
						
			waitForPageLoad(selenium);
			verifyInPrescriptions(selenium,medicationTestData.medicationName1,medicationTestData.brandName1,medicationTestData.genericName1);

			click(selenium,"medications");
			waitForPageLoad(selenium);
			verifyInMedications(selenium,medicationTestData.medicationName2,medicationTestData.brandName2,medicationTestData.genericName2);
			click(selenium,"prescriptions");
						
			waitForPageLoad(selenium);
			verifyInPrescriptions(selenium,medicationTestData.medicationName2,medicationTestData.brandName2,medicationTestData.genericName2);

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + medicationTestData.toString());
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

	public void verifyInMedications(Selenium selenium, String medicationName, String brandName, String genericName){

		Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link");
		
		click(selenium,"addMedication");
		waitForPageLoad(selenium);

		waitForElement(selenium, "medicationBoxBox", 7000);
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationName),"Could not enter Medication Name");
		Assert.assertTrue(getText(selenium,"//div[@class='suggestPopupMiddleCenterInner suggestPopupContent']/div/table/tbody").trim().contains(brandName.trim()),"Expected Brand Name Not Found in Suggest List In Medication Section; Expected Brand Name : "+ brandName);
		Assert.assertTrue(getText(selenium,"//div[@class='suggestPopupMiddleCenterInner suggestPopupContent']/div/table/tbody").trim().contains(genericName.trim()),"Expected Generic Medication Not Found in Suggest List In Medication Section; Generic Medication : "+ genericName);

		if(waitForElement(selenium,"//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]",WAIT_TIME)){
			selenium.click("//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]");
		}
		
		click(selenium,"cancelButton");
		waitForPageLoad(selenium);

	}

	public void verifyInPrescriptions(Selenium selenium, String medicationName, String brandName, String genericName){

		Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link");
		waitForPageLoad(selenium);
	
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", medicationName),"Could not enter Medication Name");
		Assert.assertTrue(getText(selenium,"//div[@class='suggestPopupMiddleCenterInner suggestPopupContent']/div/table/tbody").trim().contains(brandName.trim()),"Expected Brand Name Not Found in Suggest List In Prescription Section; Expected Brand Name : "+ brandName);
		Assert.assertTrue(getText(selenium,"//div[@class='suggestPopupMiddleCenterInner suggestPopupContent']/div/table/tbody").trim().contains(genericName.trim()),"Expected Generic Medication Not Found in Suggest List In Prescription Section; Generic Medication : "+ genericName);

		if(waitForElement(selenium,"//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]",WAIT_TIME)){
			selenium.click("//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]");
		}
		
		click(selenium,"cancelButton");
		waitForPageLoad(selenium);

	}

	public boolean selectValueFromAjaxList(Selenium selenium, String textboxName, String valueForSelection){
		textboxName = textboxName != null ? textboxName.trim() : "";
		valueForSelection = valueForSelection != null ? valueForSelection.trim() : "";

		try{
			if(!textboxName.equals("") && !valueForSelection.equals("") && waitForElement(selenium, textboxName, WAIT_TIME)){
				selenium.focus(textboxName);
				selenium.type(textboxName,valueForSelection);
				selenium.fireEvent(textboxName,"keydown");
				selenium.fireEvent(textboxName,"keypress");                           
				selenium.fireEvent(textboxName,"keyup");
				waitForPageLoad(selenium);
				
				return true;
			}

		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("selectValueFromAjaxList:" + textboxName + "," +valueForSelection,e);
		}
		return false;
	}

}

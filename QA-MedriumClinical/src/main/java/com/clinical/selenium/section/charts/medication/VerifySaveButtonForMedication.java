package com.clinical.selenium.section.charts.medication;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForMedication extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Verifying Save button and Data for Un saved Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "VerifySaveButtonForMedication";
		medicationTestData.testCaseId = "TC_MED_001";		
		medicationTestData.fetchChartsTestData();
		createMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	/**
	 * @Function 	: createMedication
	 * @Description : Function to create a New Medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 25, 2010
	 */
	public void createMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){

		Selenium selenium = null;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Medication and enter details             //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);

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

			click(selenium,"medications");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(selenium.getAttribute("medicationsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Medications Add(+)  button is OFF and not backgrounded by Orange; More Details" + medicationTestData.toString());
			Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details" + medicationTestData.toString());
			
			Assert.assertTrue(click(selenium,"addMedication"),"Could not Click Add Medication Link; More Details" + medicationTestData.toString());
			waitForPageLoad(selenium);

			verifyStoredValues(selenium, medicationTestData);
			Assert.assertTrue(selenium.isEditable("saveButton"),"Save Button is Not Enabled; More Details" + medicationTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + medicationTestData.toString());
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

	

	public void verifyStoredValues(Selenium selenium, ChartsLib medicationTestData){

		Assert.assertTrue(getText(selenium,"medicationBoxLabel").trim().contains(medicationTestData.medicationName.trim()),"Un saved Madication Value is not available; It is modified; More Details" + medicationTestData.toString());
		Assert.assertEquals(getValue(selenium, "startdateInput").trim(),medicationTestData.startDate.trim(),"Un saved Start Date Input Value is not available; It is modified; More Details" + medicationTestData.toString());
		Assert.assertEquals(getValue(selenium, "enddateInput").trim(),medicationTestData.endDate.trim(),"Un saved End Date Input Value is not available; It is modified; More Details" + medicationTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "itemStatusInput").trim(),medicationTestData.status.trim(),"Un saved Item Status Input Value is not available; It is modified; More Details" + medicationTestData.toString());
		Assert.assertEquals(getValue(selenium, "notesInput").trim(),medicationTestData.medicationNote.trim(),"Un saved Notes Input Value is not available; It is modified; More Details" + medicationTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "workStatusInput").trim(),medicationTestData.taskName.trim(),"Un saved Work Status Input Value is not available; It is modified; More Details" + medicationTestData.toString());

	}

}
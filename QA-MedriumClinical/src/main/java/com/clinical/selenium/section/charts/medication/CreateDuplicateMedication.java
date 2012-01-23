package com.clinical.selenium.section.charts.medication;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateDuplicateMedication extends AbstractChartsTest {


	/**
	 * @Function 	: BeforeTest
	 * @Description : This method is used for creating a directory for storing log files
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 20, 2010
	 */

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying the alert for duplicate medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createDuplicateMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "CreateDuplicateMedication";
		medicationTestData.testCaseId = "TC_DMED_001";		
		medicationTestData.fetchChartsTestData();
		createDuplicateMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	/**
	 * @Function 	: createDuplicateMedication
	 * @Description : Function to create Duplicate Medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 25, 2011
	 */
	public void createDuplicateMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){
		
		Selenium selenium = null;
		boolean isMedicationExisit = false;
		int recordCounter = 0;
		int counter = 1;
		String currentMedicationCount = "";
		boolean isMedicationDeleted = false;
		
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Verify and delete if Medication already Exists             //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);
			
			click(selenium,"AllMedications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
			currentMedicationCount = getListCount(getText(selenium,"CurrentMedications"));
			
			if (Integer.parseInt(currentMedicationCount)!= 0){
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isMedicationExisit = true;
						waitForPageLoad(selenium);
						String uniqueID = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("medication")[1];
						click(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);			
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
						
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().matches("^Are you sure you want to delete this medication [\\s\\S]$")){
								isMedicationDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + medicationTestData.toString());
						}
						Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + medicationTestData.toString());
						counter = counter -1;
					}
					counter++;
				}
			}

			//--------------------------------------------------------------------//
			//  Step-3: Create the new Medication if medication not Exists        //
			//--------------------------------------------------------------------//

			if (Integer.parseInt(currentMedicationCount) == 0 || !isMedicationExisit){
				createMedication(selenium,medicationTestData);
			}
			
			//--------------------------------------------------------------------//
			//  Step-4:  Click Add New Medication and enter duplicate values      //
			//--------------------------------------------------------------------//
			
			Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details :" + medicationTestData.toString());
			Assert.assertTrue(click(selenium,"addMedication"),"Could not Click Add Medication Link; More Details :" + medicationTestData.toString());
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, "medicationBoxBox", 7000),"Medication Box is not loaded; More Details :" + medicationTestData.toString());
			Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationTestData.medicationName),"Could not enter Medication Name; More Details :" + medicationTestData.toString());

			Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details :" + medicationTestData.toString());

			if (medicationTestData.endDate != null && !medicationTestData.endDate.equals("")){
				Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details :" + medicationTestData.toString());
			}

			Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details :" + medicationTestData.toString());

			Assert.assertTrue(select(selenium,"itemStatusInput", medicationTestData.status),"Could not select Medication Status; More Details :" + medicationTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			if(!selenium.isVisible("workStatusInput")){
				Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  ; More Details :" + medicationTestData.toString());
				waitForPageLoad(selenium);
			}

			Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details :" + medicationTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + medicationTestData.toString());

			if(!waitForElementToEnable(selenium, "saveButton")){
				Assert.assertFalse(selenium.getText("medicationBoxLabel").trim().equals("")|| selenium.getText("medicationBoxLabel").trim() == null,"Medication Name Not properly loaded; Please Check the Medication Name; More Details :" + medicationTestData.toString());
				Assert.fail("Save button is not enabled; More Details :" + medicationTestData.toString());
			}

			Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details :" + medicationTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertTrue(selenium.isAlertPresent(),"Duplicate medication error message  - " + selenium.getAlert() + "; More Details :" + medicationTestData.toString());
				click(selenium,"//a[@id='errorCloseButton']");
			}
			Assert.assertFalse(selenium.isAlertPresent(),"No duplicate medication error message was displayed;Detailed data:" + medicationTestData.toString());
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
}
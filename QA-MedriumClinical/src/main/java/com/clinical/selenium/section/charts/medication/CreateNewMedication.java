package com.clinical.selenium.section.charts.medication;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewMedication extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Adding New Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "TC_MED_001";		
		medicationTestData.fetchChartsTestData();
		createMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Adding New Inactive Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewInActiveMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "TC_MED_002";		
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
	 * @Created on 	: May 04, 2010
	 */
	public void createMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){

		Selenium selenium = null;
		String summaryMedicationCount = null;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity =false;
		boolean isRecordFoundInCurrentMedications = false;
		boolean isRecordFoundInAllMedications = false;
		boolean isInactiveMedication = false;

		if(medicationTestData.status.trim().equalsIgnoreCase("inactive")){
			isInactiveMedication = true;
		}

		try{
			
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:   Deactivating existing Medication with the same name     //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);
			deactiveMedication(selenium, medicationTestData);
			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "medicationTitle", 120000),"Could not capture existing Medication Count in patient summary section; More Details :" + medicationTestData.toString());
			summaryMedicationCount = getListCount(getText(selenium,"medicationTitle"));

			click(selenium,"medications");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Click Add New Medication and enter details               //
			//--------------------------------------------------------------------//
			
			click(selenium,"AllMedications");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink")){
				selenium.click("patientMedicationListMoreLink");
				waitForPageLoad(selenium);
			}
			
			Collection<String> firstList = getDataBaseIDs(selenium, "medication"); 	
			createMedication(selenium,medicationTestData);

			click(selenium,"AllMedications");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink")){
				selenium.click("patientMedicationListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "medication");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ medicationTestData.toString());
			}
			//---------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Medication Current section  //
			//---------------------------------------------------------------------------------------//

			click(selenium,"CurrentMedications");
			waitForPageLoad(selenium);
			
			if(!isInactiveMedication){
				while(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					selenium.click("patientMedicationListMoreLink");
					waitForPageLoad(selenium);
				}
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInCurrentMedications = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);
				click(selenium, "showList");
				waitForPageLoad(selenium);
			}

			//---------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Medication All section      //
			//---------------------------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);

			click(selenium,"AllMedications");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				selenium.click("patientMedicationListMoreLink");
				waitForPageLoad(selenium);
			}
			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInAllMedications = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);
			}

			//----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "medicationTitle", 120000),"Could not capture Medication Count in summary section after saving a Medication; More Details :" + medicationTestData.toString());
			if(!isInactiveMedication){
				Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"medicationTitle"))) == Integer.parseInt(summaryMedicationCount)+1,"The Medication is not Saved Successfully in summary section, Medication count has no change after adding a new Medication; More Details :" + medicationTestData.toString());
			}else{
				Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"medicationTitle"))) == Integer.parseInt(summaryMedicationCount),"The Medication is not Saved Successfully in summary section, Medication count has no change after adding a new Medication; More Details :" + medicationTestData.toString());
			}

			if(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				while(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					if(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink")){
						click(selenium, "patientMedicationListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}
			
			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInSummary = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);
			}

			//-----------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//		

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInActivity = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);

			if(!isInactiveMedication){
				Assert.assertTrue(isRecordFoundInCurrentMedications,"Active Medication record is Not found in Active Medication Page; Medication Creation Failed: Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInAllMedications,"Active Medication record is Not found in All Medication Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Active Medication record is not found in Activity Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInSummary,"Active Medication record is not found in Summary Page ; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());

			}else{

				Assert.assertFalse(isRecordFoundInCurrentMedications,"In Active Medication record is found in Active Medication Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInAllMedications,"In Active Medication record is not found in All Medication Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"In Active Medication record is not found in Activity Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertFalse(isRecordFoundInSummary,"In Active Medication record is found in Summary Page ; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + medicationTestData.toString());
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			
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

	public void deactiveMedication(Selenium selenium,ChartsLib medicationTestData) throws InterruptedException{

		int counter = 1;
		while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
			String content = null;
			content = selenium.getText("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
			content = content != null ? content.trim() : "" ;
			if(content != null && !content.equals("")){		
				if(content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					String uniqueID = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("medication")[1];
					click(selenium,"//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					click(selenium, "actionButton");
					Thread.sleep(4000);
					click(selenium, "edit"+uniqueID);
					Thread.sleep(4000);
					select(selenium,"itemStatusInput", "Inactive");
					if(click(selenium,"saveButton")){
						waitForPageLoad(selenium);
						Thread.sleep(4000);
						break;
					}
				}
			}
			counter++;
		}
	}

	public boolean verifyStoredValues(Selenium selenium, ChartsLib medicationTestData){

		if(!getText(selenium,"medicationBoxLabel").trim().contains(medicationTestData.medicationName.trim())){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().equalsIgnoreCase(medicationTestData.startDate.trim())){
			return false;
		}

		if(!getValue(selenium, "enddateInput").trim().equalsIgnoreCase(medicationTestData.endDate.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "itemStatusInput").trim().equalsIgnoreCase(medicationTestData.status.trim())){
			return false;
		}

		if(!getValue(selenium, "notesInput").trim().equalsIgnoreCase(medicationTestData.medicationNote.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(medicationTestData.taskName.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "taskUsersInput").trim().equalsIgnoreCase(medicationTestData.sendTaskTo.trim())){
			return false;
		}

		return true;
	}

	public boolean verifyStoredValuesForInactiveMedication(Selenium selenium, ChartsLib medicationTestData){

		if(!getText(selenium,"medicationlabel").trim().contains(medicationTestData.medicationName.trim())){
			return false;
		}

		if(!getText(selenium, "startdateInput").trim().equalsIgnoreCase(medicationTestData.startDate.trim())){
			return false;
		}

		if(!getText(selenium, "enddateInput").trim().equalsIgnoreCase(medicationTestData.endDate.trim())){
			return false;
		}

		if(!getText(selenium, "itemstatus").trim().equalsIgnoreCase(medicationTestData.status.trim())){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().equalsIgnoreCase(medicationTestData.medicationNote.trim())){
			return false;
		}
		if(!getText(selenium, "workStatus").trim().equalsIgnoreCase(medicationTestData.taskName.trim())){
			return false;
		}
		return true;
	}
}
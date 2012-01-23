package com.clinical.selenium.section.charts.medication;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class DeleteMedication extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Adding New Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "DeleteMedication";
		medicationTestData.testCaseId = "TC_DMD_001";		
		medicationTestData.fetchChartsTestData();
		deleteMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	/**
	 * @Function 	: deleteMedication
	 * @Description : Function to Delete Medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Oct 01, 2010
	 */
	public void deleteMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){

		Selenium selenium = null;
		String summaryMedicationCount = null;
		String uniqueID = null;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity =false;
		boolean isRecordFoundInActiveMedications = false;
		boolean isRecordFoundInAllMedications = false;
		boolean isMedicationExisit = false;
		boolean isMedicationDeleted = false;
		int recordCounter = 0;
		int counter = 1;
		String currentMedicationCount = "";
		String allMedicationCount = "";
		
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Capture the active medication counts in Summary Page     //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "medicationTitle", 10000),"Could not capture existing Medication Count in patient summary section; More Details :" + medicationTestData.toString());
			summaryMedicationCount = getListCount(getText(selenium,"medicationTitle"));
		
			click(selenium,"medications");
			waitForPageLoad(selenium);
			click(selenium,"AllMedications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
			allMedicationCount = getListCount(getText(selenium,"AllMedications"));

			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
			currentMedicationCount = getListCount(getText(selenium,"CurrentMedications"));

			//--------------------------------------------------------------------//
			//  Step-3:  Verify and delete if Medication already Exists             //
			//--------------------------------------------------------------------//
			
			String idOfTheRecord = null;
			if (Integer.parseInt(currentMedicationCount)!= 0){
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isMedicationExisit = true;
						waitForPageLoad(selenium);
						idOfTheRecord =selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id"); 
						uniqueID = idOfTheRecord.split("medication")[1];
						click(selenium, idOfTheRecord);
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button; More Details :" + medicationTestData.toString());
						Thread.sleep(3000);
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
						isMedicationDeleted = true;
						counter = counter -1;
					}
					counter++;
				}
			}

			//--------------------------------------------------------------------//
			//  Step-4: Create the new Medication if medication not Exists        //
			//--------------------------------------------------------------------//

			if (Integer.parseInt(currentMedicationCount) == 0 || !isMedicationExisit){
				createMedication(selenium,medicationTestData);
			}

			//--------------------------------------------------------------------//
			//  Step-5: Delete the Medication                                     //
			//--------------------------------------------------------------------//

			if(!isMedicationExisit && !isMedicationDeleted){

				click(selenium,"summary");
				waitForPageLoad(selenium);
				Assert.assertTrue(waitForValue(selenium, "medicationTitle", 120000),"Could not capture existing Medication Count in patient summary section");
				summaryMedicationCount = getListCount(getText(selenium,"medicationTitle"));

				click(selenium,"medications");
				waitForPageLoad(selenium);
				click(selenium,"AllMedications");
				waitForPageLoad(selenium);

				Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
				allMedicationCount = getListCount(getText(selenium,"AllMedications"));

				Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
				currentMedicationCount = getListCount(getText(selenium,"CurrentMedications"));

				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isMedicationExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						uniqueID = idOfTheRecord.split("medication")[1];
						click(selenium, idOfTheRecord);
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button; More Details :" + medicationTestData.toString());
						Thread.sleep(3000);
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
						isMedicationDeleted = true;
						counter = counter -1;
					}
					counter++;
				}
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are Deleted properly in Medication Current section  //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"AllMedications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 10000),"Could not capture Medication[Current] Count after Deleting a Medication; More Details :" + medicationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentMedications"))),(Integer.parseInt(currentMedicationCount)-recordCounter),"The Medication is not Deleted Correctly, Medication[Current] count has a change after Deleting an Medication; More Details :" + medicationTestData.toString());
			Assert.assertTrue(waitForValue(selenium, "AllMedications", 10000),"Could not capture Medication[All] Count after deleting a Medication; More Details :" + medicationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("AllMedications"))),(Integer.parseInt(allMedicationCount)-recordCounter),"The Medication is not Deleted Correctly, Medication[All] count has a change after Deleting an Medication; More Details :" + medicationTestData.toString());

			click(selenium,"CurrentMedications");
			waitForPageLoad(selenium);
			counter = 1;
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInActiveMedications = true;
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are Deleted properly in Medication All section      //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"AllMedications");
			waitForPageLoad(selenium);
			counter = 1;
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInAllMedications = true;
			}
			//-------------------------------------------------------------------------------//
			//  Step-8:  Verifying Details Entered are Deleted properly in summary section   //
			//-------------------------------------------------------------------------------//		

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "medicationTitle", 10000),"Could not capture Medication Count in summary section after Deleting a Medication; More Details :" + medicationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(getText(selenium,"medicationTitle"))),(Integer.parseInt(summaryMedicationCount)-recordCounter),"The Medication is not Deleted Successfully in summary section, Medication count has no change after adding a new Medication; More Details :" + medicationTestData.toString());

			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
					if(selenium.isElementPresent("patientMedicationListMoreLink") && selenium.isVisible("patientMedicationListMoreLink")){
						click(selenium, "patientMedicationListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}

			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInSummary = true;
			}

			//------------------------------------------------------------------------------//
			//  Step-9:  Verifying Details Entered are Deletd properly in activity section  //
			//------------------------------------------------------------------------------//		

			click(selenium,"activity");
			waitForPageLoad(selenium);

			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInActivity = true;
			}

			Assert.assertFalse(isRecordFoundInActiveMedications,"Deleted Record found; The Record is not deleted successfully in patient Medication[Active] section:  Detailed data:" + medicationTestData.toString());
			Assert.assertFalse(isRecordFoundInAllMedications,"Deleted Record found; The Record is not deleted successfully in patient Medication[All] section:  Detailed data:" + medicationTestData.toString());
			Assert.assertFalse(isRecordFoundInActivity,"Deleted Record found; The Record is not deleted successfully in patient Activity Page:  Detailed data:" + medicationTestData.toString());
			Assert.assertFalse(isRecordFoundInSummary,"Deleted Record found; The Record is not deleted successfully in patient Summary Page:  Detailed data:" + medicationTestData.toString());

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

	public void deactiveMedication(Selenium selenium,ChartsLib medicationTestData){
		int counter = 1;
		while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]")){
			String content = null;
			content = selenium.getText("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]");
			content = content != null ? content.trim() : "" ;
			if(content != null && !content.equals("")){		
				if(content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					click(selenium,"//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/a");
					waitForPageLoad(selenium);
					select(selenium,"itemStatusInput", "Inactive");
					if(click(selenium,"saveButton")){
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

		if(!getText(selenium, "workStatusInput").trim().equalsIgnoreCase(medicationTestData.taskName.trim())){
			return false;
		}

		return true;
	}
}

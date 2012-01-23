package com.clinical.selenium.section.charts.medication;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateMedication extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Updating a Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib medicationTestData = new ChartsLib();
		medicationTestData.workSheetName = "UpdateMedication";
		medicationTestData.testCaseId = "TC_UMD_001";		
		medicationTestData.fetchChartsTestData();
		updateExistingMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);    
	}

	/**
	 * @Function 	: updateExistingMedication
	 * @Description : Function to update existing medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 28, 2010
	 */

	public void updateExistingMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib medicationTestData){

		Selenium selenium = null;
		String activeMedicationCount = null;
		String allMedicationCount = null;
		String uniqueID = null;
		boolean isEndDatePresented = true;
		boolean isCreated = false;
		String summaryMedicationCount= null;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity =false;
		boolean isRecordFoundInActiveMedications = false;
		boolean isRecordFoundInAllMedications = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Update the Medication with New Details and Save           //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);
			
			click(selenium,"AllMedications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
			activeMedicationCount = getListCount(getText(selenium,"CurrentMedications"));

			//--------------------------------------------------------------------------------------//
			//  Step-3: Creates a New Medication if no existing Medication available  //
			//--------------------------------------------------------------------------------------//

			if(!activeMedicationCount.trim().equals(null) || !activeMedicationCount.trim().equals("")){
				if (Integer.parseInt(activeMedicationCount)== 0){
					medicationTestData = null;
					medicationTestData = new ChartsLib();
					medicationTestData.workSheetName = "Medication";
					medicationTestData.testCaseId = "TC_MED_001";		
					medicationTestData.fetchChartsTestData();
					click(selenium,"CurrentMedications");
								
					createMedication(selenium,medicationTestData);
					isCreated = true;
					activeMedicationCount = "1";

				}else{
					isCreated = true;
				}
			}

			//--------------------------------------------------------------------------------------//
			//  Step-4: Update the Existing Medication                                              //
			//--------------------------------------------------------------------------------------//

			if(isCreated){
				medicationTestData = null;
				medicationTestData = new ChartsLib();
				medicationTestData.workSheetName = "UpdateMedication";
				medicationTestData.testCaseId = "TC_UMD_001";		
				medicationTestData.fetchChartsTestData();
			
				click(selenium,"summary");
				waitForPageLoad(selenium);

				Assert.assertTrue(waitForValue(selenium, "medicationTitle", 120000),"Could not capture existing Medication Count in patient summary section; More Details" + medicationTestData.toString());
				summaryMedicationCount = getListCount(getText(selenium,"medicationTitle"));

				click(selenium,"medications");
				waitForPageLoad(selenium);
				
				click(selenium,"AllMedications");
				waitForPageLoad(selenium);

				Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
				allMedicationCount = getListCount(getText(selenium,"AllMedications"));

				click(selenium,"CurrentMedications");
				waitForPageLoad(selenium);
				
				String idOfTheRecord = null;
				if(selenium.isElementPresent("//div[@id='patientMedicationList']/table/tbody[1]/tr/td[1]/div")){
					idOfTheRecord = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
					uniqueID = idOfTheRecord.split("medication")[1];
					click(selenium,idOfTheRecord);
								
					waitForPageLoad(selenium);
					click(selenium, "actionButton");
								
					click(selenium, "edit"+uniqueID);
					waitForPageLoad(selenium);

					medicationTestData.medicationName = getText(selenium,"medicationBoxLabel");
					medicationTestData.status = medicationTestData.status != null || medicationTestData.status != "" ? medicationTestData.status.trim() : getSelectedValue(selenium, "status");
					medicationTestData.startDate = medicationTestData.startDate != null || medicationTestData.startDate != "" ? medicationTestData.startDate.trim() : getValue(selenium,"startDateInput");
					medicationTestData.endDate = medicationTestData.endDate != null || medicationTestData.endDate != "" ? medicationTestData.endDate.trim() : getValue(selenium,"endDateInput");								
					medicationTestData.medicationNote = medicationTestData.medicationNote != null || medicationTestData.medicationNote != "" ? medicationTestData.medicationNote.trim() : getValue(selenium,"notesInput");
					medicationTestData.taskName = medicationTestData.taskName != null || medicationTestData.taskName != "" ? medicationTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
					medicationTestData.sendTaskTo = medicationTestData.sendTaskTo != null || medicationTestData.sendTaskTo != "" ? medicationTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
					medicationTestData.taskNotes = medicationTestData.taskNotes != null || medicationTestData.taskNotes != "" ? medicationTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

					Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details" + medicationTestData.toString());
					if(isEndDatePresented){
						Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details" + medicationTestData.toString());
					}
					Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details" + medicationTestData.toString());
					Assert.assertTrue(select(selenium,"itemStatusInput", medicationTestData.status),"Could not select Medication Status; More Details" + medicationTestData.toString());
					Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

					if(!selenium.isVisible("workStatusInput")){
						Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + medicationTestData.toString());
									
					}
					Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details" + medicationTestData.toString());
					Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details" + medicationTestData.toString());

					if(!waitForElementToEnable(selenium, "saveButton")){
						Assert.assertFalse(selenium.getText("medicationBoxLabel").trim().equals("")|| selenium.getText("medicationBoxLabel").trim().equals(null),"Medication Name Not properly loaded; Please Check the Medication Name" );
						Assert.fail("Save button is not enabled; More Details" + medicationTestData.toString());
					}

					Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details" + medicationTestData.toString());
					waitForPageLoad(selenium);
				
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
				}

				Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + medicationTestData.toString());
				
				click(selenium,"AllMedications");
				waitForPageLoad(selenium);

				Assert.assertTrue(isElementPresent(selenium,"CurrentMedications"),"Could not capture Medication[Current] Count after saving a Medication; More Details" + medicationTestData.toString());
				Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentMedications"))) == (Integer.parseInt(activeMedicationCount)-1),"The Medication is not Saved Correctly, Medication[Current] count has a change after updating an Medication; More Details" + medicationTestData.toString());

				Assert.assertTrue(waitForValue(selenium, "AllMedications", 125000),"Could not capture Medication[All] Count after saving a Medication; More Details" + medicationTestData.toString());
				Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllMedications"))) == (Integer.parseInt(allMedicationCount)),"The Medication is not Saved Correctly, Medication[All] count has a change after updating an Medication; More Details" + medicationTestData.toString());

				//---------------------------------------------------------------------------------------//
				//  Step-5:  Verifying Details Entered are saved properly in Medication Current section  //
				//---------------------------------------------------------------------------------------//

				click(selenium,"CurrentMedications");
				waitForPageLoad(selenium);
				
				if(selenium.isElementPresent(idOfTheRecord)){
					click(selenium, idOfTheRecord);
					waitForPageLoad(selenium);
					click(selenium, "actionButton");
								
					click(selenium, "edit"+uniqueID);
					waitForPageLoad(selenium);
					
					isRecordFoundInActiveMedications = verifyStoredValues(selenium, medicationTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}

				//-----------------------------------------------------------------------------------//
				//  Step-6:  Verifying Details Entered are saved properly in Medication All section  //
				//-----------------------------------------------------------------------------------//

				click(selenium,"medications");
				waitForPageLoad(selenium);
				
				click(selenium,"AllMedications");
				waitForPageLoad(selenium);

				click(selenium, idOfTheRecord);
				waitForPageLoad(selenium);
				
				isRecordFoundInAllMedications = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);

				//----------------------------------------------------------------------------//
				//  Step-7:  Verifying Details Entered are saved properly in summary section  //
				//----------------------------------------------------------------------------//

				click(selenium,"summary");
				waitForPageLoad(selenium);
				
				Assert.assertTrue(waitForValue(selenium, "medicationTitle",  120000),"Could not capture Medication Count in summary section after saving a Medication; More Details" + medicationTestData.toString());
				Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"medicationTitle"))) == (Integer.parseInt(summaryMedicationCount)-1),"The Medication is not Saved Successfully in summary section, Medication count has no change after adding a new Medication; More Details" + medicationTestData.toString());

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
					click(selenium, idOfTheRecord);
					waitForPageLoad(selenium);
					isRecordFoundInSummary = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);
				}

				//-----------------------------------------------------------------------------//
				//  Step-8:  Verifying Details Entered are saved properly in activity section  //
				//-----------------------------------------------------------------------------//

				click(selenium,"activity");
				waitForPageLoad(selenium);

				click(selenium,"activityHeaderCurrentYear");
				waitForPageLoad(selenium);

				click(selenium, idOfTheRecord);
				waitForPageLoad(selenium);
				isRecordFoundInActivity = verifyStoredValuesForInactiveMedication(selenium, medicationTestData);

				Assert.assertFalse(isRecordFoundInActiveMedications,"Updated Medication[In Active] record is found in Active Medication Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInAllMedications,"Updated Medication[In Active] record is not found in All Medication Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Updated Medication[In Active] record is not found in Activity Page; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());
				Assert.assertFalse(isRecordFoundInSummary,"In Active Medication record is found in Summary Page ; Medication Creation Failed:  Detailed data:" + medicationTestData.toString());

			}else{
				Assert.fail("Could Not Create Medication");
			}

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


		if(!getSelectedValue(selenium, "taskUsersInput").trim().equalsIgnoreCase(medicationTestData.sendTaskTo.trim())){
			return false;
		}

		return true;
	}



}
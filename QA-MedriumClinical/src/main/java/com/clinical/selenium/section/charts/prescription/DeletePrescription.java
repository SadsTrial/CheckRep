package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class DeletePrescription extends AbstractChartsTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"   }, description = "Test script to create new prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void deletePretscription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "DeletePrescription";
		prescriptionTestData.testCaseId = "TC_DPRS_001";
		prescriptionTestData.fetchChartsTestData();
		createNewPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: createNewPrescription
	 * @Description : Function to Delete a Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 05, 2010
	 */
	public void createNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib prescriptionTestData){

		Selenium selenium = null;
		int counter = 1;
		int recordCounter = 0;
		boolean isRecordFoundInCurrentPrescriptions = false;
		boolean isRecordFoundInAllPrescriptions = false;
		boolean isRecordFoundInActivity = false;
		boolean isPrescriptionExisit = false;
		boolean isPrescriptionDeleted = false;
		String currentPrescriptionCount = "";
		String allPrescriptionCount = "";
		String uniqueID = null;

		try{
			
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//-------------------------------------------------------------------------//
			//  Step-2: Capture the active Prescription counts in Prescription Page    //
			//------------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);
			
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllPrescriptions", 120000),"Could not capture existing prescription[All] Count; More Details :" + prescriptionTestData.toString());
			allPrescriptionCount = getListCount(selenium.getText("AllPrescriptions"));
			Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details :" + prescriptionTestData.toString());
			currentPrescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));

			//--------------------------------------------------------------------//
			//  Step-3:  Verify and delete if Prescription already Exists         //
			//--------------------------------------------------------------------//
			
			String idOfTheRecord = null;
			if(Integer.parseInt(currentPrescriptionCount)!= 0){
				while(selenium.isElementPresent( "//div[@id='prescriptionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='prescriptionList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						idOfTheRecord = selenium.getAttribute("//div[@id='prescriptionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						uniqueID = idOfTheRecord.split("prescription")[1];
						click(selenium, "//div[@id='prescriptionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						isPrescriptionExisit = true;
						
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click the Delete Button");
						Thread.sleep(3000);
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().matches("^Are you sure you want to delete this prescription[\\s\\S]$")){
								isPrescriptionDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						isPrescriptionDeleted = true;
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + prescriptionTestData.toString());
						}
						Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + prescriptionTestData.toString());
						counter = counter -1;
					}
					counter++;
				}
			}

			//--------------------------------------------------------------------//
			//  Step-4: Create the new Prescription if Prescription not Exists    //
			//--------------------------------------------------------------------//

			if (Integer.parseInt(currentPrescriptionCount)== 0 || !isPrescriptionExisit){
				createPrescription(selenium, prescriptionTestData);
			}

			//--------------------------------------------------------------------//
			//  Step-5: Delete the Prescription                                   //
			//--------------------------------------------------------------------//

			if(!isPrescriptionExisit && !isPrescriptionDeleted){
				click(selenium,"prescriptions");
				waitForPageLoad(selenium);
				
				click(selenium,"AllPrescriptions");
				waitForPageLoad(selenium);
				
				click(selenium,"CurrentPrescriptions");
				waitForPageLoad(selenium);
				
				Assert.assertTrue(waitForValue(selenium, "AllPrescriptions", 120000),"Could not capture existing prescription[All] Count; More Details :" + prescriptionTestData.toString());
				allPrescriptionCount = getListCount(selenium.getText("AllPrescriptions"));
				Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details :" + prescriptionTestData.toString());
				currentPrescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));

				counter = 1;

				while(selenium.isElementPresent( "//div[@id='prescriptionList']/div/div["+counter+"]")){
					if(getText(selenium, "//div[@id='prescriptionList']/div/div["+counter+"]/div[1]").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						idOfTheRecord = selenium.getAttribute("//div[@id='prescriptionList']/div/div["+counter+"]/div[2]/table/tbody/tr/td/div/a@id");
						uniqueID = idOfTheRecord.split("prescription")[1];
						click(selenium, idOfTheRecord);
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);

						isPrescriptionExisit = true;
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click the Delete Button");
						Thread.sleep(3000);
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().matches("^Are you sure you want to delete this prescription[\\s\\S]$")){
								isPrescriptionDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						isPrescriptionDeleted = true;
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + prescriptionTestData.toString());
						}
						Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + prescriptionTestData.toString());
						counter = counter -1;
					}
					counter++;
				}
			}


			//-----------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in Prescription Current section  //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
						
			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);
				
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 10000),"Could not capture Prescription[Current] Count after deleting a Prescription; More Details :" + prescriptionTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentPrescriptions"))) , (Integer.parseInt(currentPrescriptionCount)-recordCounter),"The Prescription is not Deleted Correctly, Prescription count[Current] has a change after Deleting an Prescription; More Details :" + prescriptionTestData.toString());

			Assert.assertTrue(waitForValue(selenium, "AllPrescriptions", 10000),"Could not capture Prescription[All] Count after deleting a Prescription; More Details :" + prescriptionTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("AllPrescriptions"))),(Integer.parseInt(allPrescriptionCount)-recordCounter),"The Prescription is not Deleted Correctly, Prescription count[All] has a change after Deleting an Prescription; More Details :" + prescriptionTestData.toString());

			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("prescriptionListMoreLink") && selenium.isVisible("prescriptionListMoreLink")){
					click(selenium, "prescriptionListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}
			
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInCurrentPrescriptions = true;
			}
			
			//-------------------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in Prescription All section  //
			//-------------------------------------------------------------------------------------//

			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);
			
			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("prescriptionListMoreLink") && selenium.isVisible("prescriptionListMoreLink")){
					click(selenium, "prescriptionListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}
			
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInAllPrescriptions = true;
			}
			
			click(selenium,"activity");
			waitForPageLoad(selenium);
			
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInActivity = true;
			}
			
			Assert.assertFalse(isRecordFoundInCurrentPrescriptions,"Deleted Record found; The Record is not deleted successfully in patient Prescription[Current] section; More Details :" + prescriptionTestData.toString());
			Assert.assertFalse(isRecordFoundInAllPrescriptions,"Deleted Record found; The Record is not deleted successfully in patient Prescription[All] section; More Details :" + prescriptionTestData.toString());
			Assert.assertFalse(isRecordFoundInActivity,"Deleted Record found; The Record is not deleted successfully in patient Activity Page; More Details :" + prescriptionTestData.toString());

		}
		catch (RuntimeException e) {
             e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
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

		if(!getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(prescriptionTestData.providerName)){
			return false;
		}

		if(!getSelectedValue(selenium, "locationsInput").trim().equalsIgnoreCase(prescriptionTestData.providerLocation)){
			return false;
		}

		if(!getText(selenium,"medicationboxLabel").trim().contains(prescriptionTestData.medicationName)){
			return false;
		}

		if(!getValue(selenium,"unitQtyInput").trim().equalsIgnoreCase(prescriptionTestData.unitQuantityInput)){
			return false;
		}

		if(!getSelectedValue(selenium, "quantityTypeInput").trim().equalsIgnoreCase(prescriptionTestData.quantityTypeInput)){
			return false;
		}

		if(!getSelectedValue(selenium, "frequencyInput").trim().equalsIgnoreCase(prescriptionTestData.frequencyInput)){
			return false;
		}

		if(!getSelectedValue(selenium, "instructionInput").trim().equalsIgnoreCase(prescriptionTestData.instructionInput)){
			return false;
		}

		if(!getValue(selenium,"daysSupplyInput").trim().equalsIgnoreCase(prescriptionTestData.daySupplyInput)){
			return false;
		}

		if(!getValue(selenium,"quantityInput").trim().equalsIgnoreCase(prescriptionTestData.quantity)){
			return false;
		}

		if(!getValue(selenium,"refillsInput").trim().equalsIgnoreCase(prescriptionTestData.refills)){
			return false;
		}

		if(!getSelectedValue(selenium,"itemStatusInput").trim().equalsIgnoreCase(prescriptionTestData.status)){
			return false;
		}

		if(!getValue(selenium,"commentsInput").trim().equalsIgnoreCase(prescriptionTestData.comments)){
			return false;
		}

		if(!getSelectedValue(selenium,"workStatusInput").trim().equalsIgnoreCase(prescriptionTestData.taskName)){
			return false;
		}

		if(!getSelectedValue(selenium,"taskUsersInput").trim().equalsIgnoreCase(prescriptionTestData.sendTaskTo)){
			return false;
		}
		return true;
	}

	public boolean verifyStoredValuesForInactivePrescription(Selenium selenium, ChartsLib prescriptionTestData){

		if(!getText(selenium,"//div[@id='fixedcontent']/div/div[3]/div/div/table/tbody/tr/td[2]/div/div/form/div/div[1]/ol/li[2]/div[2]/span").trim().contains(prescriptionTestData.medicationName)){
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

		if(!getText(selenium,"workStatusInput").trim().equalsIgnoreCase(prescriptionTestData.taskName)){
			return false;
		}

		return true;
	}
}

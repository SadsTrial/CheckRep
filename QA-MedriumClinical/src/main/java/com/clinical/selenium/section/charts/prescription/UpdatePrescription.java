package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdatePrescription extends AbstractChartsTest {

	String currentPrescriptionCount;

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Update the Prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updatePrescription(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "UpdatePrescription";
		prescriptionTestData.testCaseId = "TC_UPR_001";
		prescriptionTestData.fetchChartsTestData();
		updateExistingPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);
	}

	/**
	 * @Function 	updateExistingPrescription 
	 * @Description Function to update existing Prescription
	 * @param 		selinumHost
	 * @param		seleninumPort
	 * @param 		browser
	 * @param		website
	 * @return		void
	 * @Author 	    Aspire QA
	 * @Created on 	Jul 28, 2010
	 */	
	public void updateExistingPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib prescriptionTestData){

		Selenium selenium = null;
		boolean isRecordFoundInCurrentPrescriptions = false;
		String uniqueID = null;
		boolean isRecordFoundInAllPrescriptions = false;
		boolean isRecordFoundInActivity = false;	

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details" +prescriptionTestData.toString());
			currentPrescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));

			//-----------------------------------------------------------------------------------//
			//  Step-2: Add the Prescription if no prescription is available to Update           //
			//-----------------------------------------------------------------------------------//

			if(!currentPrescriptionCount.trim().equals(null) || !currentPrescriptionCount.trim().equals("")){
				if (Integer.parseInt(currentPrescriptionCount)== 0){

					prescriptionTestData = null;
					prescriptionTestData = new ChartsLib();
					prescriptionTestData.workSheetName = "Prescription";
					prescriptionTestData.testCaseId = "TC_PRES_001";		
					prescriptionTestData.fetchChartsTestData();
					click(selenium,"CurrentPrescriptions");

					createPrescription(selenium, prescriptionTestData);
					currentPrescriptionCount = "1";

				}
			}

			//--------------------------------------------------------------------//
			//  Step-3: Update the Prescription with New Details and Save         //
			//--------------------------------------------------------------------//

			prescriptionTestData = null;
			prescriptionTestData = new ChartsLib();
			prescriptionTestData.workSheetName = "UpdatePrescription";
			prescriptionTestData.testCaseId = "TC_UPR_001";		
			prescriptionTestData.fetchChartsTestData();

			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);
			click(selenium, "CurrentPrescriptions");
			waitForPageLoad(selenium);
			String idOfTheRecord = null;

			if(isElementPresent(selenium, "//descendant::a[starts-with(@id, 'prescription')][1]")){
				idOfTheRecord = selenium.getAttribute("//descendant::a[starts-with(@id, 'prescription')][1]@id");
				uniqueID = idOfTheRecord.split("prescription")[1];
				click(selenium, "//descendant::a[starts-with(@id, 'prescription')][1]");
				waitForPageLoad(selenium);
				click(selenium, "actionButton");

				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				prescriptionTestData.medicationName = getText(selenium,"medicationboxLabel").trim();
				prescriptionTestData.visitDate = prescriptionTestData.visitDate!=null || prescriptionTestData.visitDate!="" ? prescriptionTestData.visitDate.trim() : getSelectedValue(selenium, "listBoxVisitsConditions").trim();				
				prescriptionTestData.providerName = prescriptionTestData.providerName!=null || prescriptionTestData.providerName!="" ? prescriptionTestData.providerName.trim() : getSelectedValue(selenium, "providersInput").trim();
				prescriptionTestData.providerLocation =  prescriptionTestData.providerLocation!=null || prescriptionTestData.providerLocation!="" ? prescriptionTestData.providerLocation.trim() : getSelectedValue(selenium, "locationsInput").trim();
				prescriptionTestData.unitQuantityInput = prescriptionTestData.unitQuantityInput!=null || prescriptionTestData.unitQuantityInput!="" ? prescriptionTestData.unitQuantityInput.trim() : getValue(selenium,"unitQtyInput").trim(); 
				prescriptionTestData.quantityTypeInput = prescriptionTestData.quantityTypeInput!=null || prescriptionTestData.quantityTypeInput!="" ? prescriptionTestData.quantityTypeInput.trim() : getSelectedValue(selenium, "quantityTypeInput").trim(); 
				prescriptionTestData.frequencyInput = prescriptionTestData.frequencyInput!=null || prescriptionTestData.frequencyInput!="" ? prescriptionTestData.frequencyInput.trim() : getSelectedValue(selenium, "frequencyInput").trim(); 
				prescriptionTestData.instructionInput = prescriptionTestData.instructionInput!=null || prescriptionTestData.instructionInput!="" ? prescriptionTestData.instructionInput.trim() : getSelectedValue(selenium, "instructionInput").trim();
				prescriptionTestData.daySupplyInput = prescriptionTestData.daySupplyInput!=null || prescriptionTestData.daySupplyInput!="" ? prescriptionTestData.daySupplyInput.trim() : getValue(selenium,"ss_daysSupplyInput").trim();
				prescriptionTestData.refills = prescriptionTestData.refills!=null || prescriptionTestData.refills!="" ? prescriptionTestData.refills.trim() : getValue(selenium,"ss_refillsInput").trim();
				prescriptionTestData.comments = prescriptionTestData.comments!=null || prescriptionTestData.comments!="" ? prescriptionTestData.comments.trim() : getValue(selenium,"commentsInput").trim();
				prescriptionTestData.status = prescriptionTestData.status!=null || prescriptionTestData.status!="" ? prescriptionTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput").trim();
				prescriptionTestData.taskName =  prescriptionTestData.taskName!=null || prescriptionTestData.taskName!="" ? prescriptionTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput").trim();
				prescriptionTestData.sendTaskTo = prescriptionTestData.sendTaskTo!=null || prescriptionTestData.sendTaskTo!="" ? prescriptionTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput").trim();

				click(selenium, "editProvider");
				waitForPageLoad(selenium);
				Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(select(selenium, "quantityTypeInput", prescriptionTestData.quantityTypeInput),"Could not select Quantity type input; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"ss_quantityInput", prescriptionTestData.quantity),"Could not enter the Quantity input; More Details" + prescriptionTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details" +prescriptionTestData.toString());
				selenium.click("link=Search");
				waitForPageLoad(selenium);
				Assert.assertTrue(selectValueFromAjaxList(selenium,"pharmacyBox", prescriptionTestData.pharmacyName),"Could not enter Pharmacy Name; More Details" +prescriptionTestData.toString());

				if(!isElementPresent(selenium,"workStatusInput")){
					click(selenium, "addTask");
					waitForPageLoad(selenium);
				}


				Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details" +prescriptionTestData.toString());

				Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details" +prescriptionTestData.toString());
				Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details" +prescriptionTestData.toString());
				waitForPageLoad(selenium);


				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Prescription not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

			}else{
				Assert.fail("No Prescription Record exists for further updation; More Details" +prescriptionTestData.toString());
			}


			//-----------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Prescription All section      //
			//-----------------------------------------------------------------------------------------//

			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + prescriptionTestData.toString());
			
			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);

			int counter = 1;
			String prescriptionMorelinkId = "";
			while(selenium.isElementPresent("//div[@id='prescriptionList']/div/div["+counter+"]")){
				if(selenium.getText("//div[@id='prescriptionList']/div/div["+counter+"]/div[1]").toLowerCase(new java.util.Locale("en", "US")).trim().contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					prescriptionMorelinkId = selenium.getAttribute("//div[@id='prescriptionList']/div/div["+counter+"]/div[2]@id");
					break;
				}
				counter ++;
			}

			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
					if(selenium.isElementPresent(prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
						click(selenium, prescriptionMorelinkId+"MoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);

			isRecordFoundInAllPrescriptions = verifyStoredValues(selenium,prescriptionTestData);

			//-----------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Prescription Current section      //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			if(selenium.isElementPresent(idOfTheRecord)){
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
				isRecordFoundInCurrentPrescriptions=verifyStoredValues( selenium, prescriptionTestData);
				click(selenium,"showList");
				waitForPageLoad(selenium);
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in Activity section              //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity=verifyStoredValues(selenium, prescriptionTestData);


			Assert.assertFalse(isRecordFoundInCurrentPrescriptions,"Updated Prescription[In Active] record is  found in Active Prescription Page; Prescription Updation Failed; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(isRecordFoundInAllPrescriptions," Updated Prescription[In Active] record is not found in All Prescription Page; Prescription Updation Failed; More Details" +prescriptionTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Updated Prescription[In Active] record is not found in Activity Page; Prescription Updation Failed; More Details" +prescriptionTestData.toString());

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

	public  void waitForAlert(Selenium selenium){
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

		if(!getText(selenium,"//div[@id='fixedcontent']/div/div[3]/div/div/table/tbody/tr/td[2]/div/div/form/div/div[2]/div[9]/ol/li[3]/div[2]/span").trim().contains(prescriptionTestData.medicationName)){
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
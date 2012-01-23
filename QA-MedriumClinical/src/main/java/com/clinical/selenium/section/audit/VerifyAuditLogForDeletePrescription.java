package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForDeletePrescription extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"   }, description = "Test script to verify Audit Log for delete  prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForDeletePrescription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		AuditLib prescriptionTestData = new AuditLib();
		prescriptionTestData.workSheetName = "DeletePrescription";
		prescriptionTestData.testCaseId = "TC_DPRS_001";
		prescriptionTestData.fetchAuditTestData();
		verifyAuditLogForDeletePrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: verifyAuditLogForDeletePrescription
	 * @Description : Function to verify Audit Log For Delete Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 22, 2010
	 */

	@SuppressWarnings("deprecation")
	public void verifyAuditLogForDeletePrescription(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib prescriptionTestData){

		Selenium selenium = null;
		int counter = 1;
		int recordCounter = 0;
		boolean isPrescriptionExisit = false;
		boolean isPrescriptionDeleted = false;
		boolean isAuditResultVerified = false;
		String prescriptionCount = "";
		String uniqueID = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Select the  prescription  and Delete                      //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details :" + prescriptionTestData.toString());
			prescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));

			//--------------------------------------------------------------------//
			//  Step-3: Check for Existing Records and Delete                     //
			//--------------------------------------------------------------------//

			Date currentDate = new Date();
			String idOfTheRecord = null;
			if(!prescriptionCount.equals("0")){
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
						prescriptionTestData.visitDate = prescriptionTestData.visitDate!=null || prescriptionTestData.visitDate!="" ? prescriptionTestData.visitDate.trim() : getSelectedValue(selenium, "listBoxVisitsConditions").trim();				
						prescriptionTestData.providerName = prescriptionTestData.providerName!=null || prescriptionTestData.providerName!="" ? prescriptionTestData.providerName.trim() : getSelectedValue(selenium, "providersInput").trim();
						prescriptionTestData.providerLocation =  prescriptionTestData.providerLocation!=null || prescriptionTestData.providerLocation!="" ? prescriptionTestData.providerLocation.trim() : getSelectedValue(selenium, "locationsInput").trim();
						prescriptionTestData.medicationName = prescriptionTestData.medicationName!=null || prescriptionTestData.medicationName!="" ? prescriptionTestData.medicationName.trim() : getText(selenium,"medicationboxLabel").trim();				
						prescriptionTestData.unitQuantityInput = prescriptionTestData.unitQuantityInput!=null || prescriptionTestData.unitQuantityInput!="" ? prescriptionTestData.unitQuantityInput.trim() : getValue(selenium,"unitQtyInput").trim(); 
						prescriptionTestData.quantityTypeInput = prescriptionTestData.quantityTypeInput!=null || prescriptionTestData.quantityTypeInput!="" ? prescriptionTestData.quantityTypeInput.trim() : getSelectedValue(selenium, "quantityTypeInput").trim(); 
						prescriptionTestData.frequencyInput = prescriptionTestData.frequencyInput!=null || prescriptionTestData.frequencyInput!="" ? prescriptionTestData.frequencyInput.trim() : getSelectedValue(selenium, "frequencyInput").trim(); 
						prescriptionTestData.instructionInput = prescriptionTestData.instructionInput!=null || prescriptionTestData.instructionInput!="" ? prescriptionTestData.instructionInput.trim() : getSelectedValue(selenium, "instructionInput").trim();
						prescriptionTestData.daySupplyInput = prescriptionTestData.daySupplyInput!=null || prescriptionTestData.daySupplyInput!="" ? prescriptionTestData.daySupplyInput.trim() : getValue(selenium,"daysSupplyInput").trim();
						prescriptionTestData.quantity = prescriptionTestData.quantity!=null || prescriptionTestData.quantity!="" ? prescriptionTestData.quantity.trim() : getValue(selenium,"quantityInput").trim();
						prescriptionTestData.refills = prescriptionTestData.refills!=null || prescriptionTestData.refills!="" ? prescriptionTestData.refills.trim() : getValue(selenium,"refillsInput").trim();
						prescriptionTestData.comments = prescriptionTestData.comments!=null || prescriptionTestData.comments!="" ? prescriptionTestData.comments.trim() : getValue(selenium,"commentsInput").trim();
						prescriptionTestData.status = prescriptionTestData.status!=null || prescriptionTestData.status!="" ? prescriptionTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput").trim();
						prescriptionTestData.taskName =  prescriptionTestData.taskName!=null || prescriptionTestData.taskName!="" ? prescriptionTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput").trim();
						prescriptionTestData.sendTaskTo = prescriptionTestData.sendTaskTo!=null || prescriptionTestData.sendTaskTo!="" ? prescriptionTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput").trim();

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

			//----------------------------------------------------------------------------------------------------//
			//  Step-4: function call to create prescription if prescription is not available                     //
			//----------------------------------------------------------------------------------------------------//

			if (Integer.parseInt(prescriptionCount)== 0 || !isPrescriptionExisit){
				createPrescription(selenium, prescriptionTestData);
			}

			if(!isPrescriptionExisit && !isPrescriptionDeleted){
				click(selenium,"prescriptions");
				waitForPageLoad(selenium);

				click(selenium,"CurrentPrescriptions");
				waitForPageLoad(selenium);

				Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details :" + prescriptionTestData.toString());
				prescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));
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
						prescriptionTestData.visitDate = prescriptionTestData.visitDate!=null || prescriptionTestData.visitDate!="" ? prescriptionTestData.visitDate.trim() : getSelectedValue(selenium, "listBoxVisitsConditions").trim();				
						prescriptionTestData.providerName = prescriptionTestData.providerName!=null || prescriptionTestData.providerName!="" ? prescriptionTestData.providerName.trim() : getSelectedValue(selenium, "providersInput").trim();
						prescriptionTestData.providerLocation =  prescriptionTestData.providerLocation!=null || prescriptionTestData.providerLocation!="" ? prescriptionTestData.providerLocation.trim() : getSelectedValue(selenium, "locationsInput").trim();
						prescriptionTestData.medicationName = prescriptionTestData.medicationName!=null || prescriptionTestData.medicationName!="" ? prescriptionTestData.medicationName.trim() : getText(selenium,"medicationboxLabel").trim();				
						prescriptionTestData.unitQuantityInput = prescriptionTestData.unitQuantityInput!=null || prescriptionTestData.unitQuantityInput!="" ? prescriptionTestData.unitQuantityInput.trim() : getValue(selenium,"unitQtyInput").trim(); 
						prescriptionTestData.quantityTypeInput = prescriptionTestData.quantityTypeInput!=null || prescriptionTestData.quantityTypeInput!="" ? prescriptionTestData.quantityTypeInput.trim() : getSelectedValue(selenium, "quantityTypeInput").trim(); 
						prescriptionTestData.frequencyInput = prescriptionTestData.frequencyInput!=null || prescriptionTestData.frequencyInput!="" ? prescriptionTestData.frequencyInput.trim() : getSelectedValue(selenium, "frequencyInput").trim(); 
						prescriptionTestData.instructionInput = prescriptionTestData.instructionInput!=null || prescriptionTestData.instructionInput!="" ? prescriptionTestData.instructionInput.trim() : getSelectedValue(selenium, "instructionInput").trim();
						prescriptionTestData.daySupplyInput = prescriptionTestData.daySupplyInput!=null || prescriptionTestData.daySupplyInput!="" ? prescriptionTestData.daySupplyInput.trim() : getValue(selenium,"daysSupplyInput").trim();
						prescriptionTestData.quantity = prescriptionTestData.quantity!=null || prescriptionTestData.quantity!="" ? prescriptionTestData.quantity.trim() : getValue(selenium,"quantityInput").trim();
						prescriptionTestData.refills = prescriptionTestData.refills!=null || prescriptionTestData.refills!="" ? prescriptionTestData.refills.trim() : getValue(selenium,"refillsInput").trim();
						prescriptionTestData.comments = prescriptionTestData.comments!=null || prescriptionTestData.comments!="" ? prescriptionTestData.comments.trim() : getValue(selenium,"commentsInput").trim();
						prescriptionTestData.status = prescriptionTestData.status!=null || prescriptionTestData.status!="" ? prescriptionTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput").trim();
						prescriptionTestData.taskName =  prescriptionTestData.taskName!=null || prescriptionTestData.taskName!="" ? prescriptionTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput").trim();
						prescriptionTestData.sendTaskTo = prescriptionTestData.sendTaskTo!=null || prescriptionTestData.sendTaskTo!="" ? prescriptionTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput").trim();
						currentDate = new Date();
						currentDate.setSeconds(currentDate.getSeconds()-10);
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

			Date currentDate1 = new Date();
			
			//-----------------------------------------------------------------------------//
			//  Step-5:  Verify the Deleted details are logged properly  in the Audit page //
			//-----------------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			counter = 1;
			int counter1 =0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Deleted Record; more Details :-" + prescriptionTestData.toString());
			String recordID = "Prescription("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Delete", recordID,  prescriptionTestData).split("\n");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" Old value was ")){
					String ColName =auditValue[counter1].split(" was deleted. ")[0];
					String value = 	auditValue[counter1].split(" Old value was ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, prescriptionTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + prescriptionTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + prescriptionTestData.toString());
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
			e.printStackTrace();
		}finally{
			if(selenium != null){
				if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
					click(selenium, "errorCloseButton");
					waitForPageLoad(selenium);	
				}

				if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
					click(selenium, "headerClinicalMenu");

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

	public boolean verifyAuditValues(String ColName, String value, AuditLib prescriptionTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.comments.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("dayssupply")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.daySupplyInput.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"dayssupply,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("unitqty")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.unitQuantityInput)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"unitqty,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("quantity")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.quantity)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"quantity,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("refillnumber")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.refills)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"refillnumber,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("location")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.providerLocation)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"location,";
				return false;
			}
		}else{
			return true;
		}
	}	
}
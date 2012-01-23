package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdatePrescription extends AbstractAuditTest {

	String prescriptionCount;
	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for verifying audit log for update Prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForUpdatePrescription(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib prescriptionTestData = new AuditLib();
		prescriptionTestData.workSheetName = "UpdatePrescription";
		prescriptionTestData.testCaseId = "TC_UPR_001";
		prescriptionTestData.fetchAuditTestData();
		verifyAuditLogForUpdatePrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);
	}

	/**
	 * @Function 	verifyAuditLogForUpdatePrescription 
	 * @Description Function to verify audit log for update Prescription
	 * @param 		selinumHost
	 * @param		seleninumPort
	 * @param 		browser
	 * @param		website
	 * @return		void
	 * @Author 		Aspire QA
	 * @Created on 	Nov 15, 2010
	 */	
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForUpdatePrescription(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib prescriptionTestData){

		Selenium selenium = null;
		String uniqueID = null;
		boolean isAuditResultVerified = false;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			click(selenium, "prescriptions");
			waitForPageLoad(selenium);

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentPrescriptions", 120000),"Could not capture existing prescription[Current] Count; More Details :" + prescriptionTestData.toString());
			prescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));
			if(!prescriptionCount.trim().equals(null) || !prescriptionCount.trim().equals("")){
				if (Integer.parseInt(prescriptionCount) == 0){
					prescriptionTestData = null;
					prescriptionTestData = new AuditLib();
					prescriptionTestData.workSheetName = "Prescription";
					prescriptionTestData.testCaseId = "TC_PRES_001";		
					prescriptionTestData.fetchAuditTestData();
					createPrescription(selenium, prescriptionTestData);
					prescriptionCount = "1";
				}
			}

			//--------------------------------------------------------------------//
			//  Step-2: Update the Prescription with New Details and Save         //
			//--------------------------------------------------------------------//

			prescriptionTestData = null;
			prescriptionTestData = new AuditLib();
			prescriptionTestData.workSheetName = "UpdatePrescription";
			prescriptionTestData.testCaseId = "TC_UPR_001";		
			prescriptionTestData.fetchAuditTestData();

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);
			Assert.assertTrue(isElementPresent(selenium, "//descendant::a[starts-with(@id, 'prescription')][1]"),"No Prescription Record exists for further updation; More Details :" + prescriptionTestData.toString());
			uniqueID = selenium.getAttribute("//descendant::a[starts-with(@id, 'prescription')][1]@id").split("prescription")[1];
			click(selenium, "//descendant::a[starts-with(@id, 'prescription')][1]");
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);

			prescriptionTestData.visitDate = prescriptionTestData.visitDate!=null || prescriptionTestData.visitDate!="" ? prescriptionTestData.visitDate.trim() : getSelectedValue(selenium, "listBoxVisitsConditions").trim();				
			prescriptionTestData.providerName = prescriptionTestData.providerName!=null || prescriptionTestData.providerName!="" ? prescriptionTestData.providerName.trim() : getSelectedValue(selenium, "providersInput").trim();
			prescriptionTestData.providerLocation =  prescriptionTestData.providerLocation!=null || prescriptionTestData.providerLocation!="" ? prescriptionTestData.providerLocation.trim() : getSelectedValue(selenium, "locationsInput").trim();
			prescriptionTestData.medicationName = prescriptionTestData.medicationName!=null || prescriptionTestData.medicationName!="" ? prescriptionTestData.medicationName.trim() : getText(selenium, "medicationboxLabel").trim();				
			prescriptionTestData.unitQuantityInput = prescriptionTestData.unitQuantityInput!=null || prescriptionTestData.unitQuantityInput!="" ? prescriptionTestData.unitQuantityInput.trim() : getValue(selenium, "unitQtyInput").trim(); 
			prescriptionTestData.quantityTypeInput = prescriptionTestData.quantityTypeInput!=null || prescriptionTestData.quantityTypeInput!="" ? prescriptionTestData.quantityTypeInput.trim() : getSelectedValue(selenium, "quantityTypeInput").trim(); 
			prescriptionTestData.frequencyInput = prescriptionTestData.frequencyInput!=null || prescriptionTestData.frequencyInput!="" ? prescriptionTestData.frequencyInput.trim() : getSelectedValue(selenium, "frequencyInput").trim(); 
			prescriptionTestData.instructionInput = prescriptionTestData.instructionInput!=null || prescriptionTestData.instructionInput!="" ? prescriptionTestData.instructionInput.trim() : getSelectedValue(selenium, "instructionInput").trim();
			prescriptionTestData.daySupplyInput = prescriptionTestData.daySupplyInput!=null || prescriptionTestData.daySupplyInput!="" ? prescriptionTestData.daySupplyInput.trim() : getValue(selenium, "daysSupplyInput").trim();
			prescriptionTestData.quantity = prescriptionTestData.quantity!=null || prescriptionTestData.quantity!="" ? prescriptionTestData.quantity.trim() : getValue(selenium, "quantityInput").trim();
			prescriptionTestData.refills = prescriptionTestData.refills!=null || prescriptionTestData.refills!="" ? prescriptionTestData.refills.trim() : getValue(selenium, "refillsInput").trim();
			prescriptionTestData.comments = prescriptionTestData.comments!=null || prescriptionTestData.comments!="" ? prescriptionTestData.comments.trim() : getValue(selenium, "commentsInput").trim();
			prescriptionTestData.status = prescriptionTestData.status!=null || prescriptionTestData.status!="" ? prescriptionTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput").trim();
			prescriptionTestData.taskName =  prescriptionTestData.taskName!=null || prescriptionTestData.taskName!="" ? prescriptionTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput").trim();
			prescriptionTestData.sendTaskTo = prescriptionTestData.sendTaskTo!=null || prescriptionTestData.sendTaskTo!="" ? prescriptionTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput").trim();

			selenium.click("//div[@id='pharmacy']/div");
			waitForPageLoad(selenium);
			Assert.assertTrue(selectValueFromAjaxList(selenium,"pharmacyBox", prescriptionTestData.pharmacyName),"Could not enter Pharmacy Name; More Details" +prescriptionTestData.toString());
			click(selenium, "editProvider");
			waitForPageLoad(selenium);
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);

			Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "quantityTypeInput", prescriptionTestData.quantityTypeInput),"Could not select Quantity type input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_quantityInput", prescriptionTestData.quantity),"Could not enter the Quantity input; More Details" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details :" + prescriptionTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			if(!isElementPresent(selenium,"workStatusInput") || !selenium.isVisible("workStatusInput")){
				click(selenium, "addTask");
				waitForPageLoad(selenium);

			}
			Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button");
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + prescriptionTestData.toString());
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + prescriptionTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Date currentDate1 = new Date();

			int counter1 = 0;

			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + prescriptionTestData.toString());
			String recordID = "Prescription("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID, prescriptionTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
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
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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

	public  void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			counter++;
		}
	}

	@SuppressWarnings("deprecation")
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
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lastupdated")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date currDate = new Date();
			currDate.setHours(0);
			currDate.setMinutes(0);
			currDate.setSeconds(0);
			if(actualStartdate.toString().trim().toLowerCase(new java.util.Locale("en", "US")).equals(currDate.toString().trim().toLowerCase(new java.util.Locale("en", "US"))) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lastupdated,";
				return false;
			}
		}else{
			return true;
		}
	}	
}
package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateMedication extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Audit Logs for update Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib medicationTestData = new AuditLib();
		medicationTestData.workSheetName = "UpdateMedication";
		medicationTestData.testCaseId = "TC_UMD_001";		
		medicationTestData.fetchAuditTestData();
		verifyAuditLogForUpdateMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);    
	}

	/**
	 * @Function 	: verifyAuditLogForUpdateMedication
	 * @Description : Function to verify audit logs for update medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 15, 2010
	 */


	@SuppressWarnings("deprecation")
	public void verifyAuditLogForUpdateMedication(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib medicationTestData){

		Selenium selenium = null;
		String uniqueID = null;
		boolean isAuditResultVerified = false;
		String medicationCount = null;
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
			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details :" + medicationTestData.toString());
			medicationCount = getListCount(getText(selenium,"CurrentMedications"));
			if(!medicationCount.trim().equals(null) || !medicationCount.trim().equals("")){
				if (Integer.parseInt(medicationCount) == 0){
					medicationTestData = null;
					medicationTestData = new AuditLib();
					medicationTestData.workSheetName = "Medication";
					medicationTestData.testCaseId = "TC_MED_001";		
					medicationTestData.fetchAuditTestData();
					createMedication(selenium, medicationTestData);
					medicationCount = "1";
				}
			}
			medicationTestData = null;
			medicationTestData = new AuditLib();
			medicationTestData.workSheetName = "UpdateMedication";
			medicationTestData.testCaseId = "TC_UMD_001";		
			medicationTestData.fetchAuditTestData();
			click(selenium, "medications");
			waitForPageLoad(selenium);
			Date currentDate = new Date();
			if(selenium.isElementPresent("//div[@id='patientMedicationList']/table/tbody[1]/tr/td[1]")){
				uniqueID = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id").split("medication")[1];
				click(selenium,"//div[@id='patientMedicationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a");
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				medicationTestData.medicationName = getText(selenium,"medicationBoxLabel");
				medicationTestData.status = medicationTestData.status != null || medicationTestData.status != "" ? medicationTestData.status.trim() : getSelectedValue(selenium, "status");
				medicationTestData.startDate = medicationTestData.startDate != null || medicationTestData.startDate != "" ? medicationTestData.startDate.trim() : getValue(selenium, "startDateInput");
				medicationTestData.endDate = medicationTestData.endDate != null || medicationTestData.endDate != "" ? medicationTestData.endDate.trim() : getValue(selenium, "endDateInput");								
				medicationTestData.medicationNote = medicationTestData.medicationNote != null || medicationTestData.medicationNote != "" ? medicationTestData.medicationNote.trim() : getValue(selenium, "notesInput");
				medicationTestData.taskName = medicationTestData.taskName != null || medicationTestData.taskName != "" ? medicationTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
				medicationTestData.sendTaskTo = medicationTestData.sendTaskTo != null || medicationTestData.sendTaskTo != "" ? medicationTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
				medicationTestData.taskNotes = medicationTestData.taskNotes != null || medicationTestData.taskNotes != "" ? medicationTestData.taskNotes.trim() : getValue(selenium, "taskNotesInput");
				waitForElement(selenium, "medicationBoxBox", 7000);
				currentDate.setSeconds(currentDate.getSeconds()-10);
				selenium.type("medicationBoxBox"," " );
				Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details :" + medicationTestData.toString());
				if (medicationTestData.endDate != null && !medicationTestData.endDate.equals("")){
					Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details :" + medicationTestData.toString());
				}
				Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details :" + medicationTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", medicationTestData.status),"Could not select Medication Status; More Details :" + medicationTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				if(!selenium.isVisible("workStatusInput")){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details :" + medicationTestData.toString());
					waitForPageLoad(selenium);
				}
				Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details :" + medicationTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + medicationTestData.toString());
				if(!waitForElementToEnable(selenium, "saveButton")){
					Assert.assertFalse(selenium.getText("medicationBoxLabel").trim().equals("")|| selenium.getText("medicationBoxLabel").trim().equals(null),"Medication Name Not properly loaded; Please Check the Medication Name; More Details :" + medicationTestData.toString());
					Assert.fail("Save button is not enabled; More Details :" + medicationTestData.toString());
				}
				Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details :" + medicationTestData.toString());
				waitForPageLoad(selenium);
				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + medicationTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + medicationTestData.toString());
				Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture Medication Count after saving a Medication ; More Details :" + medicationTestData.toString());
				Assert.assertTrue(Integer.parseInt(getListCount( selenium.getText("CurrentMedications"))) == Integer.parseInt(medicationCount)-1,"The Medication is not Saved Correctly, Medication count has a change after updating an Medication; More Details :" + medicationTestData.toString());
			}

			Date currentDate1 = new Date(); 
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + medicationTestData.toString());
			String recordID = "Medication("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,medicationTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, medicationTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + medicationTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + medicationTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + medicationTestData.toString());
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

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value, AuditLib medicationTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(medicationTestData.startDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"startdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("enddate")){
			isValueChecked = true;
			Date actualEnddate = new Date(value.split(" ")[0].replace("-","/"));
			Date expectedEnddate = new Date(medicationTestData.endDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(medicationTestData.medicationNote)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
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

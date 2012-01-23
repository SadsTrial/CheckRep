package com.clinical.selenium.section.audit;

import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForDeleteMedication extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Verifying Audit logs for Delete Medication")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForDeleteMedication(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib medicationTestData = new AuditLib();
		medicationTestData.workSheetName = "DeleteMedication";
		medicationTestData.testCaseId = "TC_DMD_001";		
		medicationTestData.fetchAuditTestData();
		verifyAuditLogForDeleteMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForDeleteMedication
	 * @Description : Verify Audit Log For Delete Medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 22, 2010
	 */

	public void verifyAuditLogForDeleteMedication(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib medicationTestData){

		Selenium selenium = null;
		int recordCounter = 0;
		int counter = 1;
		String uniqueID = null;
		boolean isMedicationExisit = false;
		boolean isMedicationDeleted = false;
		boolean isAuditResultVerified = false;
		String medicationCount = "";

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Select the  Medication  and Delete                        //
			//--------------------------------------------------------------------//

			String idOfTheRecord = null;
			click(selenium,"medications");
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More details-" + medicationTestData.toString());
			medicationCount = getListCount(getText(selenium,"CurrentMedications"));
			if (Integer.parseInt(medicationCount)!= 0){
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isMedicationExisit = true;
						waitForPageLoad(selenium);
						idOfTheRecord =selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id"); 
						uniqueID = idOfTheRecord.split("medication")[1];
						click(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
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
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().matches("^Are you sure you want to delete this medication [\\s\\S]$")){
								isMedicationDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() +"; More details-" + medicationTestData.toString()); 
						}
						Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More details-" + medicationTestData.toString());
						isMedicationDeleted = true;
						counter = counter -1;
					}
					counter++;
				}
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-3: function call to create Medication if Medication is not available              //
			//-----------------------------------------------------------------------------------------//

			if (Integer.parseInt(medicationCount)== 0 || !isMedicationExisit){
				createMedication(selenium, medicationTestData);
			}

			Date currentDate = new Date();
			if(!isMedicationExisit && !isMedicationDeleted){
				click(selenium,"medications");
				waitForPageLoad(selenium);
				Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More details-" + medicationTestData.toString());
				medicationCount = getListCount(getText(selenium,"CurrentMedications"));
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					currentDate = new Date();
					if(getText(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isMedicationExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						uniqueID = idOfTheRecord.split("medication")[1];
						click(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
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
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button; More details" + medicationTestData.toString());
						waitForPageLoad(selenium);
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().matches("^Are you sure you want to delete this medication [\\s\\S]$")){
								isMedicationDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More details-" + medicationTestData.toString());
						}
						Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More details" + medicationTestData.toString());
						isMedicationDeleted = true;
						counter = counter -1;
					}
					counter++;
				}
			}
			
			Date currentDate1 = new Date();
			
			Assert.assertTrue(waitForElement(selenium, "CurrentMedications", 120000),"Could not capture Medication[Current] Count after Deleting a Medication; More details-" + medicationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentMedications"))),(Integer.parseInt(medicationCount)-recordCounter),"The Medication is not Deleted Correctly, Medication[Current] count has a change after Deleting an MedicationMore details-" + medicationTestData.toString());

			//-----------------------------------------------------------------------------//
			//  Step-4:  Verify the Deleted details are logged properly  in the Audit page //
			//-----------------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			counter = 1;
			int counter1 =0;
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Deleted Record; more Details :-" + medicationTestData.toString());
			String recordID = "Medication("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Delete", recordID,  medicationTestData).split("\n");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" Old value was ")){
					String ColName =auditValue[counter1].split(" was deleted. ")[0];
					String value = 	auditValue[counter1].split(" Old value was ")[1];
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
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){
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

	public void deactiveMedication(Selenium selenium, AuditLib medicationTestData){
		int counter = 1;
		while(selenium.isElementPresent( "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]")){
			String content = null;
			content = selenium.getText("//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]");
			content = content != null ? content.trim() : "" ;
			if(content!= null && !content.equals("")){		
				if(content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(medicationTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					click(selenium, "//div[@id='patientMedicationList']/table/tbody[1]/tr["+counter+"]/td[1]/a");
					waitForPageLoad(selenium);
					select(selenium, "itemStatusInput", "Inactive");
					if(click(selenium, "saveButton")){
						break;
					}
				}
			}
			counter++;
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
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(medicationTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else{
			return true;
		}
	}
}
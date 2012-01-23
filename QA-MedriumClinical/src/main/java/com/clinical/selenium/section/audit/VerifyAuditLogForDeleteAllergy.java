package com.clinical.selenium.section.audit;

import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForDeleteAllergy extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying audit logs for delete Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForDeleteAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib allergyTestData = new AuditLib();
		allergyTestData.workSheetName = "DeleteAllergy";
		allergyTestData.testCaseId = "TC_DAL_001";
		allergyTestData.fetchAuditTestData();
		verifyAuditLogForDeleteAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForDeleteAllergy
	 * @Description : Function to verify audit logs for delete allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: AspireQA
	 * @Created on 	: Nov 22, 2010
	 */


	@SuppressWarnings("deprecation")
	public void verifyAuditLogForDeleteAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib allergyTestData){

		Selenium selenium = null;
		int counter = 1;
		int recordCounter=0;
		boolean isAllergyExisit = false;
		boolean isAllergyDeleted = false;
		boolean isAuditResultVerified = false;
		String allergyCount = null;
		String content = null;
		String uniqueID = null;


		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Select the  Allergy and Delete                            //
			//--------------------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies Count; More details :" + allergyTestData.toString());
			allergyCount = getListCount(selenium.getText("CurrentAllergies"));
			String idOfTheRecord = null;	
			if(!allergyCount.equals("0")){
				counter = 1;
				while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
					content = null;				
					content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
					content = content != null ? content.trim() : "";
					if(content != null && !content.equals("")){
						if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US")))){
							isAllergyExisit = true;
							idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
							uniqueID = idOfTheRecord.split("allergy")[1];
							click(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
							waitForPageLoad(selenium);
							click(selenium, "actionButton");
							click(selenium, "edit"+uniqueID);
							waitForPageLoad(selenium);

							allergyTestData.allergyName = getText(selenium, "allergyBoxLabel");
							allergyTestData.eventType = getText(selenium, "adverseeventtypeInput");
							allergyTestData.severity = getText(selenium, "severityInput");
							allergyTestData.status = allergyTestData.status != null && allergyTestData.status != "" ? allergyTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput");
							allergyTestData.startDate = allergyTestData.startDate != null && allergyTestData.startDate != "" ? allergyTestData.startDate.trim() : getValue(selenium,"startdateInput");
							allergyTestData.endDate = allergyTestData.endDate != null && allergyTestData.endDate != "" ? allergyTestData.endDate.trim() : getValue(selenium,"enddateInput");
							allergyTestData.allergyNotes = allergyTestData.allergyNotes != null && allergyTestData.allergyNotes != "" ? allergyTestData.allergyNotes.trim() : getValue(selenium,"notesInput");
							allergyTestData.taskName = allergyTestData.taskName != null && allergyTestData.taskName != "" ? allergyTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
							allergyTestData.sendTaskTo = allergyTestData.sendTaskTo != null && allergyTestData.sendTaskTo != "" ? allergyTestData.sendTaskTo.trim() : selenium.getSelectedValue("taskUsersInput");
							allergyTestData.taskNotes = allergyTestData.taskNotes != null && allergyTestData.taskNotes != "" ? allergyTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");
							Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
							if(selenium.isConfirmationPresent())
								if(selenium.getConfirmation().matches("^Are you sure you want to delete this allergy [\\s\\S]$")){
									isAllergyDeleted = true;
								}
							recordCounter ++;
							waitForPageLoad(selenium);
							if(selenium.isAlertPresent()){
								Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More details :" + allergyTestData.toString() );
							}
							Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More details" + allergyTestData.toString());
							isAllergyDeleted = true;
							counter = counter -1;
						}						
					}
					counter++;
				}
			}

			//--------------------------------------------------------------------------------------------//
			//  Step-3: function call to create allergy if allergy is not available                       //
			//--------------------------------------------------------------------------------------------//

			if(allergyCount.equals("0") || !isAllergyExisit ){	
				createAllergy(selenium, allergyTestData);
			}

			if(!isAllergyExisit && !isAllergyDeleted){
				click(selenium,"allergies");
				waitForPageLoad(selenium);	
				Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies Count; More details :" + allergyTestData.toString());
				allergyCount = getListCount(selenium.getText("CurrentAllergies"));
				recordCounter =0;
				counter = 1;

				while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
					content = null;				
					content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
					content = content != null ? content.trim() : "";
					if(content != null && !content.equals("")){
						if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US"))) ){
							isAllergyExisit = true;
							idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
							uniqueID = idOfTheRecord.split("allergy")[1];
							click(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
							waitForPageLoad(selenium);
							click(selenium, "actionButton");
							click(selenium, "edit"+uniqueID);
							waitForPageLoad(selenium);

							allergyTestData.allergyName = getText(selenium, "allergyBoxLabel");
							allergyTestData.eventType = getText(selenium, "adverseeventtypeInput");
							allergyTestData.severity = getText(selenium, "severityInput");
							allergyTestData.status = allergyTestData.status != null && allergyTestData.status != "" ? allergyTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput");
							allergyTestData.startDate = allergyTestData.startDate != null && allergyTestData.startDate != "" ? allergyTestData.startDate.trim() : getValue(selenium,"startdateInput");
							allergyTestData.endDate = allergyTestData.endDate != null && allergyTestData.endDate != "" ? allergyTestData.endDate.trim() : getValue(selenium,"enddateInput");
							allergyTestData.allergyNotes = allergyTestData.allergyNotes != null && allergyTestData.allergyNotes != "" ? allergyTestData.allergyNotes.trim() : getValue(selenium,"notesInput");
							allergyTestData.taskName = allergyTestData.taskName != null && allergyTestData.taskName != "" ? allergyTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
							allergyTestData.sendTaskTo = allergyTestData.sendTaskTo != null && allergyTestData.sendTaskTo != "" ? allergyTestData.sendTaskTo.trim() : selenium.getSelectedValue("taskUsersInput");
							allergyTestData.taskNotes = allergyTestData.taskNotes != null && allergyTestData.taskNotes != "" ? allergyTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

							Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button; More details" + allergyTestData.toString());
							if(selenium.isConfirmationPresent())
								if(selenium.getConfirmation().matches("^Are you sure you want to delete this allergy [\\s\\S]$")){
									isAllergyDeleted = true;
								}
							recordCounter ++;
							waitForPageLoad(selenium);
							if(selenium.isAlertPresent()){
								Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() +"; More details :" + allergyTestData.toString());
							}
							Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") +"; More details :" + allergyTestData.toString());
							isAllergyDeleted = true;
							counter --;
						}						
					}
					counter++;
				}
			}
			Date currentDate1 = new Date();
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture Allergy Count after deleting a Allergy; More details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentAllergies"))) == (Integer.parseInt(allergyCount)-recordCounter),"The Allergy is not Deleted Correctly, Allergy count has a change after Deleting an Allergy; More details: " + allergyTestData.toString());

			//-----------------------------------------------------------------------------//
			//  Step-4:  Verifying the Deleted details are logged in the Audit page        //
			//-----------------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			counter = 1;
			int counter1 =0;
			
			Date currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
            Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Record; more Details :-" + allergyTestData.toString());
			String recordID = "Allergy("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Delete", recordID, allergyTestData).split("\n");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" Old value ")){
					String ColName =auditValue[counter1].split(" was deleted. ")[0];
					String value = 	auditValue[counter1].split(" Old value was ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, allergyTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records(0) displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			org.testng.Assert.fail("some error has occured during execution;More details: " + allergyTestData.toString());
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
	public boolean verifyAuditValues(String ColName, String value, AuditLib allergyTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(allergyTestData.startDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"StartDate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("enddate")){
			isValueChecked = true;
			Date actualEnddate = new Date(value.replace("-","/"));
			Date expectedEnddate = new Date(allergyTestData.endDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(allergyTestData.allergyNotes)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else{
			return true;
		}
	}
}
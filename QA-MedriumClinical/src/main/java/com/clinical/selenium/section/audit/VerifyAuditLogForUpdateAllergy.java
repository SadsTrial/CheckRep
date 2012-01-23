package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateAllergy extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Audit logs for Update Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForupdateAllergy(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib allergyTestData = new AuditLib();
		allergyTestData.workSheetName = "UpdateAllergy";
		allergyTestData.testCaseId = "TC_UAL_001";		
		allergyTestData.fetchAuditTestData();
		verifyAuditLogsForupdateAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForupdateAllergy
	 * @Description : Function to verify Audit Logs for update  Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 11, 2010
	 */

	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForupdateAllergy(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib allergyTestData){

		Selenium selenium = null;
		String uniqueID = null;
		boolean isEndDatePresented = false;
		boolean isAuditResultVerified = false;
		String allergyCount = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Select the first Allergy and store the details            //
			//--------------------------------------------------------------------//

			click(selenium, "allergies");
			waitForPageLoad(selenium);						
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies Count; More Details :" + allergyTestData.toString());
			allergyCount = getListCount(selenium.getText("CurrentAllergies"));
			if (!allergyTestData.endDate.equals ("") || !allergyTestData.endDate.equals(null)){
				isEndDatePresented = true;
			}
			if(allergyCount.equals("0")){	
				allergyTestData = null;
				allergyTestData = new AuditLib();
				allergyTestData.workSheetName = "Allergy";
				allergyTestData.testCaseId = "TC_ALL_001";
				allergyTestData.fetchAuditTestData();

				//--------------------------------------------------------------------//
				//  Step-3: Create New Allergy if Allergy is not available            // 
				//--------------------------------------------------------------------//

				createAllergy(selenium, allergyTestData);
				allergyTestData = null;
				allergyTestData = new AuditLib();
				allergyTestData.workSheetName = "UpdateAllergy";
				allergyTestData.testCaseId = "TC_UAL_001";		
				allergyTestData.fetchAuditTestData();
			}
			waitForPageLoad(selenium);
			click(selenium, "allergies");
			waitForPageLoad(selenium);						
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies Count; More Details :" + allergyTestData.toString());
			allergyCount = getListCount(selenium.getText("CurrentAllergies"));
			String idOfTheRecord = null;
			click(selenium, "allergies");
			waitForPageLoad(selenium);						
			Assert.assertTrue(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr[1]/td[1]"),"No Records found to update an Allergy; More Details :" + allergyTestData.toString());
			idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
			uniqueID = idOfTheRecord.split("allergy")[1];
			click(selenium, "//div[@id='patientAllergyList']/table/tbody[1]/tr[1]/td[1]/div/strong/a");
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);
			allergyTestData.allergyName = getText(selenium, "allergyBoxLabel");
			allergyTestData.eventType = getText(selenium, "adverseeventtypeInput");
			allergyTestData.severity = getText(selenium, "severityInput");
			allergyTestData.status = allergyTestData.status != null && allergyTestData.status != "" ? allergyTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput");
			allergyTestData.startDate = allergyTestData.startDate != null && allergyTestData.startDate != "" ? allergyTestData.startDate.trim() : getValue(selenium, "startdateInput");
			allergyTestData.endDate = allergyTestData.endDate != null && allergyTestData.endDate != "" ? allergyTestData.endDate.trim() : getValue(selenium, "enddateInput");
			allergyTestData.allergyNotes = allergyTestData.allergyNotes != null && allergyTestData.allergyNotes != "" ? allergyTestData.allergyNotes.trim() : getValue(selenium, "notesInput");
			allergyTestData.taskName = allergyTestData.taskName != null && allergyTestData.taskName != "" ? allergyTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
			allergyTestData.sendTaskTo = allergyTestData.sendTaskTo != null && allergyTestData.sendTaskTo != "" ? allergyTestData.sendTaskTo.trim() : selenium.getSelectedValue("taskUsersInput");
			allergyTestData.taskNotes = allergyTestData.taskNotes != null && allergyTestData.taskNotes != "" ? allergyTestData.taskNotes.trim() : getValue(selenium, "taskNotesInput");

			//--------------------------------------------------------------------//
			//  Step-4: Update the Allergy with New Details and Save              //
			//--------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);

			Assert.assertTrue(enterDate(selenium, "startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details :" + allergyTestData.toString());
			if(isEndDatePresented){
				Assert.assertTrue(enterDate(selenium, "enddateInput", allergyTestData.endDate),"Could not enter End Date; More Details :" + allergyTestData.toString());
			}
			Assert.assertTrue(select(selenium, "itemStatusInput", allergyTestData.status),"Could not select Allergy Status; More Details :" + allergyTestData.toString());
			Assert.assertTrue(type(selenium, "notesInput", allergyTestData.allergyNotes),"Could not enter Allergy Notes; More Details :" + allergyTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			if(!isElementPresent(selenium,"workStatusInput") || !selenium.isVisible("workStatusInput") ){
				Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ");
			}
			Assert.assertTrue(select(selenium, "workStatusInput", allergyTestData.taskName),"Could not select Work Status; More Details :" + allergyTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", allergyTestData.sendTaskTo),"Could not select Send To Task; More Details :" + allergyTestData.toString());
			Assert.assertTrue(type(selenium, "taskNotesInput", allergyTestData.taskNotes),"Could not enter Task Notes; More Details :" + allergyTestData.toString());
			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + allergyTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent() || (selenium.isElementPresent("//p")))
			{
				Assert.assertTrue(selenium.isAlertPresent(),"An unexpected alert has occured : " + selenium.getAlert().trim() + "; More Details :" + allergyTestData.toString());
				Assert.assertTrue( (selenium.isElementPresent("//p")),"An unexpected Alert Occured - " + selenium.getText("//p").trim());
			}
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture Allergy Count after saving a Allergy; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentAllergies"))) == Integer.parseInt(allergyCount),"The Allergy is not Saved Correctly, Allergy count has a change after updating an Allergy; More Details :" + allergyTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//
			Date currentDate1 = new Date(); 
			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + allergyTestData.toString());
			
			String recordID = "Allergy("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID, allergyTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, allergyTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + allergyTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + allergyTestData.toString());
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
				unMatchedFields = unMatchedFields+"startdate,";
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
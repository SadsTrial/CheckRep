package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateCondition extends AbstractAuditTest{

	boolean isValueChecked = false;
	String conditionCode = null;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for verifying Audit logs for Update Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAudiltLogsForupdateCondition(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib conditionTestData = new AuditLib();
		conditionTestData.workSheetName = "UpdateCondition";
		conditionTestData.testCaseId = "TC_UCN_001";
		conditionTestData.fetchAuditTestData();
		verifyAudiltLogsForUpdateCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: verifyAudiltLogsForupdateCondition
	 * @Description : Function to verify audit logs for update Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 11, 2010
	 */	

	@SuppressWarnings("deprecation")
	public void verifyAudiltLogsForUpdateCondition(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib conditionTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		String conditionCount = null;
		String uniqueID = null;
		String tmpCondition = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);

			click(selenium, "conditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentConditions", 120000),"Could not capture existing Allergies Count; More Details :" + conditionTestData.toString());
			conditionCount = getListCount(selenium.getText("CurrentConditions"));

			//--------------------------------------------------------------------//
			//  Step-2: Add the New Condition if no condition is available        //
			//--------------------------------------------------------------------//

			if(!conditionCount.trim().equals(null) || !conditionCount.trim().equals("")){
				if (Integer.parseInt(conditionCount)== 0){
					conditionTestData = null;
					conditionTestData = new AuditLib();
					conditionTestData.workSheetName = "Condition";
					conditionTestData.testCaseId = "TC_CON_001";		
					conditionTestData.fetchAuditTestData();
					createCondition(selenium, conditionTestData);
					conditionCount = "1";
				}
			}
			conditionTestData = null;
			conditionTestData = new AuditLib();
			conditionTestData.workSheetName = "UpdateCondition";
			conditionTestData.testCaseId = "TC_UCN_001";		
			conditionTestData.fetchAuditTestData();

			//--------------------------------------------------------------------//
			//  Step-3: Update the Condition with New Details and Save            //
			//--------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Date currentDate = new Date();
			String idOfTheRecord = null;
			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			if(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){
				idOfTheRecord = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord .split("condition")[1];
				waitForPageLoad(selenium);
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);
				tmpCondition = selenium.getText("conditionsBox");
				conditionTestData.conditionStartDate = conditionTestData.conditionStartDate != null && conditionTestData.conditionStartDate != "" ? conditionTestData.conditionStartDate.trim() : getValue(selenium, "startdateInput");
				conditionTestData.conditionEndDate = conditionTestData.conditionEndDate != null && conditionTestData.conditionEndDate != "" ? conditionTestData.conditionEndDate.trim() : getValue(selenium, "enddateInput");
				conditionTestData.conditionNote = conditionTestData.conditionNote != null && conditionTestData.conditionNote != "" ? conditionTestData.conditionNote.trim() : getValue(selenium, "notesInput");
				conditionTestData.taskName = conditionTestData.taskName != null && conditionTestData.taskName != "" ? conditionTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
				conditionTestData.sendTaskTo = conditionTestData.sendTaskTo != null && conditionTestData.sendTaskTo != "" ? conditionTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
				conditionTestData.taskNotes = conditionTestData.taskNotes != null && conditionTestData.taskNotes != "" ? conditionTestData.taskNotes.trim() : getValue(selenium, "taskNotesInput");

				currentDate.setSeconds(currentDate.getSeconds()-10);
				Assert.assertTrue(enterDate(selenium, "startdateInput",conditionTestData.conditionStartDate),"Could not enter Date; More Details :" + conditionTestData.toString());
				Assert.assertTrue(enterDate(selenium, "enddateInput",conditionTestData.conditionEndDate),"Could not enter Date; More Details :" + conditionTestData.toString());
				Assert.assertTrue(type(selenium, "notesInput",conditionTestData.conditionNote),"Could not enter Condition Note; More Details :" + conditionTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy Status; More Details :" + conditionTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				if(!isElementPresent(selenium,"workStatusInput") || !selenium.isVisible("workStatusInput") ){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details :" + conditionTestData.toString());
				}
				Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details :" + conditionTestData.toString());
				Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details :" + conditionTestData.toString());
				Assert.assertTrue(type(selenium, "taskNotesInput",conditionTestData.taskNotes),"Could not enter Task Notes; More Details :" + conditionTestData.toString());

				click(selenium, "validateButton");
				waitForPageLoad(selenium);

				if(selenium.isAlertPresent() || (selenium.isElementPresent("//p")))
				{
					Assert.assertTrue(selenium.isAlertPresent(),"An unexpected alert has occured : " + selenium.getAlert().trim() + "; More Details :" + conditionTestData.toString());
					Assert.assertTrue( (selenium.isElementPresent("//p")),"An unexpected Alert Occured - " + selenium.getText("//p").trim() + "; More Details :" + conditionTestData.toString());
				}
			}
			Assert.assertTrue(waitForValue(selenium, "CurrentConditions", 120000),"Could not capture condition Count after saving a condition; More Details :" + conditionTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentConditions"))) == (Integer.parseInt(conditionCount)-1),"The condition is not Updated Successfully, condition count has a change after Updating condition; More Details :" + conditionTestData.toString());
			if(conditionTestData.condition.contains("(")){
				conditionTestData.condition = conditionTestData.condition.substring(0,conditionTestData.condition.indexOf("(")-1 ) ;   
			}
			Date currentDate1 = new Date();

			//----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			tmpCondition = tmpCondition.split("\\(")[2];
			conditionCode = tmpCondition.substring(0,(tmpCondition.length()-1));
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + conditionTestData.toString());
			String recordID = "Condition("+uniqueID+")";
			
			int counter1 = 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID, conditionTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, conditionTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + conditionTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + conditionTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			org.testng.Assert.fail("some error has occured during execution; More Details :" + conditionTestData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {				
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
	public boolean verifyAuditValues(String ColName, String value, AuditLib conditionTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(conditionTestData.conditionStartDate);
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
			Date expectedEnddate = new Date(conditionTestData.conditionEndDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(conditionTestData.conditionNote)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("conditioncode")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(conditionCode.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"conditioncode,";
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

package com.clinical.selenium.section.charts.condition;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateCondition extends AbstractChartsTest{

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for creating a New Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateExistingCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "UpdateCondition";
		conditionTestData.testCaseId = "TC_UCN_001";
		conditionTestData.fetchChartsTestData();
		updateExistingCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: updateExistingCondition
	 * @Description : Function to update an Existing Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 29, 2010
	 */	
	public void updateExistingCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib conditionTestData){

		Selenium selenium = null;
		String allConditionCount = null;
		String activeConditionCount = null;
		String uniqueID = null;
		boolean isRecordFoundInActivity = false;
		boolean isRecordFoundInConditionsActivePage = false;
		boolean isRecordFoundInConditionsAllPage = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);

			//----------------------------------------------------------------------//
			//  Step-2: Check if condition already exist to update                  //
			//----------------------------------------------------------------------//

			click(selenium,"conditions");
							
			waitForPageLoad(selenium);
			click(selenium,"AllConditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentConditions", 120000),"Could not capture existing Condition Count; More Details :" + conditionTestData.toString());
			activeConditionCount = getListCount(selenium.getText("CurrentConditions"));

			//--------------------------------------------------------------------------------------//
			//  Step-3: Creates a New Condition if no existing condition available                  //
			//--------------------------------------------------------------------------------------//

			if(!activeConditionCount.trim().equals(null) || !activeConditionCount.trim().equals("")){
				if (Integer.parseInt(activeConditionCount)== 0){

					conditionTestData = null;
					conditionTestData = new ChartsLib();
					conditionTestData.workSheetName = "Condition";
					conditionTestData.testCaseId = "TC_CON_001";		
					conditionTestData.fetchChartsTestData();
					click(selenium,"CurrentConditions");
									
					createCondition(selenium, conditionTestData);
					activeConditionCount = "1";

				}
			}

			conditionTestData = null;
			conditionTestData = new ChartsLib();
			conditionTestData.workSheetName = "UpdateCondition";
			conditionTestData.testCaseId = "TC_UCN_001";		
			conditionTestData.fetchChartsTestData();

			//--------------------------------------------------------------------//
			//  Step-4: Update the Condition with New Details and Save            //
			//--------------------------------------------------------------------//

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllConditions", 120000),"Could not capture existing All Condition  Count; More Details :" + conditionTestData.toString());
			allConditionCount = getListCount(selenium.getText("AllConditions"));		

			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			String idOfTheRecord = null;
			if(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr[1]/td[1]/div")){
				idOfTheRecord = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord .split("condition")[1];
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
							
				click(selenium, "actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				conditionTestData.conditionStartDate = conditionTestData.conditionStartDate != null && conditionTestData.conditionStartDate != "" ? conditionTestData.conditionStartDate.trim() : getValue(selenium,"startdateInput");
				conditionTestData.conditionEndDate = conditionTestData.conditionEndDate != null && conditionTestData.conditionEndDate != "" ? conditionTestData.conditionEndDate.trim() : getValue(selenium,"enddateInput");
				conditionTestData.conditionNote = conditionTestData.conditionNote != null && conditionTestData.conditionNote != "" ? conditionTestData.conditionNote.trim() : getValue(selenium,"notesInput");
				conditionTestData.taskName = conditionTestData.taskName != null && conditionTestData.taskName != "" ? conditionTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput");
				conditionTestData.sendTaskTo = conditionTestData.sendTaskTo != null && conditionTestData.sendTaskTo != "" ? conditionTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput");
				conditionTestData.taskNotes = conditionTestData.taskNotes != null && conditionTestData.taskNotes != "" ? conditionTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

				Assert.assertTrue(enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate),"Could not enter the Condition Start Date; More Details :" + conditionTestData.toString());
				Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End Date; More Details :" + conditionTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition Note; More Details :" + conditionTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy Status; More Details :" + conditionTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				if(!isElementPresent(selenium,"workStatusInput")){
					click(selenium, "addTask");
					waitForPageLoad(selenium);
				}

				Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details :" + conditionTestData.toString());;
				Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details :" + conditionTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", conditionTestData.taskNotes),"Could not enter Task Notes; More Details :" + conditionTestData.toString());
				Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details :" + conditionTestData.toString());
				waitForPageLoad(selenium);			

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + conditionTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + conditionTestData.toString());
			}

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllConditions", 120000),"Could not capture condition[All] Count after saving a condition");
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllConditions"))) == Integer.parseInt(allConditionCount),"The condition[All] is not Updated Successfully, condition[All] count has a change after Updating condition; More Details :" + conditionTestData.toString());

			Assert.assertTrue(waitForValue(selenium, "CurrentConditions", 120000),"Could not capture condition Count(Active) after saving a condition");
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentConditions"))) == (Integer.parseInt(activeConditionCount)-1),"The condition is not Updated Successfully, condition(Active) count has a change after Updating condition; More Details :" + conditionTestData.toString());

			//--------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Condition Current section  //
			//--------------------------------------------------------------------------------------//
			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + conditionTestData.toString());

			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			if(selenium.isElementPresent(idOfTheRecord)){
				click(selenium,idOfTheRecord);
								
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);
				isRecordFoundInConditionsActivePage=verifyStoredValues( selenium, conditionTestData);
				click(selenium,"cancelButton");
			}
			
			//--------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in Condition All section      //
			//--------------------------------------------------------------------------------------//

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);

			click(selenium,idOfTheRecord);
							
			waitForPageLoad(selenium);
			isRecordFoundInConditionsAllPage = verifyStoredValuesForInactiveConditions( selenium, conditionTestData);

			//--------------------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in Condition Activity         //
			//--------------------------------------------------------------------------------------//

			click(selenium,"activity");
						
			waitForPageLoad(selenium);
			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium,idOfTheRecord);
							
			waitForPageLoad(selenium);
			isRecordFoundInActivity=verifyStoredValuesForInactiveConditions( selenium, conditionTestData);

			Assert.assertFalse(isRecordFoundInConditionsActivePage,"Updated Condition[InActive] record found in patient condition Active section; The Record updation failed in patient condition Active section; Detailed data:" + conditionTestData.toString());
			Assert.assertTrue(isRecordFoundInConditionsAllPage,"Updated Condition[InActive] found in patient condition All section; The Record is not Updated successfully in patient condition All section; Detailed data:" + conditionTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Updated Condition[InActive]  record  not found in Patient Activity section; Detailed data:" + conditionTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + conditionTestData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {				
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib conditionTestData){

		if(!selenium.getText("//div[@id='conditionsBox']/div").trim().contains(conditionTestData.condition)){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().contains(conditionTestData.conditionStartDate)){
			return false;
		}

		if(!getValue(selenium, "enddateInput").trim().contains(conditionTestData.conditionEndDate)){
			return false;
		}

		if(!getValue(selenium, "notesInput").trim().equalsIgnoreCase(conditionTestData.conditionNote.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(conditionTestData.taskName.trim())){
			return false;
		}

		if(!getValue(selenium, "taskNotesInput").trim().equalsIgnoreCase(conditionTestData.taskNotes.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "taskUsersInput").trim().equalsIgnoreCase(conditionTestData.sendTaskTo.trim())){
			return false;
		}

		return true;
	}

	public boolean verifyStoredValuesForInactiveConditions(Selenium selenium, ChartsLib conditionTestData){

		if(!getText(selenium, "startdateInput").trim().contains(conditionTestData.conditionStartDate)){
			return false;
		}

		if(!getText(selenium, "enddateInput").trim().contains(conditionTestData.conditionEndDate)){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().contains(conditionTestData.conditionNote.trim())){
			return false;
		}


		if(!getText(selenium, "taskUsers").trim().equalsIgnoreCase(conditionTestData.sendTaskTo.trim())){
			return false;
		}

		if(!getText(selenium, "taskNotes").trim().equalsIgnoreCase(conditionTestData.taskNotes.trim())){
			return false;
		}
		return true;
	}
}

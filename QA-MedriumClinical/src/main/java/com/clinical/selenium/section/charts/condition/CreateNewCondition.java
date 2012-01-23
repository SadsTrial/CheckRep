package com.clinical.selenium.section.charts.condition;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewCondition extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for creating a New Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "TC_CON_001";
		conditionTestData.fetchChartsTestData();
		createNewCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for creating a In active Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewInAciveCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "TC_CON_002";
		conditionTestData.fetchChartsTestData();
		createNewCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: createNewCondition
	 * @Description : Function to create a New Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 16, 2010
	 */	
	public void createNewCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib conditionTestData){

		Selenium selenium = null;
		String content = null;
		int counter = 1;
		boolean isRecordFoundInActivity = false;
		boolean isRecordFoundInConditionsActivePage = false;
		boolean isRecordFoundInConditionsAllPage = false;
		boolean isInactiveCondition = false;
		String uniqueID = null;

		if(conditionTestData.status.trim().equalsIgnoreCase("inactive")){
			isInactiveCondition = true;
		}

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);

			searchPatient(selenium,conditionTestData.patientID);

			click(selenium,"conditions");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-2:  Removing existing Conditions with same name              //
			//--------------------------------------------------------------------//

			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "" ;
				if(content!=null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.toLowerCase(new java.util.Locale("en", "US")))){
						uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						click(selenium, "addTask");
						select(selenium, "itemStatusInput", "Inactive");
						click(selenium,"validateButton");
						waitForPageLoad(selenium);
					}						
				}
				counter++;
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Click Add New Condition and enter details                //
			//--------------------------------------------------------------------//

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientConditionListMoreLink") && selenium.isVisible("patientConditionListMoreLink")){
				selenium.click("patientConditionListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "condition"); 	
			createCondition(selenium, conditionTestData);
			click(selenium,"AllConditions");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientConditionListMoreLink") && selenium.isVisible("patientConditionListMoreLink")){
				selenium.click("patientConditionListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "condition");	
			secondList.removeAll(firstList);

			String idOfTheNewlyAddedRecord =  "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ conditionTestData.toString());
			}
			
			//--------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Condition Current section  //
			//--------------------------------------------------------------------------------------//	
			
			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			if(!isInactiveCondition){
				while(selenium.isElementPresent("patientConditionListMoreLink") && selenium.isVisible("patientConditionListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					selenium.click("patientConditionListMoreLink");
					waitForPageLoad(selenium);
				}
				Assert.assertTrue(isElementPresent(selenium, idOfTheNewlyAddedRecord),"Newly added Active Condition is not present in CurrentCondition section");
				click(selenium,idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				click(selenium, "actionButton");

				click(selenium, "edit"+idOfTheNewlyAddedRecord.split("condition")[1]);
				waitForPageLoad(selenium);
				
				isRecordFoundInConditionsActivePage=verifyStoredValues( selenium, conditionTestData);
				click(selenium,"cancelButton");

			}else{
				Assert.assertFalse(isElementPresent(selenium, idOfTheNewlyAddedRecord),"Newly added Inactive Condition is present in CurrentCondition section");
			}

			//--------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Condition All section      //
			//--------------------------------------------------------------------------------------//		

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientConditionListMoreLink") && selenium.isVisible("patientConditionListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				selenium.click("patientConditionListMoreLink");
				waitForPageLoad(selenium);
			}
			Assert.assertTrue(isElementPresent(selenium, idOfTheNewlyAddedRecord),"Newly added Condition is not present in AllCondition section");
			click(selenium,idOfTheNewlyAddedRecord);

			waitForPageLoad(selenium);
			isRecordFoundInConditionsAllPage=verifyStoredValuesForInactiveConditions( selenium, conditionTestData);

			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//		

			click(selenium,"activity");

			waitForPageLoad(selenium);
			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);
			Assert.assertTrue(isElementPresent(selenium, idOfTheNewlyAddedRecord),"Newly added Condition is not present in Activity section");
			click(selenium,idOfTheNewlyAddedRecord);

			waitForPageLoad(selenium);
			isRecordFoundInActivity=verifyStoredValuesForInactiveConditions( selenium, conditionTestData);
			if(!isInactiveCondition){
				Assert.assertTrue(isRecordFoundInConditionsActivePage,"Active Condition record is not found in Active Condition Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
				Assert.assertTrue(isRecordFoundInConditionsAllPage,"Active Condition record is not found in All Condition Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Active Condition record is not found in Activity Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
			}else{
				Assert.assertFalse(isRecordFoundInConditionsActivePage,"In Active Condition record is found in Active Condition Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
				Assert.assertTrue(isRecordFoundInConditionsAllPage,"In Active Condition record is not found in All Condition Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"In Active Condition record is not found in Activity Page; Condition Creation Failed; Detailed data:" + conditionTestData.toString());
			}

		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*) Detailed data:" + conditionTestData.toString());
			e.printStackTrace();
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
	public boolean verifyStoredValues(Selenium selenium, ChartsLib conditionTestData){

		if(!getText(selenium, "conditionsBox").trim().contains(conditionTestData.condition.trim())){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().contains(conditionTestData.conditionStartDate.trim())){
			return false;
		}

		if(!getValue(selenium, "enddateInput").trim().contains(conditionTestData.conditionEndDate.trim())){
			return false;
		}

		if(!getValue(selenium, "notesInput").trim().contains(conditionTestData.conditionNote.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(conditionTestData.taskName.trim())){
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

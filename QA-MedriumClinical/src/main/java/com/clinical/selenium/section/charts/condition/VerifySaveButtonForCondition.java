package com.clinical.selenium.section.charts.condition;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForCondition extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for verifying the save button and data for Un saved Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "VerifySaveButtonForCondition";
		conditionTestData.testCaseId = "TC_CON_001";
		conditionTestData.fetchChartsTestData();
		verifySaveButtonForUnSavedCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: createNewCondition
	 * @Description : Function to create a New Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 25, 2011
	 */	
	public void verifySaveButtonForUnSavedCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib conditionTestData){

		Selenium selenium = null;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);
			
			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Condition and enter details                //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details" + conditionTestData.toString());
						
			waitForPageLoad(selenium);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"conditionsBoxBox", conditionTestData.condition),"Could not enter the Condition; More Details" + conditionTestData.toString());
						
			Assert.assertTrue(enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate),"Could not enter the Condition Start Date; More Details" + conditionTestData.toString());

			Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End Date; More Details" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition Note; More Details" + conditionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy Status; More Details" + conditionTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + conditionTestData.toString());
						
			Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details" + conditionTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", conditionTestData.taskNotes),"Could not enter Task Notes; More Details" + conditionTestData.toString());

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			
			Assert.assertEquals(selenium.getAttribute("conditionsAdd@src").trim(),"images/plus-icon-on.png","Condition Add(+)  button is OFF and not backgrounded by Orange; More Details" + conditionTestData.toString());
			
			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details" + conditionTestData.toString());
						
			waitForPageLoad(selenium);
			
			verifyStoredValues(selenium, conditionTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" + conditionTestData.toString());
			
		   
		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + conditionTestData.toString());	
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
	public void verifyStoredValues(Selenium selenium, ChartsLib conditionTestData){

		Assert.assertTrue(getText(selenium, "conditionsBox").trim().contains(conditionTestData.condition.trim()),"Un saved Condition name is not available; It is modified; More Details" + conditionTestData.toString());
		Assert.assertTrue(getValue(selenium, "startdateInput").trim().contains(conditionTestData.conditionStartDate.trim()),"Un saved Start Date value is not available; It is modified; More Details" + conditionTestData.toString());
		Assert.assertTrue(getValue(selenium, "enddateInput").trim().contains(conditionTestData.conditionEndDate.trim()),"Un saved End Date value is not available; It is modified; More Details" + conditionTestData.toString());
		Assert.assertTrue(getValue(selenium, "notesInput").trim().contains(conditionTestData.conditionNote.trim()),"Un saved Notes Input value is not available; It is modified; More Details" + conditionTestData.toString());
		Assert.assertTrue(getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(conditionTestData.taskName.trim()),"Un saved Work Status value is not available; It is modified; More Details" + conditionTestData.toString());
		
	}


}

package com.clinical.selenium.section.charts.condition;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateDuplicateCondition extends AbstractChartsTest {


	/**
	 * @Function 	: createLog
	 * @Description : This method is used for creating a log file
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 20, 2010
	 */


	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for verifying the Alert for Duplicate Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createDuplicateCondition(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "CreateDuplicateCondition";
		conditionTestData.testCaseId = "TC_CON_001";
		conditionTestData.fetchChartsTestData();
		createDuplicateCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: createDuplicateCondition
	 * @Description : Function to create a New Duplicate Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 16, 2010
	 */	
	public void createDuplicateCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib conditionTestData){

		Selenium selenium = null;
		String content = null;
		int counter = 1;
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
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "" ;
				if(content!=null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.toLowerCase(new java.util.Locale("en", "US")))){
						String uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
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
			
			createCondition(selenium, conditionTestData);

			//------------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Condition section  //
			//------------------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition LinkMore Details :" + conditionTestData.toString());;
						
			waitForPageLoad(selenium);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"conditionsBoxBox", conditionTestData.condition),"Could not enter the ConditionMore Details :" + conditionTestData.toString());
						
			Assert.assertTrue(enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate),"Could not enter the Condition Start DateMore Details :" + conditionTestData.toString());
			Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End DateMore Details :" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition NoteMore Details :" + conditionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy StatusMore Details :" + conditionTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save ButtonMore Details :" + conditionTestData.toString());
			waitForPageLoad(selenium);			
			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "More Details :" + conditionTestData.toString());
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "More Details :" + conditionTestData.toString());
			Assert.assertTrue(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"),"The alert is not getting Displyed; Expected  : "+ conditionTestData.expectedAlertText + "More Details :" + conditionTestData.toString());
			String alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
			Assert.assertTrue(alertText.trim().toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.expectedAlertText.trim().toLowerCase(new java.util.Locale("en", "US"))),"Expected  Alert is not  Occured;   Expected : "+ conditionTestData.expectedAlertText +" Actual  : "+alertText+ "More Details :" + conditionTestData.toString());
			click(selenium, "errorCloseButton");
						
			waitForPageLoad(selenium);


		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*) Detailed data:" + conditionTestData.toString());
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

package com.clinical.selenium.section.charts.Allergy;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForAllergy extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying Save button and Data for Un saved Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "VerifySaveButtonForAllergy";
		allergyTestData.testCaseId = "TC_ALL_001";		
		allergyTestData.fetchChartsTestData();
		verifySaveButtonForUnSavedAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForUnSavedAllergy
	 * @Description : Function to verify Save Button For UnSaved Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 25, 2010
	 */
	public void verifySaveButtonForUnSavedAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib allergyTestData){

		Selenium selenium = null;
		allergyTestData.endDate = allergyTestData.endDate != null ? allergyTestData.endDate.trim() : "";

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);
			
			click(selenium,"allergies");
			waitForPageLoad(selenium);	

			click(selenium,"addAllergy");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(selectValueFromAjaxList(selenium,"allergyBoxBox", allergyTestData.allergyName),"Could not enter Allergy Name; More Details" +allergyTestData.toString());
			Assert.assertTrue(select(selenium,"adverseeventtypeInput", allergyTestData.eventType),"Could not select Event Type; More Details" +allergyTestData.toString());
			Assert.assertTrue(select(selenium,"severityInput", allergyTestData.severity),"Could not select Allergy Severity; More Details" +allergyTestData.toString());
			Assert.assertTrue(enterDate(selenium,"startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details" +allergyTestData.toString());
			if(!allergyTestData.endDate.equals("")){
				Assert.assertTrue(enterDate(selenium,"enddateInput", allergyTestData.endDate),"Could not enter End Date; More Details" +allergyTestData.toString());
			}

			Assert.assertTrue(select(selenium,"itemStatusInput", allergyTestData.status),"Could not select Allergy Status; More Details" +allergyTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", allergyTestData.allergyNotes),"Could not enter Allergy Notes; More Details" +allergyTestData.toString());
			
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +allergyTestData.toString());
			waitForPageLoad(selenium);
		
			Assert.assertTrue(select(selenium, "workStatusInput", allergyTestData.taskName),"Could not select Work Status - " + allergyTestData.taskName);
			Assert.assertTrue(select(selenium, "taskUsersInput", allergyTestData.sendTaskTo),"Could not select Send To Task - " + allergyTestData.sendTaskTo);
			Assert.assertTrue(type(selenium,"taskNotesInput", allergyTestData.taskNotes),"Could not enter Task Notes; More Details" +allergyTestData.toString());

			click(selenium,"allergies");
			waitForPageLoad(selenium);
			
			Assert.assertEquals(selenium.getAttribute("allergiesAdd@src").trim(),"images/plus-icon-on.png","Allergy Add(+)  button is OFF and not backgrounded by Orange; More Details" +allergyTestData.toString());
			Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link; More Details" +allergyTestData.toString());
			click(selenium,"addAllergy");
			waitForPageLoad(selenium);
						
			
			verifyStoredValues(selenium, allergyTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +allergyTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + allergyTestData.toString());try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){				
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

	public void verifyStoredValues(Selenium selenium, ChartsLib allergyTestData){

		Assert.assertEquals(getSelectedValue(selenium,"adverseeventtypeInput").trim(),allergyTestData.eventType.trim(),"Un saved Adverse Event Type is not available; It is modified; More Details" +allergyTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"severityInput").trim(),allergyTestData.severity.trim(),"Un saved Severity is not available; It is modified; More Details" +allergyTestData.toString());
		Assert.assertEquals(getValue(selenium, "startdateInput").trim(),allergyTestData.startDate.trim(),"Un saved Start Date is not available; It is modified; More Details" +allergyTestData.toString());
		if(!(allergyTestData.endDate.equals(""))){
			Assert.assertEquals(getValue(selenium, "enddateInput").trim(),allergyTestData.endDate.trim(),"Un saved End Date Value is not available; It is modified; More Details" +allergyTestData.toString());
		}
		Assert.assertEquals(getSelectedValue(selenium, "itemStatusInput").trim(),allergyTestData.status.trim(),"Un saved Status Value is not available; It is modified; More Details" +allergyTestData.toString());
		Assert.assertEquals(getValue(selenium, "notesInput").trim(),allergyTestData.allergyNotes.trim(),"Un saved Notes Input Value is not available; It is modified; More Details" +allergyTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "workStatusInput").trim(),allergyTestData.taskName.trim(),"Un saved Work Status Value is not available; It is modified; More Details" +allergyTestData.toString());

	}
}
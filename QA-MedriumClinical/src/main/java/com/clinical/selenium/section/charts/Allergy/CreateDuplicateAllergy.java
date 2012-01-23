package com.clinical.selenium.section.charts.Allergy;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateDuplicateAllergy extends AbstractChartsTest {

	/**
	 * @Function 	: BeforeTest
	 * @Description : This method is used for creating a directory for storing log files
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 20, 2010
	 */

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying the alert for duplicate allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createDuplicateAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "CreateDuplicateAllergy";
		allergyTestData.testCaseId = "TC_ALL_001";		
		allergyTestData.fetchChartsTestData();
		createDuplicateAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: createDuplicateAllergy
	 * @Description : Function to create Duplicate Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 25, 2011
	 */
	public void createDuplicateAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib allergyTestData){

		Selenium selenium = null;
		int counter = 1;
		String content = null;			
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
			while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
				content = null;				
				content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "";
				if(content != null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US"))) && ! content.toLowerCase(new java.util.Locale("en", "US")).contains("inactive")){
						String uniqueID = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("allergy")[1];
						click(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						
						if(click(selenium,"closeButton")){
							waitForPageLoad(selenium);
							click(selenium,"chartsHeaderActionLink");
							waitForPageLoad(selenium);
						}else {
							select(selenium, "itemStatusInput", "Inactive");
							click(selenium,"validateButton");
							waitForPageLoad(selenium);
						}
					}						
				}
				counter++;
			}

			createAllergy(selenium,allergyTestData);

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in allergy section  //
			//----------------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "addAllergy", 120000),"Could not find Add Allergy Link ; More  Details : " +allergyTestData.toString());
			click(selenium,"addAllergy");

			Assert.assertTrue(selectValueFromAjaxList(selenium,"allergyBoxBox", allergyTestData.allergyName),"Could not enter Allergy Name " +allergyTestData.allergyName + "; More Details :" + allergyTestData.toString());
			Assert.assertTrue(select(selenium,"adverseeventtypeInput", allergyTestData.eventType),"Could not select Event Type; More Details :" + allergyTestData.toString());
			Assert.assertTrue(select(selenium,"severityInput", allergyTestData.severity),"Could not select Allergy Severity; More Details :" + allergyTestData.toString());
			Assert.assertTrue(enterDate(selenium,"startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details :" + allergyTestData.toString());
			if(allergyTestData.endDate!=""){
				Assert.assertTrue(enterDate(selenium,"enddateInput", allergyTestData.endDate),"Could not enter End Date; More Details :" + allergyTestData.toString());
			}
			Assert.assertTrue(select(selenium,"itemStatusInput", allergyTestData.status),"Could not select Allergy Status; More Details :" + allergyTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", allergyTestData.allergyNotes),"Could not enter Allergy Notes; More Details :" + allergyTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details :" + allergyTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.fail("Allergy not saved successfully, An unexpected Alert Occured - "+ selenium.isAlertPresent() + "; More Details :" + allergyTestData.toString());
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + allergyTestData.toString());
			Assert.assertTrue(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"),"The alert is not getting Displyed; Expected  : "+ allergyTestData.expectedAlertText + "; More Details :" + allergyTestData.toString());
			String alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
			Assert.assertTrue(alertText.trim().toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.expectedAlertText.trim().toLowerCase(new java.util.Locale("en", "US"))),"Expected  Alert is not  Occured;   Expected : "+ allergyTestData.expectedAlertText +" Actual  : "+ alertText + "; More Details :" + allergyTestData.toString());
			click(selenium, "errorCloseButton");

			waitForPageLoad(selenium);

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + allergyTestData.toString());
			try{
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib allergyTestData){

		if(!getSelectedValue(selenium,"adverseeventtypeInput").trim().equalsIgnoreCase(allergyTestData.eventType.trim())){
			return false;
		}

		if(!getSelectedValue(selenium,"severityInput").trim().equalsIgnoreCase(allergyTestData.severity.trim())){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().equalsIgnoreCase(allergyTestData.startDate.trim())){
			return false;
		}

		if(!(allergyTestData.endDate.equals(""))){
			if(!getValue(selenium, "enddateInput").trim().equalsIgnoreCase(allergyTestData.endDate.trim())){
				return false;
			}
		}

		if(!getSelectedValue(selenium, "itemStatusInput").trim().equalsIgnoreCase(allergyTestData.status.trim())){
			return false;
		}

		if(!getValue(selenium, "notesInput").trim().equalsIgnoreCase(allergyTestData.allergyNotes.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(allergyTestData.taskName.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "taskUsersInput").trim().equalsIgnoreCase(allergyTestData.sendTaskTo.trim())){
			return false;
		}

		return true;
	}


}
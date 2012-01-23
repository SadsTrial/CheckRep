package com.clinical.selenium.section.charts.immunization;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewImmunization extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New Immunization")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewImmunization(String seleniumHost, int seleniumPort, String browser, String webSite){
		ChartsLib immunizationTestData = new ChartsLib();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "TC_IMM_001";
		immunizationTestData.fetchChartsTestData();
		createNewImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	/**
	 * @Function 	: createNewImmunization
	 * @Description : Function to create a New Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 01, 2010
	 */	
	public void createNewImmunization(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib immunizationTestData){

		Selenium selenium = null;
		String summaryImmunizationCount = null;
		boolean isRecordFoundInImmunizations = false;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity = false;

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + immunizationTestData.toString());
			loginFromPublicSite(selenium, immunizationTestData.userAccount, immunizationTestData.userName, immunizationTestData.userPassword);
			searchPatient(selenium,immunizationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Immunization and enter details             //
			//--------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(waitForValue(selenium, "immunizationTitle", 10000),"Could not capture existing Immunization Count");
			summaryImmunizationCount = getListCount(selenium.getText("immunizationTitle"));
			waitForPageLoad(selenium);
			click(selenium,"immunizations");
			waitForPageLoad(selenium);
			
			while(isElementPresent(selenium, "patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
				click(selenium, "patientImmunizationListMoreLink");
				waitForPageLoad(selenium);
			}

			Collection<String> firstList = getDataBaseIDs(selenium, "immunization"); 	

			createImmunization(selenium, immunizationTestData);
			while(isElementPresent(selenium, "patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
				click(selenium, "patientImmunizationListMoreLink");
				waitForPageLoad(selenium);
			}

			Collection<String> secondList = getDataBaseIDs(selenium, "immunization");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ immunizationTestData.toString());
			}
			//---------------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Immunization section  //
			//---------------------------------------------------------------------------------//

			while(!selenium.isElementPresent(idOfTheNewlyAddedRecord) && isElementPresent(selenium, "patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
				selenium.click("patientImmunizationListMoreLink");
				waitForPageLoad(selenium);
			}

			click(selenium,idOfTheNewlyAddedRecord);

			waitForPageLoad(selenium);
			isRecordFoundInImmunizations = verifyStoredValues( selenium, immunizationTestData);


			//----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "immunizationTitle", 10000),"Could not capture immunization Count after saving a immunization in summary section; More Details :" + immunizationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("immunizationTitle"))) , (Integer.parseInt(summaryImmunizationCount)+1),"The immunization is not Saved Successfully in summary section, immunization count has no change after updating immunization in summary section; More Details :" + immunizationTestData.toString());


			if(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				while(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					if(selenium.isElementPresent("patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
						selenium.click("patientImmunizationListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInSummary = verifyStoredValues( selenium, immunizationTestData);


			//-----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity = verifyStoredValues( selenium, immunizationTestData);

			Assert.assertTrue(isRecordFoundInImmunizations,"Immunization record created is not found in Immunization Page; Immunization Creation Failed; More Details : "+immunizationTestData.toString());
			Assert.assertTrue(isRecordFoundInSummary,"Immunization record created is not found in Summary Page; Immunization Creation Failed; More Details : "+immunizationTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Immunization record created is not found in Activity Page; Immunization Creation Failed; More Details : "+immunizationTestData.toString());

		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + immunizationTestData.toString());
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib immunizationTestData){

		if(!getText(selenium, "productBoxInput").trim().contains(immunizationTestData.immunizationName.trim())){
			return false;
		}

		if(!getText(selenium, "administrationDateInput").trim().contains(immunizationTestData.administrationDate.trim())){
			return false;
		}

		if(!getText(selenium, "lotNumberInput").trim().contains(immunizationTestData.lotInput.trim())){
			return false;
		}

		if(!selenium.getText("manufacturerBoxInput").trim().contains(immunizationTestData.manufacturer.trim())){
			return false;
		}

		if(!selenium.getText("routeBoxInput").trim().toLowerCase(new java.util.Locale("en", "US")).contains(immunizationTestData.routeOfAdministartion.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium, "refusalReasonCodeInput").trim().contains(immunizationTestData.reason.trim())){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().contains(immunizationTestData.immunityNotes.trim())){
			return false;
		}

		if(!getText(selenium, "workStatus").trim().contains(immunizationTestData.taskName.trim())){
			return false;
		}

		if(!getText(selenium, "taskUsers").trim().toLowerCase(new java.util.Locale("en", "US")).contains(immunizationTestData.sendTaskTo.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"taskNotes").trim().contains(immunizationTestData.taskNotes)){
			return false;
		}		
		return true;
	}
}
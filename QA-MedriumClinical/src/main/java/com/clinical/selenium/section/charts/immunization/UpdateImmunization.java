package com.clinical.selenium.section.charts.immunization;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateImmunization extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for updating an Immunization")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateImmunization(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib immunizationTestData = new ChartsLib();
		immunizationTestData.workSheetName = "UpdateImmunization";
		immunizationTestData.testCaseId = "TC_UIM_001";
		immunizationTestData.fetchChartsTestData();
		updateExistingImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	/**
	 * @Function 	: updateExistingImmunization
	 * @Description : Function to update an existing Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 29, 2010
	 */	
	public void updateExistingImmunization(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib immunizationTestData){

		Selenium selenium = null;
		String immunizationCount = null;
		String summaryImmunizationCount = null;
		String uniqueID = null;
		boolean isRecordFoundInImmunizations = false;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity = false;
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		Date currentDate = new Date();
		String currentDateFormatted = dateFormat.format(currentDate);
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + immunizationTestData.toString());
			loginFromPublicSite(selenium, immunizationTestData.userAccount, immunizationTestData.userName, immunizationTestData.userPassword);
			searchPatient(selenium,immunizationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Update the Immunization with New Details and Save         //
			//--------------------------------------------------------------------//

			click(selenium,"immunizations");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "Immunizations", 120000),"Could not capture existing Immunization Count; More Details :" + immunizationTestData.toString());
			immunizationCount = getListCount(selenium.getText("Immunizations"));

			//---------------------------------------------------------------------------------------//
			//  Step-3: Add new Immunization If No existing immunization available to update         //
			//---------------------------------------------------------------------------------------//

			if(!immunizationCount.trim().equals(null) || !immunizationCount.trim().equals("")){
				if (Integer.parseInt(immunizationCount)== 0){
					immunizationTestData = null;
					immunizationTestData = new ChartsLib();
					immunizationTestData.workSheetName = "Immunization";
					immunizationTestData.testCaseId = "TC_IMM_001";		
					immunizationTestData.fetchChartsTestData();
					createImmunization(selenium, immunizationTestData);
					immunizationCount = "1";
				}
			}

			immunizationTestData = null;
			immunizationTestData = new ChartsLib();
			immunizationTestData.workSheetName = "UpdateImmunization";
			immunizationTestData.testCaseId = "TC_UIM_001";		
			immunizationTestData.fetchChartsTestData();

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "immunizationTitle", 10000),"Could not capture immunization Count after saving a immunization in summary section; More Details :" + immunizationTestData.toString());
			summaryImmunizationCount = getListCount(selenium.getText("immunizationTitle"));

			click(selenium,"immunizations");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------------------------//
			//  Step-4: Update the Immunization                                                     //
			//--------------------------------------------------------------------------------------//
			String idOfTheRecord = null;
			if(selenium.isElementPresent("//div[@id='patientImmunizationList']/table/tbody[1]/tr[1]/td[1]/div")){
				idOfTheRecord = selenium.getAttribute("//div[@id='patientImmunizationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord.split("immunization")[1];
			
				click(selenium,"//div[@id='patientImmunizationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a");
				waitForPageLoad(selenium);
				
				click(selenium, "actionButton");
							
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);
				
				click(selenium, "productBoxInputLabel");
				if(!getValue(selenium,"administrationDateInput").trim().equals(currentDateFormatted)){
					immunizationTestData.administrationDate = currentDateFormatted;
				}

				if(getText(selenium, "productBoxInputLabel").trim().equalsIgnoreCase(immunizationTestData.immunizationName.trim())){
					immunizationTestData.workSheetName = "UpdateImmunization";
					immunizationTestData.testCaseId = "TC_UIM_002";		
					immunizationTestData.fetchChartsTestData();	
				}
				immunizationTestData.administrationDate = immunizationTestData.administrationDate != null && immunizationTestData.administrationDate != "" ? immunizationTestData.administrationDate.trim() : getValue(selenium,"administrationDateInput");
				immunizationTestData.lotInput = immunizationTestData.lotInput != null && immunizationTestData.lotInput != "" ? immunizationTestData.lotInput.trim() : getValue(selenium,"lotNumberInput");
				immunizationTestData.manufacturer = immunizationTestData.manufacturer != null && immunizationTestData.manufacturer != "" ? immunizationTestData.manufacturer.trim() : getSelectedValue(selenium, "immunizationManufacturersInput");
				immunizationTestData.routeOfAdministartion = immunizationTestData.routeOfAdministartion != null && immunizationTestData.routeOfAdministartion != "" ? immunizationTestData.routeOfAdministartion.trim() : getSelectedValue(selenium, "fdaRoutesInput");
				immunizationTestData.reason = immunizationTestData.reason != null && immunizationTestData.reason != "" ? immunizationTestData.reason.trim() : getSelectedValue(selenium, "refusalReasonCodeInput");
				immunizationTestData.immunityNotes = immunizationTestData.immunityNotes != null && immunizationTestData.immunityNotes != "" ? immunizationTestData.immunityNotes.trim() : getValue(selenium,"notesInput");
				immunizationTestData.taskName = immunizationTestData.taskName != null && immunizationTestData.taskName != "" ? immunizationTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
				immunizationTestData.sendTaskTo = immunizationTestData.sendTaskTo != null && immunizationTestData.sendTaskTo != "" ? immunizationTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
				immunizationTestData.taskNotes = immunizationTestData.taskNotes != null && immunizationTestData.taskNotes != "" ? immunizationTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");


				Assert.assertTrue(selectValueFromAjaxList(selenium,"productBoxInputBox", immunizationTestData.immunizationName),"Could not enter Immunization Name; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(enterDate(selenium,"administrationDateInput", immunizationTestData.administrationDate),"Could not enter Administration Date; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(enterDate(selenium,"administrationTimeInput", immunizationTestData.administrationTime),"Could not enter Administration Time; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(enterDate(selenium,"administeredAmountInput", immunizationTestData.administrationAmount),"Could not enter Administration Amount; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(type(selenium,"lotNumberInput",immunizationTestData.lotInput),"Could not enter Lot Number; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(select(selenium,"immunizationManufacturersInput", immunizationTestData.manufacturer),"Could not select Manufacturer; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(select(selenium,"fdaRoutesInput", immunizationTestData.routeOfAdministartion),"Could not select Manufacturer Route; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(select(selenium,"refusalReasonCodeInput", immunizationTestData.reason),"Could not select Reason Code; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput", immunizationTestData.immunityNotes),"Could not enter Immunity Notes; More Details :" + immunizationTestData.toString());

				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

				if(!selenium.isElementPresent("workStatusInput") || !selenium.isVisible("workStatusInput")){
					click(selenium, "addTask");
				}
				Assert.assertTrue(select(selenium, "workStatusInput", immunizationTestData.taskName),"Could not select Work Status; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(select(selenium, "taskUsersInput", immunizationTestData.sendTaskTo),"Could not select Send To Task; More Details :" + immunizationTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", immunizationTestData.taskNotes),"Could not enter Task Notes; More Details :" + immunizationTestData.toString());

				Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details :" + immunizationTestData.toString());		
				waitForPageLoad(selenium);
							

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Immunization not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + immunizationTestData.toString());
				}

				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + immunizationTestData.toString());
			}

			waitForPageLoad(selenium);
						

			Assert.assertTrue(waitForValue(selenium, "Immunizations", 10000),"Could not capture immunization Count after saving a immunization; More Details :" + immunizationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("Immunizations"))) ,Integer.parseInt(immunizationCount),"The immunization is not Saved Successfully, immunization count has a change after updating a immunization; More Details :" + immunizationTestData.toString());

			//---------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Immunization section  //
			//---------------------------------------------------------------------------------//
			
			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + immunizationTestData.toString());
			
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInImmunizations = verifyStoredValues( selenium, immunizationTestData);
			click(selenium,"showList");
			waitForPageLoad(selenium);


			//----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "immunizationTitle", 10000),"Could not capture immunization Count after saving a immunization in summary section; More Details :" + immunizationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("immunizationTitle"))),(Integer.parseInt(summaryImmunizationCount)),"The immunization is not Saved Successfully in summary section, immunization count has no change after updating immunization in summary section; More Details :" + immunizationTestData.toString());

			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
					if(selenium.isElementPresent("patientImmunizationListMoreLink") && selenium.isVisible("patientImmunizationListMoreLink")){
						click(selenium, "patientImmunizationListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}
		
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInSummary = verifyStoredValues(selenium, immunizationTestData);

			//-----------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInActivity =verifyStoredValues(selenium, immunizationTestData);

			Assert.assertTrue(isRecordFoundInImmunizations,"The Record is  Not updated in Immunization Page; The Immunization Updation Failed; More Details : "+immunizationTestData.toString());
			Assert.assertTrue(isRecordFoundInSummary,"The Record is  Not updated in Summary Page; The Immunization Updation Failed; More Details : "+immunizationTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"The Record is  Not updated in Activity Page; The Immunization Updation Failed; More Details : "+immunizationTestData.toString());
		}catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + immunizationTestData.toString());
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
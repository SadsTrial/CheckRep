package com.clinical.selenium.section.charts.immunization;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForImmunization extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying the Save button and Data for Un saved Immunization")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedImmunization(String seleniumHost, int seleniumPort, String browser, String webSite){
		ChartsLib immunizationTestData = new ChartsLib();
		immunizationTestData.workSheetName = "VerifySaveButtonForImmunization";
		immunizationTestData.testCaseId = "TC_IMM_001";
		immunizationTestData.fetchChartsTestData();
		verifySaveButtonForImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForUnSavedImmunization
	 * @Description : Function to Verifying Save button and Data for Unsaved Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 26, 2011
	 */	
	public void verifySaveButtonForImmunization(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib immunizationTestData){

		Selenium selenium = null;
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

			click(selenium,"immunizations");
						
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addImmunization"),"Could not find Add Immunization Link; More Details" + immunizationTestData.toString());
			waitForPageLoad(selenium);
			
			Assert.assertTrue(selectValueFromAjaxList(selenium,"productBoxInputBox", immunizationTestData.immunizationName),"Could not enter Immunization Name; More Details" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administrationDateInput", immunizationTestData.administrationDate),"Could not enter Administration Date; More Details" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administrationTimeInput", immunizationTestData.administrationTime),"Could not enter Administration Time; More Details" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administeredAmountInput", immunizationTestData.administrationAmount),"Could not enter Administration Amount; More Details" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"lotNumberInput",immunizationTestData.lotInput),"Could not enter Lot Number; More Details" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium,"immunizationManufacturersInput", immunizationTestData.manufacturer),"Could not select Manufacturer; More Details" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium,"fdaRoutesInput", immunizationTestData.routeOfAdministartion),"Could not select Manufacturer Route; More Details" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium,"refusalReasonCodeInput", immunizationTestData.reason),"Could not select Reason Code; More Details" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", immunizationTestData.immunityNotes),"Could not enter Immunity Notes; More Details" + immunizationTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + immunizationTestData.toString());
						
			Assert.assertTrue(select(selenium, "workStatusInput", immunizationTestData.taskName),"Could not select Work Status; More Details" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", immunizationTestData.sendTaskTo),"Could not select Send To Task; More Details" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", immunizationTestData.taskNotes),"Could not enter Task Notes; More Details" + immunizationTestData.toString());

			click(selenium,"immunizations");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("immunizationsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Immunizations Add(+)  button is OFF and not backgrounded by Orange; More Details" + immunizationTestData.toString());
			Assert.assertTrue(click(selenium,"addImmunization"),"Could not find Add Immunization Link; More Details" + immunizationTestData.toString());
			waitForPageLoad(selenium);
			
			verifyStoredValues(selenium, immunizationTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" + immunizationTestData.toString());

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

	public void verifyStoredValues(Selenium selenium, ChartsLib immunizationTestData){

		Assert.assertTrue(getText(selenium, "productBoxInputLabel").trim().contains(immunizationTestData.immunizationName.trim()),"Un saved Product Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(getValue(selenium, "administrationDateInput").trim().contains(immunizationTestData.administrationDate.trim()),"Un saved Administration Date Input Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(getValue(selenium, "lotNumberInput").trim().contains(immunizationTestData.lotInput.trim()),"Un saved Lot Number Input Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(selenium.getSelectedLabel("immunizationManufacturersInput").trim().contains(immunizationTestData.manufacturer.trim()),"Un saved Immunization Manufacturers Input Value is not available; It is modified  :; More Details" + immunizationTestData.toString());
		Assert.assertTrue(selenium.getSelectedLabel("fdaRoutesInput").trim().contains(immunizationTestData.routeOfAdministartion.trim()),"Un saved fda Routes Input Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(getSelectedValue(selenium, "refusalReasonCodeInput").trim().contains(immunizationTestData.reason.trim()),"Un saved Refusal Reason Code Input Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(getValue(selenium, "notesInput").trim().contains(immunizationTestData.immunityNotes.trim()),"Un saved Notes Input Value is not available; It is modified; More Details" + immunizationTestData.toString());
		Assert.assertTrue(getSelectedValue(selenium, "workStatusInput").trim().contains(immunizationTestData.taskName.trim()),"Un saved Work Status Input Value is not available; It is modified; More Details" + immunizationTestData.toString());

	}
}
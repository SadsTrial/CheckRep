package com.clinical.selenium.section.charts.medication;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Medication extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Name Date with a Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNameWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_001";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Name with a Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNameWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_002";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Name with a Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNameWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_003";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Name with a Number")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNameWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_004";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_005";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_006";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_007";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Medication Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_008";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Start Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_009";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationStartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_010";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication End Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_011";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication End Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_012";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication End Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_013";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication End Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_014";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Medication End Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_015";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Medication End Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDtaeWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_016";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Start Date > End Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationEndDateEarlierThanStartDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_017";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_018";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNoteWithAlphanumericCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_019";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_020";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_021";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Notes with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithAlphanumericCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_022";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Notes with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_023";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest medicationTestData = new ChartsLib_UnitTest();
		medicationTestData.workSheetName = "Medication";
		medicationTestData.testCaseId = "UT_ME_024";
		medicationTestData.fetchCodingInterfaceTestData();
		addMedication(seleniumHost, seleniumPort, browser, webSite, medicationTestData);
	}

	/**
	 * @Function 	: addMedication
	 * @Description : Function to add a New Medication
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 04, 2010
	 */
	public void addMedication(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest medicationTestData){

		String alertText = "";
		Selenium selenium = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + medicationTestData.toString());
			loginFromPublicSite(selenium, medicationTestData.userAccount, medicationTestData.userName, medicationTestData.userPassword);
			
			searchPatient(selenium,medicationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Add Medication Link                         //
			//--------------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details :" + medicationTestData.toString());
			
			click(selenium,"addMedication");
			waitForPageLoad(selenium);

			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
			}

			waitForElement(selenium, "medicationBoxBox", 7000);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			if( medicationTestData.medicationName != null && !medicationTestData.medicationName .equals("") ){
				Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationTestData.medicationName),"Could not enter Medication Name; More Details :" + medicationTestData.toString());
			}

			Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details :" + medicationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details :" + medicationTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details :" + medicationTestData.toString());

			if(medicationTestData.taskName != null && !medicationTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details :" + medicationTestData.toString());

			if( medicationTestData.sendTaskTo != null && !medicationTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + medicationTestData.toString());

			Assert.assertTrue(type(selenium,"taskNotesInput", medicationTestData.taskNotes),"Could Not Enter Task Notes; More Details :" + medicationTestData.toString());
			Assert.assertTrue(waitForElementToEnable(selenium, "saveButton"),"Unable to click 'Save' - Save button is not enabled; More Details :" + medicationTestData.toString());
			Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details :" + medicationTestData.toString());
										

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText.equals("") && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(medicationTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					if(!getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains("already exists in the system"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(medicationTestData.checkAlertMessage(alertText, medicationTestData.alertMessage) || selenium.isTextPresent(medicationTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+medicationTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + medicationTestData.toString());
			}else if (medicationTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +")");
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + medicationTestData.toString());
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
}

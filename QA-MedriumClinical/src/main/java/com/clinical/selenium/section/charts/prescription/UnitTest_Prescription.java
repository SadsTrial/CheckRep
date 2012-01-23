package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Prescription extends AbstractChartsTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Medication with Blank")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_001";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Medication with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_002";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Medication with Characters and Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void medicationWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_003";
		prescriptionTestData.fetchCodingInterfaceTestData();    	
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Quantity with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void quantityWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_004";
		prescriptionTestData.fetchCodingInterfaceTestData();    	
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Quantity with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void quantityWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_005";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Quantity with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void quantityWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_006";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Refills with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void refillsWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_007";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Refills with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void refillsWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_008";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Refills with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void refillsWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_009";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Comments with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void commentsWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_010";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Comments with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void commentsWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_011";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Comments with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void commentsWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_012";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Task Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_013";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_014";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Task Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();    	
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_015";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Take Each Time with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void takeEachTimeWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_016";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Take Each Time with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void takeEachTimeWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_017";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Take Each Time with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void takeEachTimeWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_018";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Take Each Time with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void takeEachTimeWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_019";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "No of days with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noOfDaysWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_020";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "No of days with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noOfDaysWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_021";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "No of with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noOfDaysWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_022";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "No of with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noOfDaysWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_023";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Saving without selecting a Provider")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutProvider(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_024";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Saving without selecting a Location")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutLocation(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_025";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_026";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}


	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Take Each Time with Junk Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void takeEachTimeWithJunkValue(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_027";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Quantity with Junk Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void qunatityWithJunkValue(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_028";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "No Of Days with Junk Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noOfDaysWithJunkValue(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_029";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Refills with Junk Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void refillsWithJunkValue(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_030";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default" }, description = "Refills value with more than 0-999")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void refillsWithValueGreaterThan999(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{

		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "UT_PR_031";
		prescriptionTestData.fetchCodingInterfaceTestData();
		addPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: createNewPrescription
	 * @Description : Function to create a New Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010
	 */
	public void addPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest prescriptionTestData){
		
		Selenium selenium = null;
		String alertText = "";

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Prescription Link                           //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link");
			waitForPageLoad(selenium);
			
			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
			}

			if( prescriptionTestData.providerName != null && !prescriptionTestData.providerName.equals(""))
				Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details :" + prescriptionTestData.toString());

			if( prescriptionTestData.providerLocation != null && !prescriptionTestData.providerLocation.equals("") )
				Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details :" + prescriptionTestData.toString());

			if(prescriptionTestData.medicationName != null && !prescriptionTestData.medicationName.equals("")) {
				Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", prescriptionTestData.medicationName),"Could not enter Medication Name; More Details :" + prescriptionTestData.toString());
			}

			Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.units),"Could not enter Unit Quantity Input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium, "ss_quantityInput", prescriptionTestData.quantity),"Could not select Quantity type input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.noOfDays),"Could not enter Day supply input; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details :" + prescriptionTestData.toString());


			if(prescriptionTestData.taskName != null && !prescriptionTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details :" + prescriptionTestData.toString());

			if( prescriptionTestData.sendTaskTo != null && !prescriptionTestData.sendTaskTo.equals(""))

				Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + prescriptionTestData.toString());

			Assert.assertTrue(type(selenium,"taskNotesInput", prescriptionTestData.taskNotes),"Could Not Enter Task Notes; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details :" + prescriptionTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button");
			
			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText.equals("") && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(prescriptionTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(prescriptionTestData.checkAlertMessage(alertText, prescriptionTestData.alertMessage) || selenium.isTextPresent(prescriptionTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+prescriptionTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + prescriptionTestData.toString());
			}else if (prescriptionTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + prescriptionTestData.toString());
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
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
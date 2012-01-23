package com.clinical.selenium.section.charts.radiologyOrder;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_RadiologyOrder extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderData = new ChartsLib_UnitTest();
		radiologyOrderData.workSheetName = "RadiologyOrder";
		radiologyOrderData.testCaseId = "UT_RAD_001";
		radiologyOrderData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_002";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_003";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_004";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Expected Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_005";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_006";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_007";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_008";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Notewith special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_009";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_010";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_011";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_012";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting an ordering panel")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutOrderingPanel(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_013";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_014";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting an Ordering Provider")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutOrderingProvider(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest radiologyOrderTestData = new ChartsLib_UnitTest();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "UT_RAD_015";
		radiologyOrderTestData.fetchCodingInterfaceTestData();
		addRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}


	/**
	 * @Function 	: addRadiologyOrder
	 * @Description : Function to create a New Radiology Order
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 02, 2010
	 */
	public void addRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest radiologyOrderTestData){
		Selenium selenium = null;
		String alertText = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add RadiologyOrder Link                             //
			//--------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"radiologyOrderAdd"),"Could not find Add Radiology Order Link; More Details :" + radiologyOrderTestData.toString());
			waitForPageLoad(selenium);

			if(radiologyOrderTestData.providerName != null && !radiologyOrderTestData.providerName.equals("") )
				Assert.assertTrue(select(selenium,"providersInput", radiologyOrderTestData.providerName),"Could not enter Provider Name; More Details :" + radiologyOrderTestData.toString());
			Assert.assertTrue(enterDate(selenium,"expecteddateInput", radiologyOrderTestData.expectedDate),"Could not enter Expected Date; More Details :" + radiologyOrderTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",radiologyOrderTestData.providerNotes),"Could not enter Notes; More Details :" + radiologyOrderTestData.toString());

			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  ; More Details :" + radiologyOrderTestData.toString());;
			waitForPageLoad(selenium);

			if( radiologyOrderTestData.taskName != null && !radiologyOrderTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium,"workStatusInput", radiologyOrderTestData.taskName),"Could not select Work Status; More Details :" + radiologyOrderTestData.toString());

			if( radiologyOrderTestData.sendTaskTo != null && !radiologyOrderTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium,"taskUsersInput", radiologyOrderTestData.sendTaskTo),"Could not select Send Task To; More Details :" + radiologyOrderTestData.toString());

			Assert.assertTrue(type(selenium,"taskNotesInput", radiologyOrderTestData.taskNotes),"Could not enter Task Notes; More Details :" + radiologyOrderTestData.toString());

			if(!radiologyOrderTestData.MRI.equals("")){
				Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.MRI.trim()),"Could not select Radiology Panels : MRI Value- ; More Details :" + radiologyOrderTestData.toString());
			}

			if(!radiologyOrderTestData.CT.equals("") ){
				Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.CT.trim()),"Could not select Radiology Panels : CT Value; More Details :" + radiologyOrderTestData.toString());
			}

			if(!radiologyOrderTestData.radiology.equals("")){
				Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.radiology.trim()),"Could not select Radiology Panels : radiology Value; More Details :" + radiologyOrderTestData.toString());
			}

			if(!radiologyOrderTestData.nuclearMedecine.equals("") ){
				Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.nuclearMedecine.trim()),"Could not select Radiology Panels : Nuclear Medecine Value; More Details :" + radiologyOrderTestData.toString());
			}

			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + radiologyOrderTestData.toString());
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText == null && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(radiologyOrderTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(radiologyOrderTestData.checkAlertMessage(alertText, radiologyOrderTestData.alertMessage) || selenium.isTextPresent(radiologyOrderTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+radiologyOrderTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + radiologyOrderTestData.toString());
			}else if (radiologyOrderTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + radiologyOrderTestData.toString());
			}

		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + radiologyOrderTestData.toString());
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
	public boolean selectRadiologyPanels(Selenium selenium, String valRadiologyPanel){
		String panelVal[] = valRadiologyPanel.split("-");
		String elementName = "//input[@name='"+panelVal[0]+"']";
		return(check(selenium, elementName));

	}
}
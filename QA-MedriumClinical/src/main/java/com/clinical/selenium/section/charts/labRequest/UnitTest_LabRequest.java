package com.clinical.selenium.section.charts.labRequest;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_LabRequest extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_001";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_002";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_003";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_004";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Expected Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_005";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Expected Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void expectedDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_006";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_007";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_008";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Instruction Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void instructionNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_009";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_010";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_011";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_012";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting an ordering provider")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutOrderingProvider(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_013";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_014";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting an ordering panel")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutOrderingPanel(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest labRequestTestData = new ChartsLib_UnitTest();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "UT_LR_015";
		labRequestTestData.fetchCodingInterfaceTestData();
		addLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: addLabRequest
	 * @Description : Function to create a New labRequest
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 24, 2010
	 */
	public void addLabRequest(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest labRequestTestData){

		Selenium selenium = null;
		String alertText = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + labRequestTestData.toString());
			loginFromPublicSite(selenium, labRequestTestData.userAccount, labRequestTestData.userName, labRequestTestData.userPassword);
			searchPatient(selenium,labRequestTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add labRequest Link                             //
			//--------------------------------------------------------------------//

			click(selenium,"labRequest");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"labRequest"),"Could not find Lab Request Link; More Details :" + labRequestTestData.toString());
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			Assert.assertTrue(click(selenium,"labRequestAdd"),"Could not find Add Lab Request Link; More Details :" + labRequestTestData.toString());
			waitForPageLoad(selenium);


			Assert.assertTrue(select(selenium,"providersInput", labRequestTestData.providerName),"Could not enter Provider Name; More Details :" + labRequestTestData.toString());
			Assert.assertTrue(enterDate(selenium,"expecteddateInput", labRequestTestData.expectedDate),"Could not enter Expected Date; More Details :" + labRequestTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",labRequestTestData.providerNotes),"Could not enter Notes; More Details :" + labRequestTestData.toString());

			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
			}

			if(labRequestTestData.taskName != null && !labRequestTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium,"workStatusInput", labRequestTestData.taskName),"Could not select Work Status; More Details :" + labRequestTestData.toString());		

			if(labRequestTestData.sendTaskTo != null && !labRequestTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium,"taskUsersInput", labRequestTestData.sendTaskTo),"Could not select Send Tast To; More Details :" + labRequestTestData.toString());

			type(selenium,"taskNotesInput", labRequestTestData.taskNotes);

			if(!labRequestTestData.description.trim().equalsIgnoreCase("Saving without selecting an ordering panel")){
				selectTestBatteries(selenium, "Bacterial susceptibility panel(49589-5)");
			}

			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + labRequestTestData.toString());

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText == null && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(labRequestTestData.alert.equalsIgnoreCase("Yes")){
				if(alertText == null || alertText.equals("")){
					if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
				}
				Assert.assertTrue(labRequestTestData.checkAlertMessage(alertText, labRequestTestData.alertMessage) || selenium.isTextPresent(labRequestTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+labRequestTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + labRequestTestData.toString());
			}else if (labRequestTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + labRequestTestData.toString());
			}

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + labRequestTestData.toString());
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

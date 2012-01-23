package com.clinical.selenium.section.charts.immunization;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Immunization extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization with blank space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_001";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_002";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_003";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_004";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_005";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationStartDateWithCharaters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_006";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_007";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_008";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Start Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationStartDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_009";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Immunization Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationstartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_010";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationLotNumberWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_011";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationLotNumberWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_012";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationLotNumberWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_013";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationLotNumberWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_014";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_015";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_016";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_017";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationNotesWithCharaters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_018";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_019";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_020";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_021";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Task Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_022";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with 10 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunzationLotNumberWith10Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_023";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);

	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Immunization Lot Number with more than 10 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void immunizationLotNumberWithMoreThan10Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_024";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting a Manufacturer Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutManufacturerValue(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_025";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting a Route of Administration Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutAdministrationRoute(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_026";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest immunizationTestData = new ChartsLib_UnitTest();
		immunizationTestData.workSheetName = "Immunization";
		immunizationTestData.testCaseId = "UT_IM_027";
		immunizationTestData.fetchCodingInterfaceTestData();
		addImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);	
	}


	/**
	 * @Function 	: addImmunization
	 * @Description : Function to create a New Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010
	 */	
	public void addImmunization(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest immunizationTestData){
		Selenium selenium = null;
		String alertText = "";
		try{
			
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + immunizationTestData.toString());
			loginFromPublicSite(selenium, immunizationTestData.userAccount, immunizationTestData.userName, immunizationTestData.userPassword);
			searchPatient(selenium,immunizationTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Immunization  Link                          //
			//--------------------------------------------------------------------//

			click(selenium,"immunizations");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addImmunization"),"Could not find Add Immunization Link");
			waitForPageLoad(selenium);				

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
			}

			if(immunizationTestData.immunizationName != null && !immunizationTestData.immunizationName.equals("") )
				Assert.assertTrue(selectValueFromAjaxList(selenium,"productBoxInputBox", immunizationTestData.immunizationName),"Could not enter Immunization Name; More Details :" + immunizationTestData.toString());

			Assert.assertTrue(enterDate(selenium,"administrationDateInput", immunizationTestData.administrationDate),"Could not enter Administration Date; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administrationTimeInput", immunizationTestData.administrationTime),"Could not enter Administration Time; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"lotNumberInput",immunizationTestData.lotInput),"Could not enter Lot Number; More Details :" + immunizationTestData.toString());

			if(immunizationTestData.manufacturer != null && !immunizationTestData.manufacturer.equals("") )
				Assert.assertTrue(select(selenium,"immunizationManufacturersInput", immunizationTestData.manufacturer),"Could not select Manufacturer; More Details :" + immunizationTestData.toString());
			if(immunizationTestData.routeOfAdministartion != null  && !immunizationTestData.routeOfAdministartion.equals("") )   
				Assert.assertTrue(select(selenium,"fdaRoutesInput", immunizationTestData.routeOfAdministartion),"Could not select Manufacturer Route; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", immunizationTestData.immunityNotes),"Could not enter Immunity Notes; More Details :" + immunizationTestData.toString());

			if(immunizationTestData.taskName != null && !immunizationTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium, "workStatusInput", immunizationTestData.taskName),"Could not select Work Status; More Details :" + immunizationTestData.toString());

			if(immunizationTestData.sendTaskTo != null && !immunizationTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium, "taskUsersInput", immunizationTestData.sendTaskTo),"Could not select Send To Task; More Details :" + immunizationTestData.toString());

			Assert.assertTrue(type(selenium,"taskNotesInput", immunizationTestData.taskNotes),"Could not enter Task Notes; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details :" + immunizationTestData.toString());
			

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText.equals("") && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(immunizationTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
				Assert.assertTrue(immunizationTestData.checkAlertMessage(alertText, immunizationTestData.alertMessage) || selenium.isTextPresent(immunizationTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+immunizationTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + immunizationTestData.toString());
			}else if (immunizationTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + immunizationTestData.toString());
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + immunizationTestData.toString());try {
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

package com.clinical.selenium.section.charts.Visit;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Visits extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Subjective with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void subjectiveWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_001";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"} , description = "Subjective with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void subjectiveWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_002";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Objective with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void objectiveWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_003";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Objective with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void objectiveWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_004";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Assessment with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void assessmentWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_005";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Assessment with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void assessmentWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_006";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Plan with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void planWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_007";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Plan with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void planWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_008";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_009";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_010";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Visit Date with a Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_011";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Visit Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();		
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_012";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Visit Date with a Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_013";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Visit Date with a Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_014";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Visit Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_015";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting Provider Name")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void saveWithoutProviderName(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_016";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting Provider Location")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void saveWithoutProviderLocation(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_017";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void saveWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest visitTestData = new ChartsLib_UnitTest();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "UT_VI_018";
		visitTestData.fetchCodingInterfaceTestData();
		addVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: addVisit
	 * @Description : Function to create a New Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 03, 2010
	 */  
	public void addVisit(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest visitTestData){

		Selenium selenium = null;
		String alertText = "";

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Visit Link                                  //
			//--------------------------------------------------------------------//

			Assert.assertTrue(click(selenium,"visits"),"Could not find (+) Add Visit Link; More Details :" + visitTestData.toString());
			waitForPageLoad(selenium);
			click(selenium,"visitsAdd");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			type(selenium, "visitdateInput", "");
			enterDate(selenium,"visitdateInput", visitTestData.visitDate);
			
			if( visitTestData.providerName != null && !visitTestData.providerName.equals(""))
				Assert.assertTrue(select(selenium, "providersInput", visitTestData.providerName),"Could not select the test data for Provider Name; More Details :" + visitTestData.toString());

			if( visitTestData.providerLocation != null && !visitTestData.providerLocation.equals("") )
				Assert.assertTrue(select(selenium, "locationsInput", visitTestData.providerLocation),"Could not select the test data for Provider Location; More Details :" + visitTestData.toString());

			Assert.assertTrue(type(selenium,"subjectiveInput", visitTestData.patientSubjective),"Could not enter the test data for Patient Subjective; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"objectiveInput", visitTestData.patientObjective),"Could not enter the test data for Patient Objective; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"assessmentInput", visitTestData.patientAssessment),"Could not enter the test data for Patient Assessment; More Details :" + visitTestData.toString());
			Assert.assertTrue(type(selenium,"planInput", visitTestData.patientPlan),"Could not enter the test data for Patient Plan; More Details :" + visitTestData.toString());
						
			if(!isElementPresent(selenium, "workStatusInput") || !selenium.isVisible("workStatusInput")){
				if(click(selenium, "addTask")){
					waitForPageLoad(selenium);
				}
			}
			if(visitTestData.taskName != null && !visitTestData.taskName.equals(""))
				Thread.sleep(2000);
				select(selenium,"workStatusInput", visitTestData.taskName);

			Assert.assertTrue(type(selenium,"taskNotesInput", visitTestData.taskNotes),"Could not enter Task Notes; More Details :" + visitTestData.toString());
						

			if( visitTestData.sendTaskTo != null && !visitTestData.sendTaskTo.equals(""))
				Assert.assertTrue(select(selenium,"taskUsersInput", visitTestData.sendTaskTo),"Could not select Medication send task to; More Details :" + visitTestData.toString());

			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + visitTestData.toString());
			waitForPageLoad(selenium);
						


			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			if(selenium.isAlertPresent()){
				alertText = selenium.getAlert();
			}

			if(selenium.isElementPresent("//div[@class='errorPopup']/div/div/p")){
				if(selenium.isVisible("//div[@class='errorPopup']/div/div/p"))
					alertText = selenium.getText("//div[@class='errorPopup']/div/div/p");
			}

			if(visitTestData.alert.equalsIgnoreCase("Yes")){

				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(visitTestData.checkAlertMessage(alertText, visitTestData.alertMessage) || selenium.isTextPresent(visitTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+visitTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + visitTestData.toString());
			}else if (visitTestData.alert.equalsIgnoreCase("No")){
				if(!alertText.trim().toLowerCase(new java.util.Locale("en", "US")).contains("performed by") && !alertText.trim().toLowerCase(new java.util.Locale("en", "US")).contains("not signed")){
					Assert.assertFalse(!alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + visitTestData.toString());
				}
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + visitTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {				
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
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

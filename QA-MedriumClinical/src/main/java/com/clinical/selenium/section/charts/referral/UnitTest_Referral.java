package com.clinical.selenium.section.charts.referral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Referral extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_001";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_002";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_003";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_004";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_005";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Referral Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void referralDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_006";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Provider Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void providerNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_007";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Provider Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void providerNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_008";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Provider Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void providerNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_009";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_010";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with alphanumeric characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_011";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Task Note with special characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNoteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_012";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest referralTestData = new ChartsLib_UnitTest();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "UT_RE_013";
		referralTestData.fetchCodingInterfaceTestData();
		addReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: addReferral
	 * @Description : Function to create a New Referral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 24, 2010
	 */	
	public void addReferral(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest referralTestData){
		Selenium selenium = null;
		String alertText = null;	
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + referralTestData.toString());
			loginFromPublicSite(selenium, referralTestData.userAccount, referralTestData.userName, referralTestData.userPassword);
			searchPatient(selenium,referralTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Referral link                               //
			//--------------------------------------------------------------------//

			waitForPageLoad(selenium);
			Assert.assertTrue(selenium.isElementPresent("referrals"),"Referral link is available");

			click(selenium,"referrals");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add referral Link");
			waitForPageLoad(selenium);
			
			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//
				
			if( referralTestData.providerName != null && !referralTestData.providerName.equals(""))
				Assert.assertTrue(selectValueFromAjaxList(selenium,"providersBoxBox", referralTestData.providerName),"Could not enter Provider Name; More Details :" + referralTestData.toString());

			Assert.assertTrue(enterDate(selenium,"referraldateInput", referralTestData.startDate),"Could not enter Date; More Details :" + referralTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",referralTestData.providerNotes),"Could not enter Notes; More Details :" + referralTestData.toString());
			
			click(selenium, "addTask");
			waitForPageLoad(selenium);

			if(referralTestData.taskName != null && !referralTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium,"workStatusInput", referralTestData.taskName),"Could not select Work Status; More Details :" + referralTestData.toString());

			if(referralTestData.sendTaskTo != null && !referralTestData.sendTaskTo.equals(""))
				Assert.assertTrue(select(selenium,"taskUsersInput", referralTestData.sendTaskTo),"Could not select Send Task To; More Details :" + referralTestData.toString());

			type(selenium,"taskNotesInput", referralTestData.taskNotes);

			Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details :" + referralTestData.toString());				
			
			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText == null && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(referralTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(referralTestData.checkAlertMessage(alertText, referralTestData.alertMessage) || selenium.isTextPresent(referralTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+referralTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + referralTestData.toString());
			}else if (referralTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + referralTestData.toString());
			}

		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + referralTestData.toString());
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
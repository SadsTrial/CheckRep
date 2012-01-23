package com.clinical.selenium.section.charts.socialHistory;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_SocialHistory extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Smoking Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_001";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Smoking Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingStartDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_002";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Smoking Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingStartDatewithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_003";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Smoking Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_004";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Smoking Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingStartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_005";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Smoking Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_006";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Smoking Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_007";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Smoking Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void smokingNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_008";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pregnancy Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyStartDateWsithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_009";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pregnancy Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_010";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pregnancy Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_011";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pregnancy Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_012";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Pregnancy Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyStartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_013";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Pregnancy Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_014";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Pregnancy Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_015";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Pregnancy Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pregnancyNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_016";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Lactating Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_017";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Lactating Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingStartDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_018";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Lactating Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_019";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Lactating Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_020";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Lactating Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingStartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_021";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Lactating Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser","webSite"})
	public void lactatingNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_022";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Lactating Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_023";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Lactating Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lactatingNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest socialTestData = new ChartsLib_UnitTest();
		socialTestData.workSheetName = "SocialHistory";
		socialTestData.testCaseId = "UT_SH_024";
		socialTestData.fetchCodingInterfaceTestData();
		addSocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}


	/**
	 * @Function 	: addSocialHistory
	 * @Description : Function to add New SocialHistory
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 03, 2010
	 */
	public void addSocialHistory(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest socialTestData){

		Selenium selenium = null;
		String alertText = "";

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + socialTestData.toString());
			loginFromPublicSite(selenium, socialTestData.userAccount, socialTestData.userName, socialTestData.userPassword);
			searchPatient(selenium,socialTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking  Add SocialHistory Link                         //
			//--------------------------------------------------------------------//

			click(selenium,"socialHistory");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details :" + socialTestData.toString());
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			Assert.assertTrue(enterDate(selenium,"smokingStartDateInput",socialTestData.smokingStartDate),"Could not Enter Smoking Start Date; More Details :" + socialTestData.toString());
			Assert.assertTrue(type(selenium,"smokingNotesInput", socialTestData.smokingNote),"Could Not Enter Smoking Notes; More Details :" + socialTestData.toString());
			Assert.assertTrue(enterDate(selenium,"pregnancyStartDateInput", socialTestData.pregnancyStartDate),"Could Not Enter Pregnance Sart Date; More Details :" + socialTestData.toString());
			Assert.assertTrue(type(selenium,"pregnancyNotesInput", socialTestData.pregnancyNote),"Could Not Enter Pregnancy Input; More Details :" + socialTestData.toString());
			Assert.assertTrue(enterDate(selenium,"lactatingStartDateInput", socialTestData.lactatingStartDate),"Could not Enter Lactating Start Date ; More Details :" + socialTestData.toString());
			Assert.assertTrue(type(selenium,"lactatingNotesInput", socialTestData.lactatingNote),"Could Not Enter Lactating Notes Input; More Details :" + socialTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button");

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			if(socialTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(socialTestData.checkAlertMessage(alertText, socialTestData.alertMessage) || selenium.isTextPresent(socialTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+socialTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + socialTestData.toString());
			}else if (socialTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + socialTestData.toString());
			}

			click(selenium, "headerClinicalMenu");

		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + socialTestData.toString());
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

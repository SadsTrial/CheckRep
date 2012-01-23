package com.clinical.selenium.section.charts.socialHistory;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySmokingValues extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verify the Smoking List contains expected Options ")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySmokingOptions(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "VerifySmokingOptions";
		socialTestData.testCaseId = "TC_SH_001";
		socialTestData.fetchChartsTestData();
		verifySmokingOptions(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	/**
	 * @Function 	: verifySmokingOptions
	 * @Description : Function to verify options available in smoking dropdown in Add Social History
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Sep 03, 2010
	 */
	public void verifySmokingOptions(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib socialTestData){

		Selenium selenium = null;
		String smokingFields = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + socialTestData.toString());
			loginFromPublicSite(selenium, socialTestData.userAccount, socialTestData.userName, socialTestData.userPassword);
			searchPatient(selenium,socialTestData.patientID);

			//------------------------------------------------------------------------------------------//
			//  Step-2:  Click Add New Social History and get Options from Smoking data field           //
			//------------------------------------------------------------------------------------------//

			click(selenium,"socialHistory");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details" +socialTestData.toString());
			waitForPageLoad(selenium);
			
			smokingFields = selenium.getText("smokingTypeCodeInput");

			//----------------------------------------------------------------------------//
			//  Step-3:  Check the Smoking data field contains expected options           //
			//----------------------------------------------------------------------------//

			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("current every day smoker".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Current every day smoker Option; Detailed data:" + socialTestData.toString());
			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("Current some day smoker".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Current some day smoker Option; Detailed data:" + socialTestData.toString());
			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("Former smoker".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Former smoker Option; Detailed data:" + socialTestData.toString());
			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("Never smoker".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Never smoker Option; Detailed data:" + socialTestData.toString());
			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("Smoker, current status unknown".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Smoker, current status unknown Option; Detailed data:" + socialTestData.toString());
			Assert.assertTrue(smokingFields.trim().toLowerCase(new java.util.Locale("en", "US")).contains("Unknown if ever smoked".trim().toLowerCase(new java.util.Locale("en", "US"))),"Smoking field doesn't contains the Unknown if ever smoked Option; Detailed data:" + socialTestData.toString());

		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + socialTestData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {
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

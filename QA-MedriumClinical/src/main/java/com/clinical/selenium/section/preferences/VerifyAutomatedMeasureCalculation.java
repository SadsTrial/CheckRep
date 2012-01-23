package com.clinical.selenium.section.preferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.thoughtworks.selenium.Selenium;

public class VerifyAutomatedMeasureCalculation extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying clinical Quality measure")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAutomatedMeasureCalculation(String seleniumHost, int seleniumPort,String browser, String webSite){
		validateAutomatedMeasureCalculation(seleniumHost, seleniumPort, browser, webSite);
	}

	/**
	 * @Function 	: validateClinicalQualityMeasure
	 * @Description : Function to validate Clinical Quality Measure
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: AspireQA
	 * @Created on 	: Sep 15, 2010
	 */

	@SuppressWarnings("deprecation")
	public void validateAutomatedMeasureCalculation(String seleniumHost, int seleniumPort,String browser, String webSite){
		
		Selenium selenium = null;
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		Date currentDate = new Date();
		currentDate.setDate(currentDate.getDate()-1);
		String valDate = dateFormat.format(currentDate);
		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		try{
			
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :"  );
			loginFromPublicSite(selenium,userAccount, userName, userPassword);

			click(selenium, "preferences");
			waitForPageLoad(selenium);

			click(selenium, "QualityMeasures");
			waitForPageLoad(selenium);

			click(selenium, "automatedCoreMenuMeasureTab");
			waitForPageLoad(selenium);
			Assert.assertTrue(type(selenium,"css=input.inputText.inputDateText",valDate),"Could not Enter From Date");
			Assert.assertTrue(type(selenium,"css=#toDate > input.inputText.inputDateText",valDate),"Could not Enter To Date");
			Assert.assertTrue(click(selenium,"run"),"Could not Click Update Results button");
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForElement(selenium, "//table[@id='automatedCoreMenuMeasureTable']/tbody/tr/td/div", 50000),"Measure Results are not displayed");
			Assert.assertTrue(selenium.isElementPresent("//table[@id='automatedCoreMenuMeasureTable']/tbody/tr/td/div"),"Expected Measure is not displyed; Automated Quality Measure verification Failed");
			}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*);");
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
}

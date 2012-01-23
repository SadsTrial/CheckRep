package com.clinical.selenium.section.preferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.thoughtworks.selenium.Selenium;

public class VerifyClinicalQualityMeasure extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying clinical Quality measure")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyClinicalQualityMeasure(String seleniumHost, int seleniumPort,String browser, String webSite){
		
		validateClinicalQualityMeasure(seleniumHost, seleniumPort, browser, webSite);
	}

	/**
	 * @Function 	: validateClinicalQualityMeasure
	 * @Description : Function to validate Clinical Quality Measure
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Sep 15, 2011
	 */

	@SuppressWarnings("deprecation")
	public void validateClinicalQualityMeasure(String seleniumHost, int seleniumPort,String browser, String webSite){

		int counter = 1;
		boolean isMeasuredVerified = false;
		String qualityMeasure ="";
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

			click(selenium, "css=a.qualityMeasureEditButton");
			waitForPageLoad(selenium);

			Assert.assertTrue(isElementPresent(selenium, "//div[@id='fixedcontent']/div/div[3]/div/div/div[2]/div/div[2]/div/div[2]/div/div[2]/table/tbody/tr/td/div/input"),"Qualiy Measures is not displayed");

			qualityMeasure = "Blood pressure measurement";
			if(!selenium.isChecked("0013")){
				selenium.click("0013");
				selenium.click("qualityMeasureEditSave");
				waitForPageLoad(selenium);
			}

			selenium.click("clinicalQualityMeasureTab");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(type(selenium,"css=input.inputText.inputDateText",valDate),"Could not Enter From Date");
			Assert.assertTrue(type(selenium,"css=#toDate > input.inputText.inputDateText",valDate),"Could not Enter To Date");
			
			Assert.assertTrue(click(selenium,"run"),"Could not Click Update Results button");
			waitForPageLoad(selenium);
			counter = 1;
			waitForElement(selenium, "//table[@id='clinicalQualityMeasureTable']/tbody/tr["+counter+"]/td/div", 50000);
			while(selenium.isElementPresent("//table[@id='clinicalQualityMeasureTable']/tbody/tr["+counter+"]/td/div")){
				String valMeasure =selenium.getText("//table[@id='clinicalQualityMeasureTable']/tbody/tr["+counter+"]/td/div");
				if(valMeasure.trim().equalsIgnoreCase(qualityMeasure.trim())){
					isMeasuredVerified = true;
					break;
				}
				counter ++;
			}
			
			Assert.assertTrue(isMeasuredVerified,"Expected Measure is not displyed; Clinical Quality Measure verification Failed");
			
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

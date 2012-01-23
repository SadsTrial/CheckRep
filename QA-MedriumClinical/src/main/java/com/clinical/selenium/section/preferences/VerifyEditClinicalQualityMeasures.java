package com.clinical.selenium.section.preferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.preferences.AbstractPreferenceTest;
import com.thoughtworks.selenium.Selenium;

public class VerifyEditClinicalQualityMeasures extends AbstractPreferenceTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying clinical Quality measure")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyEditClinicalQualityMeasures(String seleniumHost, int seleniumPort,String browser, String webSite){

		validateEditClinicalQualityMeasures(seleniumHost, seleniumPort, browser, webSite);
	}

	/**
	 * @Function 	: validateEditClinicalQualityMeasures
	 * @Description : Function to Validate Edit Clinical Quality Measures
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Sep 16, 2011
	 */

	@SuppressWarnings("deprecation")
	public void validateEditClinicalQualityMeasures(String seleniumHost, int seleniumPort,String browser, String webSite){

		int counter = 1;
		boolean isMeasuredVerified1 = false;
		boolean isMeasuredVerified2 = false;
		Selenium selenium = null;
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		Date currentDate = new Date();
		currentDate.setDate(currentDate.getDate()-1);
		String valDate = dateFormat.format(currentDate);
		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		String qualityMeasure1 = "Tobacco Use Assessment"; //0013
		String qualityMeasure2 = "Hemoglobin A1c management";//0059
		String qualityMeasure3 = "Diabetes: Blood Pressure Management";//0061
		String qualityMeasure4 = "Influenza Vaccination";//0041

		String tmpArray[]= new String[5];
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

			Assert.assertTrue(isElementPresent(selenium, "0028a"),"Qualiy Measures is not displayed");

			if(!selenium.isChecked("0028a")){
				selenium.click("0028a");
				Thread.sleep(3000);
				if(!selenium.isChecked("0059")){
					selenium.click("0059");
					Thread.sleep(3000);
				}
				if(selenium.isChecked("0061")){
					selenium.click("0061");
					Thread.sleep(3000);
				}
				if(selenium.isChecked("0041")){
					selenium.click("0041");
					Thread.sleep(3000);
				}
				tmpArray[0] = qualityMeasure1;
				tmpArray[1] = qualityMeasure2;
				tmpArray[2] = qualityMeasure3;
				tmpArray[3] = qualityMeasure4;

			}else{
				selenium.click("0028a");
				Thread.sleep(3000);
				if(selenium.isChecked("0059")){
					selenium.click("0059");
					Thread.sleep(3000);
				}
				
				
				if(!selenium.isChecked("0061")){
					selenium.click("0061");
					Thread.sleep(3000);
				}
				if(!selenium.isChecked("0041")){
					selenium.click("0041");
					Thread.sleep(3000);
				}
				
				tmpArray[0] = qualityMeasure3;
				tmpArray[1] = qualityMeasure4;
				tmpArray[2] = qualityMeasure1;
				tmpArray[3] = qualityMeasure2;
			}

			selenium.click("qualityMeasureEditSave");
			waitForPageLoad(selenium);

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
				if(valMeasure.trim().equalsIgnoreCase(tmpArray[0].trim())){
					isMeasuredVerified1 = true;
				}else if(valMeasure.trim().equalsIgnoreCase(tmpArray[1].trim())){
					isMeasuredVerified2 = true;
				}
				counter ++;
			}

			Assert.assertTrue(isMeasuredVerified1,"Expected Measure("+ tmpArray[0] +") is not displyed; Edit Clinical Quality Measure verification Failed");
			Assert.assertTrue(isMeasuredVerified2,"Expected Measure("+ tmpArray[1] +") is not displyed; Edit Clinical Quality Measure verification Failed");

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*);");
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){				
				e1.printStackTrace();
			}
		} catch (InterruptedException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*);");
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

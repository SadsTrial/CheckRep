package com.clinical.selenium.section.charts.socialHistory;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySocialHistory extends AbstractChartsTest {
	
    @Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verifying SocialHistory fields for Male")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySocialHistory001(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "VerifySocialHistory";
		socialTestData.testCaseId = "TC_SH_001";
		socialTestData.fetchChartsTestData();
		verifySocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verifying the  SocialHistory fields for Female")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySocialHistory002(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib socialTestData = new ChartsLib();
		socialTestData.workSheetName = "VerifySocialHistory";
		socialTestData.testCaseId = "TC_SH_002";
		socialTestData.fetchChartsTestData();
		verifySocialHistory(seleniumHost, seleniumPort, browser, webSite, socialTestData);
	}

	/**
	 * @Function 	: verifySocialHistory
	 * @Description : Function to verify the SocialHistory Fields
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Oct 21, 2010
	 */
	public void verifySocialHistory(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib socialTestData){

		Selenium selenium = null;
		String patientGender = null;

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + socialTestData.toString());
			loginFromPublicSite(selenium, socialTestData.userAccount, socialTestData.userName, socialTestData.userPassword);
			searchPatient(selenium,socialTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Social History and enter details           //
			//--------------------------------------------------------------------//

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			waitForElement(selenium,"//div[@id='patientinformation']/div/div/div[2]",20000);
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientinformation']/div/div/div[2]"),"Patient Personal details are not displayed; More Details" +socialTestData.toString());
			if(selenium.getText( "//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("female")){
				patientGender = "Female";	
			}else if(selenium.getText("//div[@id='patientinformation']/div/div/div[2]").toLowerCase(new java.util.Locale("en", "US")).contains("male")){
				patientGender = "Male";
			}else{
				Assert.fail("Patient Gender is not displayed:"+selenium.getText("//div[@id='patientinformation']/div/div/div[2]")+"; More Details :"+ socialTestData.toString());
			}


			click(selenium,"socialHistory");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"socialHistoryAdd"),"Could not find Add SocialHistory Link; More Details" +socialTestData.toString());
			waitForPageLoad(selenium);
						

			if(patientGender.trim().equals("Male")){

				Assert.assertTrue(isElementPresent(selenium,"smokingTypeCodeInput") && selenium.isVisible("smokingTypeCodeInput"),"Smoking Field is not present; More Details" +socialTestData.toString());
				Assert.assertFalse(isElementPresent(selenium,"//span[@id='yesPregnancyTypeCodeInput']/input") && selenium.isVisible("//span[@id='yesPregnancyTypeCodeInput']/input"),"Pregfnancy fied is displyaed for male patient; More Details" +socialTestData.toString());
				Assert.assertFalse(isElementPresent(selenium,"//span[@id='yesLactatingTypeCodeInput']/input") && selenium.isVisible("//span[@id='yesLactatingTypeCodeInput']/input"),"Lactating Field is displyed for Male Patient; More Details" +socialTestData.toString());
			}else{
				Assert.assertTrue(isElementPresent(selenium,"smokingTypeCodeInput") && selenium.isVisible("smokingTypeCodeInput"),"Smoking Field is not present; More Details" +socialTestData.toString());
				Assert.assertTrue(isElementPresent(selenium,"//span[@id='yesPregnancyTypeCodeInput']/input") && selenium.isVisible("//span[@id='yesPregnancyTypeCodeInput']/input"),"Pregfnancy fied is not displyaed for Female patient; More Details" +socialTestData.toString());
				Assert.assertTrue(isElementPresent(selenium,"//span[@id='yesLactatingTypeCodeInput']/input") && selenium.isVisible("//span[@id='yesLactatingTypeCodeInput']/input"),"Lactating Field is not displyed for Female Patient; More Details" +socialTestData.toString());
			}


		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + socialTestData.toString());try {
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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			
			counter++;
		}
	}
}

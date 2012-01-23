package com.clinical.selenium.section.charts.referral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForReferral extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Save button and Data for Referral")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedReferral(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib referralTestData = new ChartsLib();
		referralTestData.workSheetName = "VerifySaveButtonForReferral";
		referralTestData.testCaseId = "TC_REF_001";
		referralTestData.fetchChartsTestData();
		verifySaveButtonForReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: createReferral
	 * @Description : Function to create a New Referral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 26, 2011
	 */	
	public void verifySaveButtonForReferral(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib referralTestData){

		Selenium selenium = null;
		
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + referralTestData.toString());
			loginFromPublicSite(selenium, referralTestData.userAccount, referralTestData.userName, referralTestData.userPassword);
			searchPatient(selenium,referralTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Referral and enter details                 //
			//--------------------------------------------------------------------//

			click(selenium,"referrals");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add Referral Link; More Details" +referralTestData.toString());
			waitForPageLoad(selenium);
			
			Assert.assertTrue(selectValueFromAjaxList(selenium,"providersBoxBox", referralTestData.providerName),"Could not enter Provider Name; More Details" +referralTestData.toString());
			Assert.assertTrue(enterDate(selenium,"referraldateInput", referralTestData.startDate),"Could not enter Date; More Details" +referralTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",referralTestData.providerNotes),"Could not enter Notes; More Details" +referralTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +referralTestData.toString());
			Assert.assertTrue(select(selenium,"workStatusInput", referralTestData.taskName),"Could not select Work Status; More Details" +referralTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", referralTestData.sendTaskTo),"Could not select Send Task To; More Details" +referralTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", referralTestData.taskNotes),"Could not enter Task Notes; More Details" +referralTestData.toString());


			click(selenium,"referrals");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("referralsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Referrals Add(+)  button is OFF and not backgrounded by Orange; More Details" +referralTestData.toString());  
			Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add Referral Link; More Details" +referralTestData.toString());
			waitForPageLoad(selenium);
						
			verifyStoredValues(selenium, referralTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +referralTestData.toString());
		}
		catch (RuntimeException e) {
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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			
			counter++;
		}
	}

	public void verifyStoredValues(Selenium selenium, ChartsLib referralTestData){

		Assert.assertTrue(getText(selenium, "providersBoxLabel").trim().toLowerCase(new java.util.Locale("en", "US")).contains(referralTestData.providerName.trim().toLowerCase(new java.util.Locale("en", "US"))),"Un saved Providers Input Value is not available; It is modified; More Details" +referralTestData.toString());
		Assert.assertEquals(getValue(selenium, "referraldateInput").trim(),referralTestData.startDate.trim(),"Un saved Referral Date Input Value is not available; It is modified; More Details" +referralTestData.toString());
		Assert.assertEquals(getValue(selenium, "notesInput").trim(),referralTestData.providerNotes.trim(),"Un saved Notes Input Value is not available; It is modified; More Details" +referralTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "workStatusInput").trim(),referralTestData.taskName.trim(),"Un saved WorkStatus Input Value is not available; It is modified; More Details" +referralTestData.toString());

	}

}

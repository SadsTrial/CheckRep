package com.clinical.selenium.section.charts.referral;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewReferral extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Adding New Referral")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewReferral(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib referralTestData = new ChartsLib();
		referralTestData.workSheetName = "Referral";
		referralTestData.testCaseId = "TC_REF_001";
		referralTestData.fetchChartsTestData();
		createReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: createReferral
	 * @Description : Function to create a New Referral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 13, 2010
	 */	
	public void createReferral(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib referralTestData){

		Selenium selenium = null;
		boolean isRecordFoundInReferrals = false;
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
			
			while((selenium.isElementPresent("patientReferralListMoreLink") && selenium.isVisible("patientReferralListMoreLink") )){
				selenium.click("patientReferralListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "referral"); 	
			createReferral(selenium, referralTestData);
			waitForPageLoad(selenium);
			while((selenium.isElementPresent("patientReferralListMoreLink") && selenium.isVisible("patientReferralListMoreLink") )){
				selenium.click("patientReferralListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "referral");	
			secondList.removeAll(firstList);
			
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ referralTestData.toString());
			}
			
			//--------------------------------------------------------------------//
			//  Step-3:  Verifying whether the details entered are saved properly //
			//--------------------------------------------------------------------//

			while(!(selenium.isElementPresent(idOfTheNewlyAddedRecord)) && selenium.isElementPresent( "patientReferralListMoreLink" ) && selenium.isVisible( "patientReferralListMoreLink" ) ){
				selenium.click("patientReferralListMoreLink");
				waitForPageLoad(selenium);
			}
			Assert.assertTrue(selenium.isElementPresent(idOfTheNewlyAddedRecord),"Referral Record not found; More details: " + referralTestData.toString());
			
			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			
			if(verifyStoredValues(selenium, referralTestData)){
				isRecordFoundInReferrals = true;
			}
			
			click(selenium, "showList");
			waitForPageLoad(selenium);
			Assert.assertTrue(isRecordFoundInReferrals,"The referral was not found once added:" + referralTestData.toString());
			Assert.assertTrue(isRecordFoundInReferrals,"The referral was not found once added:" + referralTestData.toString());
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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}

			counter++;
		}
	}



	public boolean verifyStoredValues(Selenium selenium, ChartsLib referralTestData){


		if(!getText(selenium, "name").trim().toLowerCase(new java.util.Locale("en", "US")).contains(referralTestData.providerName.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium, "referraldate").trim().equalsIgnoreCase(referralTestData.startDate.trim())){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().equalsIgnoreCase(referralTestData.providerNotes.trim())){
			return false;
		}

		if(!getText(selenium, "workStatus").trim().equalsIgnoreCase(referralTestData.taskName.trim())){
			return false;
		}

		if(!getText(selenium, "taskUsers").trim().equalsIgnoreCase(referralTestData.sendTaskTo.trim())){
			return false;
		}

		if(!getText(selenium, "taskNotes").trim().equalsIgnoreCase(referralTestData.taskNotes.trim())){
			return false;
		}
		return true;
	}
}

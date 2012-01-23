package com.clinical.selenium.section.charts.referral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateReferral extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Test for Adding New Referral")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateReferral(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib referralTestData = new ChartsLib();
		referralTestData.workSheetName = "UpdateReferral";
		referralTestData.testCaseId = "TC_URE_001";
		referralTestData.fetchChartsTestData();
		updateExistingReferral(seleniumHost, seleniumPort, browser, webSite, referralTestData);
	}

	/**
	 * @Function 	: updateExistngReferral
	 * @Description : Function to update an existing Referral
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 27, 2010
	 */	
	public void updateExistingReferral(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib referralTestData){

		Selenium selenium = null;
		int counter = 1;
		String uniqueID = null;
		boolean isRecordFoundInReferrals = false;
		boolean isReferralFound = false;
		String referralCount = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + referralTestData.toString());
			loginFromPublicSite(selenium, referralTestData.userAccount, referralTestData.userName, referralTestData.userPassword);
			searchPatient(selenium,referralTestData.patientID);
			//--------------------------------------------------------------------//
			//  Step-2:  Navigating to Referral Page and Capturing Referral Count //
			//--------------------------------------------------------------------//

			click(selenium,"referrals");
						
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture existing Referrals Count; More Details" +referralTestData.toString());
			referralCount = getListCount(selenium.getText("Referrals"));

			counter = 1;

			while(selenium.isElementPresent( "//div[@id='patientReferralList']/table/tbody[1]/tr["+counter+"]/td[1]")  ){
				if(!getText(selenium, "//div[@id='patientReferralList']/table/tbody[1]/tr["+counter+"]/td[1]").toLowerCase(new java.util.Locale("en", "US")).contains("signed"))
				{
					isReferralFound = true;
					break;
				}
				counter++;
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Add a New Referral if no referral available to Update    //
			//--------------------------------------------------------------------//

			if(referralCount != null && !referralCount.trim().equals("")){
				if (Integer.parseInt(referralCount)== 0 || !isReferralFound){
					referralTestData = null;
					referralTestData = new ChartsLib();
					referralTestData.workSheetName = "Referral";
					referralTestData.testCaseId = "TC_REF_001";		
					referralTestData.fetchChartsTestData();
					createReferral(selenium, referralTestData);
					referralCount = "1";
					counter =1;
				}
			}

			//-------------------------------------------------------------------//
			//  Step-4:  Opening an existing Referral and updating it             //
			//--------------------------------------------------------------------//

			referralTestData = null;
			referralTestData = new ChartsLib();
			referralTestData.workSheetName = "UpdateReferral";
			referralTestData.testCaseId = "TC_URE_001";		
			referralTestData.fetchChartsTestData();
			String idOfTheRecord = null;
			if(isElementPresent(selenium, "//div[@id='patientReferralList']/table/tbody[1]/tr["+counter+"]/td[1]")){				

				idOfTheRecord = selenium.getAttribute("//div[@id='patientReferralList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord.split("referral")[1];
				click(selenium, idOfTheRecord);				
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
							
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				referralTestData.providerName =  referralTestData.providerName != "" ? referralTestData.providerName.trim() : getText(selenium, "providersBoxLabel").trim();
				referralTestData.startDate =  referralTestData.startDate != "" ? referralTestData.startDate.trim() : getValue(selenium, "referraldateInput").trim();
				referralTestData.providerNotes =  referralTestData.providerNotes != "" ? referralTestData.providerNotes.trim() : getValue(selenium, "notesInput").trim();
				referralTestData.taskName =  referralTestData.taskName != "" ? referralTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput").trim();
				referralTestData.sendTaskTo =  referralTestData.sendTaskTo != "" ? referralTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput").trim();
				referralTestData.taskNotes =  referralTestData.taskNotes != "" ? referralTestData.taskNotes.trim() : getValue(selenium, "taskNotesInput").trim();

				Assert.assertTrue(selectValueFromAjaxList(selenium,"providersBoxBox", referralTestData.providerName),"Could not enter Provider Name; More Details" +referralTestData.toString());
				Assert.assertTrue(enterDate(selenium,"referraldateInput", referralTestData.startDate),"Could not enter Date; More Details" +referralTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput",referralTestData.providerNotes),"Could not enter Notes; More Details" +referralTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				
				if(!isElementPresent(selenium,"workStatusInput")){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +referralTestData.toString());
				}

				Assert.assertTrue(select(selenium,"workStatusInput", referralTestData.taskName),"Could not select Work Status; More Details" +referralTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", referralTestData.sendTaskTo),"Could not select Send Task To; More Details" +referralTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", referralTestData.taskNotes),"Could not enter Task Notes; More Details" +referralTestData.toString());

				Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details" +referralTestData.toString());				
				waitForPageLoad(selenium);

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Referral not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
				}

				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
				Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture Referral Count after updating a Referral; More Details" +referralTestData.toString());
				Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("Referrals"))),Integer.parseInt(referralCount),"The Referral count has a change after updating an existing Referral; More Details" +referralTestData.toString());
			}
			//--------------------------------------------------------------------//
			//  Step-5:  Verifying whether the updated Referral is stored properly//
			//--------------------------------------------------------------------//
			counter = 1;
			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + referralTestData.toString());
			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("patientReferralListMoreLink") && selenium.isVisible("patientReferralListMoreLink")){
					click(selenium, "patientReferralListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInReferrals = verifyStoredValues(selenium, referralTestData);
			Assert.assertTrue(isRecordFoundInReferrals,"None of the records matched with data stored");
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

	public  void waitForAlert(Selenium selenium){
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

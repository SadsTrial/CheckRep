package com.clinical.selenium.section.charts.labRequest;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForLabRequest extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying Save button and Data for Un saved Lab Request")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedLabrequest(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib labRequestTestData = new ChartsLib();
		labRequestTestData.workSheetName = "VerifySaveButtonForLabRequest";
		labRequestTestData.testCaseId = "TC_LAB_001";
		labRequestTestData.fetchChartsTestData();
		verifySaveButtonForUnSavedLabrequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForUnSavedLabrequest
	 * @Description : Function to verify SaveButton For UnSaved Labrequest
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 26, 2011
	 */
	public void verifySaveButtonForUnSavedLabrequest(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib labRequestTestData){

		Selenium selenium = null;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + labRequestTestData.toString());
			loginFromPublicSite(selenium, labRequestTestData.userAccount, labRequestTestData.userName, labRequestTestData.userPassword);
			searchPatient(selenium,labRequestTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Lab Request and enter details              //
			//--------------------------------------------------------------------//

			click(selenium,"labRequest");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addLabRequest"),"Could not find Add Lab Request Link; More Details" + labRequestTestData.toString());
			waitForPageLoad(selenium);
						

			Assert.assertTrue(select(selenium,"providersInput", labRequestTestData.providerName),"Could not enter Provider Name; More Details" + labRequestTestData.toString());
			Assert.assertTrue(enterDate(selenium,"expecteddateInput", labRequestTestData.expectedDate),"Could not enter Expected Date; More Details" + labRequestTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",labRequestTestData.providerNotes),"Could not enter Notes; More Details" + labRequestTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", labRequestTestData.status),"Could not enter status; More Details" + labRequestTestData.toString());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + labRequestTestData.toString());
			waitForPageLoad(selenium);
			
			Assert.assertTrue(select(selenium,"workStatusInput", labRequestTestData.taskName),"Could not select Work Status; More Details" + labRequestTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", labRequestTestData.sendTaskTo),"Could not select Send Tast To; More Details" + labRequestTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", labRequestTestData.taskNotes),"Could not enter Task Notes; More Details" + labRequestTestData.toString());

			click(selenium,"labRequest");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("labRequestAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"LabRequest Add(+)  button is OFF and not backgrounded by Orange; More Details" + labRequestTestData.toString());
			Assert.assertTrue(click(selenium,"addLabRequest"),"Could not find Add Lab Request Link; More Details" + labRequestTestData.toString());
			waitForPageLoad(selenium);
			
			verifyStoredValues(selenium, labRequestTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" + labRequestTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + labRequestTestData.toString());
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


	public void verifyStoredValues(Selenium selenium, ChartsLib labRequestTestData){

		Assert.assertEquals(getSelectedValue(selenium, "providersInput").trim(),labRequestTestData.providerName.trim(),"Un saved Providers Input Value is not available; It is modified; More Details" + labRequestTestData.toString());
		Assert.assertEquals(getValue(selenium, "expecteddateInput").trim(),labRequestTestData.expectedDate.trim(),"Un saved Expected Date Input Value is not available; It is modified; More Details" + labRequestTestData.toString());
		Assert.assertEquals(getValue(selenium, "notesInput").trim(),labRequestTestData.providerNotes.trim(),"Un saved Notes Input Value is not available; It is modified; More Details" + labRequestTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "itemStatusInput").trim(),labRequestTestData.status.trim(),"Un saved Item Status Input Value is not available; It is modified; More Details" + labRequestTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "workStatusInput").trim(),labRequestTestData.taskName.trim(),"Un saved Work Status Input Value is not available; It is modified; More Details" + labRequestTestData.toString());
		
	}
}

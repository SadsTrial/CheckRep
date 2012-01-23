package com.clinical.selenium.section.charts.radiologyOrder;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForRadiologyOrder extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying Save button and Data for Radiology Order")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib radiologyOrderTestData = new ChartsLib();
		radiologyOrderTestData.workSheetName = "VerifySaveButtonRadiologyOrder";
		radiologyOrderTestData.testCaseId = "TC_RAD_001";
		radiologyOrderTestData.fetchChartsTestData();
		verifySaveButtonForRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForRadiologyOrder
	 * @Description : Function to verify Save Button For RadiologyOrder
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 26, 2011
	 */
	public void verifySaveButtonForRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib radiologyOrderTestData){

		Selenium selenium = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Radiology Order  and enter details              //
			//--------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addRadiologyOrder"),"Could not find Add Radiology Order Link; More Details" +radiologyOrderTestData.toString());
			waitForPageLoad(selenium);
			
			Assert.assertTrue(select(selenium,"providersInput", radiologyOrderTestData.providerName),"Could not enter Provider Name; More Details" +radiologyOrderTestData.toString());
			Assert.assertTrue(enterDate(selenium,"expecteddateInput", radiologyOrderTestData.expectedDate),"Could not enter Expected Date; More Details" +radiologyOrderTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput",radiologyOrderTestData.providerNotes),"Could not enter Notes; More Details" +radiologyOrderTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", radiologyOrderTestData.status),"Could not enter status; More Details" +radiologyOrderTestData.toString());
			
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +radiologyOrderTestData.toString());
			waitForPageLoad(selenium);
						
			Assert.assertTrue(select(selenium,"workStatusInput", radiologyOrderTestData.taskName),"Could not select Work Status; More Details" +radiologyOrderTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", radiologyOrderTestData.sendTaskTo),"Could not select Send Tast To; More Details" +radiologyOrderTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", radiologyOrderTestData.taskNotes),"Could not enter Task Notes; More Details" +radiologyOrderTestData.toString());

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(selenium.getAttribute("radiologyOrderAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Radiology Order Add(+)  button is OFF and not backgrounded by Orange; More Details" +radiologyOrderTestData.toString());  
			Assert.assertTrue(click(selenium,"addRadiologyOrder"),"Could not find Add Radiology Order Link; More Details" +radiologyOrderTestData.toString());
			waitForPageLoad(selenium);
						
			verifyStoredValues(selenium, radiologyOrderTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +radiologyOrderTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + radiologyOrderTestData.toString());
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

	public void verifyStoredValues(Selenium selenium, ChartsLib radiologyOrderTestData){

		Assert.assertEquals(getSelectedValue(selenium, "providersInput").trim(),radiologyOrderTestData.providerName.trim(),"Un saved Providers Input Value is not available; It is modified; More Details" +radiologyOrderTestData.toString());
		Assert.assertEquals(getValue(selenium, "expecteddateInput").trim(),radiologyOrderTestData.expectedDate.trim(),"Un saved Expected Date Input Value is not available; It is modified; More Details" +radiologyOrderTestData.toString());
		Assert.assertEquals(getValue(selenium, "notesInput").trim(),radiologyOrderTestData.providerNotes.trim(),"Un saved Notes Input Value is not available; It is modified; More Details" +radiologyOrderTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium, "itemStatusInput").trim(),radiologyOrderTestData.status.trim(),"Un saved Item Status Input Value is not available; It is modified; More Details" +radiologyOrderTestData.toString());
		Assert.assertEquals(selenium.getSelectedLabel("workStatusInput").trim(),radiologyOrderTestData.taskName.trim(),"Un saved Work Status Input Value is not available; It is modified; More Details" +radiologyOrderTestData.toString());
	}
}

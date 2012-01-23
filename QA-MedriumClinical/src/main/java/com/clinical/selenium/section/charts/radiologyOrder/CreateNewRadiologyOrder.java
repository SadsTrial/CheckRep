package com.clinical.selenium.section.charts.radiologyOrder;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewRadiologyOrder extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for creating a New Radiology Order")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib radiologyOrderTestData = new ChartsLib();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "TC_RAD_001";
		radiologyOrderTestData.fetchChartsTestData();
		createNewRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for creating a New In Active radiology Order")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewInactiveRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib radiologyOrderTestData = new ChartsLib();
		radiologyOrderTestData.workSheetName = "RadiologyOrder";
		radiologyOrderTestData.testCaseId = "TC_RAD_002";
		radiologyOrderTestData.fetchChartsTestData();
		createNewRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	/**
	 * @Function 	: createNewRadiologyOrder
	 * @Description : Function to create a New  Radiology Order
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 01, 2010
	 */
	public void createNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib radiologyOrderTestData){

		Selenium selenium = null;
		boolean isRecordFoundInActiveRadiologyOrder = false;
		boolean isRecordFoundInAllRadiologyOrder = false;
		boolean isRecordFoundInActivity = false;
		boolean isInactive = false;

		if(radiologyOrderTestData.status.equalsIgnoreCase("Inactive")){
			isInactive = true;
		}

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Radiology Order and enter details          //
			//--------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				click(selenium, "labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "radiologyorder"); 	
			createRadiologyOrder(selenium, radiologyOrderTestData);
			while(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				click(selenium, "labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "radiologyorder");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ radiologyOrderTestData.toString());
			}

			//--------------------------------------------------------------------------------------------------------//
			//  Step-3:  Verifying whether the details entered are saved properly in Radiology Order Current Section  //
			//--------------------------------------------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			while(!isElementPresent(selenium, idOfTheNewlyAddedRecord) && selenium.isVisible("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				click(selenium, "labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInActiveRadiologyOrder = verifyStoredValues(selenium, radiologyOrderTestData);
			}

			//----------------------------------------------------------------------------------------------------//
			//  Step-4:  Verifying whether the details entered are saved properly in Radiology Order All Section  //
			//----------------------------------------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);

			while(!isElementPresent(selenium, idOfTheNewlyAddedRecord) && selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				click(selenium, "labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllRadiologyOrder = verifyStoredValues(selenium, radiologyOrderTestData);

			//----------------------------------------------------------------------------------------------------//
			//            Step-4:  Verifying whether the details entered are saved properly in Activity           //
			//----------------------------------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity = verifyStoredValues(selenium, radiologyOrderTestData);

			if(isInactive)
			{
				Assert.assertFalse(isRecordFoundInActiveRadiologyOrder,"Radiology Order is not displyed in Active Radiology Order Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
				Assert.assertTrue(isRecordFoundInAllRadiologyOrder,"Radiology Order is not displyed in All Radiology Order Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Radiology Order is not displyed in activity Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
			}
			else
			{
				Assert.assertTrue(isRecordFoundInActiveRadiologyOrder,"Radiology Order is not displyed in Active Radiology Order Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
				Assert.assertTrue(isRecordFoundInAllRadiologyOrder,"Radiology Order is not displyed in All Radiology Order Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Radiology Order is not displyed in activity Section; Radiology Order Creation Failed; More Details : "+radiologyOrderTestData.toString());
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage() +" ; Detailed Data  : " + radiologyOrderTestData.toString());
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

	public boolean verifyRadiologyPanels(Selenium selenium, String valRadiologyPanel){
		String panelVal[] = valRadiologyPanel.split("-");
		String elementName = "//input[@name='"+panelVal[0]+"']";
		return(isChecked(selenium, elementName));
	}

	public boolean verifyStoredValues(Selenium selenium, ChartsLib radiologyOrderTestData){
		if(!getText(selenium, "provider").trim().equalsIgnoreCase(radiologyOrderTestData.providerName.trim())){
			return false;
		}

		if(!getText(selenium, "expecteddate").trim().equalsIgnoreCase(radiologyOrderTestData.expectedDate.trim())){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().equalsIgnoreCase(radiologyOrderTestData.providerNotes.trim())){
			return false;
		}

		if(!getText(selenium, "itemstatus").trim().equalsIgnoreCase(radiologyOrderTestData.status.trim())){
			return false;
		}

		if(!getText(selenium, "workStatus").trim().equalsIgnoreCase(radiologyOrderTestData.taskName.trim())){
			return false;
		}

		if(!getText(selenium, "taskUsers").trim().equalsIgnoreCase(radiologyOrderTestData.sendTaskTo.trim())){
			return false;
		}

		if(!getText(selenium, "taskNotes").trim().equalsIgnoreCase(radiologyOrderTestData.taskNotes.trim())){
			return false;
		}

		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[1]/td").trim().toLowerCase(new java.util.Locale("en", "US")).contains(radiologyOrderTestData.radiology.trim().split("-")[1].toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}


		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[2]/td").trim().toLowerCase(new java.util.Locale("en", "US")).contains(radiologyOrderTestData.MRI.trim().split("-")[1].toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[3]/td").trim().toLowerCase(new java.util.Locale("en", "US")).contains(radiologyOrderTestData.CT.trim().split("-")[1].toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[4]/td").trim().toLowerCase(new java.util.Locale("en", "US")).contains(radiologyOrderTestData.nuclearMedecine.trim().toLowerCase(new java.util.Locale("en", "US")).split("-")[1])){
			return false;
		}

		return true;
	}
}

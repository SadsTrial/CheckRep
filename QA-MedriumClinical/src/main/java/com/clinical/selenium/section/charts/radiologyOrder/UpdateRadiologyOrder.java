package com.clinical.selenium.section.charts.radiologyOrder;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateRadiologyOrder extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for updating a Radiology Order")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib radiologyOrderTestData = new ChartsLib();
		radiologyOrderTestData.workSheetName = "UpdateRadiologyOrder";
		radiologyOrderTestData.testCaseId = "TC_URAD_001";
		radiologyOrderTestData.fetchChartsTestData();
		updateExistingRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, radiologyOrderTestData);
	}

	/**
	 * @Function 	: updateExistingRadiologyOrder
	 * @Description : Function to update existing Radiology Order
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 02, 2010
	 */
	public void updateExistingRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib radiologyOrderTestData){

		Selenium selenium = null;
		String allRadiologyOrderCount = null;
		String currentRadiologCount = null;
		String uniqueID = null;
		boolean isRecordFoundInAllRadiologyOrder = false;
		boolean isRecordFoundInActivity = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);
			
			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(waitForValue(selenium, "CurrentRadiologyOrders", 120000),"Could not capture existing Radiology Order Count; More Details" +radiologyOrderTestData.toString());
			currentRadiologCount = getListCount(selenium.getText("CurrentRadiologyOrders"));
			
			//--------------------------------------------------------------------------------//
			//  Step-2: Add the New Lab Request if no lab requests available to update        //
			//--------------------------------------------------------------------------------//

			if(currentRadiologCount.trim() != null || !currentRadiologCount.trim().equals("")){
				if (Integer.parseInt(currentRadiologCount)== 0){

					radiologyOrderTestData = null;
					radiologyOrderTestData = new ChartsLib();
					radiologyOrderTestData.workSheetName = "RadiologyOrder";
					radiologyOrderTestData.testCaseId = "TC_RAD_001";		
					radiologyOrderTestData.fetchChartsTestData();
					createRadiologyOrder(selenium, radiologyOrderTestData);
					currentRadiologCount = "1";

				}
			}

			radiologyOrderTestData = null;
			radiologyOrderTestData = new ChartsLib();
			radiologyOrderTestData.workSheetName = "UpdateRadiologyOrder";
			radiologyOrderTestData.testCaseId = "TC_URAD_001";		
			radiologyOrderTestData.fetchChartsTestData();
						
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------------------//
			//  Step-3: Update the New Lab Request with new values                            //
			//--------------------------------------------------------------------------------//
			
			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);
			
			allRadiologyOrderCount = getListCount(selenium.getText("AllRadiologyOrders"));
			String idOfTheRecord = null;
			if(selenium.isElementPresent("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]/div")){
				idOfTheRecord =  selenium.getAttribute("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord.split("radiologyorder")[1];
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
							
				click(selenium, "actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				radiologyOrderTestData.providerName = radiologyOrderTestData.providerName != null && radiologyOrderTestData.providerName != "" ? radiologyOrderTestData.providerName.trim() : getSelectedValue(selenium,"providersInput");
				radiologyOrderTestData.expectedDate = radiologyOrderTestData.expectedDate != null && radiologyOrderTestData.expectedDate != "" ? radiologyOrderTestData.expectedDate.trim() : getValue(selenium,"expecteddateInput");
				radiologyOrderTestData.providerNotes = radiologyOrderTestData.providerNotes != null && radiologyOrderTestData.providerNotes != "" ? radiologyOrderTestData.providerNotes.trim() : getValue(selenium, "notesInput");
				radiologyOrderTestData.status = radiologyOrderTestData.status != null && radiologyOrderTestData.status != "" ? radiologyOrderTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput");
				radiologyOrderTestData.taskName = radiologyOrderTestData.taskName != null && radiologyOrderTestData.taskName != "" ? radiologyOrderTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput");
				radiologyOrderTestData.sendTaskTo = radiologyOrderTestData.sendTaskTo != null && radiologyOrderTestData.sendTaskTo != "" ? radiologyOrderTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput");
				radiologyOrderTestData.taskNotes = radiologyOrderTestData.taskNotes != null && radiologyOrderTestData.taskNotes != "" ? radiologyOrderTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

				Assert.assertTrue(select(selenium,"providersInput", radiologyOrderTestData.providerName),"Could not enter Provider Name; More Details" +radiologyOrderTestData.toString());
				Assert.assertTrue(enterDate(selenium,"expecteddateInput", radiologyOrderTestData.expectedDate),"Could not enter Expected Date; More Details" +radiologyOrderTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput",radiologyOrderTestData.providerNotes),"Could not enter Notes; More Details" +radiologyOrderTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", radiologyOrderTestData.status),"Could not enter status; More Details" +radiologyOrderTestData.toString());

				if(!isElementPresent(selenium,"workStatusInput")){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +radiologyOrderTestData.toString());
				}

				Assert.assertTrue(select(selenium,"workStatusInput", radiologyOrderTestData.taskName),"Could not select Work Status; More Details" +radiologyOrderTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", radiologyOrderTestData.sendTaskTo),"Could not select Send Tast To; More Details" +radiologyOrderTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", radiologyOrderTestData.taskNotes),"Could not enter Task Notes; More Details" +radiologyOrderTestData.toString());

				if(!radiologyOrderTestData.MRI.equals("")){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.MRI.trim()),"Could not select Radiology Panels : MRI Value; More Details" +radiologyOrderTestData.toString());
				}

				if(!radiologyOrderTestData.CT.equals("") ){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.CT.trim()),"Could not select Radiology Panels : CT Value; More Details" +radiologyOrderTestData.toString());
				}

				if(!radiologyOrderTestData.radiology.equals("")){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.radiology.trim()),"Could not select Radiology Panels : radiology Value; More Details" +radiologyOrderTestData.toString());
				}

				if(!radiologyOrderTestData.nuclearMedecine.equals("") ){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.nuclearMedecine.trim()),"Could not select Radiology Panels : Nuclear Medecine Value; More Details" +radiologyOrderTestData.toString());
				}

				Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details" +radiologyOrderTestData.toString());
				waitForPageLoad(selenium);

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Radiology Order not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
			}

			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + radiologyOrderTestData.toString());
			
			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllRadiologyOrders", 10000),"Could not capture Radiology Order Count after saving a Lab Request; More Details" +radiologyOrderTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("AllRadiologyOrders"))), Integer.parseInt(allRadiologyOrderCount),"The Radiology Order is not Saved Successfully, Radiology Order count has a change after updating Lab Request; More Details" +radiologyOrderTestData.toString());

			//----------------------------------------------------------------------------------------------------//
			//  Step-4:  Verifying whether the details entered are saved properly in Radiology Order All Section  //
			//----------------------------------------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);
			
			click(selenium,"AllRadiologyOrders");
			waitForPageLoad(selenium);
			
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInAllRadiologyOrder = verifyStoredValues(selenium, radiologyOrderTestData);
			
			//----------------------------------------------------------------------------------------------------//
			//            Step-4:  Verifying whether the details entered are saved properly in Activity           //
			//----------------------------------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInActivity = verifyStoredValues(selenium, radiologyOrderTestData);

			Assert.assertTrue(isRecordFoundInAllRadiologyOrder,"Radiology Order is not displyed in All Radiology Order Section; Radiology Order Updation Failed; More Details : "+radiologyOrderTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Radiology Order is not displyed in activity Section; Radiology Order Updation Failed; More Details : "+radiologyOrderTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
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

	public boolean selectRadiologyPanels(Selenium selenium, String valRadiologyPanel){
		String panelVal[] = valRadiologyPanel.split("-");
		String elementName = "//input[@name='"+panelVal[0]+"']";
		return(check(selenium, elementName));

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

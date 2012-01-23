package com.clinical.selenium.section.charts.labRequest;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateLabRequest extends AbstractChartsTest {
	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for updating a labRequest")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updatelabRequest(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib labRequestTestData = new ChartsLib();
		labRequestTestData.workSheetName = "UpdateLabRequest";
		labRequestTestData.testCaseId = "TC_ULB_001";
		labRequestTestData.fetchChartsTestData();
		updateExistingLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: updateExistingLabRequest
	 * @Description : Function to update existing Lab Request
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 30, 2010
	 */
	public void updateExistingLabRequest(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib labRequestTestData){

		Selenium selenium = null;
		String activeLabrequestCount = null;
		String uniqueID = null;
		boolean isRecordFoundInAllLabRequests = false;
		boolean isRecordFoundInActivity = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + labRequestTestData.toString());
			loginFromPublicSite(selenium, labRequestTestData.userAccount, labRequestTestData.userName, labRequestTestData.userPassword);
			searchPatient(selenium,labRequestTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Update the Lab Request with New Details and Save         //
			//--------------------------------------------------------------------//

			click(selenium,"labRequest");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentLabRequests", 120000),"Could not capture existing Lab Requests[Active] Count; More Details :" + labRequestTestData.toString());
			activeLabrequestCount = getListCount(selenium.getText("CurrentLabRequests"));

			//--------------------------------------------------------------------------------------//
			//  Step-3: Creates a New Lab Request if no existing Lab request available to Update    //
			//--------------------------------------------------------------------------------------//

			if(!activeLabrequestCount.trim().equals("")){
				if (Integer.parseInt(activeLabrequestCount)== 0){

					labRequestTestData = null;
					labRequestTestData = new ChartsLib();
					labRequestTestData.workSheetName = "LabRequest";
					labRequestTestData.testCaseId = "TC_LAB_001";		
					labRequestTestData.fetchChartsTestData();
					createLabRequest(selenium, labRequestTestData);
					activeLabrequestCount = "1";

				}
			}

			labRequestTestData = null;
			labRequestTestData = new ChartsLib();
			labRequestTestData.workSheetName = "UpdateLabRequest";
			labRequestTestData.testCaseId = "TC_ULB_001";		
			labRequestTestData.fetchChartsTestData();
						
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------------------------//
			//  Step-4: Update the Lab Request                                                      //
			//--------------------------------------------------------------------------------------//
		
			String idOfTheRecord = null;
			if(selenium.isElementPresent("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]")){
				idOfTheRecord = selenium.getAttribute("//div[@id='labRequestList']/table/tbody[1]/tr[1]//td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord.split("labrequest")[1];
				
				click(selenium,"//div[@id='labRequestList']/table/tbody[1]/tr[1]//td[1]/div/strong/a");
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
							
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);

				labRequestTestData.providerName = labRequestTestData.providerName != null && labRequestTestData.providerName != "" ? labRequestTestData.providerName.trim() : getSelectedValue(selenium,"providersInput");
				labRequestTestData.expectedDate = labRequestTestData.expectedDate != null && labRequestTestData.expectedDate != "" ? labRequestTestData.expectedDate.trim() : getValue(selenium,"expecteddateInput");
				labRequestTestData.providerNotes = labRequestTestData.providerNotes != null && labRequestTestData.providerNotes != "" ? labRequestTestData.providerNotes.trim() : getValue(selenium, "notesInput");
				labRequestTestData.status = labRequestTestData.status != null && labRequestTestData.status != "" ? labRequestTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput");
				labRequestTestData.taskName = labRequestTestData.taskName != null && labRequestTestData.taskName != "" ? labRequestTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput");
				labRequestTestData.sendTaskTo = labRequestTestData.sendTaskTo != null && labRequestTestData.sendTaskTo != "" ? labRequestTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput");
				labRequestTestData.taskNotes = labRequestTestData.taskNotes != null && labRequestTestData.taskNotes != "" ? labRequestTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

				Assert.assertTrue(select(selenium,"providersInput", labRequestTestData.providerName),"Could not enter Provider Name; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(enterDate(selenium,"expecteddateInput", labRequestTestData.expectedDate),"Could not enter Expected Date; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput",labRequestTestData.providerNotes),"Could not enter Notes; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", labRequestTestData.status),"Could not enter status; More Details :" + labRequestTestData.toString());

				if(!isElementPresent(selenium,"workStatusInput") || !selenium.isVisible("workStatusInput") ){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -");
					waitForPageLoad(selenium);

				}
				Assert.assertTrue(select(selenium,"workStatusInput", labRequestTestData.taskName),"Could not select Work Status; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", labRequestTestData.sendTaskTo),"Could not select Send Tast To; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", labRequestTestData.taskNotes),"Could not enter Task Notes; More Details :" + labRequestTestData.toString());

				Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + labRequestTestData.toString());
				waitForPageLoad(selenium);
							

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Lab Request not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + labRequestTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + labRequestTestData.toString());

			}
			
			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + labRequestTestData.toString());

			click(selenium,"labRequest");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(waitForElement(selenium, "CurrentLabRequests", 20000),"Could not capture Lab Request Count[Active] after saving a Lab Request; More Details :" + labRequestTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentLabRequests"))), Integer.parseInt(activeLabrequestCount),"The Lab Request is not Saved Successfully, Lab Request[Active] count has a change after updating Lab Request; More Details :" + labRequestTestData.toString());

			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
					click(selenium, "labRequestListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllLabRequests = verifyStoredValues(selenium, labRequestTestData);
			
			//------------------------------------------------------------------------------------------//
			//  Step-6: Verify whether the Lab Request is updated correctly in Activity section         //
			//------------------------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity =verifyStoredValues(selenium, labRequestTestData); 
			Assert.assertTrue(isRecordFoundInAllLabRequests,"Updated LabRequest Not Found in All LabRequest; Lab Request Updation Failed; More Details : "+labRequestTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Updated LabRequest Not Found in in Activity; Lab Request Updation Failed; More Details : "+labRequestTestData.toString());

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



	public boolean verifySelectedTestBatteries(Selenium selenium, String verfiyValue){

		String idValue = null;
		String splitSelectValue[] = verfiyValue.split("\\(");
		verfiyValue = splitSelectValue[1];
		splitSelectValue = verfiyValue.split("\\)");
		idValue = splitSelectValue[0];

		if(isChecked(selenium, "//span[@id='"+idValue+"']/input")){
			return true;
		}else{
			return false;
		}
	}


	public boolean verifyStoredValues(Selenium selenium, ChartsLib labRequestTestData){
		
		if(!getText(selenium, "provider").trim().equalsIgnoreCase(labRequestTestData.providerName.trim())){
			return false;
		}

		if(!getText(selenium, "expecteddate").trim().equalsIgnoreCase(labRequestTestData.expectedDate.trim())){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().equalsIgnoreCase(labRequestTestData.providerNotes.trim())){
			return false;
		}

		if(!getText(selenium, "itemstatus").trim().equalsIgnoreCase(labRequestTestData.status.trim())){
			return false;
		}

		if(!getText(selenium, "workStatus").trim().equalsIgnoreCase(labRequestTestData.taskName.trim())){
			return false;
		}

		if(!getText(selenium, "taskUsers").trim().equalsIgnoreCase(labRequestTestData.sendTaskTo.trim())){
			return false;
		}

		if(!getText(selenium, "taskNotes").trim().equalsIgnoreCase(labRequestTestData.taskNotes.trim())){
			return false;
		}

		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[1]/td[2]").trim().equalsIgnoreCase(labRequestTestData.testBatteries.trim()) ){
			return false;
		}
		return true;
	}
}

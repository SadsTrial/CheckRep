package com.clinical.selenium.section.charts.labRequest;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewLabRequest extends AbstractChartsTest {


	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for creating a New labRequest")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewlabRequest(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib labRequestTestData = new ChartsLib();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "TC_LAB_001";
		labRequestTestData.fetchChartsTestData();
		createNewLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for creating a New In Active labRequest")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewInactivelabRequest(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib labRequestTestData = new ChartsLib();
		labRequestTestData.workSheetName = "LabRequest";
		labRequestTestData.testCaseId = "TC_LAB_002";
		labRequestTestData.fetchChartsTestData();
		createNewLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}


	/**
	 * @Function 	: createNewLabRequest
	 * @Description : Function to create a New labRequest
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 13, 2010
	 */
	public void createNewLabRequest(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib labRequestTestData){

		Selenium selenium = null;
		boolean isRecordFoundInAllLabRequests = false;
		boolean isRecordFoundInActiveLabRequests = false;
		boolean isInactive = false;
		boolean isRecordFoundInActivity = false;

		if(labRequestTestData.status.equalsIgnoreCase("inactive")){
			isInactive = true;
		}

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
			
			click(selenium,"AllLabRequests");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				selenium.click("labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "labrequest"); 	
			createLabRequest(selenium, labRequestTestData);
			click(selenium,"AllLabRequests");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
				selenium.click("labRequestListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "labrequest");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ labRequestTestData.toString());
			}
			
			//----------------------------------------------------------------------------------------//
			//  Step-3:  Verifying whether the details entered are saved properly in All Lab Requests //
			//----------------------------------------------------------------------------------------//

			if(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				while(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					if(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
						click(selenium, "labRequestListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}
			
			click(selenium,"AllLabRequests");
			waitForPageLoad(selenium);
			
			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInAllLabRequests=verifyStoredValues( selenium, labRequestTestData);

			//--------------------------------------------------------------------------------------------//
			//  Step-4:  Verifying whether the details entered are saved properly in Current Lab Requests //
			//-------------------------------------------------------------------------------------------//

			click(selenium,"labRequest");
			waitForPageLoad(selenium);

			click(selenium,"CurrentLabRequests");
			waitForPageLoad(selenium);

			if(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				while(!selenium.isElementPresent(idOfTheNewlyAddedRecord)){
					if(selenium.isElementPresent("labRequestListMoreLink") && selenium.isVisible("labRequestListMoreLink")){
						click(selenium, "labRequestListMoreLink");
						waitForPageLoad(selenium);
					}else{
						break;
					}
				}
			}
			if(!isInactive){
				click(selenium,idOfTheNewlyAddedRecord);

				waitForPageLoad(selenium);
				isRecordFoundInActiveLabRequests=verifyStoredValues( selenium, labRequestTestData);


			}

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInActivity=verifyStoredValues( selenium, labRequestTestData);

			if(!isInactive){
				Assert.assertTrue(isRecordFoundInAllLabRequests,"Expected Lab request[Active] record Not found in Labrequest[All] Section; Create Labrequest Failed; More Details : "+labRequestTestData.toString());
				Assert.assertTrue(isRecordFoundInActiveLabRequests,"Expected Lab request[Active] record Not found in Labrequest[Active] Section; Create Labrequest Failed; More Details : "+labRequestTestData.toString());
			}else{
				Assert.assertTrue(isRecordFoundInAllLabRequests,"Expected Lab request[In Active] record Not found in Labrequest[All] Section; Create Labrequest Failed; More Details : "+labRequestTestData.toString());
				Assert.assertFalse(isRecordFoundInActiveLabRequests,"Expected Lab request[In Active] record  Found in Labrequest[Active] Section; Create Labrequest Failed; More Details : "+labRequestTestData.toString());
			}
			Assert.assertTrue(isRecordFoundInActivity,"Expected Lab request record Not found in  Section; Create Labrequest Failed; More Details : "+labRequestTestData.toString());

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
		String Element="//span[@id='"+idValue+"']/input";
		if(selenium.isChecked(Element)){
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

		if(!getText(selenium, "//table[@id='orderpanelstable']/tbody/tr[1]/td[2]").trim().equalsIgnoreCase(labRequestTestData.testBatteries.trim())){
			return false;
		}

		return true;
	}
}

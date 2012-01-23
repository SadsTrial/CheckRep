package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateLabRequest extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying audit logs for update labRequest")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForUpdateLabRequest(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib labRequestTestData = new AuditLib();
		labRequestTestData.workSheetName = "UpdateLabRequest";
		labRequestTestData.testCaseId = "TC_ULB_001";
		labRequestTestData.fetchAuditTestData();
		verifyAuditLogForUpdateLabRequest(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForUpdateLabRequest
	 * @Description : Function to verify audit logs for update labRequest
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 30, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForUpdateLabRequest(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib labRequestTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		String labrequestCount = null;
		String uniqueID = null;

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

			click(selenium, "labRequest");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentLabRequests", 120000),"Could not capture existing Lab Requests[Active] Count; More Details :" + labRequestTestData.toString());
			labrequestCount = getListCount(selenium.getText("CurrentLabRequests"));
			if(!labrequestCount.trim().equals(null) || !labrequestCount.trim().equals("")){
				if (Integer.parseInt(labrequestCount) == 0){
					labRequestTestData = null;
					labRequestTestData = new AuditLib();
					labRequestTestData.workSheetName = "LabRequest";
					labRequestTestData.testCaseId = "TC_LAB_001";		
					labRequestTestData.fetchAuditTestData();
					createLabRequest(selenium, labRequestTestData);
					labrequestCount = "1";
				}
			}
			labRequestTestData = null;
			labRequestTestData = new AuditLib();
			labRequestTestData.workSheetName = "UpdateLabRequest";
			labRequestTestData.testCaseId = "TC_ULB_001";		
			labRequestTestData.fetchAuditTestData();
			waitForPageLoad(selenium);
			Date currentDate = new Date();
			if(selenium.isElementPresent("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]")){
				uniqueID = selenium.getAttribute("//div[@id='labRequestList']/table/tbody[1]/tr[1]//td[1]/div/strong/a@id").split("labrequest")[1];
				click(selenium,"//div[@id='labRequestList']/table/tbody[1]/tr[1]//td[1]/div/strong/a");
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
				waitForPageLoad(selenium);
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);
				labRequestTestData.providerName = labRequestTestData.providerName != null && labRequestTestData.providerName != "" ? labRequestTestData.providerName.trim() : getSelectedValue(selenium, "providersInput");
				labRequestTestData.expectedDate = labRequestTestData.expectedDate != null && labRequestTestData.expectedDate != "" ? labRequestTestData.expectedDate.trim() : getValue(selenium, "expecteddateInput");
				labRequestTestData.providerNotes = labRequestTestData.providerNotes != null && labRequestTestData.providerNotes != "" ? labRequestTestData.providerNotes.trim() : getValue(selenium, "notesInput");
				labRequestTestData.status = labRequestTestData.status != null && labRequestTestData.status != "" ? labRequestTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput");
				labRequestTestData.taskName = labRequestTestData.taskName != null && labRequestTestData.taskName != "" ? labRequestTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
				labRequestTestData.sendTaskTo = labRequestTestData.sendTaskTo != null && labRequestTestData.sendTaskTo != "" ? labRequestTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
				labRequestTestData.taskNotes = labRequestTestData.taskNotes != null && labRequestTestData.taskNotes != "" ? labRequestTestData.taskNotes.trim() : getValue(selenium, "taskNotesInput");
				currentDate.setSeconds(currentDate.getSeconds()-10);
				Assert.assertTrue(select(selenium,"providersInput", labRequestTestData.providerName),"Could not enter Provider Name; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(enterDate(selenium,"expecteddateInput", labRequestTestData.expectedDate),"Could not enter Expected Date; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput",labRequestTestData.providerNotes),"Could not enter Notes; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", labRequestTestData.status),"Could not enter status; More Details :" + labRequestTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				if(!isElementPresent(selenium,"workStatusInput") || !selenium.isVisible("workStatusInput") ){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details :" + labRequestTestData.toString());
					waitForPageLoad(selenium);
				}
				Assert.assertTrue(select(selenium,"workStatusInput", labRequestTestData.taskName),"Could not select Work Status; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", labRequestTestData.sendTaskTo),"Could not select Send Tast To; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", labRequestTestData.taskNotes),"Could not enter Task Notes; More Details :" + labRequestTestData.toString());
				Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + labRequestTestData.toString());
				waitForPageLoad(selenium);
				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Lab Request not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+ "; More Details :" + labRequestTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + labRequestTestData.toString());
			}
			Assert.assertTrue(waitForValue(selenium, "CurrentLabRequests", 120000),"Could not capture Lab Request Count after saving a Lab Request; More Details :" + labRequestTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentLabRequests"))) ,(Integer.parseInt(labrequestCount)-1),"The Lab Request is not Saved Successfully, Lab Request count has a change after updating Lab Request; More Details :" + labRequestTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Date currentDate1 = new Date(); 
			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + labRequestTestData.toString());
			String recordID = "LabRequest("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,labRequestTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, labRequestTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + labRequestTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + labRequestTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + labRequestTestData.toString());	
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}finally{
			if(selenium != null){
				if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
					click(selenium, "errorCloseButton");

					waitForPageLoad(selenium);	
				}
				if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
					click(selenium, "headerClinicalMenu");

			}
		}
	}


	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName,String value, AuditLib labRequestTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("expecteddate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(labRequestTestData.expectedDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"expecteddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(labRequestTestData.providerNotes.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lastupdated")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date currDate = new Date();
			currDate.setHours(0);
			currDate.setMinutes(0);
			currDate.setSeconds(0);
			if(actualStartdate.toString().trim().toLowerCase(new java.util.Locale("en", "US")).equals(currDate.toString().trim().toLowerCase(new java.util.Locale("en", "US"))) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lastupdated,";
				return false;
			}
		}else{
			return true;
		}
	}
}
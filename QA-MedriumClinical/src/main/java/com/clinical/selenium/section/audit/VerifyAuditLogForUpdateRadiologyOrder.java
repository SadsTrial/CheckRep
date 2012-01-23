package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateRadiologyOrder extends AbstractAuditTest {
	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying audit log for New RadiologyOrder")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib labRequestTestData = new AuditLib();
		labRequestTestData.workSheetName = "UpdateRadiologyOrder";
		labRequestTestData.testCaseId = "TC_URAD_001";
		labRequestTestData.fetchAuditTestData();
		verifyAuditLogsForNewRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForNewRadiologyOrder
	 * @Description : Function to  verify audit log for New RadiologyOrder
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 26, 2011
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib radiologyOrderTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		String radiologyOrderCount = null;
		String uniqueID = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Update the Lab Request with New Details and Save         //
			//--------------------------------------------------------------------//

			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentRadiologyOrders", 120000),"Could not capture existing Radiology Order Count; More Details :" + radiologyOrderTestData.toString());
			radiologyOrderCount = getListCount(selenium.getText("CurrentRadiologyOrders"));

			if(!radiologyOrderCount.trim().equals(null) || !radiologyOrderCount.trim().equals("")){
				if (Integer.parseInt(radiologyOrderCount)== 0){
					radiologyOrderTestData = null;
					radiologyOrderTestData = new AuditLib();
					radiologyOrderTestData.workSheetName = "RadiologyOrder";
					radiologyOrderTestData.testCaseId = "TC_RAD_001";		
					radiologyOrderTestData.fetchAuditTestData();
					createRadiologyOrder(selenium, radiologyOrderTestData);
					radiologyOrderCount = "1";
				}
			}

			radiologyOrderTestData = null;
			radiologyOrderTestData = new AuditLib();
			radiologyOrderTestData.workSheetName = "UpdateRadiologyOrder";
			radiologyOrderTestData.testCaseId = "TC_URAD_001";		
			radiologyOrderTestData.fetchAuditTestData();
			waitForPageLoad(selenium);
			click(selenium,"CurrentRadiologyOrders");
			waitForPageLoad(selenium);
			Date currentDate = new Date();
			if(selenium.isElementPresent("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]/div")){
				uniqueID = selenium.getAttribute("//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id").split("radiologyorder")[1];
				click(selenium,"//div[@id='labRequestList']/table/tbody[1]/tr[1]/td[1]/div/strong/a");
				waitForPageLoad(selenium);
				selenium.click("actionButton");
				click(selenium, "edit"+uniqueID);
				waitForPageLoad(selenium);
				if(getValue(selenium,"expecteddateInput").trim().toLowerCase(new java.util.Locale("en", "US")).equals(radiologyOrderTestData.expectedDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
					radiologyOrderTestData.workSheetName = "UpdateRadiologyOrder";
					radiologyOrderTestData.testCaseId = "TC_URAD_002";		
					radiologyOrderTestData.fetchAuditTestData();
				}

				radiologyOrderTestData.providerName = radiologyOrderTestData.providerName != null && radiologyOrderTestData.providerName != "" ? radiologyOrderTestData.providerName.trim() : getSelectedValue(selenium,"providersInput");
				radiologyOrderTestData.expectedDate = radiologyOrderTestData.expectedDate != null && radiologyOrderTestData.expectedDate != "" ? radiologyOrderTestData.expectedDate.trim() : getValue(selenium,"expecteddateInput");
				radiologyOrderTestData.providerNotes = radiologyOrderTestData.providerNotes != null && radiologyOrderTestData.providerNotes != "" ? radiologyOrderTestData.providerNotes.trim() : getValue(selenium, "notesInput");
				radiologyOrderTestData.status = radiologyOrderTestData.status != null && radiologyOrderTestData.status != "" ? radiologyOrderTestData.status.trim() : getSelectedValue(selenium,"itemStatusInput");
				radiologyOrderTestData.taskName = radiologyOrderTestData.taskName != null && radiologyOrderTestData.taskName != "" ? radiologyOrderTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput");
				radiologyOrderTestData.sendTaskTo = radiologyOrderTestData.sendTaskTo != null && radiologyOrderTestData.sendTaskTo != "" ? radiologyOrderTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput");
				radiologyOrderTestData.taskNotes = radiologyOrderTestData.taskNotes != null && radiologyOrderTestData.taskNotes != "" ? radiologyOrderTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");
				currentDate.setSeconds(currentDate.getSeconds()-10);
				Assert.assertTrue(select(selenium,"providersInput", radiologyOrderTestData.providerName),"Could not enter Provider Name; More Details :" + radiologyOrderTestData.toString());
				Assert.assertTrue(enterDate(selenium,"expecteddateInput", radiologyOrderTestData.expectedDate),"Could not enter Expected Date; More Details :" + radiologyOrderTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput",radiologyOrderTestData.providerNotes),"Could not enter Notes; More Details :" + radiologyOrderTestData.toString());
				Assert.assertTrue(select(selenium,"itemStatusInput", radiologyOrderTestData.status),"Could not enter status; More Details :" + radiologyOrderTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

				if(!isElementPresent(selenium,"workStatusInput")){
					Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details :" + radiologyOrderTestData.toString());
				}
				Assert.assertTrue(select(selenium,"workStatusInput", radiologyOrderTestData.taskName),"Could not select Work Status; More Details :" + radiologyOrderTestData.toString());
				Assert.assertTrue(select(selenium,"taskUsersInput", radiologyOrderTestData.sendTaskTo),"Could not select Send Tast To; More Details :" + radiologyOrderTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", radiologyOrderTestData.taskNotes),"Could not enter Task Notes; More Details :" + radiologyOrderTestData.toString());

				if(!radiologyOrderTestData.MRI.equals("")){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.MRI.trim()),"Could not select Radiology Panels : MRI Value; More Details :" + radiologyOrderTestData.toString());
				}

				if(!radiologyOrderTestData.CT.equals("") ){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.CT.trim()),"Could not select Radiology Panels : CT Value; More Details :" + radiologyOrderTestData.toString());

				}

				if(!radiologyOrderTestData.radiology.equals("")){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.radiology.trim()),"Could not select Radiology Panels : radiology Value; More Details :" + radiologyOrderTestData.toString());

				}

				if(!radiologyOrderTestData.nuclearMedecine.equals("") ){
					Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.nuclearMedecine.trim()),"Could not select Radiology Panels : Nuclear Medecine Value; More Details :" + radiologyOrderTestData.toString());

				}

				Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details :" + radiologyOrderTestData.toString());

				waitForPageLoad(selenium);

				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Radiology Order not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + radiologyOrderTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + radiologyOrderTestData.toString());
			}

			Assert.assertTrue(waitForValue(selenium, "CurrentRadiologyOrders", 10000),"Could not capture Radiology Order Count after saving a Lab Request; More Details :" + radiologyOrderTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("CurrentRadiologyOrders"))), Integer.parseInt(radiologyOrderCount),"The Radiology Order is not Saved Successfully, Radiology Order count has a change after updating Lab Request; More Details :" + radiologyOrderTestData.toString());

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Date currentDate1 = new Date();

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			int counter1 = 0;
			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + radiologyOrderTestData.toString());
			String recordID = "RadiologyOrder("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID,  radiologyOrderTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, radiologyOrderTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + radiologyOrderTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + radiologyOrderTestData.toString());	
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + radiologyOrderTestData.toString());
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

	public boolean selectRadiologyPanels(Selenium selenium, String valRadiologyPanel){
		String panelVal[] = valRadiologyPanel.split("-");
		String elementName = "//input[@name='"+panelVal[0]+"']";
		return(check(selenium, elementName));
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
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(labRequestTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
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
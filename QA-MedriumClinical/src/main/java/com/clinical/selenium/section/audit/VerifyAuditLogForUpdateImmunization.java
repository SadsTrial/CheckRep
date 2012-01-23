package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateImmunization extends AbstractAuditTest {

	boolean isValueChecked = false;
	String immunizationManuacturerCode = null;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying audit logs for Update Immunization")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForUpdateImmunization(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib immunizationTestData = new AuditLib();
		immunizationTestData.workSheetName = "UpdateImmunization";
		immunizationTestData.testCaseId = "TC_UIM_001";
		immunizationTestData.fetchAuditTestData();
		verifyAuditLogsForUpdateImmunization(seleniumHost, seleniumPort, browser, webSite, immunizationTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForUpdateImmunization
	 * @Description : Function to Verifying audit logs for Update Immunization
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 29, 2010
	 */	
	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForUpdateImmunization(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib immunizationTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		String immunizationCount = null;
		String uniqueID = null;


		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + immunizationTestData.toString());
			loginFromPublicSite(selenium, immunizationTestData.userAccount, immunizationTestData.userName, immunizationTestData.userPassword);
			searchPatient(selenium,immunizationTestData.patientID);

			click(selenium, "immunizations");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "Immunizations", 120000),"Could not capture existing Immunization Count; More Details :" + immunizationTestData.toString());
			immunizationCount = getListCount(selenium.getText("Immunizations"));

			//------------------------------------------------------------------------------------//
			//  Step-2: Creating the New Immunization if No immunization is available to Update   //
			//------------------------------------------------------------------------------------//

			if(!immunizationCount.trim().equals(null) || !immunizationCount.trim().equals("")){
				if (Integer.parseInt(immunizationCount) == 0){
					immunizationTestData = null;
					immunizationTestData = new AuditLib();
					immunizationTestData.workSheetName = "Immunization";
					immunizationTestData.testCaseId = "TC_IMM_001";		
					immunizationTestData.fetchAuditTestData();
					createImmunization(selenium, immunizationTestData);
					immunizationCount = "1";
				}
			}

			immunizationTestData = null;
			immunizationTestData = new AuditLib();
			immunizationTestData.workSheetName = "UpdateImmunization";
			immunizationTestData.testCaseId = "TC_UIM_001";		
			immunizationTestData.fetchAuditTestData();

			click(selenium, "immunizations");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3: Update the Immunization with New Details and Save         //
			//--------------------------------------------------------------------//
			String idOfTheRecord = null;
			Assert.assertTrue(selenium.isElementPresent("//div[@id='patientImmunizationList']/table/tbody[1]/tr[1]/td[1]"),"Could not select the Immunization to Update");
			idOfTheRecord = selenium.getAttribute("//div[@id='patientImmunizationList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
			uniqueID = idOfTheRecord.split("immunization")[1];
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			click(selenium, "actionButton");

			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + immunizationTestData.toString());
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);

			click(selenium, "productBoxInputBox");
			click(selenium, "productBoxInputLabel");
			immunizationTestData.immunizationName = immunizationTestData.immunizationName != null && immunizationTestData.immunizationName != "" ? immunizationTestData.immunizationName.trim() : getText(selenium, "productBoxInputLabel").trim();
			immunizationTestData.administrationDate = immunizationTestData.administrationDate != null && immunizationTestData.administrationDate != "" ? immunizationTestData.administrationDate.trim() : getValue(selenium,"administrationDateInput");
			immunizationTestData.lotInput = immunizationTestData.lotInput != null && immunizationTestData.lotInput != "" ? immunizationTestData.lotInput.trim() : getValue(selenium,"lotNumberInput");
			immunizationTestData.manufacturer = immunizationTestData.manufacturer != null && immunizationTestData.manufacturer != "" ? immunizationTestData.manufacturer.trim() : getSelectedValue(selenium, "immunizationManufacturersInput");
			immunizationTestData.routeOfAdministartion = immunizationTestData.routeOfAdministartion != null && immunizationTestData.routeOfAdministartion != "" ? immunizationTestData.routeOfAdministartion.trim() : getSelectedValue(selenium, "fdaRoutesInput");
			immunizationTestData.reason = immunizationTestData.reason != null && immunizationTestData.reason != "" ? immunizationTestData.reason.trim() : getSelectedValue(selenium, "refusalReasonCodeInput");
			immunizationTestData.immunityNotes = immunizationTestData.immunityNotes != null && immunizationTestData.immunityNotes != "" ? immunizationTestData.immunityNotes.trim() : getValue(selenium,"notesInput");
			immunizationTestData.taskName = immunizationTestData.taskName != null && immunizationTestData.taskName != "" ? immunizationTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
			immunizationTestData.sendTaskTo = immunizationTestData.sendTaskTo != null && immunizationTestData.sendTaskTo != "" ? immunizationTestData.sendTaskTo.trim() : getSelectedValue(selenium, "taskUsersInput");
			immunizationTestData.taskNotes = immunizationTestData.taskNotes != null && immunizationTestData.taskNotes != "" ? immunizationTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"productBoxInputBox", immunizationTestData.immunizationName),"Could not enter Immunization Name; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administrationDateInput", immunizationTestData.administrationDate),"Could not enter Administration Date; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administrationTimeInput", immunizationTestData.administrationTime),"Could not enter Administration Time; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(enterDate(selenium,"administeredAmountInput", immunizationTestData.administrationAmount),"Could not enter Administration Amount; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"lotNumberInput",immunizationTestData.lotInput),"Could not enter Lot Number; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium,"immunizationManufacturersInput", immunizationTestData.manufacturer),"Could not select Manufacturer; More Details :" + immunizationTestData.toString());
			immunizationManuacturerCode =getText(selenium,"immunizationmanufacturercode");
			Assert.assertTrue(select(selenium,"fdaRoutesInput", immunizationTestData.routeOfAdministartion),"Could not select Manufacturer Route; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium,"refusalReasonCodeInput", immunizationTestData.reason),"Could not select Reason Code; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", immunizationTestData.immunityNotes),"Could not enter Immunity Notes; More Details :" + immunizationTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			if(!selenium.isElementPresent("workStatusInput") && !selenium.isVisible("workStatusInput")){
				click(selenium, "addTask");
			}

			Assert.assertTrue(select(selenium, "workStatusInput", immunizationTestData.taskName),"Could not select Work Status; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", immunizationTestData.sendTaskTo),"Could not select Send To Task; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", immunizationTestData.taskNotes),"Could not enter Task Notes; More Details :" + immunizationTestData.toString());

			Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details :" + immunizationTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Immunization not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+ "; More Details :" + immunizationTestData.toString());
			}

			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + immunizationTestData.toString());
			Assert.assertTrue(waitForValue(selenium, "Immunizations", 10000),"Could not capture immunization Count after saving a immunization; More Details :" + immunizationTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("Immunizations"))) ,Integer.parseInt(immunizationCount),"The immunization is not Saved Successfully, immunization cont has a change after updating a immunization; More Details :" + immunizationTestData.toString());

			Date currentDate1 = new Date();

			//----------------------------------------------------------------------------//
			//  Step-4 :  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			
			int counter1= 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);

			String recordID = "Immunization("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID, immunizationTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains("to")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, immunizationTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + immunizationTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + immunizationTestData.toString());	
		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + immunizationTestData.toString());
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
	public boolean verifyAuditValues(String ColName,String value, AuditLib immunizationTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("administrationdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(immunizationTestData.administrationDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"administrationdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.immunityNotes)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("manufacturercode")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationManuacturerCode.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"manufacturercode,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("administeredamount")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.administrationAmount)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"administeredamount,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lotnumber")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(immunizationTestData.lotInput)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lotnumber,";
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
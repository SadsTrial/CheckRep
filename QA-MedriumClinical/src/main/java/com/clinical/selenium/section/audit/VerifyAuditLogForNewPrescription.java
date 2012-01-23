package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewPrescription extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to verify Audit Logs For New prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		AuditLib prescriptionTestData = new AuditLib();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "TC_PRES_001";
		prescriptionTestData.fetchAuditTestData();
		verifyAuditLogForNewPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: verifyAuditLogForNewPrescription
	 * @Description : Function to verify a audit log for New Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Anil Sannareddy
	 * @Created on 	: Nov 15, 2010
	 */

	public void verifyAuditLogForNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib prescriptionTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Prescription and enter details             //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);
			int counter = 1;
			String prescriptionMorelinkId = "";
			while(selenium.isElementPresent("//div[@id='prescriptionList']/div/div["+counter+"]")){
				if(selenium.getText("//div[@id='prescriptionList']/div/div["+counter+"]/div[1]").toLowerCase(new java.util.Locale("en", "US")).trim().contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
					prescriptionMorelinkId = selenium.getAttribute("//div[@id='prescriptionList']/div/div["+counter+"]/div[2]@id");
					break;
				}
				counter ++;
			}
			while(isElementPresent(selenium, prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "prescription"); 	
			Date currentDate = new Date();
			createPrescription(selenium, prescriptionTestData);
			Date currentDate1 = new Date();
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);
			while(isElementPresent(selenium, prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "prescription");	
			secondList.removeAll(firstList);
			
			
			String idOfTheNewlyAddedRecord = "";
			
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ prescriptionTestData.toString());
			}
			
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			String uniqueID = idOfTheNewlyAddedRecord.split("prescription")[1];
			String recordID = "Prescription("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID, prescriptionTestData).split("Set");
			
			int counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, prescriptionTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + prescriptionTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + prescriptionTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
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


	public boolean verifyAuditValues(String ColName, String value, AuditLib prescriptionTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.comments.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("dayssupply")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.daySupplyInput.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"dayssupply,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("unitqty")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.unitQuantityInput)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"unitqty,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("quantity")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.quantity)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"quantity,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("refillnumber")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.refills)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"refillnumber,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("location")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(prescriptionTestData.providerLocation)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"location,";
				return false;
			}
		}else{
			return true;
		}
	}	
}
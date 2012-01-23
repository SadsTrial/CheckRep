package com.clinical.selenium.section.charts.prescription;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewPrescription extends AbstractChartsTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to create new prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "TC_PRES_001";
		prescriptionTestData.fetchChartsTestData();
		createNewPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to create new prescription")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewInActivePrescription(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib prescriptionTestData = new ChartsLib();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "TC_PRES_002";
		prescriptionTestData.fetchChartsTestData();
		createNewPrescription(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: createNewPrescription
	 * @Description : Function to create a New Prescription
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 05, 2010
	 */
	public void createNewPrescription(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib prescriptionTestData){

		Selenium selenium = null;
		boolean isRecordFoundInActivePrescriptions = false;
		boolean isRecordFoundInAllPrescriptions = false;
		boolean isRecordFoundInActivity = false;
		boolean isInactivePrescription = false;
		String prescriptionMorelinkId = "";
		if(prescriptionTestData.status.equalsIgnoreCase("inactive")){
			isInactivePrescription = true;
		}

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
			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);
			int counter =1;
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
			createPrescription(selenium, prescriptionTestData);
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

			//-----------------------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Prescription Current section  //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			while(!isElementPresent(selenium, idOfTheNewlyAddedRecord) && selenium.isElementPresent(prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){	
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInActivePrescriptions = verifyStoredValues(selenium, prescriptionTestData);
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Prescription All section      //
			//-----------------------------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			click(selenium,"AllPrescriptions");
			waitForPageLoad(selenium);

			while(!isElementPresent(selenium, idOfTheNewlyAddedRecord) && selenium.isElementPresent(prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllPrescriptions = verifyStoredValues(selenium, prescriptionTestData);

			//-----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//		

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity = verifyStoredValues(selenium, prescriptionTestData);

			if(!isInactivePrescription){
				Assert.assertTrue(isRecordFoundInActivePrescriptions,"Active Prescription record is not found in Active Prescription Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(isRecordFoundInAllPrescriptions,"Active Prescription record is not found in All Prescription Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Active Prescription record is not found in Activity Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
			}else{
				Assert.assertFalse(isRecordFoundInActivePrescriptions,"In Active Prescription record is  found in Active Prescription Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(isRecordFoundInAllPrescriptions," In Active Prescription record is not found in All Prescription Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"In Active Prescription record is not found in Activity Page; Prescription Creation Failed; More Details :" + prescriptionTestData.toString());
			}
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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			counter++;
		}
	}

	public boolean verifyStoredValues(Selenium selenium, ChartsLib prescriptionTestData){

		if(!getText(selenium, "providerInput").trim().equalsIgnoreCase(prescriptionTestData.providerName)){
			return false;
		}

		if(!getText(selenium, "providerlocation").trim().equalsIgnoreCase(prescriptionTestData.providerLocation)){
			return false;
		}

		if(!getText(selenium,"//div[@id='fixedcontent']/div/div[3]/div/div/table/tbody/tr/td[2]/div/div/form/div/div[2]/div[9]/ol/li[3]/div[2]/span").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionTestData.medicationName.trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"quantityInput").trim().equalsIgnoreCase(prescriptionTestData.quantity)){
			return false;
		}

		if(!getText(selenium,"daysSupplyInput").trim().equalsIgnoreCase(prescriptionTestData.daySupplyInput)){
			return false;
		}

		if(!getText(selenium,"refillsInput").trim().equalsIgnoreCase(prescriptionTestData.refills)){
			return false;
		}

		if(!getText(selenium,"commentsInput").trim().equalsIgnoreCase(prescriptionTestData.comments)){
			return false;
		}

		if(!getText(selenium,"workStatus").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.taskName.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"itemstatus").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.status.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"taskUsers").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionTestData.sendTaskTo.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}
		return true;
	}
}
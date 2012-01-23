package com.clinical.selenium.section.ERX;
import java.util.Collection;
import java.util.Hashtable;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.clinical.selenium.genericlibrary.eRx.ERxLib;
import com.clinical.selenium.genericlibrary.eRx.AbstractERxTest;
import com.sharedlibrary.ApacheHttpClient;
import com.sharedlibrary.ParseXML;
import com.thoughtworks.selenium.Selenium;

public class VerifyUnMatchedRefillRequest extends AbstractERxTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to verify UnMatched Refill Request")
	@Parameters( {"eRxURL", "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyUnMatchedRefillRequest(String valERxUrl,String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ERxLib prescriptionTestData = new ERxLib();
		prescriptionTestData.workSheetName = "VerifyUnMatchedRefillRequest";
		prescriptionTestData.testCaseId = "TC_PRES_001";
		prescriptionTestData.fetchErxTestData();
		verifyUnMatchedRefillRequest(valERxUrl, seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: verifyUnMatchedRefillRequest
	 * @Description : Function to Verify UnMatched Refill Request
	 * @param 		: valERxUrl
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Aug 26, 2011
	 */
	public void verifyUnMatchedRefillRequest(String valERxUrl, String seleniumHost, int seleniumPort,String browser, String webSite, ERxLib prescriptionTestData){

		Selenium selenium = null;
		String response = null;
		String idOfTheNewlyAddedRecord  = "";
		Collection<String> firstList = null;
		Collection<String> secondList = null;
		String prescriptionMorelinkId = "";
		Boolean isRefillRequestSuccess = false; 
		ApacheHttpClient objClientConnector = new ApacheHttpClient();
		ParseXML objParser = new ParseXML("clinical");
		Hashtable<String, String> prescriptionVerificationData = null;
		String xmlFileName = "test-match-patient-medication.xml";
		int counter = 1;
		try{

			prescriptionVerificationData = objParser.fetchData(xmlFileName,"RefillRequest");

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//------------------------------------------------------------------------//
			//  Step-2:  Get the id of the First prescription in the List             //
			//-----------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);
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
			firstList = getDataBaseIDs(selenium, "refill"); 

			click(selenium,"preferences");
			waitForPageLoad(selenium);
			click(selenium,"UnmatchedRefillRequest");
			waitForPageLoad(selenium);

			while(isElementPresent(selenium, "refillListMoreLink") && selenium.isVisible("refillListMoreLink")){
				click(selenium, "refillListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstRefillList = getDataBaseIDs(selenium, "refill");

			//------------------------------------------------------------------------//
			//  Step-3:  Send the E-Prescription request using ERX URL               //
			//-----------------------------------------------------------------------//

			response = objClientConnector.postXMLContent(xmlFileName, valERxUrl);
			Assert.assertEquals(response.split("@@")[1],"<?xml version=\"1.0\" ?><Response></Response>","UnMatched Refill request  Failed");

			selenium.refresh();
			waitForPageLoad(selenium);

			while(isElementPresent(selenium, "refillListMoreLink") && selenium.isVisible("refillListMoreLink")){
				click(selenium, "refillListMoreLink");
				waitForPageLoad(selenium);
			}


			Collection<String> secondRefillList = getDataBaseIDs(selenium, "refill");
			secondRefillList.removeAll(firstRefillList);
			idOfTheNewlyAddedRecord = secondRefillList.toArray()[0].toString();

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);

			Assert.assertEquals(getText(selenium, "medicationInfoPanel").trim().toLowerCase(new java.util.Locale("en", "US")),prescriptionVerificationData.get("drugDescription").trim().toLowerCase(new java.util.Locale("en", "US")),"Drug Info is not stored as Expected");

			Assert.assertEquals(getText(selenium, "patientInfoPanel").trim(),prescriptionVerificationData.get("patientLastName").trim()+", "+prescriptionVerificationData.get("patientFirstName"),"Patien Information verification failed");

			Assert.assertEquals(getText(selenium, "pharmacyInfoPanel").trim().toLowerCase(new java.util.Locale("en", "US")),prescriptionVerificationData.get("storename").trim().toLowerCase(new java.util.Locale("en", "US")),"Pharmacy info verification Failed");

			Assert.assertEquals(getText(selenium, "prescriptionInfoPanel"),prescriptionVerificationData.get("prescriberLastName")+", "+ prescriptionVerificationData.get("prescriberFirstName")+" "+ prescriptionVerificationData.get("prescriberMiddleName"),"Prescriber Info Verification Failed");

			Assert.assertTrue(selectValueFromAjaxList(selenium, "selectPatientBoxBox", prescriptionTestData.patientName), "Could Not select Patient Name");
			waitForElement(selenium, "medicationBoxBox", 10000);
			Assert.assertTrue(selectValueFromAjaxList(selenium, "medicationBoxBox", prescriptionTestData.medicationName),"Could Not select Medication Name");


			click(selenium,"//form[@id='refillForm']/div/div/div/div[8]/div/div/div[2]/span/input");
			Assert.assertTrue(waitForElementToEnable(selenium, "mapButton"), "Map Button is not Enabled");

			click(selenium,"mapButton");
			waitForPageLoad(selenium);

			//------------------------------------------------------------------------//
			//  Step-4:  Get The Newly added Refill Request                          //
			//-----------------------------------------------------------------------//

			searchPatient(selenium, prescriptionTestData.patientID);

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			click(selenium,"CurrentPrescriptions");
			waitForPageLoad(selenium);

			while(isElementPresent(selenium, prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			secondList = getDataBaseIDs(selenium, "refill");	
			secondList.removeAll(firstList);
			if(secondList.size()>=1){
			idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ prescriptionTestData.toString());
			}
			//----------------------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details in the Newly added Refill with the values from the test-auto-match.xml   //
			//----------------------------------------------------------------------------------------------------//

			if( selenium.isElementPresent(idOfTheNewlyAddedRecord) ){	
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRefillRequestSuccess = verifyStoredValues(selenium, prescriptionVerificationData);
			}
			Assert.assertTrue(isRefillRequestSuccess,"Expected Refill Request is not found; Verify Refill request failed");

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
		} catch (Exception e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + prescriptionTestData.toString());
		}finally{
			try{
				if(selenium != null){
					if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
						click(selenium, "errorCloseButton");
						waitForPageLoad(selenium);	
					}
					if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
						click(selenium, "headerClinicalMenu");
				}
			}
			catch(RuntimeException e){
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

	public boolean verifyStoredValues(Selenium selenium,   Hashtable<String, String> prescriptionVerificationData){

		if(!getText(selenium, "providerInput").trim().equalsIgnoreCase(prescriptionVerificationData.get("prescriberFirstName")+ " "+ prescriptionVerificationData.get("prescriberLastName"))){
			return false;
		}

		if(!getText(selenium, "provideraddress").trim().equalsIgnoreCase(prescriptionVerificationData.get("prescriberAddr1").trim())){
			return false;
		}
		if(!getText(selenium, "patientNameDob").trim().contains(prescriptionVerificationData.get("patientFirstName") + " "+prescriptionVerificationData.get("patientMiddleName") + " " +prescriptionVerificationData.get("patientLastName"))){
			return false;
		}

		if(!getText(selenium,"patientAddress1").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionVerificationData.get("patientAddr1").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"patientAddress2").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionVerificationData.get("patientCity").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"patientAddress2").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionVerificationData.get("patientState").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"patientAddress2").trim().toLowerCase(new java.util.Locale("en", "US")).contains(prescriptionVerificationData.get("patientZip").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"pharmacy").trim().equalsIgnoreCase(prescriptionVerificationData.get("storename"))){
			return false;
		}

		if(!getText(selenium,"pharmacyAddress1").trim().contains(prescriptionVerificationData.get("pharmacistAddr1"))){
			return false;
		}

		if(!getText(selenium,"pharmacyAddress1").trim().contains(prescriptionVerificationData.get("pharmacistAddr2"))){
			return false;
		}

		if(!getText(selenium,"pharmacyAddress2").toLowerCase(new java.util.Locale("en", "US")).trim().contains(prescriptionVerificationData.get("pharmacistCity").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"css=div.formInput > div.gwt-Label").toLowerCase(new java.util.Locale("en", "US")).trim().contains(prescriptionVerificationData.get("note").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"css=div.sigline > span.gwt-InlineHTML").toLowerCase(new java.util.Locale("en", "US")).trim().contains(prescriptionVerificationData.get("directions").trim().toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"quantityInput").toLowerCase(new java.util.Locale("en", "US")).trim().equalsIgnoreCase(prescriptionVerificationData.get("quantityValue"))){
			return false;
		}
		return true;
	}
}
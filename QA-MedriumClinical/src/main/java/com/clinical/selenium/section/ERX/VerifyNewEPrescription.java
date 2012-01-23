package com.clinical.selenium.section.ERX;

import java.io.InputStream;
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

public class VerifyNewEPrescription extends AbstractERxTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default", "FIXME"}, description = "Test script to verify New Electronic Prescription request")
	@Parameters( {"eRxURL", "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyNewElectronicPrescription(String valERxUrl,String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ERxLib prescriptionTestData = new ERxLib();
		prescriptionTestData.workSheetName = "Prescription";
		prescriptionTestData.testCaseId = "TC_PRES_001";
		prescriptionTestData.fetchErxTestData();
		verifyNewElectronicPrescription(valERxUrl, seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}

	/**
	 * @Function 	: verifyNewElectronicPrescription
	 * @Description : Function to verify New Electronic Prescription
	 * @param 		: valERxUrl
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Aug 26, 2011
	 */
	public void verifyNewElectronicPrescription(String valERxUrl, String seleniumHost, int seleniumPort,String browser, String webSite, ERxLib prescriptionTestData){

		Selenium selenium = null;
		String accountID = null;
		String provID = null;
		String response = null;
		String idOfTheNewlyAddedRecord  = "";
		InputStream objXMLContent = null;
		InputStream objXMLContent2 = null;
		Collection<String> firstList = null;
		Collection<String> secondList = null;
		String prescriptionMorelinkId = "";
		Boolean isRefillRequestSuccess = false; 
		ApacheHttpClient objClientConnector = new ApacheHttpClient();
		ParseXML objParser = new ParseXML("clinical");
		Hashtable<String, String> prescriptionVerificationData = null;
		try{

			prescriptionVerificationData = objParser.fetchData("test-auto-match.xml","RefillRequest");

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
			String currentPrescriptionCount = getListCount(selenium.getText("CurrentPrescriptions"));
			if(currentPrescriptionCount.equals("0")){
				createPrescription(selenium, prescriptionTestData);
			}
			prescriptionMorelinkId = selenium.getAttribute("//div[@id='prescriptionList']/div/div[1]/div[2]@id");
			while(isElementPresent(selenium, prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}
			String	idOfTheRecord = selenium.getAttribute("//descendant::a[starts-with(@id, 'prescription')][1]@id");
			idOfTheNewlyAddedRecord = idOfTheRecord.split("prescription")[1];
			while(isElementPresent(selenium, prescriptionMorelinkId+"MoreLink") && selenium.isVisible(prescriptionMorelinkId+"MoreLink")){
				click(selenium, prescriptionMorelinkId+"MoreLink");
				waitForPageLoad(selenium);
			}

			//------------------------------------------------------------------------//
			//  Step-3:  Send the E-Prescription request using ERX URL               //
			//-----------------------------------------------------------------------//

			firstList = getDataBaseIDs(selenium, "refill");
			accountID= objParser.getTagValue("test-auto-match.xml", "accountID");
			provID= objParser.getTagValue("test-auto-match.xml", "provID");
			objXMLContent = objParser.updateXmlStreamField("test-auto-match.xml", "prescriptionID", idOfTheNewlyAddedRecord );
			objXMLContent2 = objParser.updateXmlStreamField(objXMLContent, "pon", accountID+":"+provID+":"+idOfTheNewlyAddedRecord);
			response = objClientConnector.postXMLContent(objXMLContent2, valERxUrl);

			Assert.assertEquals(response.split("@@")[1],"<?xml version=\"1.0\" ?><Response></Response>","AutomatchVerification Failed");

			//------------------------------------------------------------------------//
			//  Step-4:  Get The Newly added Refill Request                          //
			//-----------------------------------------------------------------------//

			selenium.refresh();
			waitForPageLoad(selenium);
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
			// TODO Auto-generated catch block
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

		if(!getText(selenium,"pharmacyAddress1").trim().equalsIgnoreCase(prescriptionVerificationData.get("pharmacistAddr1"))){
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
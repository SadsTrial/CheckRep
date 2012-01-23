package com.clinical.selenium.section.charts.prescription;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class Prescription_VerifyQuantity extends AbstractChartsTest {

	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"  }, description = "Verify Prescription Quantity")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void testVerifyQuantity(String seleniumHost, int seleniumPort,String browser, String webSite)throws Throwable{
		ChartsLib_UnitTest prescriptionTestData = new ChartsLib_UnitTest();
		prescriptionTestData.workSheetName = "Prescription";
		verifyQuantity(seleniumHost, seleniumPort, browser, webSite, prescriptionTestData);	
	}


	/**
	 * @Function 	: verifyQuantity
	 * @Description : Function to verify quantity with each time values
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 11, 2010
	 */
	public void verifyQuantity(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest prescriptionTestData){

		Selenium selenium = null;
		prescriptionTestData.userAccount = "UA0";
		prescriptionTestData.userName = "ASPTEST";
		prescriptionTestData.userPassword = "1234321";
		prescriptionTestData.patientID = "6971";
		prescriptionTestData.quantity = "1";
		prescriptionTestData.noOfDays = "2";
		String frequencyValues = "Other - See Comments,Every Week,Every Other Day,Every 12 Hours,Every 8 Hours,Every 6 Hours,Every 4 Hours,4 Times a day,3 Times a day,Twice a Day,Once a Day";
		String splitFrequencyValues[]  = frequencyValues.split(",");
		String quantity[] = {"0","0.2857142857142","1","4","6","8","12","8","6","4","2"};
		int counter = 0;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + prescriptionTestData.toString());
			loginFromPublicSite(selenium, prescriptionTestData.userAccount, prescriptionTestData.userName, prescriptionTestData.userPassword);
			searchPatient(selenium,prescriptionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Verifying quantity in Prescription                       //
			//--------------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			while (!(counter >= splitFrequencyValues.length)){

				Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.quantity),"Could not enter Take each time; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(select(selenium, "frequencyInput", splitFrequencyValues[counter].trim()),"Could not select Frequency Input - "+ splitFrequencyValues[counter].trim() + "; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.noOfDays)," Could not enter No Of Days; More Details :" + prescriptionTestData.toString());
				
				type(selenium,"ss_quantityInput", "0");
				Assert.assertEquals(getValue(selenium, "ss_quantityInput").trim(),quantity[counter].trim()," Quantity is displayed as "+getValue(selenium, "ss_quantityInput")+" instead of "+ quantity[counter]+ " - Failed; More Details :" + prescriptionTestData.toString());
				Assert.assertTrue(click(selenium,"cancelButton"),"Could not click Cancel Button; More Details :" + prescriptionTestData.toString());
				counter++;
			}

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
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
}
package com.clinical.selenium.section.charts.vitals;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForVitals extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Save button and Data for Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedVitals(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib vitalsTestData = new ChartsLib();
		vitalsTestData.workSheetName = "VerifySaveButtonForVitals";
		vitalsTestData.testCaseId = "TC_VIT_001";
		vitalsTestData.fetchChartsTestData();
		verifySaveButtonForUnSavedVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: createNewVitals
	 * @Description : Function to create a New Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 26, 2011
	 */
	public void verifySaveButtonForUnSavedVitals(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib vitalsTestData){

		Selenium selenium = null;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Vitals and enter details                   //
			//--------------------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link; More Details" +vitalsTestData.toString());
			waitForPageLoad(selenium);

			vitalsTestData.vitalDate = vitalsTestData.vitalDate.equals("") ? getValue(selenium,"startdateInput").trim(): vitalsTestData.vitalDate.trim() ;

			Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());

			vitalsTestData.vitalTime = vitalsTestData.vitalTime.equals("") ? getValue(selenium,"vitalTimeInput").trim(): vitalsTestData.vitalTime.trim() ;

			Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"heightFeetsInput", vitalsTestData.patientHeightFeet));
			Assert.assertTrue(type(selenium,"heightInchesInput", vitalsTestData.patientHeightInches),"Could not enter the test data for Patient Height (Inches); More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"weightpoundsInput", vitalsTestData.patientWeightPounds),"Could not enter the test data for Patient Weight (Pounds); More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"weightouncesInput", vitalsTestData.patientWeightOunces),"Could not enter the test data for Patient Weight (Ounces); More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"temperatureInput", vitalsTestData.patientTemperature),"Could not enter the test data for Patient Temperature; More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"pulseInput", vitalsTestData.patientPulse),"Could not enter the test data for Patient Pulse; More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"systolicInput", vitalsTestData.patientBPSystolic),"Could not enter the test data for Patient Systolic Pressure; More Details" +vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"diastolicInput", vitalsTestData.patientBPDiastolic),"Could not enter the test data for Patient Diastolic Pressure; More Details" +vitalsTestData.toString());

			click(selenium,"vitals");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("vitalsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Vitals Add(+)  button is OFF and not backgrounded by Orange; More Details" +vitalsTestData.toString());  

			Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link; More Details" +vitalsTestData.toString());
			waitForPageLoad(selenium);
			
			verifyValues(selenium, vitalsTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +vitalsTestData.toString());

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + vitalsTestData.toString());
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


	public void verifyValues(Selenium selenium, ChartsLib vitalsTestData){

		Assert.assertEquals(getValue(selenium,"startdateInput").trim(),vitalsTestData.vitalDate,"Un saved Start Date Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"heightFeetsInput").trim(),vitalsTestData.patientHeightFeet,"Un saved Height Feet Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"heightInchesInput").trim(),vitalsTestData.patientHeightInches,"Un saved Height Inches Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"weightpoundsInput").trim(),vitalsTestData.patientWeightPounds,"Un saved Weight Pounds Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"weightouncesInput").trim(),vitalsTestData.patientWeightOunces,"Un saved Weight Ounces Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"temperatureInput").trim(),vitalsTestData.patientTemperature,"Un saved Temprature Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"pulseInput").trim(),vitalsTestData.patientPulse,"Un saved  Pulse Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"systolicInput").trim(),vitalsTestData.patientBPSystolic,"Un saved Systolic Input Value is not available; It is modified; More Details" +vitalsTestData.toString());
		Assert.assertEquals(getValue(selenium,"diastolicInput").trim(),vitalsTestData.patientBPDiastolic,"Un saved diastolic Input Value is not available; It is modified; More Details" +vitalsTestData.toString());

	}
}

package com.clinical.selenium.section.charts.vitals;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateDuplicateVitals extends AbstractChartsTest {
	
	ChartsLib vitalsTestData = new ChartsLib();
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating duplicate Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createDuplicateVitals(String seleniumHost, int seleniumPort,String browser, String webSite){
		vitalsTestData.workSheetName = "DuplicateVitals";
		vitalsTestData.testCaseId = "TC_DVT_001";
		vitalsTestData.fetchChartsTestData();
		createDuplicate(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: CreateDuplicate
	 * @Description : Function to Creating Duplicate Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Aug 05, 2010
	 */
	public void createDuplicate(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib vitalsTestData){

		Selenium selenium = null;
		String heightDate = null;
		String heightTime = null;
		String weightDate = null;
		String weightTime = null;
		String temperatureDate = null;
		String temperatureTime = null;
		String pulseDate = null;
		String pulseTime = null;
		String bloodpressureDate = null;
		String bloodpressureTime = null;
		boolean isWeightPresent = false;
		boolean isHeightPresent = false;
		boolean isTemperaturePresent = false;
		boolean isPulsePresent = false;
		boolean isBloodPressurePresent = false;
		int counter = 1;
		String heightAlertText = "Body height vital sign already exist in the system, at the same date and time";
		String weightAlertText = "Body weight vital sign already exist in the system, at the same date and time";
		String temperatureAlertText = "Body temperature vital sign already exist in the system, at the same date and time";
		String pulseAlertText = "Heart beat vital sign already exist in the system, at the same date and time";
		String bloodpressureAlertText = "Intravascular systolic vital sign already exist in the system, at the same date and time";		

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
		
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Identify if there are any existing vitals                //
			//--------------------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]")){
				if(!isHeightPresent && getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Body height")){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					heightTime = getValue(selenium,"vitalTimeInput").trim();
					heightDate = getValue(selenium,"startdateInput").trim();
					click(selenium,"cancelButton");
					isHeightPresent=true;
				}

				if(!isWeightPresent && getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Body weight")){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					weightTime = getValue(selenium,"vitalTimeInput").trim();
					weightDate = getValue(selenium,"startdateInput").trim();
					click(selenium,"cancelButton");
					waitForPageLoad(selenium);					
					isWeightPresent=true;
				}

				if(!isTemperaturePresent && getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Body temperature"))	{
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					temperatureTime = getValue(selenium,"vitalTimeInput").trim();
					temperatureDate = getValue(selenium,"startdateInput").trim();
					click(selenium,"cancelButton");
					waitForPageLoad(selenium);
					isTemperaturePresent=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Heart beat")&&!isPulsePresent){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					pulseTime = getValue(selenium,"vitalTimeInput").trim();
					pulseDate = getValue(selenium,"startdateInput").trim();
					click(selenium,"cancelButton");
					waitForPageLoad(selenium);
					isPulsePresent=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Blood Pressure")&&!isBloodPressurePresent){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					bloodpressureTime = getValue(selenium,"vitalTimeInput").trim();
					bloodpressureDate = getValue(selenium,"startdateInput").trim();
					click(selenium,"cancelButton");
					waitForPageLoad(selenium);
					isBloodPressurePresent=true;
				}
				counter++;

				if(isBloodPressurePresent  && isHeightPresent && isPulsePresent && isWeightPresent && isTemperaturePresent){
					break;
				}
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Create New Vitals if they does not exist                 //
			//--------------------------------------------------------------------//

			if(!isHeightPresent){
				heightDate = vitalsTestData.vitalDate;
				heightTime = vitalsTestData.vitalTime;
				addHeight(selenium, heightDate, heightTime, "6", "5", isHeightPresent, heightAlertText);
			}

			if(!isWeightPresent){
				weightDate = vitalsTestData.vitalDate;
				weightTime = vitalsTestData.vitalTime;
				addWeight(selenium, weightDate, weightTime, "70", "10", isWeightPresent, weightAlertText);
			}

			if(!isTemperaturePresent){
				temperatureDate = vitalsTestData.vitalDate;
				temperatureTime = vitalsTestData.vitalTime;
				addTemp(selenium, temperatureDate, temperatureTime, "60", isTemperaturePresent, temperatureAlertText);
			}

			if(!isPulsePresent){
				pulseDate = vitalsTestData.vitalDate;
				pulseTime = vitalsTestData.vitalTime;
				addPulse(selenium, pulseDate, pulseTime, "84", isPulsePresent, pulseAlertText);
			}

			if(!isBloodPressurePresent){
				bloodpressureDate = vitalsTestData.vitalDate;
				bloodpressureTime = vitalsTestData.vitalTime;
				addBloodPressure(selenium, bloodpressureDate, bloodpressureTime, "85", "90", isBloodPressurePresent, bloodpressureAlertText);
			}

			//-----------------------------------------------------------------------------------------//
			//  Step-4:  Create Vitals with existing date and time and verify the Alerts               //
			//-----------------------------------------------------------------------------------------//
			
			addHeight(selenium, heightDate, heightTime, "6", "5", isHeightPresent, heightAlertText);
			addWeight(selenium, weightDate, weightTime, "70", "10", isWeightPresent, weightAlertText);
			addTemp(selenium, temperatureDate, temperatureTime, "60", isTemperaturePresent, temperatureAlertText);
			addPulse(selenium, pulseDate, pulseTime, "84", isPulsePresent, pulseAlertText);
			addBloodPressure(selenium, bloodpressureDate, bloodpressureTime, "85", "90", isBloodPressurePresent, bloodpressureAlertText);
						

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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}
			
			counter++;
		}
	}

	public void addHeight(Selenium selenium, String vitalDate, String vitalTime, String heightInFeet, String heightInInches, boolean isHeightPresent, String heightAlertText)
	{
		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link");
		waitForPageLoad(selenium);

		Assert.assertTrue(enterDate(selenium, "startdateInput",vitalDate ),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"heightFeetsInput", heightInFeet),"Could not enter the test data for Patient Height (Ft); More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"heightInchesInput",heightInInches),"Could not enter the test data for Patient Height (Inches); More Details :" + vitalsTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		verifyAlerts(selenium, isHeightPresent, heightAlertText);

		click(selenium, "cancelButton");
		waitForPageLoad(selenium);
	}

	public void addWeight(Selenium selenium, String vitalDate, String vitalTime, String weightInPounds, String weightInOunces, boolean isWeightPresent, String weightAlertText)
	{
		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(enterDate(selenium, "startdateInput",vitalDate ),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"weightpoundsInput",weightInPounds),"Could not enter the test data for Patient Weight (Pounds); More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"weightouncesInput", weightInOunces),"Could not enter the test data for Patient Weight (Ounces); More Details :" + vitalsTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button");
		waitForPageLoad(selenium);


		verifyAlerts(selenium, isWeightPresent, weightAlertText);

		click(selenium, "cancelButton");
		waitForPageLoad(selenium);

	}

	public void addTemp(Selenium selenium, String vitalDate, String vitalTime, String temperature, boolean isTemperaturePresent, String temperatureAlertText)
	{
		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link");
		waitForPageLoad(selenium);

		Assert.assertTrue(enterDate(selenium, "startdateInput",vitalDate ),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"temperatureInput", temperature),"Could not enter the test data for Patient Temperature; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);
		
		verifyAlerts(selenium, isTemperaturePresent, temperatureAlertText);

		click(selenium, "cancelButton");
		waitForPageLoad(selenium);
	}

	public void addPulse(Selenium selenium, String vitalDate, String vitalTime, String pulse,  boolean isPulsePresent, String pulseAlertText)
	{
		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);
		
		Assert.assertTrue(enterDate(selenium, "startdateInput",vitalDate ),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"pulseInput",pulse),"Could not enter the test data for Patient Pulse; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		verifyAlerts(selenium, isPulsePresent, pulseAlertText);

		click(selenium, "cancelButton");
		waitForPageLoad(selenium);
	}	

	public void addBloodPressure(Selenium selenium, String vitalDate, String vitalTime, String systolicPressure, String diastolicPressure, boolean isBloodPressurePresent, String bpAlertText)
	{
		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link; More Details :" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(enterDate(selenium, "startdateInput",vitalDate ),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"systolicInput",systolicPressure),"Could not enter the test data for Patient Systolic Pressure; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(type(selenium,"diastolicInput",  diastolicPressure),"Could not enter the test data for Patient Diastolic Pressure; More Details :" + vitalsTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button");
		waitForPageLoad(selenium);

		verifyAlerts(selenium, isBloodPressurePresent, bpAlertText);

		click(selenium, "cancelButton");
		waitForPageLoad(selenium);
	}

	public void verifyAlerts(Selenium selenium, boolean isVitalPresent, String expectedAlertText)
	{
		if(selenium.isAlertPresent()){
			Assert.assertTrue(isVitalPresent,"Unexpected Alert Occured when adding the Vital - "+ selenium.getAlert()+ "; More Details :" + vitalsTestData.toString());
			String alertText = selenium.getAlert();
			Assert.assertEquals(alertText.trim().replace(" ", "").toLowerCase(new java.util.Locale("en", "US")),expectedAlertText.trim().replace(" ", "").toLowerCase(new java.util.Locale("en", "US")),"Expected  Alert is not  Occured - Expected : "+ expectedAlertText +"  : ACTUAL : "+alertText + "; More Details :" + vitalsTestData.toString());

		}else if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div")){
			String alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
			Assert.assertEquals(alertText.trim().replace(" ", "").toLowerCase(new java.util.Locale("en", "US")), expectedAlertText.trim().replace(" ", "").toLowerCase(new java.util.Locale("en", "US")),"Expected  Alert is not  Occured - Expected : "+ expectedAlertText +"  : ACTUAL : "+alertText + "; More Details :" + vitalsTestData.toString());

			click(selenium, "errorCloseButton");
						
			waitForPageLoad(selenium);	
		}else{
			if(isVitalPresent){
				Assert.fail("No Alert is displyed while trying to add duplicate Vitals ; More Details :" + vitalsTestData.toString());
			}
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"Unexpected Warning! - " + getMessage(selenium,"//p") + "; More Details :" + vitalsTestData.toString());

	}

}

package com.clinical.selenium.section.charts.vitals;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateVitals extends AbstractChartsTest {


	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Updating Existing Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateExistingVitals(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib vitalsTestData = new ChartsLib();
		vitalsTestData.workSheetName = "UpdateVitals";
		vitalsTestData.testCaseId = "TC_UVT_001";
		vitalsTestData.fetchChartsTestData();
		updateExistVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: updateExistVitals
	 * @Description : Function to Updating Existing Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 30, 2010
	 */
	public void updateExistVitals(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib vitalsTestData){

		Selenium selenium = null;
		int counter = 1;
		boolean heightUpdated = false;
		boolean weightUpdated = false;
		boolean temperatureUpdated = false;
		boolean heartBeatUpdated = false;
		boolean bloodPressureUpdated = false;
		boolean verifiedHeight = false;
		boolean verifiedWeight = false;
		boolean verifiedTemperature = false;
		boolean verifiedHeartBeat = false;
		boolean verifiedBloodPressure = false;
		boolean isVitalsPresent=false;

		String heightElementId = null;
		String weightElementId = null;
		String tempretureElementId = null;
		String beatElementId = null;
		String bloodPressureElementId = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Create New vital if no vital is available to Update       //
			//--------------------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);

			if(!isElementPresent(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]")){
				vitalsTestData = null;
				vitalsTestData = new ChartsLib();
				vitalsTestData.workSheetName = "Vitals";
				vitalsTestData.testCaseId = "TC_VIT_001";
				vitalsTestData.fetchChartsTestData();

				createVitals(selenium, vitalsTestData);
			}
			vitalsTestData = null;
			vitalsTestData = new ChartsLib();
			vitalsTestData.workSheetName = "UpdateVitals";
			vitalsTestData.testCaseId = "TC_UVT_001";
			vitalsTestData.fetchChartsTestData();

			if(vitalsTestData.vitalDate.equals("") || vitalsTestData.vitalTime.equals("")){

				click(selenium,"vitals");
				waitForPageLoad(selenium);

				Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find (+) Add Vitals Link; More Details" +vitalsTestData.toString());
				waitForPageLoad(selenium);

				if(vitalsTestData.vitalDate.equals("")){
					vitalsTestData.vitalDate = vitalsTestData.vitalDate.equals("") ? getValue(selenium,"startdateInput").trim(): vitalsTestData.vitalDate.trim() ;
				}
				if(vitalsTestData.vitalTime.equals("")){
					vitalsTestData.vitalTime = vitalsTestData.vitalTime.equals("") ? getValue(selenium,"vitalTimeInput").trim(): vitalsTestData.vitalTime.trim() ;
				}
				click(selenium, "cancelButton");
			}

			//--------------------------------------------------------------------//
			//  Step-3: Update the Vital with New Values                          //
			//--------------------------------------------------------------------//

			waitForElement(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]", 20000);

			while(selenium.isElementPresent( "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]")){
				isVitalsPresent=true;
				if(getText(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body height")&&!heightUpdated){
					heightElementId = selenium.getAttribute("//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a@id");
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);

					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"heightFeetsInput", vitalsTestData.patientHeightFeet));
					Assert.assertTrue(type(selenium,"heightInchesInput", vitalsTestData.patientHeightInches),"Could not enter the test data for Patient Height (Inches); More Details" +vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +vitalsTestData.toString());

					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
					heightUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body weight")&&!weightUpdated){
					weightElementId = selenium.getAttribute("//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a@id");
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);


					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"weightpoundsInput", vitalsTestData.patientWeightPounds),"Could not enter the test data for Patient Weight (Pounds); More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"weightouncesInput", vitalsTestData.patientWeightOunces),"Could not enter the test data for Patient Weight (Ounces); More Details" +vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +vitalsTestData.toString());

					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
					weightUpdated=true;

				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body temperature")&&!temperatureUpdated)	{
					tempretureElementId = selenium.getAttribute("//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a@id");
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);

					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"temperatureInput", vitalsTestData.patientTemperature),"Could not enter the test data for Patient Temperature; More Details" +vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +vitalsTestData.toString());

					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

					temperatureUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Heart beat")&&!heartBeatUpdated){
					beatElementId = selenium.getAttribute("//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a@id");
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);

					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"pulseInput", vitalsTestData.patientPulse),"Could not enter the test data for Patient Pulse; More Details" +vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +vitalsTestData.toString());


					waitForPageLoad(selenium);

					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}

					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
					heartBeatUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Blood Pressure")&&!bloodPressureUpdated){
					bloodPressureElementId = selenium.getAttribute("//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a@id");
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);

					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"systolicInput", vitalsTestData.patientBPSystolic),"Could not enter the test data for Patient Systolic Pressure; More Details" +vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"diastolicInput", vitalsTestData.patientBPDiastolic),"Could not enter the test data for Patient Diastolic Pressure; More Details" +vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +vitalsTestData.toString());


					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
					bloodPressureUpdated=true;
				}

				if(heightUpdated && weightUpdated && heartBeatUpdated && temperatureUpdated && bloodPressureUpdated){
					break;
				}

				counter++;
			}

			Assert.assertTrue(isVitalsPresent,"NO Reocrds Found to Update; More Details" +vitalsTestData.toString());

			//--------------------------------------------------------------------//
			//  Step-4: Verify the updated Vitals                                 //
			//--------------------------------------------------------------------//

			counter = 1;
			if(heightElementId == null || weightElementId == null || beatElementId == null || tempretureElementId == null || bloodPressureElementId == null  ){
				Assert.fail(" Could Capture the Ids of the Updated Records; More Details : " + vitalsTestData.toString());
			}

			while(!(selenium.isElementPresent(weightElementId)) &&(selenium.isElementPresent(heightElementId) )&&(selenium.isElementPresent(beatElementId) ) && (selenium.isElementPresent(bloodPressureElementId) ) && (selenium.isElementPresent(tempretureElementId))  && selenium.isElementPresent( "patientVitalsListMoreLink" ) && selenium.isVisible( "patientVitalsListMoreLink" ) ){
				selenium.click("patientVitalsListMoreLink");
				waitForPageLoad(selenium);
			}



			click(selenium, heightElementId);
			waitForPageLoad(selenium);
			verifiedHeight = verifyHeight(selenium, vitalsTestData);
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium, weightElementId);
			waitForPageLoad(selenium);
			verifiedWeight = verifyWeight(selenium, vitalsTestData);
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium, tempretureElementId);
			waitForPageLoad(selenium);
			verifiedTemperature = verifyTemperature(selenium, vitalsTestData);
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium, beatElementId);
			waitForPageLoad(selenium);
			verifiedHeartBeat = verifyHeartBeat(selenium, vitalsTestData);
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium,bloodPressureElementId);
			waitForPageLoad(selenium);
			verifiedBloodPressure = verifyBloodPressure(selenium, vitalsTestData);
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			Assert.assertTrue(verifiedHeight,"Height is not stored correctly; Vital Updation Failed; More Details : "+vitalsTestData.toString());
			Assert.assertTrue(verifiedWeight,"Weight is not stored correctly; Vital Updation Failed; More Details : "+vitalsTestData.toString());
			Assert.assertTrue(verifiedTemperature,"Temperature is not stored correctly; Vital Updation Failed; More Details : "+vitalsTestData.toString());
			Assert.assertTrue(verifiedHeartBeat,"Pulse is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());
			Assert.assertTrue(verifiedBloodPressure,"Blood Pressure is not stored correctly; Vital Updation Failed; More Details : "+vitalsTestData.toString());

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

	public boolean verifyHeight(Selenium selenium, ChartsLib vitalsTestData){
		if(!getValue(selenium,"vitalTimeInput").trim().equalsIgnoreCase(vitalsTestData.vitalTime)){
			return false;
		}
		if(!getValue(selenium,"heightFeetsInput").trim().equalsIgnoreCase(vitalsTestData.patientHeightFeet)){
			return false;
		}
		if(!getValue(selenium,"heightInchesInput").trim().equalsIgnoreCase(vitalsTestData.patientHeightInches)){
			return false;
		}
		return true;
	}

	public boolean verifyWeight(Selenium selenium, ChartsLib vitalsTestData){
		if(!getValue(selenium,"vitalTimeInput").trim().equalsIgnoreCase(vitalsTestData.vitalTime)){
			return false;
		}
		if(!getValue(selenium,"weightpoundsInput").trim().equalsIgnoreCase(vitalsTestData.patientWeightPounds)){
			return false;
		}
		if(!getValue(selenium,"weightouncesInput").trim().equalsIgnoreCase(vitalsTestData.patientWeightOunces)){
			return false;
		}
		return true;
	}

	public boolean verifyTemperature(Selenium selenium, ChartsLib vitalsTestData){
		if(!getValue(selenium,"vitalTimeInput").trim().equalsIgnoreCase(vitalsTestData.vitalTime)){
			return false;
		}
		if(!getValue(selenium,"temperatureInput").trim().equalsIgnoreCase(vitalsTestData.patientTemperature)){
			return false;
		}
		return true;
	}

	public boolean verifyHeartBeat(Selenium selenium, ChartsLib vitalsTestData){

		if(!getValue(selenium,"vitalTimeInput").trim().equalsIgnoreCase(vitalsTestData.vitalTime)){
			return false;
		}

		if(!getValue(selenium,"pulseInput").trim().equalsIgnoreCase(vitalsTestData.patientPulse)){
			return false;
		}
		return true;
	}

	public boolean verifyBloodPressure(Selenium selenium, ChartsLib vitalsTestData){

		if(!getValue(selenium,"vitalTimeInput").trim().equalsIgnoreCase(vitalsTestData.vitalTime)){
			return false;
		}

		if(!getValue(selenium,"systolicInput").trim().equalsIgnoreCase(vitalsTestData.patientBPSystolic)){
			return false;
		}

		if(!getValue(selenium,"diastolicInput").trim().equalsIgnoreCase(vitalsTestData.patientBPDiastolic)){
			return false;
		}

		return true;
	}

}

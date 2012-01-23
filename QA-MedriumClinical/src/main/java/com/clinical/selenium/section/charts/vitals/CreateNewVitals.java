package com.clinical.selenium.section.charts.vitals;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewVitals extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewVitals(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib vitalsTestData = new ChartsLib();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "TC_VIT_001";
		vitalsTestData.fetchChartsTestData();
		createNewVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: createNewVitals
	 * @Description : Function to create a New Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 08, 2010
	 */
	public void createNewVitals(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib vitalsTestData){

		Selenium selenium = null;
		boolean verifiedHeight = false;
		boolean verifiedWeight = false;
		boolean verifiedTemperature = false;
		boolean verifiedHeartBeat = false;
		boolean verifiedBloodPressure = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Validating the vital Date and Time                       //
			//--------------------------------------------------------------------//

			if(vitalsTestData.vitalDate.equals("") || vitalsTestData.vitalTime.equals("")){
				click(selenium,"vitals");
				waitForPageLoad(selenium);

				Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link");
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
			//  Step-3:  Click Add New Vitals and enter details                   //
			//--------------------------------------------------------------------//

			while((selenium.isElementPresent("css=a#patientVitalsListMoreLink") && selenium.isVisible("css=a#patientVitalsListMoreLink") )){
				selenium.click("css=a#patientVitalsListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "vitals");
			click(selenium,"vitals");
			waitForPageLoad(selenium);

			createVitals(selenium, vitalsTestData);


			waitForPageLoad(selenium);
			while((selenium.isElementPresent("css=a#patientVitalsListMoreLink") && selenium.isVisible("css=a#patientVitalsListMoreLink") )){
				selenium.click("css=a#patientVitalsListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium,"vitals");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()<1){
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ vitalsTestData.toString());
			}

			//--------------------------------------------------------------------//
			//  Step-4:  Verifying whether the details entered are saved properly //
			//--------------------------------------------------------------------//

			for(int i=0;i<secondList.size();i++ ){

				idOfTheNewlyAddedRecord = secondList.toArray()[i].toString();
				while(!(selenium.isElementPresent(idOfTheNewlyAddedRecord)) && selenium.isElementPresent( "css=a#patientVitalsListMoreLink" ) && selenium.isVisible( "css=a#patientVitalsListMoreLink" ) ){
					selenium.click("css=a#patientVitalsListMoreLink");
					waitForPageLoad(selenium);
				}


				String vitalName = selenium.getText(idOfTheNewlyAddedRecord);
				
				if(!verifiedHeight && vitalName.toLowerCase(new java.util.Locale("en", "US")).startsWith("body height")){
					click(selenium, idOfTheNewlyAddedRecord);
					waitForPageLoad(selenium);
					verifiedHeight = verifyHeight(selenium, vitalsTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}else if(!verifiedWeight &&  vitalName.toLowerCase(new java.util.Locale("en", "US")).startsWith("body weight")){
					click(selenium, idOfTheNewlyAddedRecord);
					waitForPageLoad(selenium);
					verifiedWeight = verifyWeight(selenium, vitalsTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}else if(!verifiedTemperature && vitalName.toLowerCase(new java.util.Locale("en", "US")).startsWith("body temperature")){
					click(selenium, idOfTheNewlyAddedRecord);
					waitForPageLoad(selenium);
					verifiedTemperature = verifyTemperature(selenium, vitalsTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}else if(!verifiedHeartBeat && vitalName.toLowerCase(new java.util.Locale("en", "US")).startsWith("heart beat")){
					click(selenium, idOfTheNewlyAddedRecord);
					waitForPageLoad(selenium);
					verifiedHeartBeat = verifyHeartBeat(selenium, vitalsTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}else if(!verifiedBloodPressure && vitalName.toLowerCase(new java.util.Locale("en", "US")).startsWith("blood pressure")){
					click(selenium, idOfTheNewlyAddedRecord);
					waitForPageLoad(selenium);
					verifiedBloodPressure = verifyBloodPressure(selenium, vitalsTestData);
					click(selenium, "cancelButton");
					waitForPageLoad(selenium);
				}
			
			if(verifiedBloodPressure && verifiedHeartBeat && verifiedHeight && verifiedTemperature && verifiedWeight){
				break;
			}
		}

		Assert.assertTrue(verifiedHeight,"Height is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());
		Assert.assertTrue(verifiedWeight,"Weight is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());
		Assert.assertTrue(verifiedTemperature,"Temperature is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());
		Assert.assertTrue(verifiedHeartBeat,"Pulse is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());
		Assert.assertTrue(verifiedBloodPressure,"Blood Pressure is not stored correctly; Vital Creation Failed; More Details : "+vitalsTestData.toString());


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

	if(!getValue(selenium,"heightFeetsInput").trim().equalsIgnoreCase(vitalsTestData.patientHeightFeet)){
		return false;
	}

	if(!getValue(selenium,"heightInchesInput").trim().equalsIgnoreCase(vitalsTestData.patientHeightInches)){
		return false;
	}
	return true;
}

public boolean verifyWeight(Selenium selenium, ChartsLib vitalsTestData){

	if(!getValue(selenium,"weightpoundsInput").trim().equalsIgnoreCase(vitalsTestData.patientWeightPounds)){
		return false;
	}

	if(!getValue(selenium,"weightouncesInput").trim().equalsIgnoreCase(vitalsTestData.patientWeightOunces)){
		return false;
	}
	return true;
}

public boolean verifyTemperature(Selenium selenium, ChartsLib vitalsTestData){

	if(!getValue(selenium,"temperatureInput").trim().equalsIgnoreCase(vitalsTestData.patientTemperature)){
		return false;
	}
	return true;
}

public boolean verifyHeartBeat(Selenium selenium, ChartsLib vitalsTestData){

	if(!getValue(selenium,"pulseInput").trim().equalsIgnoreCase(vitalsTestData.patientPulse)){
		return false;
	}
	return true;
}

public boolean verifyBloodPressure(Selenium selenium, ChartsLib vitalsTestData){

	if(!getValue(selenium,"systolicInput").trim().equalsIgnoreCase(vitalsTestData.patientBPSystolic)){
		return false;
	}

	if(!getValue(selenium,"diastolicInput").trim().equalsIgnoreCase(vitalsTestData.patientBPDiastolic)){
		return false;
	}

	return true;
}

public boolean verifyDiastolic(Selenium selenium, ChartsLib vitalsTestData){

	if(!getValue(selenium,"diastolicInput").trim().equalsIgnoreCase(vitalsTestData.patientBPDiastolic)){
		return false;
	}
	return true;
}
}

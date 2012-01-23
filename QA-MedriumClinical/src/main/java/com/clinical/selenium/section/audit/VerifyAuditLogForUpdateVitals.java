package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForUpdateVitals extends AbstractAuditTest {

	boolean isValueChecked = false;

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Audit Logs For Update Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForUpdateVitals(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib vitalsTestData = new AuditLib();
		vitalsTestData.workSheetName = "UpdateVitals";
		vitalsTestData.testCaseId = "TC_UVT_001";
		vitalsTestData.fetchAuditTestData();
		verifyAuditLogsForUpdateVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForUpdateVitals
	 * @Description : Function to Verifying Audit Logs For Update Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 30, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForUpdateVitals(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib vitalsTestData){

		int counter = 1;
		Selenium selenium = null;
		boolean heightUpdated = false;
		boolean weightUpdated = false;
		boolean temperatureUpdated = false;
		boolean heartBeatUpdated = false;
		boolean bloodPressureUpdated = false;
		boolean isVitalsPresent=false;
		boolean isAuditResultVerified = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Updated the existing Vitals                               //
			//--------------------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);
			click(selenium,"link=[All]");
			waitForPageLoad(selenium);

			if(!isElementPresent(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]")){
				vitalsTestData = null;
				vitalsTestData = new AuditLib();
				vitalsTestData.workSheetName = "Vitals";
				vitalsTestData.testCaseId = "TC_VIT_001";
				vitalsTestData.fetchAuditTestData();
				createVitals(selenium, vitalsTestData);
			}

			Date currentDate = new Date();
			vitalsTestData = null;
			vitalsTestData = new AuditLib();
			vitalsTestData.workSheetName = "UpdateVitals";
			vitalsTestData.testCaseId = "TC_UVT_001";
			vitalsTestData.fetchAuditTestData();

			while(selenium.isElementPresent( "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]")){
				isVitalsPresent=true;
				if(getText(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body height")&&!heightUpdated){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					if(selenium.isTextPresent("feet")){
						click(selenium, "unitsLinkLabel");
						waitForPageLoad(selenium);
					}

					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"heightFeetsInput", vitalsTestData.patientHeightFeet),"; Failed to set height; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"heightInchesInput", vitalsTestData.patientHeightInches),"Could not enter the test data for Patient Height (Inches); More Details :" + vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + vitalsTestData.toString());
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + vitalsTestData.toString());
					heightUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body weight")&&!weightUpdated){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"weightpoundsInput", vitalsTestData.patientWeightPounds),"Could not enter the test data for Patient Weight (Pounds); More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"weightouncesInput", vitalsTestData.patientWeightOunces),"Could not enter the test data for Patient Weight (Ounces); More Details :" + vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
					waitForPageLoad(selenium);

					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+ "; More Details :" + vitalsTestData.toString());
					}

					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + vitalsTestData.toString());
					weightUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Body temperature")&&!temperatureUpdated)	{

					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"temperatureInput", vitalsTestData.patientTemperature),"Could not enter the test data for Patient Temperature; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
					waitForPageLoad(selenium);

					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + vitalsTestData.toString());
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + vitalsTestData.toString());
					temperatureUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]").trim().contains("Heart beat")&&!heartBeatUpdated){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"pulseInput", vitalsTestData.patientPulse),"Could not enter the test data for Patient Pulse; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());

					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + vitalsTestData.toString());
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + vitalsTestData.toString());
					heartBeatUpdated=true;
				}

				if(getText(selenium,"//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]").trim().contains("Blood Pressure")&&!bloodPressureUpdated){
					click(selenium, "//div[@id='patientVitalsList']/table/tbody[1]/tr["+ counter +"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"systolicInput", vitalsTestData.patientBPSystolic),"Could not enter the test data for Patient Systolic Pressure; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(type(selenium,"diastolicInput", vitalsTestData.patientBPDiastolic),"Could not enter the test data for Patient Diastolic Pressure; More Details :" + vitalsTestData.toString());
					Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());

					waitForPageLoad(selenium);
					if(selenium.isAlertPresent()){
						Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+"; More Details :" + vitalsTestData.toString());
					}
					Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") +"; More Details :" + vitalsTestData.toString());
					bloodPressureUpdated=true;
				}
				if(heightUpdated && weightUpdated && heartBeatUpdated && temperatureUpdated && bloodPressureUpdated){
					break;
				}
				counter++;
			}
			Assert.assertTrue(isVitalsPresent,"NO Reocrds Found to Update; More Details :" + vitalsTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details updated are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Date currentDate1 = new Date(); 
			counter = 1;
			int counter1 = 0;

			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			searchRecordAlternative(selenium, currentDate, currentDate1, "Update", "Vital", vitalsTestData);

			int xpathCounter = (Integer) selenium.getXpathCount("//div[starts-with(@id,'detail-Update-VitalSign')]");
			String results = "";
			for(int i=1;i<=xpathCounter;i++){
				results = results + selenium.getText("//descendant::div[starts-with(@id,'detail-Update-VitalSign')][" +i+ "]")+"Changed";
			}

			String auditValue[] = results.split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, vitalsTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}


			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + vitalsTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + vitalsTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + vitalsTestData.toString());
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

	public void waitForAlert(Selenium selenium){
		int counter = 0;
		while(!selenium.isAlertPresent()){
			if(counter == 5){
				break;
			}

			counter++;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value, AuditLib vitalTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("observationdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(vitalTestData.vitalDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				return true;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("value")){
			isValueChecked = true;
			if(value.trim().contains((vitalTestData.patientWeightPounds)+"."+vitalTestData.patientWeightOunces)){
				return true;
			}else if(value.trim().equalsIgnoreCase((vitalTestData.patientHeightFeet+vitalTestData.patientHeightInches))){
				return true;
			}else if(value.trim().equalsIgnoreCase(vitalTestData.patientTemperature)){
				return true;
			}else if(value.trim().equalsIgnoreCase(vitalTestData.patientPulse)){
				return true;
			}else if(value.trim().equalsIgnoreCase(vitalTestData.patientBPSystolic)){
				return true;
			}else if(value.trim().equalsIgnoreCase(vitalTestData.patientBPDiastolic)){
				return true;
			}else{
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
				return false;
			}
		}else{
			return true;
		}
	}

}
package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewVitals extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for verifying Audit Log For New Vitals")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewVitals(String seleniumHost, int seleniumPort, String browser, String webSite){
		AuditLib vitalsTestData = new AuditLib();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "TC_VIT_001";
		vitalsTestData.fetchAuditTestData();
		verifyAuditLogForNewVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: verifyAuditLogForNewVitals
	 * @Description : Function to verify Audit Log For New Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 08, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewVitals(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib vitalsTestData){

		Selenium selenium = null;
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
			//  Step-2:  Click Add New Vitals and enter details                   //
			//--------------------------------------------------------------------//

			if(vitalsTestData.vitalDate.equals("") || vitalsTestData.vitalTime.equals("")){

				click(selenium,"vitals");
				waitForPageLoad(selenium);
				Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link; More Details :" + vitalsTestData.toString());
				waitForPageLoad(selenium);
				if(vitalsTestData.vitalDate.equals("")){
					vitalsTestData.vitalDate = vitalsTestData.vitalDate.equals("") ? getValue(selenium, "startdateInput").trim(): vitalsTestData.vitalDate.trim() ;
				}
				if(vitalsTestData.vitalTime.equals("")){
					vitalsTestData.vitalTime = vitalsTestData.vitalTime.equals("") ? getValue(selenium, "vitalTimeInput").trim(): vitalsTestData.vitalTime.trim() ;
				}
				click(selenium, "cancelButton");
			}
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			createVitals(selenium, vitalsTestData);
			Date currentDate1 = new Date(); 

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//
			int counter1 = 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			searchRecordAlternative(selenium, currentDate, currentDate1, "Add", "Vital", vitalsTestData);
			int xpathCounter = (Integer) selenium.getXpathCount("//div[starts-with(@id,'detail-Add-VitalSign')]");
			String results = "";
			for(int i=1;i<=xpathCounter;i++){
				results = results + selenium.getText("//descendant::div[starts-with(@id,'detail-Add-VitalSign')][" +i+ "]")+"Set";
			}
			String auditValue[] = results.split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, vitalsTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + vitalsTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + vitalsTestData.toString());
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
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(vitalTestData.userName)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
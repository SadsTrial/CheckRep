package com.clinical.selenium.section.charts.chartsGeneral;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyDisplayOfAge extends AbstractChartsTest {

    @Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Check the Age More than 20 years")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void checkAge001(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib CheckAgeData = new ChartsLib();
		CheckAgeData.workSheetName = "VerifyDisplayOfAge";
		CheckAgeData.testCaseId = "TC_DA_001";
		CheckAgeData.fetchChartsTestData();
		checkAge(seleniumHost, seleniumPort, browser, webSite, CheckAgeData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Check the Age less than 20 Years and More than 1 year")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void checkAge002(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib CheckAgeData = new ChartsLib();
		CheckAgeData.workSheetName = "VerifyDisplayOfAge";
		CheckAgeData.testCaseId = "TC_DA_002";
		CheckAgeData.fetchChartsTestData();
		checkAge(seleniumHost, seleniumPort, browser, webSite, CheckAgeData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Check the Age less than  1 year")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void checkAge003(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib CheckAgeData = new ChartsLib();
		CheckAgeData.workSheetName = "VerifyDisplayOfAge";
		CheckAgeData.testCaseId = "TC_DA_003";
		CheckAgeData.fetchChartsTestData();
		checkAge(seleniumHost, seleniumPort, browser, webSite, CheckAgeData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Check the Age less than 1 Month")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void checkAge004(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib checkAgeData = new ChartsLib();
		checkAgeData.workSheetName = "VerifyDisplayOfAge";
		checkAgeData.testCaseId = "TC_DA_004";
		checkAgeData.fetchChartsTestData();
		checkAge(seleniumHost, seleniumPort, browser, webSite, checkAgeData);
	}

	/**
	 * @Function 	: checkAge
	 * @Description : Function to Check the Ages
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: September 07, 2010
	 */
	public void checkAge(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib checkAgeData){

		Selenium selenium = null;
		String ageInfoText = null;
		String dateInfoText = null;
		String actualAgeInfo = null;
		String expectedAgeInfo = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + checkAgeData.toString());
			loginFromPublicSite(selenium, checkAgeData.userAccount, checkAgeData.userName, checkAgeData.userPassword);
			searchPatient(selenium,checkAgeData.patientID);
			waitForPageLoad(selenium);
			Assert.assertTrue(isElementPresent(selenium, "//div[@id='patientinformation']/div/div/div[3]"),"Patient Personal details are not displayed; More Details" + checkAgeData.toString());
			ageInfoText=selenium.getText( "//div[@id='patientinformation']/div/div/div[3]");
			dateInfoText = ageInfoText.substring(ageInfoText.indexOf("(")+1,ageInfoText.indexOf(")"));
			actualAgeInfo = ageInfoText.substring(0,ageInfoText.indexOf("(")); 

			//--------------------------------------------------------------------//
			//  Step-2:  Get the Age From Personal Information and Compare        //
			//--------------------------------------------------------------------//
 			expectedAgeInfo = getAge(dateInfoText);

			if(checkAgeData.testDescription.trim().equalsIgnoreCase("Age More Than 20 Years")){
				Assert.assertTrue(actualAgeInfo.trim().contains(expectedAgeInfo.trim()) && expectedAgeInfo.contains("Yrs") && !expectedAgeInfo.contains("Mos") && !expectedAgeInfo.contains("Days"),"Patient[Age More Than 20 Years] DateOfBirth and Age are not displayed as Expected ;  Expected DOB[ Yrs]  : " + expectedAgeInfo +"  ; Actual DOB[ Yrs]    : " + actualAgeInfo);
			}

			if(checkAgeData.testDescription.trim().equalsIgnoreCase("Age Less Than 20 Years")){
				Assert.assertTrue(actualAgeInfo.trim().contains(expectedAgeInfo.trim()) && expectedAgeInfo.contains("Yrs") && expectedAgeInfo.contains("Mos") && !expectedAgeInfo.contains("Days"),"Patient[Age Less Than 20 Years] DateOfBirth and Age are not displayed as Expected; Expected DOB[ Yrs Mths]  : " + expectedAgeInfo + "  ;Actual DOB[ Yrs Mths]    : " + actualAgeInfo);
			}

			if(checkAgeData.testDescription.trim().equalsIgnoreCase("Age Less Than 1 Year")){
				Assert.assertTrue(actualAgeInfo.trim().contains(expectedAgeInfo.trim()) && !expectedAgeInfo.contains("Yrs") && expectedAgeInfo.contains("Mos") && expectedAgeInfo.contains("Days"),"Patient[Age Less Than 1 Year] DateOfBirth and Age are not displayed as Expected;Expected DOB[Mths Days]  : " + expectedAgeInfo + "  ;  Actual DOB[Mths Days]    : " + actualAgeInfo);
			}

			if(checkAgeData.testDescription.trim().equalsIgnoreCase("Age Less Than 1 Month")){
				Assert.assertTrue(expectedAgeInfo.trim().contains(actualAgeInfo.trim()) && !expectedAgeInfo.contains("Yrs") && !expectedAgeInfo.contains("Mos") && expectedAgeInfo.contains("Days"),"Patient[Age Less Than 1 Month] DateOfBirth and Age are not displayed as Expected; Expected DOB[Days]  : " + expectedAgeInfo +" ;Actual DOB[Days]    : " + actualAgeInfo );
			}
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" +checkAgeData.toString());
			try {
				Thread.sleep(60000);
			}catch (InterruptedException e1) {
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
	@SuppressWarnings("deprecation")
	public String getAge(String date)
	{
		int Year =0;
		int Months =0;
		long Days =0;
		String resDate = "";
		Date CurrentDate = new Date();
		Date DateOfBirth = new Date(date);
		Calendar calDateOFBirth = Calendar.getInstance();
		Calendar calCurrentDate = Calendar.getInstance();

		calDateOFBirth.setTime(DateOfBirth);
		calCurrentDate.setTime(CurrentDate);
		Year = calCurrentDate.get(1) - calDateOFBirth.get(1);
		if(Year >= 0){
			if(calCurrentDate.get(2) > calDateOFBirth.get(2)){
				Months = (calCurrentDate.get(2) - calDateOFBirth .get(2));
			}else if(calCurrentDate.get(2) == calDateOFBirth.get(2) && Year != 0){
				Months =((calDateOFBirth.getMaximum(2)+1) - (calDateOFBirth.get(2)+1)) + (calCurrentDate.get(2)+1);
			}else if(Year != 0) {
				Year = Year - 1 ;
				Months =((calDateOFBirth.getMaximum(2)+1) - (calDateOFBirth.get(2)+1)) + (calCurrentDate.get(2)+1);
			}

			if(calDateOFBirth.get(5) > calCurrentDate.get(5)){
				Months = Months -1;
			}

			if(Months >= 1){
				if(calDateOFBirth.get(5) > calCurrentDate.get(5)){
					Year = Year - 1 ;
					Days = (calDateOFBirth.getMaximum(5)-calDateOFBirth.get(5)) + calCurrentDate.get(5);
					if(calCurrentDate.get(2)== 2){
						Days = Days -3;
					}
				}else{
					Days = calCurrentDate.get(5) - calDateOFBirth.get(5);
				}
			}else if(Months<0) {
				Year = Year - 1;
				Months =((calDateOFBirth.getMaximum(2)+1) - (calDateOFBirth.get(2)+1)) + (calCurrentDate.get(2));

			}else{
				Days = CurrentDate.getTime() - DateOfBirth.getTime();
				Days = Days / (1000*60*60*24);
			}

			if(Year > 20){
				resDate = Integer.toString(Year)+" Yrs";
			}else if(Year <= 20 && Year >= 1){
				resDate = Integer.toString(Year) + " Yrs " + Months + " Mos";
			}else if(Year < 1 && Months > 1){
				resDate = Months + " Mos " + Days + " Days";
			}else{
				resDate = Days + " Days";
			}
		}else{
			resDate = "Invalid";
		}
		return resDate;

	}
}
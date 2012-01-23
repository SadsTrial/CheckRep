package com.clinical.selenium.section.appointments;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class AppointmentsClinical extends AbstractTest {
	
	/**
	 * @Function 	: appointmentClinical
	 * @Description : Function for looking for an appointment in Medrium Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort 
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Regin Joseph
	 * @Created on 	: Mar 23, 2010
	 */
	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for Clinical Appointments Section")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void appointmentClinical(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		
		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		String searchText = "Kousar";
		Selenium selenium = null;
		
		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
		
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertTrue(loginFromPublicSite(selenium, userAccount,userName,userPassword),"Could not Log in to the application successfully with User Account - "+userAccount + ", User Name - "+userName + ", Password - "+userPassword);
			
			//----------------------------------------------------//
			//  Step-2:  Search for a appointment for patient P356//
			//----------------------------------------------------//
			
			click(selenium,"searchActionButton");
			click(selenium,"searchAppointment");
			waitForElement(selenium, "headerSearchInput",10000);
		
			type(selenium,"headerSearchInput", searchText);
			
			selenium.click("headerMagnifierButton");
			waitForPageLoad(selenium);
			Assert.assertTrue(click(selenium, "nameAppListing_1"),"Could not find any appointment");
			waitForElement(selenium, "headerSearchInput", 5000);
							
			
			//----------------------------------------------//
			//  Step-3:  Links displayed are                //
			//----------------------------------------------//
		
			/*Assert.assertTrue(waitForElement(selenium, "Summary", 5000),"Summary Page is not loaded");
			Assert.assertTrue(selenium.isElementPresent("summary"),"Summary Link is not available");
			
			Assert.assertTrue(selenium.isElementPresent("activity"),"Activity Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("advanceDirectives"),"Advance Directives Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("allergies"),"Allergies Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("immunization"),"Immunization Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("labResults"),"Lab Results Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("labRequest"),"Lab Request Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("medication"),"Medication Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("prescriptions"),"Prescriptions Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("referral"),"Referral Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("socialHistory"),"Social History Link is not available");
			
			
			Assert.assertTrue(selenium.isElementPresent("visit")," Visit Link is not available");*/
		
		}
		catch (RuntimeException e) {
			org.testng.Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_AppointmentsClinical.log' for more info!");
			Thread.sleep(60000);
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
package com.clinical.selenium.section.overview;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class VerifyProviderLocation extends AbstractTest {

	/**
	 * @Function 	: verifyProviderLocation
	 * @Description : Function to add the Provider,Location values in Overview Section and Verify in the charts section
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Anil Sannareddy
	 * @Created on 	: April 29, 2011
	 */
	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Add the Provider,Location values in Overview Section and Verify in the charts section")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyProviderLocation(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {

		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		String doctorName = "David Sperry";
		String locationName= "Crossroads Medical";
		Selenium selenium = null;
		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			selenium.windowMaximize();
			selenium.setTimeout("150000");
			Assert.assertTrue(loginFromPublicSite(selenium, userAccount,userName,userPassword),"Could not Log in to the application successfully with User Account - "+userAccount + ", User Name - "+userName + ", Password - "+userPassword);

			//-----------------------------------------------//
			//  Step-2:  Verifying various sections available//
			//-----------------------------------------------//
			click(selenium, "headerOverview");
			waitForPageLoad(selenium);

			click(selenium,"appEdit");
			waitForPageLoad(selenium);
						
			Assert.assertTrue(getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(doctorName.trim()),"Expected Provider Name is not displayed in Overview Section Expected : "+doctorName +"  Actual : " + getSelectedValue(selenium, "providersInput"));
		
			Assert.assertTrue(select(selenium, "locationsInput",locationName),"Could not select the test data for Provider Location");

			click(selenium,"appSave");
			waitForPageLoad(selenium);
						
			searchPatient(selenium,"1132");
			click(selenium,"visits");
			waitForPageLoad(selenium);

			click(selenium,"visitsAdd");
						
			waitForPageLoad(selenium);
			
			Assert.assertTrue(getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(doctorName.trim()),"Doctor Name is not displayed as expected in Visit Section");


			Assert.assertTrue(getSelectedValue(selenium, "locationsInput").trim().equalsIgnoreCase(locationName.trim()),"Location Name is not displayed as expected in Visit Section");

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
			
			click(selenium,"addPrescription");
			
			waitForPageLoad(selenium);

			if(!selenium.getText("editProvider").equalsIgnoreCase("Done")){
				click(selenium,"editProvider");
				waitForPageLoad(selenium);
			}
			
			Assert.assertTrue(getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(doctorName.trim()),"Doctor Name is not displayed as expected in Visit Section");


			Assert.assertTrue(getSelectedValue(selenium, "locationsInput").trim().equalsIgnoreCase(locationName.trim()),"Location Name is not displayed as expected in Visit Section");

			
			click(selenium,"radiologyOrder");
			
			waitForPageLoad(selenium);

			click(selenium,"radiologyOrderAdd");
						
			waitForPageLoad(selenium);

			Assert.assertTrue(getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(doctorName.trim()),"Doctor Name is not displayed as expected in Radiology Order Section");

			click(selenium,"labRequest");
						
			waitForPageLoad(selenium);

			click(selenium,"labRequestAdd");
						
			waitForPageLoad(selenium);

			Assert.assertTrue(getSelectedValue(selenium, "providersInput").trim().equalsIgnoreCase(doctorName.trim()),"Doctor Name is not displayed as expected in Lab Request Section");
		}
		catch (RuntimeException e) {
			org.testng.Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_ClinicalsOverview.log' for more info!");
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
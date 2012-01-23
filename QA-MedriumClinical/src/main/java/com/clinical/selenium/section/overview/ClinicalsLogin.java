package com.clinical.selenium.section.overview;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class ClinicalsLogin extends AbstractTest {

	/**
	 * @Function 	: clinicalsLogin
	 * @Description : Function to Login on to New Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Anil Sannareddy
	 * @Created on 	: Mar 23, 2010
	 */
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "This is to test for a Login in Clinical Site")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"}) 
	public void clinicalsLogin(String seleniumHost, int seleniumPort,String browser, String webSite) throws Throwable {


		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		Selenium selenium  = null;
		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertTrue(loginFromPublicSite(selenium, userAccount,userName,userPassword),"Could not Log in to the application successfully with User Account - "+userAccount + ", User Name - "+userName + ", Password - "+userPassword);
		}
		catch (RuntimeException e) {
			org.testng.Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_ClinicalsLogin.log' for more info!");
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
package com.clinical.selenium.section.charts.chartsGeneral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.thoughtworks.selenium.Selenium;

public class SearchForPatient extends AbstractChartsTest {


	/**
	 * @Function 	: clinicalCharts
	 * @Description : Function to search for patient in New Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 23, 2010
	 */
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for searching a New Patient")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void searchForPatient(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		
		Selenium selenium = null;
		String patientName = "Beaumont";
		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";

		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session");
			loginFromPublicSite(selenium, userAccount, userName,userPassword);

			//----------------------------------------------//
			//  Step-2:  Search for a patient using Name    //
			//----------------------------------------------//

			selenium.click("searchActionButton");
			click(selenium, "searchPatient");
			waitForElement(selenium, "headerSearchInput", 5000);
						

			//----------------------------------------------//
			//  Step-3:  Search Results Retrieved           //
			//----------------------------------------------//

			selenium.type("headerSearchInput",patientName);
						
			selenium.click("headerMagnifierButton");
			waitForElement(selenium, "cellPatientId3437", 5000);
			Assert.assertTrue(selenium.isElementPresent("cellPatientId3437"),"Search Results are not displayed for patient search");
			selenium.click("cellPatientId3437");
						

			//----------------------------------------------//
			//  Step-4:  Links displayed are                //
			//----------------------------------------------//

			waitForElement(selenium, "Summary", 5000);
			Assert.assertTrue(selenium.isElementPresent("summary")," Summary Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("activity"),"Activity Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("advDirectives"),"Advance Directives Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("allergies"),"Allergies Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("immunizations"),"Immunization Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("labResult"),"Lab Results Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("medications"),"Medication Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("prescriptions"),"Prescriptions Link is not available");
						
			Assert.assertTrue(selenium.isElementPresent("visits"),"Visit Link is not available");

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
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
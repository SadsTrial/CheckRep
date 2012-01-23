package com.clinical.selenium.section.charts.chartsGeneral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyChartsNumberLinkContent extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "This is script for verifying Charts link ID Content")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyChartsNumberLinkDetails(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib verifyChartsNumberLinkContentTestData = new ChartsLib();
		verifyChartsNumberLinkContentTestData.workSheetName = "VerifyChartsNumberLinkContent";
		verifyChartsNumberLinkContentTestData.testCaseId = "TC_VCNLC_001";
		verifyChartsNumberLinkContentTestData.fetchChartsTestData();
		verifyPatientDetails(seleniumHost, seleniumPort, browser, webSite, verifyChartsNumberLinkContentTestData);
	}


	/**
	 * @Function 	: verifyPatientDetails
	 * @Description : Function to verify the chart number details by clicking the chart number link from the search result
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Sep 24, 2010
	 */
	public void verifyPatientDetails(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib verifyChartsNumberLinkContentTestData){
		
		Selenium selenium = null;

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + verifyChartsNumberLinkContentTestData.toString());			
			Assert.assertTrue(loginFromPublicSite(selenium, verifyChartsNumberLinkContentTestData.userAccount,verifyChartsNumberLinkContentTestData.userName,verifyChartsNumberLinkContentTestData.userPassword),"Could not Log in to the application successfully with User Account - "+verifyChartsNumberLinkContentTestData.userAccount + ", User Name - "+verifyChartsNumberLinkContentTestData.userName + ", Password - "+verifyChartsNumberLinkContentTestData.userPassword);
		
			click(selenium,"searchActionButton");
			click(selenium,"searchPatient");
			waitForElement(selenium, "headerSearchInput",10000);
			Assert.assertEquals(selenium.getValue("headerSearchInput"),"Search patients...","Could not select - Search By Patients; More Details" + verifyChartsNumberLinkContentTestData.toString());
			
						
			type(selenium,"headerSearchInput", verifyChartsNumberLinkContentTestData.patientID);
			selenium.click("headerMagnifierButton");		
			waitForElement(selenium, "cellPatientId"+verifyChartsNumberLinkContentTestData.patientID,10000);
						
			Assert.assertTrue(selenium.isElementPresent("cellPatientId"+verifyChartsNumberLinkContentTestData.patientID),"Search Results are not displayed for patient search; More Details" + verifyChartsNumberLinkContentTestData.toString());
			
			String patientDetails = null;
			String patientName1  = null;
			String dob1  = null;
			String patientName2  = null;
			String dob2  = null;
			patientDetails = selenium.getText("//div[@id='patientList']/table/tbody[1]/tr/td[1]/div");			
			patientDetails = patientDetails != null ? patientDetails.trim():"";
			Assert.assertFalse(patientDetails == null || patientDetails.equals(""),"Patient details not retrieved; More Details" + verifyChartsNumberLinkContentTestData.toString());
			
			patientName1 = getText(selenium, "cellPatientId"+verifyChartsNumberLinkContentTestData.patientID);
			String[] tempArray2 = getText(selenium, "//div[@id='patientList']/table/tbody[1]/tr/td[1]/div/div").split(" ");
			dob1 = tempArray2[1]+" "+tempArray2[2]+" "+tempArray2[3];
			dob1 = dob1.substring(0, (dob1.length()-1));
			
			//------------------------------------------------------------------------------------------//
			//  Step-2:  Click Patient chart number link and compare the captured values	           //
			//------------------------------------------------------------------------------------------//
			
			click(selenium,"cellPatientId"+verifyChartsNumberLinkContentTestData.patientID);
						
			waitForPageLoad(selenium);
			
			patientName2 = selenium.getText("//div[@id='patientinformation']/div/div/div");
			dob2 = selenium.getText("//div[@id='patientinformation']/div/div/div[3]");

			patientName2 = patientName2 != null ? patientName2.trim():"";
			Assert.assertTrue(patientName2.toLowerCase(new java.util.Locale("en", "US")).trim().contains(patientName1.toLowerCase(new java.util.Locale("en", "US")).trim()),"Patient Name not equal; More Details" + verifyChartsNumberLinkContentTestData.toString());
			dob2 = dob2 != null ? dob2.trim():"";
			Assert.assertTrue(dob2.contains(dob1),"Born on Date not equal; More Details" + verifyChartsNumberLinkContentTestData.toString());
		
		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + verifyChartsNumberLinkContentTestData.toString());
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

}
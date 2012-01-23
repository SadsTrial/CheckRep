package com.clinical.selenium.section.charts.Visit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyVisitDate extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verify the visit date in various sections")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyVisitDates(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "VerifyVisitDate";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchChartsTestData();
		verifyVisitDate(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: updateExistingVisit
	 * @Description : Function to verifying the visit dates in various sections
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Oct 14, 2010
	 */
	public void verifyVisitDate(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){

		Selenium selenium = null;
		int counter = 1;
		boolean isValidated = true;
		String verifyText = "";
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Visit and enter details                    //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);

			if (!selenium.isElementPresent("//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div")){

				visitTestData.fetchChartsTestData();
				createVisit(selenium, visitTestData);

			}	

			//-----------------------------------------------------------------------------//
			//  Step-3:  Retrieve the dates from Visit Section                             //
			//-----------------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				String actualString = getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
				verifyText = verifyText+extractDate(actualString);
				counter++;
				if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div"))){
					click(selenium, "patientVisitListMoreLink");
					waitForPageLoad(selenium);

				}
			}
			verifyText = "None"+verifyText;

			//--------------------------------------------------------------------//
			//  Step-1: Verify the visit dates in condition Section               //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);

			if(!getText(selenium, "listBoxVisitsConditions").trim().equalsIgnoreCase(verifyText.trim())){
				isValidated = false;
			}

			//--------------------------------------------------------------------//
			//  Step-1: Verify the visit dates in Medication Section              //
			//--------------------------------------------------------------------//
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium,"medications");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details" +visitTestData.toString());
		
			click(selenium,"addMedication");
			waitForPageLoad(selenium);


			if(!getText(selenium, "listBoxVisitsConditions").trim().equalsIgnoreCase(verifyText.trim())){
				isValidated = false;
			}

			//--------------------------------------------------------------------//
			//  Step-1: Verify the visit dates in Prescription Section            //
			//--------------------------------------------------------------------//

			click(selenium, "cancelButton");
			waitForPageLoad(selenium);
			
			click(selenium,"prescriptions");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);
						

			if(!getText(selenium, "listBoxVisitsConditions").trim().equalsIgnoreCase(verifyText.trim())){
				isValidated = false;
			}

			//--------------------------------------------------------------------//
			//  Step-1: Verify the visit dates in Referral Section                //
			//--------------------------------------------------------------------//
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium,"referrals");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add Referral Link; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);

			if(!getText(selenium, "listBoxVisitsConditions").trim().equalsIgnoreCase(verifyText.trim())){
				isValidated = false;
			}
			Assert.assertTrue(isValidated,"Visit Date Validation is Success and verified; Detailed data:" + visitTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + visitTestData.toString());
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
			if(counter == 10){
				break;
			}
			
			counter++;
		}
	}

	public String extractDate(String actualString){
		String valDate = "";
		valDate = actualString.substring(actualString.indexOf("Visit on")+8,actualString.length());
		return valDate.trim();
	}
}

package com.clinical.selenium.section.charts.chartsGeneral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CheckPlusLinks extends AbstractChartsTest{

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for check for all add '+' buttons")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void checkPlusLinks(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib addButtonCheckData = new ChartsLib();
		addButtonCheckData.workSheetName = "CheckPlusLinks";
		addButtonCheckData.testCaseId = "TC_BUT_001";
		addButtonCheckData.fetchChartsTestData();
		checkAddButton(seleniumHost, seleniumPort, browser, webSite, addButtonCheckData);
	}

	/**
	 * @Function 	: checkAddButton
	 * @Description : Function to check add button
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 13, 2010
	 */
	public void checkAddButton(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib addButtonCheckData){

		Selenium selenium = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + addButtonCheckData.toString());
			loginFromPublicSite(selenium, addButtonCheckData.userAccount, addButtonCheckData.userName, addButtonCheckData.userPassword);
			searchPatient(selenium,addButtonCheckData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Check '+' button for Advance Directive                    //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "advDirectivesAdd", 5000),"Unable to click on Advance Directive Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("advDirectivesAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String advanceDirective = selenium.getText("title");
			Assert.assertTrue(advanceDirective.startsWith("Add Advance Directives"),"Titile not matching; The Expected : Title should starts with 'Advance Directive' ;The Actual   : Displayed Title with message - " + advanceDirective + "; More Details :" + addButtonCheckData.toString()); 
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-3: Check '+' button for Allergy                              //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "allergiesAdd", 5000),"Unable to click on Allergy Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("allergiesAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String allergyTitle = selenium.getText("title");
			Assert.assertTrue(allergyTitle.startsWith("Add Allergy"),"Titile not matching; The Expected : Title should starts with 'Allergy'  ;The Actual   : Displayed Title with message - " + allergyTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-4: Check '+' button for Condition                            //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "conditionsAdd", 5000),"Unable to click on Condition Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("conditionsAdd");				


			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String conditionTitle = selenium.getText("title");
			Assert.assertTrue(conditionTitle.startsWith("Add Condition"),"Titile not matching; The Expected : Title should starts with 'Condition';The Actual   : Displayed Title with message - " + conditionTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-5: Check '+' button for Immunization                         //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "immunizationsAdd", 5000),"Unable to click on Immunization Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("immunizationsAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String immunizationTitle = selenium.getText("title");
			Assert.assertTrue(immunizationTitle.startsWith("Add Immunization"),"Titile not matching; The Expected : Title should starts with 'Immunization'; The Actual   : Displayed Title with message - " + immunizationTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-6: Check '+' button for Lab Request                          //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "labRequestAdd", 5000),"Unable to click on Lab Request Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("labRequestAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String labRequestTitle = selenium.getText("title");
			Assert.assertTrue(labRequestTitle.startsWith("Add Lab Request"),"Titile not matching; The Expected : Title should starts with 'Lab Request'; The Actual   : Displayed Title with message - " + labRequestTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-7: Check '+' button for Medication                           //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "medicationsAdd", 5000),"Could not click Add Medication; More Details :" + addButtonCheckData.toString());
			selenium.click("medicationsAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			labRequestTitle = selenium.getText("title");
			Assert.assertTrue(labRequestTitle.startsWith("Add Medication"),"Titile not matching; The Expected : Title should starts with 'Medication'; The Actual   : Displayed Title with message - " + labRequestTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-8: Check '+' button for Prescription                         //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "prescriptionsAdd", 5000),"Unable to click on Prescription Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("prescriptionsAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String prescriptionTitle = selenium.getText("title");
			Assert.assertTrue(prescriptionTitle.startsWith("Add Prescription"),"Titile not matching; The Expected : Title should starts with 'Prescription';The Actual   : Displayed Title with message - " + prescriptionTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-9: Check '+' button for Referral                             //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "referralsAdd", 5000),"Unable to click on Referral Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("referralsAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String referralTitle = selenium.getText("title");
			Assert.assertTrue(referralTitle.startsWith("Add Referral"),"Titile not matching; The Expected : Title should starts with 'Referral'; The Actual   : Displayed Title with message - " + referralTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-10: Check '+' button for Social History                      //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "socialHistoryAdd", 5000),"Unable to click on Social History Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("socialHistoryAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String socialHistoryTitle = selenium.getText("title");
			Assert.assertTrue(socialHistoryTitle.startsWith("Social History"),"Titile not matching; The Expected : Title should starts with 'Social History'; The Actual   : Displayed Title with message - " + socialHistoryTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


			//--------------------------------------------------------------------//
			//  Step-11: Check '+' button for Visit                               //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "visitsAdd", 5000),"Unable to click on Visit Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("visitsAdd");				

			waitForPageLoad(selenium);
			Assert.assertTrue(selenium.isTextPresent("Visit"),"Title is not displayed correctly; More Details :" + addButtonCheckData.toString());

			//--------------------------------------------------------------------//
			//  Step-12: Check '+' button for Vitals                              //
			//--------------------------------------------------------------------//

			Assert.assertTrue(waitForElement(selenium, "vitalsAdd", 5000),"Unable to click on Vitals Add ['+'] Button; More Details :" + addButtonCheckData.toString());
			selenium.click("vitalsAdd");				

			Assert.assertTrue(waitForElement(selenium, "title", 5000),"Unable to capture Title; More Details :" + addButtonCheckData.toString());
			String vitalsTitle = selenium.getText("title");
			Assert.assertTrue(vitalsTitle.startsWith("Add Vital"),"Titile not matching; The Expected : Title should starts with 'Vitals'; The Actual   : Displayed Title with message - " + vitalsTitle + "; More Details :" + addButtonCheckData.toString());
			selenium.click("cancelButton");


		}catch (RuntimeException e){
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + addButtonCheckData.toString());
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
}

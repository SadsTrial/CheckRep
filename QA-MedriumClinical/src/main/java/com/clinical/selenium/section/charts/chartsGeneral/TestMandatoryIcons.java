package com.clinical.selenium.section.charts.chartsGeneral;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.thoughtworks.selenium.Selenium;

public class TestMandatoryIcons extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Mandatory Icons")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void testForMandatoryIcons(String seleniumHost, int seleniumPort,String browser, String webSite){
		testMandatoryIcons(seleniumHost, seleniumPort, browser, webSite);
	}

	/**
	 * @Function 	: testMandatoryIcons
	 * @Description : Function to verify the Mandatory Icons (!) available in each screen
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 09, 2010
	 */

	public void testMandatoryIcons(String seleniumHost, int seleniumPort,String browser, String webSite){

		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		String patientID = "6971";
		Selenium selenium = null;
		try{
			
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session");
			loginFromPublicSite(selenium, userAccount, userName,userPassword);
			searchPatient(selenium,patientID);

			//----------------------------------------------//
			//  Step-2:  Search for Patient using Id        //
			//----------------------------------------------//
		
			click(selenium,"searchActionButton");
			click(selenium,"searchPatient");
			waitForElement(selenium, "headerSearchInput",10000);

						
			type(selenium,"headerSearchInput", patientID);
			selenium.click("headerMagnifierButton");
			waitForElement(selenium, "cellPatientId"+patientID,10000);
			Assert.assertTrue(selenium.isElementPresent("cellPatientId"+patientID),"Search Results are not displayed for patient search");

			click(selenium,"cellPatientId"+patientID);
						

			//---------------------------------------------------------------//
			//  Step-3:  Verifying Mandatory Icons (!) in Advance Directives //
			//---------------------------------------------------------------//

			click(selenium,"advDirectives");
			waitForPageLoad(selenium);
									

			Assert.assertTrue(waitForElement(selenium, "addAdvDirective", 10000),"Could not find Add Advance Directives Link");
			click(selenium,"addAdvDirective");
						

			Assert.assertTrue(isElementPresent(selenium,"startDateInputImg")," Mandatory Icon (!) is not available for Advance Directive Start Date");
			click(selenium,"cancelButton");

			//--------------------------------------------------------//
			//  Step-4:  Verifying Mandatory Icons (!) in Add Allergy //
			//--------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link");
			click(selenium,"addAllergy");
						

			Assert.assertTrue(isElementPresent(selenium,"allergyBoxImg")," Mandatory Icon (!) is not available for Allergy Name");
			click(selenium,"cancelButton"); 


			//---------------------------------------------------------//
			//  Step-5: Verifying Mandatory Icons (!) in Add Condition //
			//---------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
									

			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link");
										
			Assert.assertTrue(isElementPresent(selenium,"conditionsBoxImg"),"Mandatory Icon (!) is not available for Condition");
			click(selenium,"cancelButton");

			//------------------------------------------------------------//
			//  Step-6: Verifying Mandatory Icons (!) in Add Immunization //
			//------------------------------------------------------------//

			click(selenium,"immunizations");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(click(selenium,"addImmunization"),"Could not find Add Immunization Link");
						
			Assert.assertTrue(isElementPresent(selenium,"productBoxInputImg"),"Mandatory Icon (!) is not available for Immunization");
			Assert.assertTrue(isElementPresent(selenium,"immunizationManufacturersInputImg"),"Mandatory Icon (!) is not available for Manufacturer Input");
			Assert.assertTrue(isElementPresent(selenium,"fdaRoutesInputImg"),"Mandatory Icon (!) is not available for  Route of Administration Start Date");

			click(selenium,"cancelButton");
			waitForPageLoad(selenium);

			//------------------------------------------------------------//
			//  Step-7: Verifying Mandatory Icons (!) in Add Medication   //
			//------------------------------------------------------------//

			click(selenium,"medications");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(click(selenium,"addMedication"),"Could not find Add Medication Link");
						
			Assert.assertTrue(isElementPresent(selenium,"medicationBoxImg"),"Mandatory Icon (!) is not available for Medication");
			click(selenium,"cancelButton");

			//------------------------------------------------------------//
			//  Step-8: Verifying Mandatory Icons (!) in Add Prescription //
			//------------------------------------------------------------//

			click(selenium,"prescriptions");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link");
						
			Assert.assertTrue(isElementPresent(selenium,"providersInputImg"),"Mandatory Icon (!) is not available for Providers");
			Assert.assertTrue(isElementPresent(selenium,"locationsInputImg"),"Mandatory Icon (!) is not available for Locations");
			Assert.assertTrue(isElementPresent(selenium,"medicationboxImg"),"Mandatory Icon (!) is not available for Medication");
			Assert.assertTrue(isElementPresent(selenium,"unitQtyInputImg"),"Mandatory Icon (!) is not available for Take Each Time");

			click(selenium,"cancelButton");

			//--------------------------------------------------------//
			//  Step-9: Verifying Mandatory Icons (!) in Add Referral //
			//--------------------------------------------------------//
			click(selenium,"referrals");
			waitForPageLoad(selenium);
						

			Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add Referral Link");
						
			Assert.assertTrue(isElementPresent(selenium,"providersBoxImg"),"Mandatory Icon (!) is not available for Provider");
			click(selenium,"cancelButton");

			//--------------------------------------------------------//
			//  Step-10: Verifying Mandatory Icons (!) in Add Visit   //
			//--------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);
						
			Assert.assertTrue(click(selenium,"visitsAdd"),"Could not find Add Visit Link");
						

			type(selenium, "visitdateInput"," ");
						
			Assert.assertTrue(isElementPresent(selenium,"visitdateInputImg"),"Mandatory Icon (!) is not available for Visit Date");
			click(selenium,"cancelButton");

			//--------------------------------------------------------//
			//  Step-11: Verifying Mandatory Icons (!) in Add Vitals  //
			//--------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);
						
			Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link");
						
			type(selenium, "startdateInput"," ");
			Assert.assertTrue(isElementPresent(selenium,"startdateInputImg")," Mandatory Icon (!) is not available for Observation Date");
			type(selenium, "vitalTimeInput"," ");
			Assert.assertTrue(isElementPresent(selenium,"vitalTimeInputImg"),"Mandatory Icon (!) is not available for Observation Time");
			click(selenium,"cancelButton");


		}catch (RuntimeException e){
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
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
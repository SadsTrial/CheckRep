package com.clinical.selenium.section.charts.directives;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForAdvanceDirectives extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifying Save button and Data for Un saved Advance Directive")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnsavedAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		ChartsLib directiveTestData = new ChartsLib();
		directiveTestData.workSheetName = "VerifySaveButtonForDirective";
		directiveTestData.testCaseId = "TC_DIR_001";
		directiveTestData.fetchChartsTestData();
		verifySaveButtonForAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForAdvanceDirective
	 * @Description : Function to Verify Save Button For Advance Directive
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2011
	 */
	public void verifySaveButtonForAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib directiveTestData) throws Exception{

		Selenium selenium = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + directiveTestData.toString());
			loginFromPublicSite(selenium, directiveTestData.userAccount, directiveTestData.userName, directiveTestData.userPassword);
			searchPatient(selenium,directiveTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Advance Directives and enter details       //
			//--------------------------------------------------------------------//

			click(selenium,"advDirectives");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, "addAdvDirective", 10000),"Could not find Add Advance Directives Link; More Details" +directiveTestData.toString());
			Assert.assertTrue(click(selenium,"addAdvDirective"),"Could Not click the Add Advance Directive Link; More Details" +directiveTestData.toString());
			waitForPageLoad(selenium);

			Assert.assertTrue(select(selenium, "//div[@id='patAdvDirectiveTypeCodeInput']/select", directiveTestData.directiveType),"Could not select Advance Directives For; More Details" +directiveTestData.toString());
			Assert.assertTrue(select(selenium,"//div[@id='patAdvDirectiveValueCodeInput']/select", directiveTestData.directiveName),"Could not select Directive Name; More Details" +directiveTestData.toString());
			Assert.assertTrue(select(selenium, "//div[@id='statusCodeInput']/select", directiveTestData.directiveStatus),"Could not select Directive Status; More Details" +directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "startDateInput", directiveTestData.directiveStartDate),"Could not enter Directive Start Date; More Details" +directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "endDateInput", directiveTestData.directiveEndDate),"Could not enter Directive End Date; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianLastNameInput", directiveTestData.custodianLastName),"Could not enter Custodian Last Name; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianFirstNameInput", directiveTestData.custodianFirstName),"Could not enter Custodian First Name; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianOrganizationInput", directiveTestData.custodianOrganization),"Could not enter Custodian Organization; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianAddr1Input", directiveTestData.custodianAddress1),"Could not enter Custodian Address 1; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianAddr2Input", directiveTestData.custodianAddress2),"Could not enter Custodian Address 2; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianCityInput", directiveTestData.custodianCity),"Could not enter Custodian City; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianStateInput", directiveTestData.custodianState),"Could not enter Custodian State; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianZipInput", directiveTestData.custodianZIP),"Could not enter Custodian ZIP; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianTelNumInput", directiveTestData.custodianTel),"Could not enter Custodian Tel; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianExtNumInput", directiveTestData.custodianExt),"Could not enter Custodian Ext; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianMobileNumInput", directiveTestData.custodianMobile),"Could not enter Custodian Mobile; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianFaxNumInput", directiveTestData.custodianFax),"Could not enter Custodian Fax; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianEmailInput", directiveTestData.custodianEmail),"Could not enter Custodian Email; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", directiveTestData.custodianNote),"Could not enter Custodian Note; More Details" +directiveTestData.toString());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +directiveTestData.toString());
			Assert.assertTrue(select(selenium, "workStatusInput", directiveTestData.taskName),"Could not select Work Status; More Details" +directiveTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", directiveTestData.sendTaskTo),"Could not select Send To Task; More Details" +directiveTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", directiveTestData.taskNotes),"Could not enter Task Notes; More Details" +directiveTestData.toString());

			click(selenium,"advDirectives");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("advDirectivesAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Allergy Add(+)  button is OFF and not backgrounded by Orange; More Details" +directiveTestData.toString());

			Assert.assertTrue(click(selenium,"addAdvDirective"),"Could Not click the Add Advance Directive Link; More Details" +directiveTestData.toString());
			waitForPageLoad(selenium);

			verifyStoredValues(selenium, directiveTestData);

			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +directiveTestData.toString());

		}catch (RuntimeException e){
			org.testng.Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_" + directiveTestData.testCaseId + ".log' for more info!; More Details" +directiveTestData.toString());
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

	public void verifyStoredValues(Selenium selenium, ChartsLib directiveTestData){

		Assert.assertTrue(getSelectedValue(selenium,"//div[@id='patAdvDirectiveTypeCodeInput']/select").trim().contains(directiveTestData.directiveType),"Un saved ADirective Type input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getSelectedValue(selenium,"//div[@id='patAdvDirectiveValueCodeInput']/select").trim().contains(directiveTestData.directiveName),"Un saved ADirective Value input value is not available; It is modified; More Details" +directiveTestData.toString());														

		Assert.assertTrue(getValue(selenium,"startDateInput").trim().equalsIgnoreCase(directiveTestData.directiveStartDate),"Un saved Start Date input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"endDateInput").trim().equalsIgnoreCase(directiveTestData.directiveEndDate),"Un saved End Date input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianLastNameInput").trim().equalsIgnoreCase(directiveTestData.custodianLastName),"Un saved Cust.LastName input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianFirstNameInput").trim().equalsIgnoreCase(directiveTestData.custodianFirstName),"Un saved Cust.FirstName input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianOrganizationInput").trim().equalsIgnoreCase(directiveTestData.custodianOrganization),"Un saved Cust.Organization input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianAddr1Input").trim().equalsIgnoreCase(directiveTestData.custodianAddress1),"Un saved Cust.Address1 input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianAddr2Input").trim().equalsIgnoreCase(directiveTestData.custodianAddress2),"Un saved Cust.Address2 input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianCityInput").trim().equalsIgnoreCase(directiveTestData.custodianCity),"Un saved Cust.City input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianStateInput").trim().equalsIgnoreCase(directiveTestData.custodianState),"Un saved Cust.State input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianZipInput").trim().equalsIgnoreCase(directiveTestData.custodianZIP),"Un saved Cust.Zip input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianTelNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianTel),"Un saved Cust.TelNumber input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianExtNumInput").trim().equalsIgnoreCase(directiveTestData.custodianExt),"Un saved Cust.ExtNumber input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianMobileNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianMobile),"Un saved Cust.MobileNumber input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianFaxNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianFax),"Un saved Cust.FaxNumber input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"documentCustodianEmailInput").trim().equalsIgnoreCase(directiveTestData.custodianEmail),"Un saved Cust.Email input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getValue(selenium,"notesInput").trim().equalsIgnoreCase(directiveTestData.custodianNote),"Un saved Notes input value is not available; It is modified; More Details" +directiveTestData.toString());

		Assert.assertTrue(getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(directiveTestData.taskName),"Un saved Work Status input value is not available; It is modified; More Details" +directiveTestData.toString());


	}

}
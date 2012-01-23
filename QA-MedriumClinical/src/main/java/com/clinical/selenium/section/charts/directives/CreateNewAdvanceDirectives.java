package com.clinical.selenium.section.charts.directives;

import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewAdvanceDirectives extends AbstractChartsTest {

	ChartsLib directiveTestData = new ChartsLib();

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Adding New Advance Directive without end date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewAdvanceDirectiveWithoutEndDate(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "TC_DIR_001";
		directiveTestData.fetchChartsTestData();
		createNewAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Adding New Advance Directive with end date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewAdvanceDirectiveWithEndDate(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		ChartsLib directiveTestData = new ChartsLib();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "TC_DIR_002";
		directiveTestData.fetchChartsTestData();
		createNewAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	/**
	 * @Function 	: createNewAdvanceDirective
	 * @Description : Function to create a New AdvanceDirective
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 07, 2010
	 */
	public void createNewAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib directiveTestData) throws Exception{

		Selenium selenium = null;
		boolean isRecordFoundInActiveDirectives = false;
		boolean isRecordFoundInAllDirectives = false;
		boolean isRecordFoundInActivity = false;

		directiveTestData.directiveEndDate = directiveTestData.directiveEndDate != null ? directiveTestData.directiveEndDate.trim() : "" ; 

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
			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("patientAdvDirectivesListMoreLink") && selenium.isVisible("patientAdvDirectivesListMoreLink")){
				selenium.click("patientAdvDirectivesListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "advdirectives"); 
			createAdvanceDirective(selenium,directiveTestData);
			while(selenium.isElementPresent("patientAdvDirectivesListMoreLink") && selenium.isVisible("patientAdvDirectivesListMoreLink")){
				selenium.click("patientAdvDirectivesListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "advdirectives");	
			secondList.removeAll(firstList);

			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ directiveTestData.toString());
			}
		
			//-----------------------------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Advance Directives Current section  //
			//-----------------------------------------------------------------------------------------------//
			
			click(selenium,"advDirectives");
			waitForPageLoad(selenium);
			click(selenium,"CurrentDirectives");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientAdvDirectivesListMoreLink") && selenium.isVisible("patientAdvDirectivesListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				selenium.click("patientAdvDirectivesListMoreLink");
				waitForPageLoad(selenium);
			}
			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				click(selenium, idOfTheNewlyAddedRecord);
				waitForPageLoad(selenium);
				isRecordFoundInActiveDirectives = verifyStoredValues(selenium, directiveTestData);
			}

			//-----------------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in Advance Directives All section      //
			//-----------------------------------------------------------------------------------------------//
			click(selenium,"advDirectives");
			waitForPageLoad(selenium);
			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientAdvDirectivesListMoreLink") && selenium.isVisible("patientAdvDirectivesListMoreLink") && !selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				selenium.click("patientAdvDirectivesListMoreLink");
				waitForPageLoad(selenium);
			}

			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllDirectives = verifyStoredValues(selenium, directiveTestData);

			//-------------------------------------------------------------------------------------//
			//  Step-5:  Verifying whether details entered are saved properly in activity section  //
			//-------------------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);
			
			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);

			isRecordFoundInActivity = verifyStoredValues( selenium, directiveTestData);

			Assert.assertTrue(isRecordFoundInActivity,"Newly Added Advance Directive is not displayed in Activity Page: More details : " + directiveTestData.toString() );

			if(directiveTestData.directiveEndDate.equals("") ){
				Assert.assertTrue(isRecordFoundInActiveDirectives ,"Newly Added Advance Directive is not displayed in Adavance Directive Page: More details : " + directiveTestData.toString() );	
			}else{
				Assert.assertFalse(isRecordFoundInActiveDirectives,"Newly Added Advance Directive(In Active) is displayed in Adavance[Active] Directive Page: More details : " + directiveTestData.toString() );
			}
			Assert.assertTrue (isRecordFoundInAllDirectives,"Newly Added Advance Directive is not displayed in Adavance Directive[All] Page: More details : " + directiveTestData.toString() );

		}catch (RuntimeException e){
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + directiveTestData.toString());
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib directiveTestData){

		if(!getText(selenium,"patAdvDirectiveTypeCodeLabelInput").trim().contains(directiveTestData.directiveType)){							
			return false;
		}

		if(!getText(selenium,"startDate").trim().equalsIgnoreCase(directiveTestData.directiveStartDate)){
			return false;
		}

		if(!getText(selenium,"endDate").trim().equalsIgnoreCase(directiveTestData.directiveEndDate)){
			return false;
		}

		if(!getText(selenium,"documentCustodianLastNameInput").trim().equalsIgnoreCase(directiveTestData.custodianLastName)){
			return false;
		}

		if(!getText(selenium,"documentCustodianFirstNameInput").trim().equalsIgnoreCase(directiveTestData.custodianFirstName)){
			return false;
		}

		if(!getText(selenium,"documentCustodianOrganizationInput").trim().equalsIgnoreCase(directiveTestData.custodianOrganization)){
			return false;
		}

		if(!getText(selenium,"documentCustodianAddr1Input").trim().equalsIgnoreCase(directiveTestData.custodianAddress1)){
			return false;
		}

		if(!getText(selenium,"documentCustodianAddr2Input").trim().equalsIgnoreCase(directiveTestData.custodianAddress2)){
			return false;
		}

		if(!getText(selenium,"documentCustodianCityInput").trim().equalsIgnoreCase(directiveTestData.custodianCity)){
			return false;
		}

		if(!getText(selenium,"documentCustodianStateInput").trim().equalsIgnoreCase(directiveTestData.custodianState)){
			return false;
		}

		if(!getText(selenium,"documentCustodianZipInput").trim().equalsIgnoreCase(directiveTestData.custodianZIP)){
			return false;
		}

		if(!getText(selenium,"documentCustodianTelNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianTel)){
			return false;
		}

		if(!getText(selenium,"documentCustodianExtNumInput").trim().equalsIgnoreCase(directiveTestData.custodianExt)){
			return false;
		}

		if(!getText(selenium,"documentCustodianMobileNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianMobile)){
			return false;
		}

		if(!getText(selenium,"documentCustodianFaxNumInput").trim().replace(" ","").equalsIgnoreCase(directiveTestData.custodianFax)){
			return false;
		}

		if(!getText(selenium,"documentCustodianEmailInput").trim().equalsIgnoreCase(directiveTestData.custodianEmail)){
			return false;
		}

		if(!getText(selenium,"notes").trim().equalsIgnoreCase(directiveTestData.custodianNote)){
			return false;
		}

		if(!getText(selenium, "workStatus").trim().toLowerCase(new java.util.Locale("en", "US")).equalsIgnoreCase(directiveTestData.taskName.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium, "taskUsers").trim().toLowerCase(new java.util.Locale("en", "US")).equalsIgnoreCase(directiveTestData.sendTaskTo.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}

		if(!getText(selenium,"taskNotes").trim().toLowerCase(new java.util.Locale("en", "US")).equalsIgnoreCase(directiveTestData.taskNotes.toLowerCase(new java.util.Locale("en", "US")))){
			return false;
		}
		return true;
	}	
}
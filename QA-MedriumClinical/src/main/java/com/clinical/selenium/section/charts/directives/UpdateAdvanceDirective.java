package com.clinical.selenium.section.charts.directives;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateAdvanceDirective extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for updating Advance Directive")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateExistingAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		ChartsLib directiveTestData = new ChartsLib();
		directiveTestData.workSheetName = "UpdateDirective";
		directiveTestData.testCaseId = "TC_ADU_001";
		directiveTestData.fetchChartsTestData();
		updateExistingAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	/**
	 * @Function 	: updateExistingAdvanceDirective
	 * @Description : Function to update an existing AdvanceDirective
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 26, 2010
	 */
	public void updateExistingAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib directiveTestData) throws Exception{

		String activeDirectiveCount = null;
		String allDirectiveCount = null;
		String uniqueID = null;
		boolean isRecordFoundInActiveDirectives = false;
		boolean isRecordFoundInAllDirectives = false;
		boolean isRecordFoundInActivity = false;
		directiveTestData.directiveEndDate = directiveTestData.directiveEndDate != null ? directiveTestData.directiveEndDate.trim() : "" ;
		Selenium selenium = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + directiveTestData.toString());
			loginFromPublicSite(selenium, directiveTestData.userAccount, directiveTestData.userName, directiveTestData.userPassword);
			searchPatient(selenium,directiveTestData.patientID);

			//----------------------------------------------------------------------//
			//  Step-2: Check if any advance directive is already exist to update  //
			//----------------------------------------------------------------------//

			click(selenium,"advDirectives");
									
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 120000),"Could not capture existing Advance Directives[Active] Count; More Details :" + directiveTestData.toString());
			activeDirectiveCount = getListCount(selenium.getText("CurrentDirectives"));

			//--------------------------------------------------------------------------------------//
			//  Step-3: Creates a New advance directive if no existing advance directive available  //
			//--------------------------------------------------------------------------------------//

			if(activeDirectiveCount.equals("0")){	
				directiveTestData = null;
				directiveTestData = new ChartsLib();
				directiveTestData.workSheetName = "Directive";
				directiveTestData.testCaseId = "TC_DIR_001";
				directiveTestData.fetchChartsTestData();

				createAdvanceDirective(selenium,directiveTestData);
				directiveTestData = null;
				directiveTestData = new ChartsLib();
				directiveTestData.workSheetName = "UpdateDirective";
				directiveTestData.testCaseId = "TC_ADU_001";
				directiveTestData.fetchChartsTestData();
			}
			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllDirectives", 120000),"Could not capture existing Advance Directives[All] Count; More Details :" + directiveTestData.toString());
			allDirectiveCount = getListCount(selenium.getText("AllDirectives"));
			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 120000),"Could not capture existing Advance Directives[Active] Count; More Details :" + directiveTestData.toString());
			activeDirectiveCount = getListCount(selenium.getText("CurrentDirectives"));

			click(selenium,"CurrentDirectives");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------------------------//
			//  Step-4: Update the Existing advance Directive                                       //
			//--------------------------------------------------------------------------------------//

			Assert.assertTrue(selenium.isElementPresent("//div[@id='patientAdvDirectivesList']/table/tbody[1]/tr[1]/td[1]/div"),"Could not select the First Advance Directive; More Details :" + directiveTestData.toString());
			String idOfTheRecord =selenium.getAttribute("//div[@id='patientAdvDirectivesList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
			uniqueID = idOfTheRecord.split("advdirectives")[1];
			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
						
			click(selenium, "actionButton");
						
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);

			directiveTestData.directiveType = selenium.getSelectedLabel("//div[@id='patAdvDirectiveTypeCodeInput']/select");
			directiveTestData.directiveName = selenium.getSelectedLabel("//div[@id='patAdvDirectiveValueCodeInput']/select");
			directiveTestData.directiveStatus = getSelectedValue(selenium, "//div[@id='statusCodeInput']/select");

			Assert.assertTrue(enterDate(selenium, "startDateInput", directiveTestData.directiveStartDate),"Could not enter Directive Start Date; More Details :" + directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "endDateInput", directiveTestData.directiveEndDate),"Could not enter Directive End Date; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianLastNameInput", directiveTestData.custodianLastName),"Could not enter Custodian Last Name; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianFirstNameInput", directiveTestData.custodianFirstName),"Could not enter Custodian First Name; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianOrganizationInput", directiveTestData.custodianOrganization),"Could not enter Custodian Organization; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianAddr1Input", directiveTestData.custodianAddress1),"Could not enter Custodian Address 1; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianAddr2Input", directiveTestData.custodianAddress2),"Could not enter Custodian Address 2; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianCityInput", directiveTestData.custodianCity),"Could not enter Custodian City; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianStateInput", directiveTestData.custodianState),"Could not enter Custodian State; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianZipInput", directiveTestData.custodianZIP),"Could not enter Custodian ZIP; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianTelNumInput", directiveTestData.custodianTel),"Could not enter Custodian Tel; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianExtNumInput", directiveTestData.custodianExt),"Could not enter Custodian Ext; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianMobileNumInput", directiveTestData.custodianMobile),"Could not enter Custodian Mobile; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianFaxNumInput", directiveTestData.custodianFax),"Could not enter Custodian Fax; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianEmailInput", directiveTestData.custodianEmail),"Could not enter Custodian Email; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", directiveTestData.custodianNote),"Could not enter Custodian Note; More Details :" + directiveTestData.toString());				
			if(!selenium.isElementPresent("workStatusInput") && !selenium.isVisible("workStatusInput"))   
				Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link ; More Details :" + directiveTestData.toString());
			Assert.assertTrue(select(selenium, "workStatusInput", directiveTestData.taskName),"Could not select Work Status; More Details :" + directiveTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", directiveTestData.sendTaskTo),"Could not select Send To Task; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", directiveTestData.taskNotes),"Could not enter Task Notes; More Details :" + directiveTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Validate Button; More Details :" + directiveTestData.toString());
			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + directiveTestData.toString());
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + directiveTestData.toString());
			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 10000),"Could not capture Advance Directives[Active] Count after saving a Advance Directives; More Details :" + directiveTestData.toString());
			String count2 = getListCount(selenium.getText("CurrentDirectives"));
			Assert.assertTrue(Integer.parseInt(count2) == Integer.parseInt(activeDirectiveCount)-1,"The Advance Directive[Active] is not Saved Successfully, Advance Directives count has a change after updating an Advance Directive; More Details :" + directiveTestData.toString());

			Assert.assertTrue(waitForValue(selenium, "AllDirectives", 10000),"Could not capture Advance Directives[All] Count after saving a Advance Directives; More Details :" + directiveTestData.toString());
			count2 = getListCount(selenium.getText("AllDirectives"));
			Assert.assertTrue(Integer.parseInt(count2) == Integer.parseInt(allDirectiveCount),"The Advance Directive[All] is not Saved Successfully, Advance Directives count has a change after updating an Advance Directive; More Details :" + directiveTestData.toString());

			//----------------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in Advance Directive Current section  //
			//----------------------------------------------------------------------------------------------//

			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + directiveTestData.toString());
			
			click(selenium,"CurrentDirectives");
			waitForPageLoad(selenium);
			if(selenium.isElementPresent(idOfTheRecord)){
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
				isRecordFoundInActiveDirectives = verifyStoredValues( selenium, directiveTestData); //verifyStoredValues( selenium, directiveTestData);
				waitForPageLoad(selenium);
			}


			//----------------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in Advance Directive All section      //
			//----------------------------------------------------------------------------------------------//

			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllDirectives = verifyStoredValues( selenium, directiveTestData);
			
			//-----------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);
			
			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInActivity = verifyStoredValues( selenium, directiveTestData);


			Assert.assertTrue(isRecordFoundInActivity,"Updated[Inactive] Advance Directive is not displayed in Activity Page: More details : " + directiveTestData.toString() );
			Assert.assertFalse(isRecordFoundInActiveDirectives,"Updated[Inactive] Advance Directive(In Active) is displayed in Adavance[Active] Directive Page: More details : " + directiveTestData.toString() );
			Assert.assertTrue (isRecordFoundInAllDirectives,"Updated[Inactive] Advance Directive is not displayed in Adavance Directive[All] Page: More details : " + directiveTestData.toString() );

		}catch (RuntimeException e){
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

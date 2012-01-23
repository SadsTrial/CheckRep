package com.clinical.selenium.section.audit;

import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForupdateAdvanceDirective extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for  Verifying audit logs for update Advance Directive")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForupdateAdvanceDirective(String seleniumHost, int seleniumPort, String browser, String webSite) throws Exception {
		AuditLib directiveTestData = new AuditLib();
		directiveTestData.workSheetName = "UpdateDirective";
		directiveTestData.testCaseId = "TC_ADU_001";
		directiveTestData.fetchAuditTestData();
		verifyAuditLogsForupdateAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForupdateAdvanceDirective
	 * @Description : Function to update an existing AdvanceDirective
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 26, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForupdateAdvanceDirective(String seleniumHost, int seleniumPort, String browser, String webSite, AuditLib directiveTestData) throws Exception{

		Selenium selenium = null;
		String uniqueID = null;
		boolean isAuditResultVerified = false;
		String directiveCount = null;
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
			//  Step-2: Select the first Directive and store the details          //
			//--------------------------------------------------------------------//

			click(selenium, "advDirectives");
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 120000),"Could not capture existing Advance Directives Count; More Details :" + directiveTestData.toString());
			directiveCount = getListCount(selenium.getText("CurrentDirectives"));

			//--------------------------------------------------------------------//
			//  Step-3: Create New Directive if directive is not available        // 
			//--------------------------------------------------------------------//

			if(directiveCount.equals("0")){	
				directiveTestData = null;
				directiveTestData = new AuditLib();
				directiveTestData.workSheetName = "Directive";
				directiveTestData.testCaseId = "TC_DIR_001";
				directiveTestData.fetchAuditTestData();
				createAdvanceDirective(selenium, directiveTestData);
				directiveTestData = null;
				directiveTestData = new AuditLib();
				directiveTestData.workSheetName = "UpdateDirective";
				directiveTestData.testCaseId = "TC_ADU_001";
				directiveTestData.fetchAuditTestData();
			}
			if(waitForValue(selenium, "CurrentDirectives", 120000)){
				directiveCount = getListCount(selenium.getText("CurrentDirectives"));
			}
			Assert.assertTrue(selenium.isElementPresent("//div[@id='patientAdvDirectivesList']/table/tbody[1]/tr[1]/td[1]/div"),"Unable to select First Advance Directives ; More Details :" + directiveTestData.toString());
			String idOfTheNewlyAddedRecord =selenium.getAttribute("//div[@id='patientAdvDirectivesList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id"); 
			uniqueID = idOfTheNewlyAddedRecord.split("advdirectives")[1];
			click(selenium,"//div[@id='patientAdvDirectivesList']/table/tbody[1]/tr[1]/td[1]/div/strong/a");
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);

			//------------------------------------------------------------------------------//
			//  Step-4: Select the First Advance Directive and Update with New Details      // 
			//------------------------------------------------------------------------------//

			directiveTestData.directiveType = selenium.getSelectedLabel("//div[@id='patAdvDirectiveTypeCodeInput']/select");
			directiveTestData.directiveName = selenium.getSelectedLabel("//div[@id='patAdvDirectiveValueCodeInput']/select");
			directiveTestData.directiveStatus = getSelectedValue(selenium, "//div[@id='statusCodeInput']/select");
			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
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
			Assert.assertTrue(type(selenium,"documentCustodianExtNumInput", directiveTestData.custodianExt),"Could not enter Custodian Tel; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianMobileNumInput", directiveTestData.custodianMobile),"Could not enter Custodian Mobile; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianFaxNumInput", directiveTestData.custodianFax),"Could not enter Custodian Fax; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianEmailInput", directiveTestData.custodianEmail),"Could not enter Custodian Email; More Details :" + directiveTestData.toString());
			Assert.assertTrue((type(selenium,"notesInput", directiveTestData.custodianNote)),"Could not enter Custodian Note; More Details :" + directiveTestData.toString());

			if(!selenium.isElementPresent("workStatusInput") && !selenium.isVisible("workStatusInput"))
			{
				Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  ; More Details :" + directiveTestData.toString());
			}

			Assert.assertTrue(select(selenium, "workStatusInput", directiveTestData.taskName),"Could not select Work Status; More Details :" + directiveTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", directiveTestData.sendTaskTo),"Could not select Send To Task; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", directiveTestData.taskNotes),"Could not enter Task Notes; More Details :" + directiveTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could click Validate Button; More Details :" + directiveTestData.toString());
			waitForPageLoad(selenium);				
			if(selenium.isAlertPresent() || (selenium.isElementPresent("//p")))
			{
				Assert.assertTrue(selenium.isAlertPresent(),"An unexpected alert has occured : " + selenium.getAlert().trim() + "; More Details :" + directiveTestData.toString());
				Assert.assertTrue( (selenium.isElementPresent("//p")),"An unexpected Alert Occured - " + selenium.getText("//p").trim() + "; More Details :" + directiveTestData.toString());
			}

			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 120000),"Could not capture Advance Directives Count after saving a Advance Directives; More Details :" + directiveTestData.toString());
			String count2 = getListCount(selenium.getText("CurrentDirectives"));
			Assert.assertTrue(Integer.parseInt(count2) == Integer.parseInt(directiveCount)-1,"The Advance Directive is not Saved Successfully, Advance Directives count has a change after updating an Advance Directive; More Details :" + directiveTestData.toString());

			//----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Date currentDate1 = new Date(); 
			
			int counter1= 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			
			Assert.assertTrue(uniqueID != null,"Could not fetch the id of the Updated Record; more Details :-" + directiveTestData.toString());
			String recordID = "AdvanceDirective("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Update", recordID, directiveTestData).split("Changed");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" from ")[0];
					String value = 	auditValue[counter1].split(" to ")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, directiveTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + directiveTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + directiveTestData.toString());
		}catch (RuntimeException e){
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + directiveTestData.toString());
			Thread.sleep(60000);
		}finally{
			if(selenium != null){
				if(selenium.isElementPresent("errorCloseButton") && selenium.isVisible("errorCloseButton")){
					click(selenium, "errorCloseButton");
					waitForPageLoad(selenium);	
				}

				if(selenium.isElementPresent("headerClinicalMenu")&& selenium.isVisible("headerClinicalMenu"))
					click(selenium, "headerClinicalMenu");

			}
		}
	}

	@SuppressWarnings("deprecation")
	public boolean verifyAuditValues(String ColName, String value, AuditLib directiveTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(directiveTestData.directiveStartDate);
			if(actualStartdate.equals(expectedStartdate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"startdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("enddate")){
			isValueChecked = true;
			Date actualEnddate = new Date(value.replace("-","/"));
			Date expectedEnddate = new Date(directiveTestData.directiveEndDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianlastname")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianLastName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianlastname,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianfirstname")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianFirstName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianfirstname,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianorganization")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianOrganization)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianorganization,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianaddr1")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianAddress1)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianaddr1,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianaddr2")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianAddress2)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianaddr2,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodiancity")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianCity)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodiancity,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianstate")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianState)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianstate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianzip")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianZIP)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianzip,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodiantelnum")){
			isValueChecked = true;
			String telNO = directiveTestData.custodianTel.replace("(","").replace(")","").replace("-",""); 
			if(value.trim().equalsIgnoreCase(telNO)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodiantelnum,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianextnum")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianExt)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianextnum,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianmobilenum")){
			isValueChecked = true;
			String mobileNO = directiveTestData.custodianMobile.replace("(","").replace(")","").replace("-",""); 
			if(value.trim().equalsIgnoreCase(mobileNO.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianmobilenum,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianfaxnum")){
			isValueChecked = true;
			String FaxNO = directiveTestData.custodianFax.replace("(","").replace(")","").replace("-",""); 
			if(value.trim().equalsIgnoreCase(FaxNO.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianfaxnum,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("documentcustodianemail")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianEmail)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"documentcustodianemail,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.custodianNote)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("lastupdated")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date currDate = new Date();
			currDate.setHours(0);
			currDate.setMinutes(0);
			currDate.setSeconds(0);
			if(actualStartdate.toString().trim().toLowerCase(new java.util.Locale("en", "US")).equals(currDate.toString().trim().toLowerCase(new java.util.Locale("en", "US"))) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"lastupdated,";
				return false;
			}
		}else{
			return true;
		}
	}
}

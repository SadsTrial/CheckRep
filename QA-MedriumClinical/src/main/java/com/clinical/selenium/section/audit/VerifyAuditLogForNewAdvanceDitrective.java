package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewAdvanceDitrective extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Verifyning Audit Logs for New Advance Directive")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
		AuditLib directiveTestData = new AuditLib();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "TC_DIR_001";
		directiveTestData.fetchAuditTestData();
		verifyAuditLogForNewAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	/**
	 * @Function 	: VerifyAuditLogForNewAdvanceDirective
	 * @Description : Function to Verify Audit Logs for New AdvanceDirective
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 10, 2010
	 */
	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib directiveTestData) throws Exception{

		Selenium selenium = null;
		boolean isAuditResultVerified = false;
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
			//  Step-2:  Create a New Advance Directives                          //
			//--------------------------------------------------------------------//

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			click(selenium,"advDirectives");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent("patientAdvDirectivesListMoreLink") && selenium.isVisible("patientAdvDirectivesListMoreLink")){
				selenium.click("patientAdvDirectivesListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "advdirectives");
			createAdvanceDirective(selenium,directiveTestData);
			Date currentDate1 = new Date();
			click(selenium,"CurrentDirectives");
			waitForPageLoad(selenium);
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
			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
			
			int counter1= 0;
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String uniqueID = idOfTheNewlyAddedRecord.split("advdirectives")[1];
			String recordID = "AdvanceDirective("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID, directiveTestData).split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
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
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(directiveTestData.userName)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"createdby,";
				return false;
			}
		}else{
			return true;
		}
	}
}
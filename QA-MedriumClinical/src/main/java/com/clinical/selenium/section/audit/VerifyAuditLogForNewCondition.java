package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewCondition extends AbstractAuditTest {

	boolean isValueChecked = false;
	String conditionCode = null;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for Verifying Audit Log for NewCondition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogForNewCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib conditionTestData = new AuditLib();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "TC_CON_001";
		conditionTestData.fetchAuditTestData();
		verifyAuditLogForNewCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: verifyAuditLogForNewCondition
	 * @Description : Function to verify audit logs for New Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: ASPIRE QA
	 * @Created on 	: Nov 11, 2010
	 */	

	@SuppressWarnings("deprecation")
	public void verifyAuditLogForNewCondition(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib conditionTestData){

		Selenium selenium = null;
		String content = "";
		String tmpCondition = "";
		int counter = 1;
		int counter1 =0;
		boolean isAuditResultVerified = false;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Removing existing Conditions with same name               //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]") && selenium.isVisible("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]")){
				content = null;
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]");
				content = content != null ? content.trim() : "" ;
				if(content!= null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.toLowerCase(new java.util.Locale("en", "US")))){
						String uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						select(selenium, "itemStatusInput", "Inactive");
						click(selenium,"validateButton");
						waitForPageLoad(selenium);
					}						
				}
				counter++;
			}

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);

			//--------------------------------------------------------------------//
			//  Step-3:  Click Add New Condition and enter details                //
			//--------------------------------------------------------------------//

			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			Collection<String> firstList = getDataBaseIDs(selenium, "condition"); 	
			createCondition(selenium, conditionTestData);
			Date currentDate1 = new Date(); 
			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);
			Collection<String> secondList = getDataBaseIDs(selenium, "condition");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ conditionTestData.toString());
			}
			//----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//
			String uniqueID = idOfTheNewlyAddedRecord.split("condition")[1];
			click(selenium, "CurrentConditions");
			waitForPageLoad(selenium);

			click(selenium,idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);
			tmpCondition = getText(selenium, "//div[@id='conditionsBox']/div");
			click(selenium,"cancelButton");
			waitForPageLoad(selenium);

			tmpCondition = tmpCondition.split("\\(")[2];
			conditionCode = tmpCondition.substring(0,(tmpCondition.length()-1));
			counter = 1;
			

			
			currentDate = new Date(currentDate1.toLocaleString());
			currentDate.setSeconds(currentDate.getSeconds()-60);
			String recordID = "Condition("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID, conditionTestData).split("Set");
			counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName = auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, conditionTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}

			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + conditionTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + conditionTestData.toString());

		}catch (RuntimeException e) {
			e.printStackTrace();
			org.testng.Assert.fail("some error has occured during execution;Detailed information-" + conditionTestData.toString());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {				
				e1.printStackTrace();
			}
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
	public boolean verifyAuditValues(String ColName, String value, AuditLib conditionTestData){
		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("startdate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.replace("-","/"));
			Date expectedStartdate = new Date(conditionTestData.conditionStartDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"startdate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("enddate")){
			isValueChecked = true;
			Date actualEnddate = new Date(value.replace("-","/"));
			Date expectedEnddate = new Date(conditionTestData.conditionEndDate);
			if(actualEnddate.equals(expectedEnddate)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"enddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(conditionTestData.conditionNote)){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("conditioncode")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(conditionCode.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"conditioncode,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(conditionTestData.userName)){
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
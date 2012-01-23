package com.clinical.selenium.section.audit;

import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.audit.AbstractAuditTest;
import com.clinical.selenium.genericlibrary.audit.AuditLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyAuditLogForNewRadiologyOrder extends AbstractAuditTest {

	boolean isValueChecked = false;
	String unMatchedFields = "";

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for verifying audit log for New RadiologyOrder")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyAuditLogsForNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite){
		AuditLib labRequestTestData = new AuditLib();
		labRequestTestData.workSheetName = "RadiologyOrder";
		labRequestTestData.testCaseId = "TC_RAD_001";
		labRequestTestData.fetchAuditTestData();
		verifyAuditLogsForNewRadiologyOrder(seleniumHost, seleniumPort, browser, webSite, labRequestTestData);
	}

	/**
	 * @Function 	: verifyAuditLogsForNewRadiologyOrder
	 * @Description : Function to  verify audit log for New RadiologyOrder
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Nov 12, 2010
	 */

	@SuppressWarnings("deprecation")
	public void verifyAuditLogsForNewRadiologyOrder(String seleniumHost, int seleniumPort,String browser, String webSite, AuditLib radiologyOrderTestData){

		Selenium selenium = null;
		boolean isAuditResultVerified = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + radiologyOrderTestData.toString());
			loginFromPublicSite(selenium, radiologyOrderTestData.userAccount, radiologyOrderTestData.userName, radiologyOrderTestData.userPassword);
			searchPatient(selenium,radiologyOrderTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Lab Request and enter details              //
			//--------------------------------------------------------------------//

			Date currentDate = new Date();
			currentDate.setSeconds(currentDate.getSeconds()-10);
			click(selenium,"radiologyOrder");
			waitForPageLoad(selenium);
			click(selenium,"CurrentRadiologyOrders");
			waitForPageLoad(selenium);
			Collection<String> firstList = getDataBaseIDs(selenium, "radiologyorder"); 	
			createRadiologyOrder(selenium, radiologyOrderTestData);
			Date currentDate1 = new Date();
			click(selenium,"CurrentRadiologyOrders");
			waitForPageLoad(selenium);

			Collection<String> secondList = getDataBaseIDs(selenium, "radiologyorder");	
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ radiologyOrderTestData.toString());
			}

			//----------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are logged properly in the Audit page  //
			//----------------------------------------------------------------------------//

			String uniqueID = idOfTheNewlyAddedRecord.split("radiologyorder")[1];
			String recordID = "RadiologyOrder("+uniqueID+")";
			String auditValue[] = searchRecord(selenium, currentDate, currentDate1, "Add", recordID,  radiologyOrderTestData).split("Set");
			int counter1 = 0;
			while(counter1 < auditValue.length){
				if(auditValue[counter1].contains(" to ")){
					String ColName =auditValue[counter1].split(" to ")[0];
					String value = 	auditValue[counter1].split(" to")[1];
					isAuditResultVerified = verifyAuditValues(ColName, value, radiologyOrderTestData);
					if(!isAuditResultVerified){
						break;
					}
				}
				counter1 ++;
			}
			Assert.assertTrue(isValueChecked, "No Records displayed in the audit section; Time frame  Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + radiologyOrderTestData.toString());
			Assert.assertTrue(isAuditResultVerified," The Audit fields[ "+unMatchedFields+" ] are not matched as expected; Audit log verification failed; Start Time : "+ currentDate +" End Time : "+currentDate1 + ";More details- " + radiologyOrderTestData.toString());
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + radiologyOrderTestData.toString());
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
	public boolean verifyAuditValues(String ColName,String value, AuditLib labRequestTestData){

		if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("expecteddate")){
			isValueChecked = true;
			Date actualStartdate = new Date(value.trim().split(" ")[0].replace("-","/"));
			Date expectedStartdate = new Date(labRequestTestData.expectedDate);
			Date currDate = new Date();
			if(actualStartdate.equals(expectedStartdate) || actualStartdate.equals(currDate) ){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"expecteddate,";
				return false;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("notes")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(labRequestTestData.providerNotes.trim())){
				return true;
			}else{
				unMatchedFields = unMatchedFields+"notes,";
				return true;
			}
		}else if(ColName.trim().toLowerCase(new java.util.Locale("en", "US")).equals("createdby")){
			isValueChecked = true;
			if(value.trim().equalsIgnoreCase(labRequestTestData.userName)){
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
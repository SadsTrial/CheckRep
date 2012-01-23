package com.clinical.selenium.section.charts.Visit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class CreateNewVisit extends AbstractChartsTest {


	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewVisit(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "Visit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchChartsTestData();
		createNewVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: createNewVisit
	 * @Description : Function to create a New Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: July 08, 2010
	 */
	public void createNewVisit(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){

		Selenium selenium = null;
		String summaryVisitCount = null;
		boolean isRecordFoundInVisits = false;
		boolean isRecordFoundInSummary = false;
		boolean isRecordFoundInActivity = false;
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			Date currentDate = new Date();
			visitTestData.visitDate = dateFormat.format(currentDate);
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Visit and enter details                    //
			//--------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture existing Visit Count; More Details :" + visitTestData.toString());
			summaryVisitCount = getListCount(selenium.getText("visitTitle"));

			click(selenium,"visits");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("patientVisitListMoreLink") && selenium.isVisible("patientVisitListMoreLink") ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "visit"); 	
			createVisit(selenium, visitTestData);
			while(selenium.isElementPresent("patientVisitListMoreLink") && selenium.isVisible("patientVisitListMoreLink") ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "visit");	
			secondList.removeAll(firstList);
			
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ visitTestData.toString());
			}
			//--------------------------------------------------------------------------//
			//  Step-3:  Verifying Details Entered are saved properly in Visit section  //
			//--------------------------------------------------------------------------//

			while(!(isElementPresent(selenium,idOfTheNewlyAddedRecord)) &&(isElementPresent(selenium,"patientVisitListMoreLink")&& selenium.isVisible("patientVisitListMoreLink")) ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}

			Assert.assertTrue(waitForElement(selenium, idOfTheNewlyAddedRecord, 20000), "Could not capture the added Visit; More details :"  + visitTestData.toString());
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			if(verifyStoredValues(selenium, visitTestData))
			{
				isRecordFoundInVisits = true;
			}
			
			click(selenium, "visits");
			waitForPageLoad(selenium);


			//----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture Visit Count in patient summary section after saving a Visit; More Details :" + visitTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("visitTitle"))) == Integer.parseInt(summaryVisitCount)+1,"The Visit is not Saved Successfully in patient summary section, visit count has no change after adding a new visit; More Details :" + visitTestData.toString());

			while(!(isElementPresent(selenium,idOfTheNewlyAddedRecord)) &&(isElementPresent(selenium,"patientVisitListMoreLink")&& selenium.isVisible("patientVisitListMoreLink")) ){
				selenium.click("patientVisitListMoreLink");
				waitForPageLoad(selenium);
			}

			Assert.assertTrue(waitForElement(selenium, idOfTheNewlyAddedRecord, 20000), "Could not capture the added Visit; More details :"  + visitTestData.toString());
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			if(verifyStoredValues(selenium, visitTestData))
			{
				isRecordFoundInSummary = true;
			}

			//-----------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, idOfTheNewlyAddedRecord, 20000), "Could not capture the added Visit; More details :"  + visitTestData.toString());
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			if(verifyStoredValues(selenium, visitTestData))
			{
				isRecordFoundInActivity = true;
			}
			
			click(selenium, "cancelButton");
			waitForPageLoad(selenium);

			click(selenium,"activity");
			waitForPageLoad(selenium);

			Assert.assertTrue(isRecordFoundInVisits,"No matching records found in patient Visit section; Visit Creation Failed; More Details : "+visitTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"No matching records found in Patient Activity section; Visit Creation Failed; More Details : "+visitTestData.toString());
			Assert.assertTrue(isRecordFoundInSummary,"No matching records found in Patient summary section; Visit Creation Failed; More Details : "+visitTestData.toString());

		}catch (RuntimeException e) {
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

	public boolean verifyStoredValues(Selenium selenium, ChartsLib visitTestData){

		if(!getValue(selenium,"visitdateInput").trim().equalsIgnoreCase(visitTestData.visitDate.trim())){
			return false;
		}

		if(!getSelectedValue(selenium,"providersInput").trim().equalsIgnoreCase(visitTestData.providerName.trim())){
			return false;
		}

		if(!getSelectedValue(selenium,"locationsInput").trim().equalsIgnoreCase(visitTestData.providerLocation.trim())){
			return false;
		}

		if(!getValue(selenium,"subjectiveInput").trim().equalsIgnoreCase(visitTestData.patientSubjective)){
			return false;
		}

		if(!getValue(selenium,"objectiveInput").trim().equalsIgnoreCase(visitTestData.patientObjective)){
			return false;
		}

		if(!getValue(selenium,"assessmentInput").trim().equalsIgnoreCase(visitTestData.patientAssessment)){
			return false;
		}

		if(!getValue(selenium,"planInput").trim().equalsIgnoreCase(visitTestData.patientPlan)){
			return false;
		}

		if(!getSelectedValue(selenium,"workStatusInput").trim().equalsIgnoreCase(visitTestData.taskName)){
			return false;
		}

		if(!getSelectedValue(selenium,"taskUsersInput").trim().equalsIgnoreCase(visitTestData.sendTaskTo)){
			return false;
		}

		if(!getValue(selenium,"taskNotesInput").trim().equalsIgnoreCase(visitTestData.taskNotes)){
			return false;
		}
		return true;
	}
}

package com.clinical.selenium.section.charts.Visit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateVisit extends AbstractChartsTest {

	
	String unMatchedFields = "";
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for updating an existing Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateExistingVisit(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "UpdateVisit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchChartsTestData();
		updateExistingVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: updateExistingVisit
	 * @Description : Function to update an existing visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: July 26, 2010
	 */
	public void updateExistingVisit(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){

		Selenium selenium = null;
		String summaryVisitCount = null;
		boolean foundRecord = false;
		boolean summaryFoundRecord = false;
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

			click(selenium,"visits");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-2: create New visit if no visit is available to update       //
			//--------------------------------------------------------------------//

			if(!isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){

				visitTestData.workSheetName = "Visit";
				visitTestData.testCaseId = "TC_VIS_001";		
				visitTestData.fetchChartsTestData();
				createVisit(selenium, visitTestData);
			}

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture existing Visit Count; More Details" +visitTestData.toString());
			summaryVisitCount = getListCount(selenium.getText("visitTitle"));

			visitTestData = null;
			visitTestData = new ChartsLib();
			visitTestData.workSheetName = "UpdateVisit";
			visitTestData.testCaseId = "TC_VIS_001";		
			visitTestData.fetchChartsTestData();

			visitTestData.visitDate = dateFormat.format(currentDate);
			
			click(selenium,"visits");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3: Click the first visit and update with New Details         //
			//--------------------------------------------------------------------//
			
			String idOfTheRecord = null;
			idOfTheRecord = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
			
			Assert.assertTrue(click(selenium,idOfTheRecord),"Could not find First Visit link in the list; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);

			visitTestData.visitDate = visitTestData.visitDate != null && visitTestData.visitDate != "" ? visitTestData.visitDate.trim() : getValue(selenium,"visitdateInput");
			visitTestData.providerName = visitTestData.providerName != null && visitTestData.providerName != "" ? visitTestData.providerName.trim() : getSelectedValue(selenium,"providersInput");
			visitTestData.providerLocation = visitTestData.providerLocation != null && visitTestData.providerLocation != "" ? visitTestData.providerLocation.trim() : getSelectedValue(selenium,"locationsInput");
			visitTestData.patientSubjective = visitTestData.patientSubjective != null && visitTestData.patientSubjective != "" ? visitTestData.patientSubjective.trim() : getValue(selenium,"subjectiveInput");
			visitTestData.patientObjective = visitTestData.patientObjective != null && visitTestData.patientObjective != "" ? visitTestData.patientObjective.trim() : getValue(selenium,"objectiveInput");
			visitTestData.patientAssessment = visitTestData.patientAssessment != null && visitTestData.patientAssessment != "" ? visitTestData.patientAssessment.trim() : getValue(selenium,"assessmentInput");
			visitTestData.patientPlan = visitTestData.patientPlan != null && visitTestData.patientPlan != "" ? visitTestData.patientPlan.trim() : getValue(selenium,"planInput");
			visitTestData.taskName = visitTestData.taskName != null && visitTestData.taskName != "" ? visitTestData.taskName.trim() : getSelectedValue(selenium,"workStatusInput");
			visitTestData.sendTaskTo = visitTestData.sendTaskTo != null && visitTestData.sendTaskTo != "" ? visitTestData.sendTaskTo.trim() : getSelectedValue(selenium,"taskUsersInput");
			visitTestData.taskNotes = visitTestData.taskNotes != null && visitTestData.taskNotes != "" ? visitTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");

			Assert.assertTrue(enterDate(selenium, "visitdateInput", visitTestData.visitDate),"Could not enter the test data for Visit Date; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium, "providersInput", visitTestData.providerName),"Could not select the test data for Provider Name; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", visitTestData.providerLocation),"Could not select the test data for Provider Location; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"subjectiveInput", visitTestData.patientSubjective),"Could not enter the test data for Patient Subjective; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"objectiveInput", visitTestData.patientObjective),"Could not enter the test data for Patient Objective; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"assessmentInput", visitTestData.patientAssessment),"Could not enter the test data for Patient Assessment; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"planInput", visitTestData.patientPlan),"Could not enter the test data for Patient Plan; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", visitTestData.sendTaskTo),"Could not select Medication send task to; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", visitTestData.taskNotes),"Could not enter the test data for Task Notes; More Details" +visitTestData.toString());

			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);
			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Visit not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
			}

			if(selenium.isElementPresent("cancelButton")){
				click(selenium,"cancelButton");

			}
			
			Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + visitTestData.toString());

			//-----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in visit section     //
			//-----------------------------------------------------------------------------//
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			foundRecord = verifyStoredValues(selenium, visitTestData);
			
			Assert.assertTrue(foundRecord,"No matching records found in patient Visit section; The Unamtched Fields are : "+ unMatchedFields +" Visit Updation Failed; More Details : "+visitTestData.toString());
			
			//---------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in summary section     //
			//---------------------------------------------------------------------------------//

			unMatchedFields = "";
			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture Visit Count in patient summary section after saving a Visit; More Details" +visitTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("visitTitle"))) , Integer.parseInt(summaryVisitCount),"The Visit is not Saved Successfully in summary section, visit count has no change after adding a new visit; More Details" +visitTestData.toString());


			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("patientVisitListMoreLink") && selenium.isVisible("patientVisitListMoreLink")){
					click(selenium, "patientVisitListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}
			
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			summaryFoundRecord = verifyStoredValues(selenium, visitTestData);
		
			Assert.assertTrue(summaryFoundRecord ,"No matching records found in Patient summary section The Unamtched Fields are : "+ unMatchedFields +"; Visit Updation Failed; More Details : "+visitTestData.toString());
			
			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			unMatchedFields = "";
			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);
			
			click(selenium, idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInActivity = verifyStoredValues(selenium, visitTestData);

			Assert.assertTrue(isRecordFoundInActivity,"No matching records found in Patient Activity section;The Unamtched Fields are : "+ unMatchedFields +"; Visit Updation Failed; More Details : "+visitTestData.toString());
			
			

		}
		catch (RuntimeException e) {
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
			unMatchedFields = unMatchedFields +" VisitDate,"; 
			return false;
		}

		if(!getSelectedValue(selenium,"providersInput").trim().equalsIgnoreCase(visitTestData.providerName.trim())){
			unMatchedFields = unMatchedFields +" Provider,"; 
			return false;
		}

		if(!getSelectedValue(selenium,"locationsInput").trim().equalsIgnoreCase(visitTestData.providerLocation.trim())){
			unMatchedFields = unMatchedFields +" Locations,";
			return false;
		}

		if(!getValue(selenium,"subjectiveInput").trim().equalsIgnoreCase(visitTestData.patientSubjective)){
			unMatchedFields = unMatchedFields +" Subjective,";
			return false;
		}

		if(!getValue(selenium,"objectiveInput").trim().equalsIgnoreCase(visitTestData.patientObjective)){
			unMatchedFields = unMatchedFields +" Objective,";
			return false;
		}

		if(!getValue(selenium,"assessmentInput").trim().equalsIgnoreCase(visitTestData.patientAssessment)){
			unMatchedFields = unMatchedFields +" Assessmennt,";
			return false;
		}

		if(!getValue(selenium,"planInput").trim().equalsIgnoreCase(visitTestData.patientPlan)){
			unMatchedFields = unMatchedFields +" Patient Plan,";
			return false;
		}

		if(!getSelectedValue(selenium,"workStatusInput").trim().equalsIgnoreCase(visitTestData.taskName)){
			unMatchedFields = unMatchedFields +" WorkStatus,";
			return false;
		}

		if(!getSelectedValue(selenium,"taskUsersInput").trim().equalsIgnoreCase(visitTestData.sendTaskTo)){
			unMatchedFields = unMatchedFields +" TaskUsers,";
			return false;
		}

		if(!getValue(selenium,"taskNotesInput").trim().equalsIgnoreCase(visitTestData.taskNotes)){
			unMatchedFields = unMatchedFields +" TaskNotes,";
			return false;
		}

		return true;
	}
}

package com.clinical.selenium.section.charts.Allergy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class UpdateAllergy extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Updating an Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void updateAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "UpdateAllergy";
		allergyTestData.testCaseId = "TC_UAL_001";		
		allergyTestData.fetchChartsTestData();
		updateExistingAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: updateExistingAllergy
	 * @Description : Function to update an existing Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 27, 2010
	 */

	@SuppressWarnings("deprecation")
	public void updateExistingAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib allergyTestData){

		Selenium selenium = null;
		String activeAllergyCount = null;
		String allAllergyCount = null;
		String allergyInitialCountInSummary = null;
		String uniqueID = null;
		boolean isRecordFoundinActiveAllergy = false;
		boolean isRecordFoundinAllAllergy = false;
		boolean isRecordFoundInActivity = false;
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		Date currentDate = new Date();
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//----------------------------------------------------------------------//
			//  Step-2: Check if any active allergy is already exist to update      //
			//----------------------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);						
			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies[Active] Count; More Details :" + allergyTestData.toString());
			activeAllergyCount = getListCount(selenium.getText("CurrentAllergies"));

			//--------------------------------------------------------------------------------------//
			//  Step-3: Creates a New allergy if no existing allergy  available                     //
			//--------------------------------------------------------------------------------------//

			if(activeAllergyCount.equals("0")){	
				allergyTestData = null;
				allergyTestData = new ChartsLib();
				allergyTestData.workSheetName = "Allergy";
				allergyTestData.testCaseId = "TC_ALL_001";
				allergyTestData.fetchChartsTestData();

				createAllergy(selenium,allergyTestData);
				allergyTestData = null;
				allergyTestData = new ChartsLib();
				allergyTestData.workSheetName = "UpdateAllergy";
				allergyTestData.testCaseId = "TC_UAL_001";		
				allergyTestData.fetchChartsTestData();
			}

			click(selenium,"allergies");
			waitForPageLoad(selenium);						

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "allergyTitle", 100000),"Could not capture existing Allergies Count from Summary Page; More Details :" + allergyTestData.toString());
			allergyInitialCountInSummary= getListCount(selenium.getText("allergyTitle"));

			click(selenium,"allergies");
			waitForPageLoad(selenium);						

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllAllergies", 120000),"Could not capture existing Allergies[All] Count; More Details :" + allergyTestData.toString());
			allAllergyCount = getListCount(selenium.getText("AllAllergies"));
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture existing Allergies[Active] Count; More Details :" + allergyTestData.toString());
			activeAllergyCount = getListCount(selenium.getText("CurrentAllergies"));

			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);
			String idOfTheRecord = null;
			if(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr[1]/td[1]/div")){
				idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr[1]/td[1]/div/strong/a@id");
				uniqueID = idOfTheRecord.split("allergy")[1];
				click(selenium,idOfTheRecord);
				waitForPageLoad(selenium);
				click(selenium, "actionButton");
							
				click(selenium, "edit"+uniqueID);
							
				waitForPageLoad(selenium);

				allergyTestData.allergyName = getText(selenium,"allergyBoxLabel");
				allergyTestData.eventType = getSelectedValue(selenium,"adverseeventtypeInput");
				allergyTestData.severity = getSelectedValue(selenium,"severityInput");
				allergyTestData.status = allergyTestData.status != null && allergyTestData.status != "" ? allergyTestData.status.trim() : getSelectedValue(selenium, "itemStatusInput");
				allergyTestData.startDate = allergyTestData.startDate != null && allergyTestData.startDate != "" ? allergyTestData.startDate.trim() : getValue(selenium,"startdateInput");
				allergyTestData.endDate = allergyTestData.endDate != null && allergyTestData.endDate != "" ? allergyTestData.endDate.trim() : getValue(selenium,"enddateInput");
				allergyTestData.allergyNotes = allergyTestData.allergyNotes != null && allergyTestData.allergyNotes != "" ? allergyTestData.allergyNotes.trim() : getValue(selenium,"notesInput");
				allergyTestData.taskName = allergyTestData.taskName != null && allergyTestData.taskName != "" ? allergyTestData.taskName.trim() : getSelectedValue(selenium, "workStatusInput");
				allergyTestData.sendTaskTo = allergyTestData.sendTaskTo != null && allergyTestData.sendTaskTo != "" ? allergyTestData.sendTaskTo.trim() : selenium.getSelectedValue("taskUsersInput");
				allergyTestData.taskNotes = allergyTestData.taskNotes != null && allergyTestData.taskNotes != "" ? allergyTestData.taskNotes.trim() : getValue(selenium,"taskNotesInput");
							

				allergyTestData.endDate = dateFormat.format(currentDate);
				currentDate.setDate(currentDate.getDate()-2);
				allergyTestData.startDate = dateFormat.format(currentDate);
				
				//--------------------------------------------------------------------//
				//  Step-4: Update the Allergy with New Details and Save              //
				//--------------------------------------------------------------------//

				Assert.assertTrue(enterDate(selenium,"startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details :" + allergyTestData.toString());

				if(allergyTestData.endDate!=""){
					Assert.assertTrue(enterDate(selenium,"enddateInput", allergyTestData.endDate),"Could not enter End Date; More Details :" + allergyTestData.toString());
				}

				Assert.assertTrue(select(selenium,"itemStatusInput", allergyTestData.status),"Could not select Allergy Status; More Details :" + allergyTestData.toString());
				Assert.assertTrue(type(selenium,"notesInput", allergyTestData.allergyNotes),"Could not enter Allergy Notes; More Details :" + allergyTestData.toString());
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				if(!isElementPresent(selenium,"workStatusInput")){
					click(selenium, "addTask");
					waitForPageLoad(selenium);
				}

				Assert.assertTrue(select(selenium, "workStatusInput", allergyTestData.taskName),"Could not select Work Status - " + allergyTestData.taskName + "; More Details :" + allergyTestData.toString());
				Assert.assertTrue(select(selenium, "taskUsersInput", allergyTestData.sendTaskTo),"Could not select Send To Task - " + allergyTestData.sendTaskTo + "; More Details :" + allergyTestData.toString());
				Assert.assertTrue(type(selenium,"taskNotesInput", allergyTestData.taskNotes),"Could not enter Task Notes; More Details :" + allergyTestData.toString());

				Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details :" + allergyTestData.toString());
				waitForPageLoad(selenium);
			
				if(selenium.isAlertPresent()){
					Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert()+ "; More Details :" + allergyTestData.toString());
				}
				Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + allergyTestData.toString());
			}else{
				Assert.fail("No Records found to update an Allergy; More Details :" + allergyTestData.toString());
			}

			//--------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in allergy All section  //
			//--------------------------------------------------------------------------------//
			
		  Assert.assertFalse(idOfTheRecord == null,"Could not get the ID of the Updated Record; More Details :" + allergyTestData.toString());
				
		

			click(selenium,"allergies");
			waitForPageLoad(selenium);		
			
			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture Allergy[Active] Count after saving a Allergy; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentAllergies"))) == (Integer.parseInt(activeAllergyCount)-1),"The Allergy is not Saved Successfully, Allergy[Active] count has no change after adding a new Allergy; More Details :" + allergyTestData.toString());

			Assert.assertTrue(waitForValue(selenium, "AllAllergies", 120000),"Could not capture Allergy Count[All] after saving a Allergy; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllAllergies"))) == Integer.parseInt(allAllergyCount),"The Allergy is not Saved Correctly, Allergy count[All] has a change after updating an Allergy; More Details :" + allergyTestData.toString());

			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundinAllAllergy = verifyStoredValuesForInactiveAllergy(selenium, allergyTestData);
			
			//------------------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in allergy Current section  //
			//------------------------------------------------------------------------------------//

			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);
			
			if(selenium.isElementPresent(idOfTheRecord)){
			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			click(selenium, "actionButton");
						
			click(selenium, "edit"+uniqueID);
			waitForPageLoad(selenium);
			
			isRecordFoundinActiveAllergy= verifyStoredValues( selenium, allergyTestData);
			}
			
			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);
			
			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);
			waitForElement(selenium,"idOfTheRecord", 50000);
			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			
			isRecordFoundInActivity = verifyStoredValuesForInactiveAllergy(selenium, allergyTestData);			
		
			//----------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in Summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			boolean isRecordFoundInSummary = false;
			Assert.assertTrue(waitForValue(selenium, "allergyTitle", 120000),"Could not capture Allergy Count from summary page; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("allergyTitle"))) == ((Integer.parseInt(allergyInitialCountInSummary)-1)),"The Allergy is not Reflected Successfully in Summary Page, Allergy count has no change after adding a new Allergy; More Details :" + allergyTestData.toString());
			if(selenium.isElementPresent(idOfTheRecord)){
			click(selenium,idOfTheRecord);
			waitForPageLoad(selenium);
			isRecordFoundInSummary = verifyStoredValues(selenium, allergyTestData);
			}
			
			Assert.assertFalse(isRecordFoundinActiveAllergy,"Updated allergy[In active] found in Summary Page; Updation Failed; Detailed data:" + allergyTestData.toString());
			Assert.assertTrue(isRecordFoundinAllAllergy,"Updated allergy[In active] not found in Allergy[All]; Updation Failed; Detailed data:" + allergyTestData.toString());
			Assert.assertFalse(isRecordFoundInSummary,"Updated[Inactive] allergy record found in Summary Page; Updation Failed; Detailed data:" + allergyTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"Updated allergy[In active] not found in Activity Page; Updation Failed; Detailed data:" + allergyTestData.toString());
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + allergyTestData.toString());
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
	public boolean verifyStoredValues(Selenium selenium, ChartsLib allergyTestData){

		if(!getText(selenium,"adverseeventtypeInput").trim().equalsIgnoreCase(allergyTestData.eventType)){
			return false;
		}

		if(!getText(selenium,"severityInput").trim().equalsIgnoreCase(allergyTestData.severity)){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().equalsIgnoreCase(allergyTestData.startDate.trim())){
			return false;
		}

		if(!allergyTestData.endDate.trim().equals("") ){
			if(!getValue(selenium, "enddateInput").trim().equalsIgnoreCase(allergyTestData.endDate.trim())){
				return false;
			}
		}

		if(!getSelectedValue(selenium, "itemStatusInput").trim().equalsIgnoreCase(allergyTestData.status.trim())){
			return false;
		}

		if(!getValue(selenium, "notesInput").trim().equalsIgnoreCase(allergyTestData.allergyNotes.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "workStatusInput").trim().equalsIgnoreCase(allergyTestData.taskName.trim())){
			return false;
		}

		if(!getSelectedValue(selenium, "taskUsersInput").trim().equalsIgnoreCase(allergyTestData.sendTaskTo.trim())){
			return false;
		}			
		return true;
	}

	public boolean verifyStoredValuesForInactiveAllergy(Selenium selenium, ChartsLib allergyTestData){


		if(!getText(selenium,"adverseeventtypeInput").trim().equalsIgnoreCase(allergyTestData.eventType.trim())){
			return false;
		}

		if(!getText(selenium,"severityInput").trim().equalsIgnoreCase(allergyTestData.severity.trim())){
			return false;
		}

		if(!getText(selenium, "startdateInput").trim().equalsIgnoreCase(allergyTestData.startDate.trim())){
			return false;
		}

		if(!(allergyTestData.endDate.equals(""))){
			if(!getText(selenium, "enddateInput").trim().equalsIgnoreCase(allergyTestData.endDate.trim())){
				return false;
			}
		}

		if(!getText(selenium, "notesInput").trim().equalsIgnoreCase(allergyTestData.allergyNotes.trim())){
			return false;
		}
		return true;
	}
}
package com.clinical.selenium.section.charts.condition;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifyResolvedCondition extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Test for creating a Resolved Condition")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyResolvedCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib conditionTestData = new ChartsLib();
		conditionTestData.workSheetName = "ResolvedCondition";
		conditionTestData.testCaseId = "TC_CNR_001";
		conditionTestData.fetchChartsTestData();
		verifyResolvedCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: verfiyResolvedCondition
	 * @Description : Function to verify a resolved condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Sep 03, 2010
	 */	
	public void verifyResolvedCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib conditionTestData){

		Selenium selenium = null;
		String conditionCount = null;
		String content = null;	
		int counter=1;
		boolean isRecordFoundInConditions = false;
		boolean isEditableInConditions = true; 
		boolean isRecordFoundInActivity = false;
		boolean isEditableInActivity = true;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Condition and enter details                //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);

			// defining the condition status as resolved
			conditionTestData.status = "Resolved";

			// Removing existing Conditions with same name
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "" ;
				if(content!=null && content !=""){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.toLowerCase(new java.util.Locale("en", "US")))){
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						select(selenium, "itemStatusInput", "Inactive");
						click(selenium,"validateButton");
						waitForPageLoad(selenium);
					}						
				}
				counter++;
			}

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllConditions", 120000),"Could not capture existing All Condition  Count; More Details" + conditionTestData.toString());
			conditionCount = getListCount(selenium.getText("AllConditions"));		

			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details" + conditionTestData.toString());
						
			waitForPageLoad(selenium);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"conditionsBoxBox", conditionTestData.condition),"Could not enter the Condition; More Details" + conditionTestData.toString());
						
			Assert.assertTrue(enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate),"Could not enter the Condition Start Date; More Details" + conditionTestData.toString());

			Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End Date; More Details" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition Note; More Details" + conditionTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy Status; More Details" + conditionTestData.toString());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + conditionTestData.toString());
						
			Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details" + conditionTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", conditionTestData.taskNotes),"Could not enter Task Notes; More Details" + conditionTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details" + conditionTestData.toString());
			waitForPageLoad(selenium);			

			if(selenium.isAlertPresent()){
				Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
			}
			Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

			click(selenium,"AllConditions");
			waitForPageLoad(selenium);
									

			Assert.assertTrue(waitForValue(selenium, "AllConditions", 10000),"Could not capture condition Count after saving a condition; More Details" + conditionTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("AllConditions"))),Integer.parseInt(conditionCount)+1,"The condition is not Saved Successfully, condition count has no change after adding a new condition; More Details" + conditionTestData.toString());

			//--------------------------------------------------------------------//
			//  Step-3:  Verifying whether the details entered are saved properly //
			//--------------------------------------------------------------------//
			 
			counter = 1;
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;				
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content!=null?content.trim():"";				
				if(content!=null && content !=""){
					if(content.contains(conditionTestData.conditionStartDate) || content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.trim().toLowerCase(new java.util.Locale("en", "US")))){
						String uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
										
						waitForPageLoad(selenium);
						isRecordFoundInConditions = verifyStoredValues( selenium, conditionTestData);
						click(selenium, "actionButton");
									
						if (isRecordFoundInConditions){
							if(!getText(selenium, "edit"+uniqueID).trim().equalsIgnoreCase("Edit")){
								isEditableInConditions = false;
								break;
							}
						}
						click(selenium,"cancelButton");
						waitForPageLoad(selenium);
					}
				}
				counter++;
			}

			//-----------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//		

			click(selenium,"activity");
						
			waitForPageLoad(selenium);
			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);

			counter = 1;
			while(selenium.isElementPresent("//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;				
				String category = selenium.getText("//div[@id='patActivity']/table/tbody[1]/tr["+ counter +"]/td[2]/div");
				content = selenium.getText("//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[3]/div");
				content = content != null ? content.trim() : "" ;
				if(category.toLowerCase(new java.util.Locale("en", "US")).trim().contains("condition") &&content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(conditionTestData.condition.trim().toLowerCase(new java.util.Locale("en", "US")))){
					String uniqueID = selenium.getAttribute("//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[3]/div/strong/a@id").split("condition")[1];
					click(selenium,"//div[@id='patActivity']/table/tbody[1]/tr["+counter+"]/td[3]/div/strong/a");
									
					waitForPageLoad(selenium);
					isRecordFoundInActivity=verifyStoredValues( selenium, conditionTestData);
					click(selenium, "actionButton");
								
					if(!getText(selenium, "edit"+uniqueID).trim().equalsIgnoreCase("Edit")){
						isEditableInActivity = false;
						break;
					}	}

				click(selenium,"showList");
				waitForPageLoad(selenium);	
				click(selenium,"activity");
							
				waitForPageLoad(selenium);

				counter++;
			}

			Assert.assertTrue(isRecordFoundInConditions,"No Matching Record is Found in Conditions Page; More Details" + conditionTestData.toString());
			Assert.assertFalse(isEditableInConditions,"The Matching Record found in Conditions page with resolved status is Editable; More Details" + conditionTestData.toString());
			Assert.assertTrue(isRecordFoundInActivity,"No Matching Record is Found in Activity Page; More Details" + conditionTestData.toString());
			Assert.assertFalse(isEditableInActivity,"The Matching Record found in activity page with resolved status is Editable; More Details" + conditionTestData.toString());

		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + conditionTestData.toString());
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
	public boolean verifyStoredValues(Selenium selenium, ChartsLib conditionTestData){

		boolean verifySaved = true;


		if(!getText(selenium, "startdateInput").trim().contains(conditionTestData.conditionStartDate)){
			return false;
		}

		if(!getText(selenium, "enddateInput").trim().contains(conditionTestData.conditionEndDate)){
			return false;
		}

		if(!getText(selenium, "notesInput").trim().contains(conditionTestData.conditionNote.trim())){
			return false;
		}


		if(!getText(selenium, "taskUsers").trim().equalsIgnoreCase(conditionTestData.sendTaskTo.trim())){
			return false;
		}

		if(!getText(selenium, "taskNotes").trim().equalsIgnoreCase(conditionTestData.taskNotes.trim())){
			return false;
		}

		return verifySaved;
	}
}

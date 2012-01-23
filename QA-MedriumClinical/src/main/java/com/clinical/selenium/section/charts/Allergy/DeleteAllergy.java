package com.clinical.selenium.section.charts.Allergy;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class DeleteAllergy extends AbstractChartsTest {

	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Updating an Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void deleteAllergy(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "DeleteAllergy";
		allergyTestData.testCaseId = "TC_DAL_001";		
		allergyTestData.fetchChartsTestData();
		deleteAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: deleteAllergy
	 * @Description : Function to delete an existing Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: oct 27, 2010
	 */

	public void deleteAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib allergyTestData){

		Selenium selenium = null;
		String allAllergyCount = null;
		String activeAllergyCount = null;
		String allergyInitialCountInSummary = null;
		String content = null;
		int counter = 1;
		int recordCounter=0;
		boolean isRecordFoundInActivePage = false;
		boolean isRecordFoundInAllPage = false;
		boolean isAllergyExisit = false;
		boolean isAllergyDeleted = false;
		boolean isRecordFoundInActivity = false;
		String uniqueID = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);
			
			//--------------------------------------------------------------------//
			//  Step-2: Capture the active allergy counts in Summary Page         //
			//--------------------------------------------------------------------//

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
			Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link; More Details :" + allergyTestData.toString());
            
			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3: Varify and delete if allergy already Exists               //
			//--------------------------------------------------------------------//

			String idOfTheRecord = null;
			if(!activeAllergyCount.equals("0")){
				counter = 1;
				while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
					content = null;				
					content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
					content = content != null ? content.trim() : "";
					if(content != null && !content.equals("")){
						if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US")))){
							isAllergyExisit = true;
							idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
							uniqueID = idOfTheRecord.split("allergy")[1];
							click(selenium,idOfTheRecord);
							waitForPageLoad(selenium);
							click(selenium, "actionButton");
										
							click(selenium, "edit"+uniqueID);
							waitForPageLoad(selenium);
							
							Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
							
							if(selenium.isConfirmationPresent())
								if(selenium.getConfirmation().matches("^Are you sure you want to delete this allergy [\\s\\S]$")){
									isAllergyDeleted = true;
								}
							recordCounter ++;
							waitForPageLoad(selenium);
							if(selenium.isAlertPresent()){
								Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + allergyTestData.toString());
							}
							Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + allergyTestData.toString());
							isAllergyDeleted = true;
							counter = counter -1;
						}						
					}
					counter++;
				}
			}

			//--------------------------------------------------------------------//
			//  Step-4: Create the new allergy if allergy not Exists               //
			//--------------------------------------------------------------------//

			if(activeAllergyCount.equals("0") || !isAllergyExisit ){	
				createAllergy(selenium,allergyTestData);
			}

			//--------------------------------------------------------------------//
			//  Step-5: Delete the allergy                                        //
			//--------------------------------------------------------------------//

			if(!isAllergyExisit && !isAllergyDeleted){

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
				Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link; More Details :" + allergyTestData.toString());

				click(selenium,"CurrentAllergies");
				waitForPageLoad(selenium);

				recordCounter =0;
				while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
					content = null;				
					content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
					content = content != null ? content.trim() : "";
					if(content != null && !content.equals("")){
						if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US"))) ){
							isAllergyExisit = true;
							idOfTheRecord = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
							uniqueID = idOfTheRecord.split("allergy")[1];
							click(selenium,idOfTheRecord);
							waitForPageLoad(selenium);
							click(selenium, "actionButton");
										
							click(selenium, "edit"+uniqueID);
							waitForPageLoad(selenium);
							
							Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
							
							if(selenium.isConfirmationPresent())
								if(selenium.isConfirmationPresent())
									if(selenium.getConfirmation().matches("^Are you sure you want to delete this allergy [\\s\\S]$")){
										isAllergyDeleted = true;
									}
							recordCounter ++;
							waitForPageLoad(selenium);
							if(selenium.isAlertPresent()){
								Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + allergyTestData.toString());
							}
							Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p") + "; More Details :" + allergyTestData.toString());
							isAllergyDeleted = true;
							counter = counter -1;
						}						
					}
					counter++;
				}
			}

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllAllergies", 10000),"Could not capture Allergy[All] Count after deleting a Allergy; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllAllergies"))) == (Integer.parseInt(allAllergyCount)-recordCounter),"The Allergy is not Deleted Correctly, Allergy[All] count has a change after Deleting an Allergy; More Details :" + allergyTestData.toString());

			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 10000),"Could not capture Allergy[Active] Count after deleting a Allergy; More Details :" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentAllergies"))) == (Integer.parseInt(activeAllergyCount)-recordCounter),"The Allergy[Active] is not Deleted Correctly, Allergy count has a change after Deleting an Allergy; More Details :" + allergyTestData.toString());


			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying the Deletion in allergy  Current Page                   //
			//-----------------------------------------------------------------------------//

			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);
			counter = 1;
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInActivePage = true;
			}
			

			//-----------------------------------------------------------------------------//
			//  Step-7:  Verifying the Deletion in allergy All Page                        //
			//-----------------------------------------------------------------------------//

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInAllPage = true;
			}
		
			//-----------------------------------------------------------------------------//
			//  Step-8:  Verifying the Deletion  in activity page                          //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);
			
			counter = 1;
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInActivity = true;
			}
			
			
			//----------------------------------------------------------------------------//
			//  Step-9:   Verifying the Deletion in Summary Page                          //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);
			
			counter = 1;
			boolean isRecordFoundInSummary = false;	

			if(!allergyInitialCountInSummary.equals("0")){
				Assert.assertTrue(waitForValue(selenium, "allergyTitle", 10000),"Could not capture Allergy Count from summary page; More Details :" + allergyTestData.toString());
				Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("allergyTitle"))) == (Integer.parseInt(allergyInitialCountInSummary)-recordCounter),"The Allergy is not Reflected Successfully in Summary Page, Allergy count has no change after adding a new Allergy; More Details :" + allergyTestData.toString());
			}
			if(!selenium.isElementPresent(idOfTheRecord)){
				while(!selenium.isElementPresent(idOfTheRecord)){
				if(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
					click(selenium, "patientAllergyListMoreLink");
					waitForPageLoad(selenium);
				}else{
					break;
				}
				}
			}
			if(selenium.isElementPresent(idOfTheRecord)){
				isRecordFoundInSummary = true;
			}
			
			Assert.assertFalse(isRecordFoundInSummary,"Matching records found in Summary Section : problem in Deletion; More Details :  "+allergyTestData.toString()); 
			Assert.assertFalse( isRecordFoundInActivePage,"Matching records found Active Section : problem in Deletion"+allergyTestData.toString());
			Assert.assertFalse( isRecordFoundInAllPage,"Matching records found All Section : problem in Deletion"+allergyTestData.toString());			
			Assert.assertFalse(isRecordFoundInActivity,"Matching records found in Activity Section : problem in Deletion; More Details :  "+allergyTestData.toString());

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

		if(!getSelectedValue(selenium,"adverseeventtypeInput").trim().equalsIgnoreCase(allergyTestData.eventType.trim())){
			return false;
		}

		if(!getSelectedValue(selenium,"severityInput").trim().equalsIgnoreCase(allergyTestData.severity.trim())){
			return false;
		}

		if(!getValue(selenium, "startdateInput").trim().equalsIgnoreCase(allergyTestData.startDate.trim())){
			return false;
		}

		if(!(allergyTestData.endDate.equals(""))){
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

package com.clinical.selenium.section.charts.Allergy;
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

public class CreateNewAllergy extends AbstractChartsTest {

	protected int checkVariable = 0;

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Adding New Allergy with End Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewAllergyWithEndDate(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "TC_ALL_001";		
		allergyTestData.fetchChartsTestData();
		checkVariable ++;
		createNewAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	//@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Adding New Allergy without End Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewAllergyWithoutEndDate(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "TC_ALL_002";		
		allergyTestData.fetchChartsTestData();
		checkVariable ++;
		createNewAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);

	}

//	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Test for Adding  a Inactive Allergy")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void createNewAllergyWithInActive(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib allergyTestData = new ChartsLib();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "TC_ALL_003";		
		allergyTestData.fetchChartsTestData();
		checkVariable ++;
		createNewAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);

	}

	/**
	 * @Function 	: createNewAllergy
	 * @Description : Function to create a New Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 01, 2010
	 */

	@SuppressWarnings("deprecation")
	public void createNewAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib allergyTestData){

		Selenium selenium = null;
		checkVariable ++;
		String allergyInitialCountInSummary = null;
		int counter = 1;
		String content = null;			
		boolean isRecordFoundInActiveAllergy = false;
		boolean isRecordFoundInAllAllergy = false;
		boolean isRecordFoundInActivity = false;
		boolean isRecordFoundInSummary = false;
		boolean isInActiveAllergy = false;
		String uniqueID = null;
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		allergyTestData.endDate = allergyTestData.endDate != null ? allergyTestData.endDate.trim() : "";

		if(allergyTestData.status.trim().equalsIgnoreCase("inactive")){
			isInActiveAllergy = true;
		}

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Removing existing Allergies with same name				  //
			//--------------------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);			

			while(selenium.isElementPresent("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div") ){
				content = null;				
				content = selenium.getText("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "";
				if(content != null && !content.equals("")){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(allergyTestData.allergyName.toLowerCase(new java.util.Locale("en", "US"))) && ! content.toLowerCase(new java.util.Locale("en", "US")).contains("inactive")){
						uniqueID = selenium.getAttribute("//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("allergy")[1];
				
						click(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						
						click(selenium, "actionButton");
						waitForPageLoad(selenium);
						
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						
						if(selenium.isElementPresent("closeButton")){
							click(selenium,"closeButton");
							waitForPageLoad(selenium);
							click(selenium,"chartsHeaderActionLink");
							waitForPageLoad(selenium);
						}else {
							select(selenium, "itemStatusInput", "Inactive");
							click(selenium,"validateButton");
							waitForPageLoad(selenium);
						}
					}						
				}
				counter++;
			}

			click(selenium,"summary");
			waitForPageLoad(selenium);
			Assert.assertTrue(waitForValue(selenium, "allergyTitle", 100000),"Could not capture existing Allergies Count from Summary Page; More Details :" + allergyTestData.toString());
			allergyInitialCountInSummary= getListCount(selenium.getText("allergyTitle"));

			//----------------------------------------------------------------------------//
			//  Step-3:  Calling the function to create new Allergy                       //
			//----------------------------------------------------------------------------//
			
			click(selenium,"allergies");
			waitForPageLoad(selenium);			

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			if(allergyTestData.endDate != null && !allergyTestData.endDate.equals(""))
				allergyTestData.endDate = dateFormat.format(currentDate);
			currentDate.setDate(currentDate.getDate()-2);
			allergyTestData.startDate = dateFormat.format(currentDate);
			while(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
				selenium.click("patientAllergyListMoreLink");

				waitForPageLoad(selenium);
			}
			Collection<String> firstList = getDataBaseIDs(selenium, "allergy");
			System.out.println("First LIst Size : "+ firstList.size());
			System.out.println("First LIst  : "+ firstList.toString());
			createAllergy(selenium,allergyTestData);
			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);
			while(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
				selenium.click("patientAllergyListMoreLink");
				waitForPageLoad(selenium);
			}
			Collection<String> secondList = getDataBaseIDs(selenium, "allergy");	
			System.out.println("Second LIst Size : "+ secondList.size());
			System.out.println("Second LIst  : "+ secondList.toString());
			secondList.removeAll(firstList);
			String idOfTheNewlyAddedRecord = "";
			if(secondList.size()>=1){
				idOfTheNewlyAddedRecord = secondList.toArray()[0].toString();
			}else{
				Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
				Assert.fail("Expected record Not found; Record addition Failed more Details; "+ allergyTestData.toString());
			}

			//------------------------------------------------------------------------------------//
			//  Step-4:  Verifying Details Entered are saved properly in allergy Current section  //
			//------------------------------------------------------------------------------------//

			System.out.println("idOfTheNewlyAddedRecord   : " + idOfTheNewlyAddedRecord);
			click(selenium,"allergies");
			waitForPageLoad(selenium);
			
			click(selenium,"CurrentAllergies");
			waitForPageLoad(selenium);

			if(selenium.isElementPresent(idOfTheNewlyAddedRecord)){
				click(selenium, idOfTheNewlyAddedRecord);
				if(verifyStoredValues(selenium, allergyTestData)){ 
					click(selenium, "showList");
					waitForPageLoad(selenium);
					isRecordFoundInActiveAllergy = true;
				}
			}

			//------------------------------------------------------------------------------------//
			//  Step-5:  Verifying Details Entered are saved properly in allergy All section      //
			//------------------------------------------------------------------------------------//


			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			waitForElement(selenium, "//div[@id='patientAllergyList']/table/tbody[1]/tr["+counter+"]/td[1]/div", 30000);
			while(!selenium.isElementPresent(idOfTheNewlyAddedRecord) && (selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink"))){
				selenium.click("patientAllergyListMoreLink");
				waitForPageLoad(selenium);
			}
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			isRecordFoundInAllAllergy = verifyStoredValues(selenium, allergyTestData);



			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in activity section  //
			//-----------------------------------------------------------------------------//

			click(selenium,"activity");
			waitForPageLoad(selenium);

			click(selenium,"activityHeaderCurrentYear");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("patientAllergyListMoreLink") && selenium.isVisible("patientAllergyListMoreLink")){
				selenium.click("patientAllergyListMoreLink");
				waitForPageLoad(selenium);
			}

			Assert.assertTrue(isElementPresent(selenium, idOfTheNewlyAddedRecord),"The Allergy is not listed: More details :" + allergyTestData.toString());
			click(selenium, idOfTheNewlyAddedRecord);
			waitForPageLoad(selenium);
			isRecordFoundInActivity = verifyStoredValues(selenium, allergyTestData);


			//----------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly in Summary section  //
			//----------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);
			
			counter = 1;
			Assert.assertTrue(waitForElement(selenium, "allergyTitle", 120000),"Could not capture Allergy Count from summary page");
			if(!isInActiveAllergy){
				Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("allergyTitle"))) == (Integer.parseInt(allergyInitialCountInSummary)+1),"The Allergy is not Reflected Successfully in Summary Page, Allergy count has no change after adding a new Allergy; More Details :" + allergyTestData.toString());
			}else{
				Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("allergyTitle"))) == (Integer.parseInt(allergyInitialCountInSummary)),"The Allergy is not Reflected Successfully in Summary Page, Allergy count has no change after adding a new Allergy; More Details :" + allergyTestData.toString());
			}

			if(selenium.isElementPresent(idOfTheNewlyAddedRecord))	{	
				click(selenium, idOfTheNewlyAddedRecord);
				isRecordFoundInSummary = verifyStoredValues(selenium, allergyTestData);
			}

			if(!isInActiveAllergy){
				Assert.assertTrue(isRecordFoundInActiveAllergy,"Active Allergy record is not found in Active Allergy Page; Allergy Creation Failed; More Details :" + allergyTestData.toString());
				Assert.assertTrue(isRecordFoundInAllAllergy,"Active Allergy record is not found in All Allergy Page; Allergy Creation Failed; More Details :" + allergyTestData.toString());
				Assert.assertTrue(isRecordFoundInSummary,"Active Allergy record is not found in Summary Page ; Allergy Creation Failed; More Details :" + allergyTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Active Allergy record is not found in Activity Page; Allergy Creation Failed; More Details :" + allergyTestData.toString());
			}else{
				Assert.assertFalse(isRecordFoundInActiveAllergy,"In Active Allergy record is found in Active Allergy Page; Allergy Creation Failed Detailed data:" + allergyTestData.toString());
				Assert.assertTrue(isRecordFoundInAllAllergy,"Active Allergy record is not found in All Allergy Page; Allergy Creation Failed; Detailed data:" + allergyTestData.toString());
				Assert.assertFalse(isRecordFoundInSummary,"In Active Allergy record is found in Summary Page; Allergy Creation Failed; Detailed data:" + allergyTestData.toString());
				Assert.assertTrue(isRecordFoundInActivity,"Active Allergy record is not found in Activity Page; Allergy Creation Failed; Detailed data:" + allergyTestData.toString());
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + allergyTestData.toString());
			try{
				Thread.sleep(60000);
			}catch (InterruptedException e1){				
				e1.printStackTrace();
			}
		} finally{
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
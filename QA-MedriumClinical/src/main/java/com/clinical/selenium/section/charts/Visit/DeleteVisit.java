package com.clinical.selenium.section.charts.Visit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class DeleteVisit extends AbstractChartsTest {


	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for deleting an existing Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void deleteVisit(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "DeleteVisit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchChartsTestData();
		deleteVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: deleteVisit
	 * @Description : Function to delete an existing visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Oct 01, 2010
	 */
	public void deleteVisit(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){

		Selenium selenium = null;
		String summaryVisitCount = null;
		int counter = 1;
		int recordCounter = 0;
		boolean foundRecord = false;
		boolean summaryFoundRecord = false;
		boolean activityFoundRecord = false;
		boolean isVisitExisit = false;
		boolean isVisitDeleted = false;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2: Capture the active Visit counts in Summary Page           //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);
			
			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture Visit Count in patient summary section after saving a Visit; More Details :" + visitTestData.toString());
			summaryVisitCount = getListCount(selenium.getText("visitTitle"));

			//--------------------------------------------------------------------//
			//  Step-3:  Verify and delete if Visit already Exists             //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);
			
			String idOfTheRecord = null;
			if (isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")){
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isVisitExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						click(selenium, idOfTheRecord);
						waitForPageLoad(selenium);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
						
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().contains("Are you sure you want to delete this visit")){
								isVisitDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Visit not Deleted successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + visitTestData.toString());
						}
						isVisitDeleted = true;
						counter = counter -1;
						click(selenium, "visits");
						waitForPageLoad(selenium);
					}
					counter++;

					if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div"))){
						click(selenium, "patientVisitListMoreLink");
						waitForPageLoad(selenium);
					}
				}
			}

			//--------------------------------------------------------------------//
			//  Step-4: Create the new Visit if Visit not Exists                  //
			//--------------------------------------------------------------------//

			if ((!isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr[1]/td[1]/div/strong/a")  && !isVisitDeleted) || !isVisitExisit){
				createVisit(selenium, visitTestData);
			}

			//--------------------------------------------------------------------//
			//  Step-5: Delete the Visit                                          //
			//--------------------------------------------------------------------//

			if(!isVisitExisit && !isVisitDeleted){
				click(selenium,"summary");
							
				waitForPageLoad(selenium);
				Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture Visit Count in patient summary section after saving a Visit; More Details :" + visitTestData.toString());
				summaryVisitCount = getListCount(selenium.getText("visitTitle"));

				click(selenium,"visits");
				waitForPageLoad(selenium);
				
				counter = 1;
				while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
					if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(visitTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
						isVisitExisit = true;
						idOfTheRecord = selenium.getAttribute("//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id");
						click(selenium, idOfTheRecord );
						waitForPageLoad(selenium);
						Assert.assertTrue(click(selenium,"deleteButton"),"Could not click Delete Button");
						
						if(selenium.isConfirmationPresent())
							if(selenium.getConfirmation().contains("Are you sure you want to delete this visit")){
								isVisitDeleted = true;
							}
						recordCounter ++;
						waitForPageLoad(selenium);
						if(selenium.isAlertPresent()){
							Assert.assertFalse(selenium.isAlertPresent(),"Visit not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() + "; More Details :" + visitTestData.toString());
						}
						isVisitDeleted = true;
						counter = counter -1;
						click(selenium, "visits");
						waitForPageLoad(selenium);
					}
					counter++;
					if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div"))){
						click(selenium, "patientVisitListMoreLink");
						waitForPageLoad(selenium);
					}
				}
			}

			//-----------------------------------------------------------------------------//
			//  Step-6:  Verifying Details Entered are saved properly in visit section     //
			//-----------------------------------------------------------------------------//

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
			
			if(selenium.isElementPresent(idOfTheRecord)){
				foundRecord = true;
			}
		
			//---------------------------------------------------------------------------------//
			//  Step-7:  Verifying Details Entered are saved properly	in summary section     //
			//---------------------------------------------------------------------------------//

			click(selenium,"summary");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "visitTitle", 10000),"Could not capture Visit Count in patient summary section after deleting a Visit; More Details :" + visitTestData.toString());
			Assert.assertEquals(Integer.parseInt(getListCount(selenium.getText("visitTitle"))) , Integer.parseInt(summaryVisitCount)-recordCounter,"The Visit is not Deleted Successfully in summary section, visit count has no change after adding a new visit; More Details :" + visitTestData.toString());

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
			
			if(selenium.isElementPresent(idOfTheRecord)){
				summaryFoundRecord = true;
			}
		
			click(selenium,"activity");
			waitForPageLoad(selenium);
			
			if(selenium.isElementPresent(idOfTheRecord)){
				activityFoundRecord = true;
			}
						
			Assert.assertFalse(foundRecord,"Deleted Record found; The Record is not deleted successfully in patient Visit section; More Details : "+visitTestData.toString());
			Assert.assertFalse(activityFoundRecord,"Deleted Record found; The Record is not deleted successfully in patient Activity section; More Details : "+visitTestData.toString());
			Assert.assertFalse(summaryFoundRecord,"Deleted Record found; The Record is not deleted successfully in patient Summary section; More Details : "+visitTestData.toString());
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

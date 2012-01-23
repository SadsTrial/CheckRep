package com.clinical.selenium.section.charts.Visit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerifySaveButtonForVisit extends AbstractChartsTest {
	
	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying Save button and Data for Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifySaveButtonForUnSavedVisit(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "VerifySaveButtonForVisit";
		visitTestData.testCaseId = "TC_VIS_001";
		visitTestData.fetchChartsTestData();
		verifySaveButtonForUnSavedVisit(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: verifySaveButtonForUnSavedVisit
	 * @Description : Function to verify Save Button For UnSaved Visit
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 26, 2011
	 */
	public void verifySaveButtonForUnSavedVisit(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){

		Selenium selenium = null;
		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + visitTestData.toString());
			loginFromPublicSite(selenium, visitTestData.userAccount, visitTestData.userName, visitTestData.userPassword);
			searchPatient(selenium,visitTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Click Add New Visit and enter details                    //
			//--------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"visitsAdd"),"Could not find (+) Add Visit Link; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);

			Assert.assertTrue(enterDate(selenium, "visitdateInput", visitTestData.visitDate),"Could not enter the test data for Visit Date; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium, "providersInput", visitTestData.providerName),"Could not select the test data for Provider Name; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium, "locationsInput", visitTestData.providerLocation),"Could not select the test data for Provider Location; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"subjectiveInput", visitTestData.patientSubjective),"Could not enter the test data for Patient Subjective; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"objectiveInput", visitTestData.patientObjective),"Could not enter the test data for Patient Objective; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"assessmentInput", visitTestData.patientAssessment),"Could not enter the test data for Patient Assessment; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"planInput", visitTestData.patientPlan),"Could not enter the test data for Patient Plan; More Details" +visitTestData.toString());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium,"workStatusInput", visitTestData.taskName),"Could not enter work status; More Details" +visitTestData.toString());
			Assert.assertTrue(select(selenium,"taskUsersInput", visitTestData.sendTaskTo),"Could not select Medication send task to; More Details" +visitTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", visitTestData.taskNotes),"Could not enter the test data for Task Notes; More Details" +visitTestData.toString());

			click(selenium,"visits");
			waitForPageLoad(selenium);

			Assert.assertTrue(selenium.getAttribute("visitsAdd@src").trim().equalsIgnoreCase("images/plus-icon-on.png"),"Visits Add(+)  button is OFF and not backgrounded by Orange; More Details" +visitTestData.toString());  
			Assert.assertTrue(click(selenium,"visitsAdd"),"Could not find (+) Add Visit Link; More Details" +visitTestData.toString());
			waitForPageLoad(selenium);

			verifyStoredValues(selenium, visitTestData);
			Assert.assertTrue(selenium.isEditable("validateButton"),"Save Button is Not Enabled; More Details" +visitTestData.toString());

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
	public void verifyStoredValues(Selenium selenium, ChartsLib visitTestData){

		Assert.assertEquals(getValue(selenium,"visitdateInput").trim(),visitTestData.visitDate.trim(),"Un saved Visit Date Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"providersInput").trim(),visitTestData.providerName.trim(),"Un saved Providers Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"locationsInput").trim(),visitTestData.providerLocation.trim(),"Un saved Locations Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getValue(selenium,"subjectiveInput").trim(),visitTestData.patientSubjective,"Un saved Subjective Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getValue(selenium,"objectiveInput").trim(),visitTestData.patientObjective,"Un saved Objective Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getValue(selenium,"assessmentInput").trim(),visitTestData.patientAssessment,"Un saved Assessment input Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getValue(selenium,"planInput").trim(),visitTestData.patientPlan,"Un saved Plan input Value is not available; It is modified; More Details" +visitTestData.toString());
		Assert.assertEquals(getSelectedValue(selenium,"workStatusInput").trim(),visitTestData.taskName,"Un saved WorkStatus input Value is not available; It is modified; More Details" +visitTestData.toString());

	}
}

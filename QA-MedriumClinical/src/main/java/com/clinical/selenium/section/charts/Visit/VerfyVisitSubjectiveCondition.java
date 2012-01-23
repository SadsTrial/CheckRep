package com.clinical.selenium.section.charts.Visit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VerfyVisitSubjectiveCondition extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for Verifying the Subjective Condition For Tied and Un Tied Conditions")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void verifyVisitSubjectiveCondition(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib subjectiveConditionTestData = new ChartsLib();
		subjectiveConditionTestData.workSheetName = "VerifyVisitSubjectiveCondition";
		subjectiveConditionTestData.testCaseId = "TC_VIS_001";
		subjectiveConditionTestData.fetchChartsTestData();
		verifyVisitSubjectiveCondition(seleniumHost, seleniumPort, browser, webSite, subjectiveConditionTestData);
	}

	/**
	 * @Function 	: verifyVisitSubjectiveCondition
	 * @Description : Function to Verify Visit Subjective Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: April 29, 2011
	 */
	public void verifyVisitSubjectiveCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib subjectiveConditionTestData){

		Selenium selenium = null;
		boolean isVisitPresent = false;
		boolean isTiedConditionDisplyed = false;
		boolean isUnTiedConditionDisplyed = false;
		boolean flag = false;
		String content = null;
		int counter = 1;
		int counter1 = 1;

		try{
			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + subjectiveConditionTestData.toString());
			loginFromPublicSite(selenium, subjectiveConditionTestData.userAccount, subjectiveConditionTestData.userName, subjectiveConditionTestData.userPassword);
			searchPatient(selenium,subjectiveConditionTestData.patientID);

			//--------------------------------------------------------------------------//
			//  Step-2:  Verify the expected visit is already available or not          //
			//--------------------------------------------------------------------------//

			click(selenium,"visits");
			waitForPageLoad(selenium);

			while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a")){
				if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
					isVisitPresent = true;
					break;
				}
				counter ++;
				if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a"))){
					if(isElementPresent(selenium, "patientVisitListMoreLink"))
						if(selenium.isVisible("patientVisitListMoreLink")){
							click(selenium, "patientVisitListMoreLink");
							waitForPageLoad(selenium);
						}else{
							break;
						}
				}
			}

			//--------------------------------------------------------------------------//
			//  Step-3:  Click Add New Visit and enter details if Visit not available   //
			//--------------------------------------------------------------------------//

			if(!isVisitPresent){
				createVisit(selenium, subjectiveConditionTestData);
			}

			//--------------------------------------------------------------------------//
			//  Step-4:  Remove the Existing condition with same Name                   //
			//--------------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div") && selenium.isVisible("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim() : "" ;
				if(content!=null && content !=""){
					if(content.toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.condition.toLowerCase(new java.util.Locale("en", "US")))){
						String uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						click(selenium, "actionButton");
									
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						
						click(selenium, "addTask");
						select(selenium, "itemStatusInput", "Inactive");
						
						click(selenium,"validateButton");
						waitForPageLoad(selenium);
					}						
				}
				counter++;
			}
			createCondition(selenium, subjectiveConditionTestData);

			click(selenium,"visits");
						
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------------//
			//  Step-5:  Verifying the Untied condition is displayed in Visit page     //
			//--------------------------------------------------------------------------//

			while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a")){
				if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
					click(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					counter1 = 1;
					while(selenium.isElementPresent( "//tr["+counter1+"]/td/div/div/div/div[1]/div/div[1]/ul/li/span[1]")){
						flag = true;
						if(getText(selenium, "//tr["+counter1+"]/td/div/div/div/div[1]/div/div[1]/ul/li/span[1]").trim().contains(subjectiveConditionTestData.condition.trim())){
							isUnTiedConditionDisplyed = true;

						}
						counter1 ++ ;
					}
					if(!flag){
						break;
					}
					click(selenium, "visits");
					waitForPageLoad(selenium);
				}
				counter++;

				if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a"))){
					if(isElementPresent(selenium, "patientVisitListMoreLink"))
						if(selenium.isVisible("patientVisitListMoreLink")){
							click(selenium, "patientVisitListMoreLink");
							waitForPageLoad(selenium);
						}else{
							break;
						}
				}
			}

			//------------------------------------------------------------------//
			//  Step-6: Tie the condition with  Visit Date                      //
			//------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			click(selenium,"CurrentConditions");
			waitForPageLoad(selenium);

			counter = 1;
			while(selenium.isElementPresent("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div")){
				content = null;				
				content = selenium.getText("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div");
				content = content != null ? content.trim(): "" ;				
				if(content!= null && !content.equals("")){
					if(content.contains(subjectiveConditionTestData.conditionNote) && content.trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.condition.trim().toLowerCase(new java.util.Locale("en", "US")))){
						String uniqueID = selenium.getAttribute("//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a@id").split("condition")[1];
						click(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
						waitForPageLoad(selenium);
						
						click(selenium, "actionButton");
						click(selenium, "edit"+uniqueID);
						waitForPageLoad(selenium);
						Assert.assertTrue(select(selenium,"listBoxVisitsConditions", subjectiveConditionTestData.visitDate),"Could not select The Visit Date");
						click(selenium,"validateButton");
									
						waitForPageLoad(selenium);
						break;
					}
				}
				counter ++;
			}

			click(selenium,"visits");
			waitForPageLoad(selenium);

			//---------------------------------------------------------------------//
			//  Step-7: verifying the tied condition is displayed in visit Page    //
			//---------------------------------------------------------------------//

			while(selenium.isElementPresent( "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a")){
				if(getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.providerLocation.trim().toLowerCase(new java.util.Locale("en", "US"))) && getText(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains(subjectiveConditionTestData.visitDate.trim().toLowerCase(new java.util.Locale("en", "US")))){
					click(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a");
					waitForPageLoad(selenium);
					counter1 = 1;
					while(selenium.isElementPresent( "//tr["+counter1+"]/td/div/div/div/div[1]/div/div[1]/ul/li/span[1]")){

						if(getText(selenium, "//tr["+counter1+"]/td/div/div/div/div[1]/div/div[1]/ul/li/span[1]").trim().contains(subjectiveConditionTestData.condition.trim())){
							isTiedConditionDisplyed = true;
							break;
						}
						counter1++;
					}
					click(selenium, "visits");
					waitForPageLoad(selenium);
				}
				counter++;
				if(isTiedConditionDisplyed) break;

				if(!(isElementPresent(selenium, "//div[@id='patientVisitList']/table/tbody[1]/tr["+counter+"]/td[1]/div/strong/a"))){
					if(isElementPresent(selenium, "patientVisitListMoreLink"))
						if(selenium.isVisible("patientVisitListMoreLink")){
							click(selenium, "patientVisitListMoreLink");
							waitForPageLoad(selenium);
						}else{
							break;
						}
				}
			}

			Assert.assertFalse(isUnTiedConditionDisplyed,"Untied Condition is getting displayed in Subjective Conditon; Subjective Verification for Untied Condtion Failed; Detailed data:" + subjectiveConditionTestData.toString());
			Assert.assertTrue(isTiedConditionDisplyed,"Tied Condition is not getting displayed in Subjective Conditon; Subjective Verification for Untied Condtion Failed; Detailed data:" + subjectiveConditionTestData.toString());

		}catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + subjectiveConditionTestData.toString());
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

}

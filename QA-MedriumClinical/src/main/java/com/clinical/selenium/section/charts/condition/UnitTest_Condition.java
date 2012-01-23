package com.clinical.selenium.section.charts.condition;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Condition extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition with blank space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_001";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_002";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Condition with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_003";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Condition with Number")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_004";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_005";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Condition Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionStartDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_006";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Condition Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_007";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Condition Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void coditionStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_008";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Start Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionStartDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_009";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionStartDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_010";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition End Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_011";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Condition End Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_012";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition End Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_013";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition End Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_014";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Condition End Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_015";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition End Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_016";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Validation for End Date < Start Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionEndDateEarlierThanStartDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_017";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Condition Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_018";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_019";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionNotesWithSpecialCharacterss(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_020";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Condition Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void conditionNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_021";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_022";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_023";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_024";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_025";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest conditionTestData = new ChartsLib_UnitTest();
		conditionTestData.workSheetName = "Condition";
		conditionTestData.testCaseId = "UT_CO_026";
		conditionTestData.fetchCodingInterfaceTestData();
		addCondition(seleniumHost, seleniumPort, browser, webSite, conditionTestData);	
	}

	/**
	 * @Function 	: addCondition
	 * @Description : Function to create a New Condition
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010
	 */	
	public void addCondition(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest conditionTestData){
		Selenium selenium = null;
		String alertText = "";

		conditionTestData.alert = conditionTestData.alert == null ? "" : conditionTestData.alert.trim();
		conditionTestData.alertMessage = conditionTestData.alertMessage == null ? "" : conditionTestData.alertMessage.trim();

		Assert.assertFalse((conditionTestData.alert.trim().equals("No") && !(conditionTestData.alertMessage.equals(""))) || (conditionTestData.alert.trim().equals("Yes")) && conditionTestData.alertMessage.equals(""),"The Alert Details or Alert Message Details are not proper in the Test Data Sheet");
		
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
				
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + conditionTestData.toString());
			loginFromPublicSite(selenium, conditionTestData.userAccount, conditionTestData.userName, conditionTestData.userPassword);
			searchPatient(selenium,conditionTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Condition Link     		                  //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);

			Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details :" + conditionTestData.toString());
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
							
			}

			if(conditionTestData.condition != null && !conditionTestData.condition.equals("") )
				Assert.assertTrue(selectValueFromAjaxList(selenium,"conditionsBoxBox", conditionTestData.condition),"Could not enter the Condition; More Details :" + conditionTestData.toString());
						
			Assert.assertTrue(enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate),"Could not enter the Condition Start Date; More Details :" + conditionTestData.toString());
			Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End Date; More Details :" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition Note; More Details :" + conditionTestData.toString());

			if(conditionTestData.taskName != null && !conditionTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details :" + conditionTestData.toString());

			if(conditionTestData.sendTaskTo != null && !conditionTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details :" + conditionTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", conditionTestData.taskNotes),"Could not enter Task Notes; More Details :" + conditionTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details :" + conditionTestData.toString());
										

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText.equals("") && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			if(conditionTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					if(!getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains("already exists in the system"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(conditionTestData.checkAlertMessage(alertText, conditionTestData.alertMessage) || selenium.isTextPresent(conditionTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+conditionTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + conditionTestData.toString());
			}else if (conditionTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +")" + "; More Details :" + conditionTestData.toString());
			}

		}
		catch (RuntimeException e) {
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
}

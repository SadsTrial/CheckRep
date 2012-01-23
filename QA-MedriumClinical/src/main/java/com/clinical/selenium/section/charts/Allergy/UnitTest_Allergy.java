package com.clinical.selenium.section.charts.Allergy;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Allergy extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy with blank space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_001";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_002";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_003";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_004";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStartDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_005";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStartDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_006";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStartDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_007";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStartDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_008";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Start Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStarteDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_009";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default", "FIXME" }, description = "Allergy Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyStarteDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_010";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy End Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_011";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy End Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_012";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy End Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_013";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy End Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_014";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy End Date with a Valid Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithValidDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_015";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	} 

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy End Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_016";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Allergy Validation for End Date < Start Date ")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyEndDateEarlierThanStartDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_017";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_018";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_019";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Allergy Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_020";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Allergy Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void allergyNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_021";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_022";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_023";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_024";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Task Notes with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_025";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_026";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the Adverse Event Type")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutAdverseEvent(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_027";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the Severity")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSeverity(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest allergyTestData = new ChartsLib_UnitTest();
		allergyTestData.workSheetName = "Allergy";
		allergyTestData.testCaseId = "UT_AL_028";
		allergyTestData.fetchCodingInterfaceTestData();
		addAllergy(seleniumHost, seleniumPort, browser, webSite, allergyTestData);
	}

	/**
	 * @Function 	: addAllergy
	 * @Description : Function to add a New Allergy
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010
	 */
	public void addAllergy(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest allergyTestData){

		String alertText = "";
		allergyTestData.alert = allergyTestData.alert == null ? "" : allergyTestData.alert.trim();
		allergyTestData.alertMessage = allergyTestData.alertMessage == null ? "" : allergyTestData.alertMessage.trim();

		Assert.assertFalse((allergyTestData.alert.trim().equals("No") && !(allergyTestData.alertMessage.equals(""))) || (allergyTestData.alert.trim().equals("Yes")) && allergyTestData.alertMessage.equals(""),"The Alert Details or Alert Message Details are not proper in the Test Data Sheet; More Details :" + allergyTestData.toString());
		Selenium selenium = null;
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);		
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + allergyTestData.toString());
			loginFromPublicSite(selenium, allergyTestData.userAccount, allergyTestData.userName, allergyTestData.userPassword);
			searchPatient(selenium,allergyTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Allergy Link			                      // 
			//--------------------------------------------------------------------//

			click(selenium,"allergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link; More Details :" + allergyTestData.toString());
			click(selenium,"addAllergy");
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			if(click(selenium, "addTask")){
				waitForPageLoad(selenium);
			}

			if( allergyTestData.allergyName != null  && !allergyTestData.allergyName.equals(""))
				Assert.assertTrue(selectValueFromAjaxList(selenium,"allergyBoxBox", allergyTestData.allergyName),"Could not enter Allergy Name; More Details :" + allergyTestData.toString());

			if(allergyTestData.eventType != null &&  !allergyTestData.eventType.equals("") )
				Assert.assertTrue(select(selenium,"adverseeventtypeInput", allergyTestData.eventType),"Could not select Event Type; More Details :" + allergyTestData.toString());

			if(allergyTestData.severity != null && !allergyTestData.severity.equals(""))
				Assert.assertTrue(select(selenium,"severityInput", allergyTestData.severity),"Could not select Allergy Severity; More Details :" + allergyTestData.toString());
			Assert.assertTrue(enterDate(selenium,"startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details :" + allergyTestData.toString());

			if(allergyTestData.endDate!=""){
				Assert.assertTrue(enterDate(selenium,"enddateInput", allergyTestData.endDate),"Could not enter End Date; More Details :" + allergyTestData.toString());
			}

			Assert.assertTrue(type(selenium,"notesInput", allergyTestData.allergyNotes),"Could not enter Allergy Notes; More Details :" + allergyTestData.toString());

			if(allergyTestData.taskName != null && !allergyTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium, "workStatusInput", allergyTestData.taskName),"Could not select Work Status - " + allergyTestData.taskName + "; More Details :" + allergyTestData.toString());

			if(allergyTestData.sendTaskTo != null && !allergyTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium, "taskUsersInput", allergyTestData.sendTaskTo),"Could not select Send To Task - " + allergyTestData.sendTaskTo + "; More Details :" + allergyTestData.toString());

			Assert.assertTrue(type(selenium,"taskNotesInput", allergyTestData.taskNotes),"Could not enter Task Notes; More Details :" + allergyTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details :" + allergyTestData.toString());


			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//p", 5000);
			if(alertText.equals("") && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}
			if(allergyTestData.alert.equalsIgnoreCase("Yes")){
				if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
					if(!getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim().toLowerCase(new java.util.Locale("en", "US")).contains("already exists in the system"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim(); 	 
				Assert.assertTrue(allergyTestData.checkAlertMessage(alertText, allergyTestData.alertMessage) || selenium.isTextPresent(allergyTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+allergyTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + allergyTestData.toString());
			}else if (allergyTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +")" + "; More Details :" + allergyTestData.toString());
			}

		}catch (RuntimeException e) {
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
}
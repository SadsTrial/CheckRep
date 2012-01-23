package com.clinical.selenium.section.charts.directives;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_AdvanceDirective extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Start Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void startDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_001";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Start Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void startDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_002";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Start Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void startDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_003";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Start Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void startDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_004";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Start Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void startDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_005";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDateWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_006";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_007";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_008";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDatewithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_009";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_010";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "End Date < Start Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void endDateEarlierThanStartDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_011";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Last Name with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lastNameWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_012";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Last Name with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lastNameWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_013";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Last Name with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lastNameWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_014";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "First Name with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void firstNameWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_015";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "First Name with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void firstNameWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_016";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "First Name with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void firstNameWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_017";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Organization with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void organizationWithAplhanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_018";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Organization with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void organizationWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();		
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_019";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}	

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Address1 with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address1WithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_020";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Address1 with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address1WithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_021";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Address2 with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address2WithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_022";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Address2 with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address2WithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_023";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "City with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void cityWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_024";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "City with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void cityWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_025";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "City with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void cityWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_026";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "State with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void stateWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_027";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "State with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void stateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_028";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "State with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void stateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_029";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}	

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Zip with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void zipWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_030";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Zip with less than 5 Digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void zipWithLessThan5Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_031";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Zip with greater than 5 Digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void zipWithMoreThan5Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_032";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Zip with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void zipWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_033";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Phone with less than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void phoneWithLessThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_034";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Phone with greater than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void phoneWithMoreThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_035";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Phone with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void phoneWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_036";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Phone with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void phoneWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_037";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Extension with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void extensionWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_038";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Extension with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void extensionWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_039";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Mobile with less than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void mobileWithLessThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_040";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Mobile with greater than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void mobileWithMoreThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_041";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Mobile with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void mobileWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_042";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Mobile with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void mobileWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_043";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Fax with less than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void faxWithLessThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_044";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Fax with greater than 10 digits")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void faxWithMoreThan10Digits(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_045";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Fax with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void faxWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_046";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Fax with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void faxWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_047";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Email with two @ symbols")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void emailWithMoreAtTheRateSymbols(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_048";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Email without @ symbols")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void emailWithoutAtTheRateSymbol(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_049";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}	

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Email with only Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void emailWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_050";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Note with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noteWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_051";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Note with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void noteWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_052";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Notes with Alphanumeric")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithAlphanumerics(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_053";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Task Notes with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void taskNotesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_054";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Last Name with 20 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lastNameWith20Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_055";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Last Name with more than 20 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void lastNameWithMoreThan20Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_056";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "First Name with 12 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void firstNameWith12Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_057";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "First Name with more than 12 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void firstNameWithMoreThan12Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_058";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Company with 33 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void companyWith33Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_059";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Company with more than 33 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void companyWithMoreThan33Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_060";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Address 1 with 30 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address1With30Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_061";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Address 1 with more than 30 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address1WithMoreThan30Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_062";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Address 2 with 30 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address2With30Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_063";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Address 2 with more than 30 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void address2WithMoreThan30Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_064";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "City with 20 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void cityWith20Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_065";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "City with more than 20 Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void cityWithMoreThan20Characters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_066";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting the SendTask to Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutSendToTask(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_067";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Saving without selecting the Advance Directive For Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutAdvanceDirectiveFor(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_068";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting the Directive Value")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutDirective(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_069";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Saving without selecting the Status")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void savingWithoutStatus(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest directiveTestData = new ChartsLib_UnitTest();
		directiveTestData.workSheetName = "Directive";
		directiveTestData.testCaseId = "UT_AD_070";
		directiveTestData.fetchCodingInterfaceTestData();
		addAdvanceDirective(seleniumHost, seleniumPort, browser, webSite, directiveTestData);
	}



	/**
	 * @Function 	: addAdvanceDirective
	 * @Description : Function to add an AdvanceDirective
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: May 03, 2010
	 */

	public void addAdvanceDirective(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest directiveTestData){

		String alertText = null;
		Selenium selenium = null;

		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//

			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + directiveTestData.toString());
			loginFromPublicSite(selenium, directiveTestData.userAccount, directiveTestData.userName, directiveTestData.userPassword);
			searchPatient(selenium,directiveTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Advance Directives Link                     //
			//--------------------------------------------------------------------//

			click(selenium,"advDirectives");
			waitForPageLoad(selenium);		

			Assert.assertTrue(waitForElement(selenium, "addAdvDirective", 10000),"Could not find Add Advance Directives Link; More Details :" + directiveTestData.toString());
			Assert.assertTrue(click(selenium,"addAdvDirective"),"Could Not click the Add Advance Directive Link; More Details :" + directiveTestData.toString());
			waitForPageLoad(selenium);

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			if(directiveTestData.directiveType !=null && !directiveTestData.directiveType.equals("") )
				Assert.assertTrue(select(selenium, "//div[@id='patAdvDirectiveTypeCodeInput']/select", directiveTestData.directiveType),"Could not select Advance Directives For; More Details :" + directiveTestData.toString());
			if( directiveTestData.directiveName != null && !directiveTestData.directiveName.equals(""))
				Assert.assertTrue(select(selenium,"//div[@id='patAdvDirectiveValueCodeInput']/select", directiveTestData.directiveName),"Could not select Directive Name; More Details :" + directiveTestData.toString());
			if(directiveTestData.directiveStatus != null && !directiveTestData.directiveStatus.equals("") )
				Assert.assertTrue(select(selenium, "//div[@id='statusCodeInput']/select", directiveTestData.directiveStatus),"Could not select Directive Status; More Details :" + directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "startDateInput", directiveTestData.directiveStartDate),"Could not enter Directive Start Date; More Details :" + directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "endDateInput", directiveTestData.directiveEndDate),"Could not enter Directive End Date; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianLastNameInput", directiveTestData.custodianLastName),"Could not enter Custodian Last Name; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianFirstNameInput", directiveTestData.custodianFirstName),"Could not enter Custodian First Name; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianOrganizationInput", directiveTestData.custodianOrganization),"Could not enter Custodian Organization; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianAddr1Input", directiveTestData.custodianAddress1),"Could not enter Custodian Address 1; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianAddr2Input", directiveTestData.custodianAddress2),"Could not enter Custodian Address 2; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianCityInput", directiveTestData.custodianCity),"Could not enter Custodian City; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianStateInput", directiveTestData.custodianState),"Could not enter Custodian State; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianZipInput", directiveTestData.custodianZIP),"Could not enter Custodian ZIP; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianTelNumInput", directiveTestData.custodianTel),"Could not enter Custodian Tel; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianExtNumInput", directiveTestData.custodianExt),"Could not enter Custodian Ext; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianMobileNumInput", directiveTestData.custodianMobile),"Could not enter Custodian Mobile; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianFaxNumInput", directiveTestData.custodianFax),"Could not enter Custodian Fax; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianEmailInput", directiveTestData.custodianEmail),"Could not enter Custodian Email; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", directiveTestData.custodianNote),"Could not enter Custodian Note; More Details :" + directiveTestData.toString());
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  ; More Details :" + directiveTestData.toString());
			waitForPageLoad(selenium);
			if(directiveTestData.taskName != null && !directiveTestData.taskName.equals(""))
				Assert.assertTrue(select(selenium, "workStatusInput", directiveTestData.taskName),"Could not select Work Status; More Details :" + directiveTestData.toString());
			if(directiveTestData.sendTaskTo != null && !directiveTestData.sendTaskTo.equals("") )
				Assert.assertTrue(select(selenium, "taskUsersInput", directiveTestData.sendTaskTo),"Could not select Send To Task; More Details :" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"taskNotesInput", directiveTestData.taskNotes),"Could not enter Task Notes; More Details :" + directiveTestData.toString());
			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Validate Button; More Details :" + directiveTestData.toString());
			waitForPageLoad(selenium);
			waitForElement(selenium, "//p", 5000);
			if(alertText == null && selenium.isElementPresent("//p")){
				alertText = selenium.getText("//p");
			}

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			if(directiveTestData.alert.equalsIgnoreCase("Yes")){
				if(alertText == null || alertText.equals("")){
					if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div");
				}
				Assert.assertTrue(directiveTestData.checkAlertMessage(alertText, directiveTestData.alertMessage) || selenium.isTextPresent(directiveTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+directiveTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + directiveTestData.toString());
				click(selenium,"cancelButton");
			}else if (directiveTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +")" + "; More Details :" + directiveTestData.toString());;
			}
		}catch (RuntimeException e){
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + directiveTestData.toString());
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
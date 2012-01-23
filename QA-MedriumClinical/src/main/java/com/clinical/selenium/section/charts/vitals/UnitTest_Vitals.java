package com.clinical.selenium.section.charts.vitals;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib_UnitTest;
import com.thoughtworks.selenium.Selenium;

public class UnitTest_Vitals extends AbstractChartsTest {

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Feet with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void feetWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_001";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Feet with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void feetWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_002";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}


	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Feet with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void feetWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_003";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Inches within the range (0 to 11)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void inchesLesserThan12(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_004";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Inches not within the range (>= 12)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void inchesGreaterThan11(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_005";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Inches with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void inchesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_006";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Inches with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void inchesSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_007";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pounds with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void poundsWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_008";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"   }, description = "Pounds with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void poundsWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_009";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pounds with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void poundsWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_010";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Ounces within the range (0 to 15)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void ouncesWithValueLesserThan16(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_011";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Ounces not within the range (>= 16)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void ouncesWithValueGreaterThan15(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();		
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_012";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Ounces with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void ouncesWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_013";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Ounces with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void ouncesWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_014";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Temperature with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void temperatureWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_015";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Temperature with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void temperatureWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_016";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Temperature with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void temperatureWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_017";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pulse with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pulseWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_018";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Pulse with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pulseWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_019";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Pulse with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void pulseWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_020";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Systolic with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void systolicWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_021";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Systolic with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void systolicWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_022";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Systolic with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void systolicWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_023";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Diastolic with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void diastolicWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_024";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Diastolic with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void diastolicWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_025";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Diastolic with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void diastolicWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_026";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Time with Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void timeWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_027";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Time with Alphabets")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void timeWithAlphabets(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_028";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Time with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void timeWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_029";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Time with Number")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void timeWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_030";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Metre with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void metreWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_031";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Metre with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void metreWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_032";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Metre with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void metreWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_033";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Centimeters within the range (0 to 99)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void centimetersWithValueLesserThan100(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_034";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Centimeters not within the range (>= 99)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void centimetersWithValueGreaterThan99(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_035";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Centimeters with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void centimetersWithSpecialCharaters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_036";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Centimeters with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void centimetersWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_037";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Kilograms with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void kilogramsWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_038";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Kilograms with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void kilogramsWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_039";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default"}, description = "Kilograms with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void kilogramsWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_040";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Grams within the range (0 to 999)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void gramsWithValueLesserThan1000(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_041";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Grams not within the range (>= 999)")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void gramsWithValueGreaterThan999(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_042";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Grams with Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void gramsWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_043";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Grams with Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void gramsWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_044";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Observation Date with a Blank Space")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void gramsWithBlankSpace(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_045";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Observation Date with Numbers")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void observationDateWithNumbers(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_046";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Observation Date with a Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void observationDateWithCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_047";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Observation Date with a Special Characters")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void observationDateWithSpecialCharacters(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_048";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Observation Date with a Future Date")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void observationDateWithFutureDate(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_049";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Kilograms with value > 900")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void kilogramsWithValueGreaterThan900(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_050";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Pounds with Value > 2000")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void poundsWithValueGreaterThan2000(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_051";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Feet with Value > 10")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void feetWithValueGreaterThan10(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_052";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Height Meter with Value > 3")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void heightInMeterWithValueGreaterThan3(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_053";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Temperature(C) value with > 50")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void celciusWithValueGreaterThan50(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_054";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Temperature(F) value with > 125")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void farenheitWithValueGreaterThan125(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_055";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	@Test(groups = {"firefox", "iexplore", "safari", "default" }, description = "Saving Without selecting any Vital")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void saveWithoutAnyVital(String seleniumHost, int seleniumPort,String browser, String webSite){

		ChartsLib_UnitTest vitalsTestData = new ChartsLib_UnitTest();
		vitalsTestData.workSheetName = "Vitals";
		vitalsTestData.testCaseId = "UT_VI_056";
		vitalsTestData.fetchCodingInterfaceTestData();
		addVitals(seleniumHost, seleniumPort, browser, webSite, vitalsTestData);
	}

	/**
	 * @Function 	: addVitals
	 * @Description : Function to create a New Vitals
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Anil Sannareddy
	 * @Created on 	: Jul 08, 2010
	 */
	public void addVitals(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib_UnitTest vitalsTestData){
		Selenium selenium = null;
		String alertText = "";

		
		try{

			//--------------------------------------------------------------------//
			//  Step-1: Login to the application and search for the given patient //
			//--------------------------------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session; More Details :" + vitalsTestData.toString());
			loginFromPublicSite(selenium, vitalsTestData.userAccount, vitalsTestData.userName, vitalsTestData.userPassword);
			searchPatient(selenium,vitalsTestData.patientID);

			//--------------------------------------------------------------------//
			//  Step-2:  Clicking Add Vitals Link                                 //
			//--------------------------------------------------------------------//

			click(selenium,"vitals");
			waitForPageLoad(selenium);
			
			Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link; More Details :" + vitalsTestData.toString());
			waitForPageLoad(selenium);

			if((vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("meter") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("centimeters") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("kilograms") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("grams") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("temperature(c)")) && !selenium.isTextPresent("grams") ){
				click(selenium, "unitsLinkLabel");
			}
			
			if((vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("feet") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("inches") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("pounds") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("ounces") || vitalsTestData.description.toLowerCase(new java.util.Locale("en", "US")).contains("temperature(f)")) && !selenium.isTextPresent("inches") ){
				click(selenium, "unitsLinkLabel");
			}

			//--------------------------------------------------------------------//
			//  Step-3:  Entered Test Data                                        //
			//--------------------------------------------------------------------//

			type(selenium, "startdateInput", "");
			Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details :" + vitalsTestData.toString());

			type(selenium,"vitalTimeInput", "");
			Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details :" + vitalsTestData.toString());

			Assert.assertTrue(type(selenium,"heightFeetsInput", vitalsTestData.patientHeightFeet), "Could not enter testdata for patient height in feet; More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"heightInchesInput", vitalsTestData.patientHeightInches),"Could not enter the test data for Patient Height (Inches); More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"weightpoundsInput", vitalsTestData.patientWeightPounds),"Could not enter the test data for Patient Weight (Pounds); More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"weightouncesInput", vitalsTestData.patientWeightOunces),"Could not enter the test data for Patient Weight (Ounces); More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"temperatureInput", vitalsTestData.patientTemperature),"Could not enter the test data for Patient Temperature; More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"pulseInput", vitalsTestData.patientPulse),"Could not enter the test data for Patient Pulse; More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"systolicInput", vitalsTestData.patientBPSystolic),"Could not enter the test data for Patient Systolic Pressure; More Details :" + vitalsTestData.toString());
			Assert.assertTrue(type(selenium,"diastolicInput", vitalsTestData.patientBPDiastolic),"Could not enter the test data for Patient Diastolic Pressure; More Details :" + vitalsTestData.toString());
			Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details :" + vitalsTestData.toString());
						

			//--------------------------------------------------------------------//
			//  Step-4:  Validating Alerts displayed                              //
			//--------------------------------------------------------------------//

			waitForElement(selenium, "//div[@class='errorPopup']/div/div/p", 5000);
			if(selenium.isElementPresent("//div[@class='errorPopup']/div/div/p")){
				alertText = selenium.getText("//div[@class='errorPopup']/div/div/p");
			}
			if(vitalsTestData.alert.equalsIgnoreCase("Yes")){
				if(alertText == null || alertText.equals("")){
					if(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div"))
						alertText = getText(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim();
				}
				Assert.assertTrue(vitalsTestData.checkAlertMessage(alertText, vitalsTestData.alertMessage) || selenium.isTextPresent(vitalsTestData.alertMessage.trim()),"Expected Alert is not displayed; The Expected : "+vitalsTestData.alertMessage+" ;The Actual   :  "+alertText + "; More Details :" + vitalsTestData.toString());
			}else if (vitalsTestData.alert.equalsIgnoreCase("No")){
				Assert.assertFalse(alertText!=null && !alertText.equals(""),"An unxpected Alert is displayed with message ("+ alertText +"); More Details :" + vitalsTestData.toString());
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + vitalsTestData.toString());
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

package com.clinical.selenium.section.charts.chartsGeneral;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.thoughtworks.selenium.Selenium;

public class SearchByKeywords extends AbstractChartsTest {

	public final Hashtable<String, String> searchKeywords = new Hashtable<String, String>();
	public final Hashtable<String, String> searchKeywordsDisplayNames = new Hashtable<String, String>();

	/**
	 * @Function 	: searchByKeyword
	 * @Description : Function to perform search operation with a keyword and an input search value in New Clinical Site
	 * @param 		: selenium
	 * @param 		: keyword
	 * @param		: searchValue
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 13, 2010
	 */

	public boolean searchByKeyword(Selenium selenium, String keyword, String searchValue){


		boolean isPresent = true;

		try {	

			searchKeywords.put("headerSearchMagnifier", "searchActionButton");
			searchKeywords.put("Patients", "searchPatient");
			searchKeywords.put("Appointments", "searchAppointment");
			searchKeywords.put("Tasks", "headerSearchTasks");
			searchKeywords.put("Messages", "headerSearchMessages");
			searchKeywords.put("All", "searchAll");

			searchKeywordsDisplayNames.put("Patients", "Search patients...");
			searchKeywordsDisplayNames.put("Appointments", "Search appoitments...");
			searchKeywordsDisplayNames.put("Tasks", "Search by tasks");
			searchKeywordsDisplayNames.put("Messages", "Search by messages");
			searchKeywordsDisplayNames.put("All", "Search all...");

			selenium.isElementPresent(searchKeywords.get("headerSearchMagnifier"));
			click(selenium,searchKeywords.get("headerSearchMagnifier"));
			selenium.isElementPresent(searchKeywords.get(keyword));
			click(selenium, searchKeywords.get(keyword));		
			waitForElement(selenium, "headerSearchInput", 5000);		

			selenium.type("headerSearchInput",searchValue);
						
			selenium.click("headerMagnifierButton");

						
			waitForPageLoad(selenium);

		} catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
			return false;
		}


		return isPresent;
	}


	/**
	 * @Function 	: searchByPatientID
	 * @Description : Function to search for patient by ID in New Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 13, 2010
	 */
	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"  }, description = "Claims")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void searchByPatientID(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {
	
		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";

		String searchKeyword = null;
		String searchValue = null;		
		boolean returnedValue = false;
		Selenium selenium = null;
		try{
		
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
			
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session");
			loginFromPublicSite(selenium, userAccount,userName,userPassword);

			//----------------------------------------------//
			//  Step-2:  Search by Patient ID             //
			//----------------------------------------------//

			searchKeyword = "Patients";
			searchValue = "6971";			

			returnedValue = searchByKeyword(selenium, searchKeyword, searchValue);

			Assert.assertTrue(returnedValue,"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");				
			String text = selenium.getText("//h1[@id='pageTitleSearch']/div");
			Assert.assertTrue(text.contains("Patients"),"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");
		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
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

	/**
	 * @Function 	: searchByAppointments
	 * @Description : Function to perform search by Appointments in New Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 13, 2010
	 */
	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"  }, description = "Claims")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void searchByAppointments(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {

		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";

		String searchKeyword = null;
		String searchValue = null;		
		boolean returnedValue = false;
		Selenium selenium = null;
		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session");
			loginFromPublicSite(selenium, userAccount,userName,userPassword);

			//----------------------------------------------//
			//  Step-2:  Search by Appointments             //
			//----------------------------------------------//

			searchKeyword = "Appointments";
			searchValue = "Kousar";			

			returnedValue = searchByKeyword(selenium, searchKeyword, searchValue);
			Assert.assertTrue(returnedValue,"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");				
			String text = selenium.getText("//h1[@id='pageTitleSearch']/div");
			Assert.assertTrue(text.contains("Appointments"),"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");

		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
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

	/**
	 * @Function 	: searchForAll
	 * @Description : Function to perform search by All in New Clinical Site
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 13, 2010
	 */
	@Test(groups = { "Claims", "firefox", "iexplore", "safari", "default"  }, description = "Claims")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void searchForAll(String seleniumHost, int seleniumPort,String browser, String webSite) throws Exception {

		String userAccount = "UA0";
		String userName = "ASPTEST";
		String userPassword = "1234321";
		Selenium selenium = null;
		String searchKeyword = null;
		String searchValue = null;		
		boolean returnedValue = false;

		try{
			//----------------------------------------------//
			//  Step-1:  Login to the Clinical Site         //
			//----------------------------------------------//
			selenium = getSession(seleniumHost, seleniumPort, browser, webSite);
			Assert.assertNotNull(selenium,"Could Not Retrive the New Selenium Session");
			loginFromPublicSite(selenium, userAccount,userName,userPassword);
			//----------------------------------------------//
			//  Step-6:  Search for All                     //
			//----------------------------------------------//
			searchKeyword = "All";
			searchValue = "Kousar";			
			returnedValue = searchByKeyword(selenium, searchKeyword, searchValue);
			Assert.assertTrue(returnedValue,"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");				
			String text = selenium.getText("//h1[@id='pageTitleSearch']/div");
			Assert.assertTrue(text.contains("Appointments"),"Search by "+searchKeyword+" with value : "+searchValue+" - Failed");


		}
		catch (RuntimeException e) {
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage());
			e.printStackTrace();			

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
package com.clinical.selenium.genericlibrary.global;
import static com.clinical.selenium.genericlibrary.global.ThreadSafeSeleniumSessionStorage.session;
import static com.clinical.selenium.genericlibrary.global.ThreadSafeSeleniumSessionStorage.startSeleniumSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;
import com.thoughtworks.selenium.Wait;

public class AbstractTest {
	public final static  ArrayList<Selenium> seleniumSessions = new ArrayList<Selenium>();
	public int WAIT_TIME = fetchProperty("clinical", "waittime").trim().equals("") ? 0 : Integer.parseInt(fetchProperty("clinical", "waittime").trim());
	public String pauseTime = fetchProperty("clinical", "pausetime").trim().equals("") ? "0" : fetchProperty("clinical", "pausetime").trim();

	public Selenium getSession(String seleniumHost, int seleniumPort,String browser, String webSite){
		if(session()== null){
			startSeleniumSession(seleniumHost, seleniumPort, browser, webSite);
			seleniumSessions.add(session());
			session().setTimeout("210000");
			session().windowMaximize();
		}
		try{
			session().deleteAllVisibleCookies();
			session().open("/");
			session().waitForPageToLoad("130000");
		}catch(SeleniumException e){
			try {
				session().stop();
			} catch (RuntimeException e1) {

			}
			startSeleniumSession(seleniumHost, seleniumPort, browser, webSite);
			seleniumSessions.add(session());
			session().setTimeout("210000");
			session().windowMaximize();
			session().deleteAllVisibleCookies();
			session().open("/");
			session().waitForPageToLoad("130000");
		}

		return session();

	}

	protected Selenium initiateSeleniumConnection(String seleniumHost, int seleniumPort,String browser,String webSite){
		startSeleniumSession(seleniumHost, seleniumPort, browser, webSite);
		Selenium selenium = session();
		selenium.setTimeout("180000");
		selenium.windowMaximize();
		return selenium;
	}


	public void searchPatient(Selenium selenium,String patientID){	
		click(selenium,"searchActionButton");
		click(selenium,"searchPatient");
		waitForElement(selenium, "headerSearchInput",10000);
		type(selenium,"headerSearchInput", patientID);
		selenium.click("headerMagnifierButton");
		waitForPageLoad(selenium);
		waitForElement(selenium, "cellPatientId"+patientID,10000);
		Assert.assertTrue(selenium.isElementPresent("cellPatientId"+patientID),"Search Results are not displayed for the patient with ID :- "+ patientID);
		click(selenium,"cellPatientId"+patientID);
		waitForPageLoad(selenium);
	}

	/**
	 * @Function 	: waitForElement
	 * @Description : Function for waiting for the AJAX element load or until the timeOut value    
	 * @param 		: selinum
	 * @param 		: ajaxElementName (Name of the ajax element)
	 * @param		: timeOutValue (The time out value until which the selenium can wait for the element to load)
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 24, 2010
	 */
	public boolean waitForElement(final Selenium selenium, final String ajaxElementName, int timeOutValue ){
		try{
			new Wait()
			{
				public boolean until()
				{
					return selenium.isElementPresent(ajaxElementName);
				}
			}.wait("The Element '" + ajaxElementName + "' did not appear  within "
					+ timeOutValue + " ms.", timeOutValue);
			return true;
		}catch(RuntimeException e){
			return false;	
		}

	}

	/**
	 * @Function 	: isElementPresent
	 * @Description : Function to verify if the AJAX based element is present     
	 * @param 		: selinum
	 * @param 		: ajaxElementName (Name of the ajax element)
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 08, 2010
	 */
	public boolean isElementPresent(Selenium selenium, String ajaxElementName, int wait){

		ajaxElementName = ajaxElementName != null ? ajaxElementName.trim():"";
		if(waitForElement(selenium, ajaxElementName, WAIT_TIME)){
			return true;
		}else{
			return false;
		}
	}

	public boolean isElementPresent(Selenium selenium,String ajaxElementName) {
		boolean present = selenium.isElementPresent(ajaxElementName);
		int wait = WAIT_TIME;
		ajaxElementName = ajaxElementName != null ? ajaxElementName.trim():"";
		while(!present && wait > 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wait -= 500;
			present = selenium.isElementPresent(ajaxElementName);
		}
		return present;
	}

	/**
	 * @Function 	: isChecked
	 * @Description : Function to verify if the AJAX based Checkbox is checked     
	 * @param 		: selinum
	 * @param 		: ajaxElementName (Name of the ajax Checkbox)
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 17, 2010
	 */
	public boolean isChecked(Selenium selenium, String ajaxCheckboxName){

		ajaxCheckboxName = ajaxCheckboxName != null ? ajaxCheckboxName.trim():"";
		if(waitForElement(selenium, ajaxCheckboxName, WAIT_TIME)){
			if(selenium.isChecked(ajaxCheckboxName)){
				return true;	
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: waitForValue
	 * @Description : Function for waiting for value for the AJAX element or until the timeOut value    
	 * @param 		: selinum
	 * @param 		: ajaxElementName (Name of the ajax element)
	 * @param		: timeOutValue (The time out value until which the selenium can wait for the value in the element) 
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 24, 2010
	 */
	public boolean waitForValue(Selenium selenium, String ajaxElementName, int timeOutValue ){

		boolean isPresent = false;
		timeOutValue = timeOutValue/100;
		ajaxElementName = ajaxElementName != null ? ajaxElementName.trim():"";

		try{
			if(ajaxElementName.trim().equals(""))
			{
				return isPresent;
			}else if(!waitForElement(selenium, ajaxElementName, timeOutValue)){
				return isPresent;
			}

			for(int i = 0; i<timeOutValue; i++ ){
				if (!selenium.getText(ajaxElementName).trim().equals("")){
					isPresent = true;
					break;
				}else{
					Thread.sleep(100);
					continue;
				}
			}
			return isPresent;
		}catch (RuntimeException e) {
			return isPresent;
		} catch (InterruptedException e) {
			return isPresent;
		}
	}

	/**
	 * @Function 	: waitForListToLoad
	 * @Description : Function for waiting for List to Load for the AJAX element or until the timeOut value    
	 * @param 		: selinum
	 * @param 		: listName (Name of the List element)
	 * @param		: timeOutValue (The time out value until which the selenium can wait for the value in the element) 
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */
	public boolean waitForListToLoad(Selenium selenium, String listName, int timeOutValue ){

		boolean isPresent = false;
		timeOutValue = timeOutValue/100;
		listName = listName != null ? listName.trim():"";

		try{
			if(listName.trim().equals("") || waitForElement(selenium, listName, timeOutValue))
			{
				return isPresent;
			}
			if (waitForValue(selenium, listName, WAIT_TIME)){
				isPresent = true;
			}
			return isPresent;
		}catch (RuntimeException e) {
			return isPresent;
		}
	}

	/**
	 * @Function 	: loginFromPublicSite
	 * @Description : This function will login to Clinical Site
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param 		: userAccount
	 * @param 		: userName
	 * @param 		: userPassword
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 29, 2010
	 */
	public boolean loginFromPublicSite(Selenium selenium, String userAccount, String userName, String userPassword){		
		if(!waitForElement(selenium, "loginAccountText", 30000)){
			Assert.fail("Login Page Not loaded More Details; UserAccount :- "+userAccount +"  UserName :- " +userName+ " UserPassword :- "+ userPassword);
		}
		type(selenium, "loginAccountText", userAccount);
		type(selenium, "loginUseridText", userName);
		type(selenium, "loginPasswordText", userPassword);
		click(selenium, "loginButton");
		if(waitForElement(selenium, "headerClinicalMenu", WAIT_TIME) && selenium.isTextPresent("Sign out")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: MaximizeWindow
	 * @Description : This function will Focus and Maximize the current Firefox Window
	 * @param 		: selenium (an instance of current selenium browser )
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 29, 2010
	 */
	public void maximizeWindow(Selenium selenium){
		selenium.windowFocus();
		selenium.windowMaximize();
	}

	/**
	 * @Function 	: type
	 * @Description : This function will type a value into a TextBox / Text Area after waiting for the mentioned WAIT TIME
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: textboxName (Name of the Text Box / Text Area)
	 * @param       : valueForEntry (The value that must be entered in to the TextBox / TextArea)  
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 30, 2010
	 */
	public boolean type(Selenium selenium, String textboxName, String valueForEntry){
		textboxName = textboxName != null ? textboxName.trim() : "";
		valueForEntry = valueForEntry != null ? valueForEntry.trim() : "";
		if(!textboxName.equals("") && waitForElement(selenium, textboxName, WAIT_TIME) && selenium.isVisible(textboxName)){
			selenium.type(textboxName, valueForEntry);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: enterDate
	 * @Description : This function will enter a date value into a TextBox after waiting for the mentioned WAIT TIME
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: dateField (Name of the Date Text Box)
	 * @param       : valueForEntry (The value that must be entered in to the TextBox)  
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 10, 2010
	 */
	public boolean enterDate(Selenium selenium, String dateField, String valueForEntry){
		dateField = dateField != null ? dateField.trim() : "";
		valueForEntry = valueForEntry != null ? valueForEntry.trim() : "";
		if(!dateField.equals("") && waitForElement(selenium, dateField, WAIT_TIME) && selenium.isVisible(dateField)){
			selenium.type(dateField, valueForEntry);
			selenium.keyDown(dateField,  "\\9");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: select
	 * @Description : This function will select a value from a list after waiting for the mentioned WAIT TIME
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: listName (Name of the list)
	 * @param       : valueForSelection (The value that must be selected from list)  
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 01, 2010
	 */
	public boolean select(Selenium selenium, String listName, String valueForSelection){
		listName = listName != null ? listName.trim() : "";
		valueForSelection = valueForSelection != null ? valueForSelection.trim() : "";
		try{
			if(isElementPresent(selenium, listName) && selenium.isVisible(listName)){
				selenium.select(listName, "label="+ valueForSelection);
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Function 	: selectValueFromAjaxList
	 * @Description : This function will select a value from an Ajaz based list after waiting for the mentioned WAIT TIME
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: textboxName (Name of the list)
	 * @param       : valueForSelection (The value that must be selected from list)
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 22, 2010
	 */
	public boolean selectValueFromAjaxList(Selenium selenium, String textboxName, String valueForSelection){
		textboxName = textboxName != null ? textboxName.trim() : "";
		valueForSelection = valueForSelection != null ? valueForSelection.trim() : "";

		try{
			if(!textboxName.equals("") && !valueForSelection.equals("") && waitForElement(selenium, textboxName, WAIT_TIME)){
				selenium.focus(textboxName);
				selenium.type(textboxName,valueForSelection);
				selenium.fireEvent(textboxName,"keydown");
				selenium.fireEvent(textboxName,"keypress");                           
				selenium.fireEvent(textboxName,"keyup");
				waitForPageLoad(selenium);
				if(waitForElement(selenium,"//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]",WAIT_TIME)){
					selenium.click("//td[@class='suggestPopupMiddleCenter']/div/div/table/tbody/tr[1]/td[1]");
				}
				return true;
			}

		}
		catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("selectValueFromAjaxList:" + textboxName + "," +valueForSelection,e);
		}
		return false;
	}

	/**
	 * @Function 	: selectValueFromAjaxList
	 * @Description : This function will select a value from an Ajaz based list after waiting for the mentioned WAIT TIME
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: textboxName (Name of the list)
	 * @param       : valueForSelection (The value that must be selected from list)
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 22, 2010
	 */
	public boolean selectFirstMatchingValueFromAjaxList(Selenium selenium, String textboxName, String valueForSelection){
		textboxName = textboxName != null ? textboxName.trim() : "";
		valueForSelection = valueForSelection != null ? valueForSelection.trim() : "";

		try{
			if(!textboxName.equals("") && !valueForSelection.equals("") && waitForElement(selenium, textboxName, WAIT_TIME)){
				selenium.type(textboxName, valueForSelection);
				waitForElement(selenium, textboxName, 200);
				selenium.keyDown(textboxName, "\\13");
				return true;
			}else{
				return false;
			}
		}catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 * @Function 	: click
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then click
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be clicked)
	 * @Author 		: Aspire QA
	 * @Created on 	: Mar 30, 2010
	 */
	public boolean click(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";
		if(!elementName.equals("") && waitForElement(selenium, elementName, WAIT_TIME)){
			selenium.click(elementName);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: check
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then select a check box
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be checked)
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 18, 2010
	 */
	public boolean check(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";
		if(!elementName.equals("")&& waitForElement(selenium, elementName, WAIT_TIME)){
			selenium.check(elementName);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: uncheck
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then uncheck a check box
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be unchecked)
	 * @Author 		: Aspire QA
	 * @Created on 	: Jun 18, 2010
	 */
	public boolean uncheck(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";
		if(!elementName.equalsIgnoreCase("") && waitForElement(selenium, elementName, WAIT_TIME)){
			selenium.uncheck(elementName);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @Function 	: getText
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then Get the Text
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be clicked)
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */
	public String getText(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";

		if(!elementName.equals("") && waitForElement(selenium, elementName, WAIT_TIME)){
			return selenium.getText(elementName);
		}else{
			return "";
		}
	}

	public String getMessage(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";

		if(selenium.isElementPresent(elementName) && selenium.isVisible(elementName)){
			return selenium.getText(elementName);
		}else{
			return "";
		}
	}

	/**
	 * @Function 	: getValue
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then Get the Value
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be clicked)
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */
	public String getValue(Selenium selenium, String elementName){
		elementName = elementName != null ? elementName.trim() : "";
		if(!elementName .equals("") && waitForElement(selenium, elementName, WAIT_TIME) && selenium.isVisible(elementName)){
			return selenium.getValue(elementName);
		}else{
			return "";
		}
	}


	/**
	 * @Function 	: getSelectedValue
	 * @Description : This function will wait for the element for the mentioned WAIT TIME and then Get the selected value
	 * @param 		: selenium (an instance of current selenium browser )
	 * @param		: elementName (Name of the element which needs to be clicked)
	 * @Author 		: Aspire QA
	 * @Created on 	: May 03, 2010
	 */
	public String getSelectedValue(Selenium selenium, String listName){
		listName = listName != null ? listName.trim() : "";
		try{
			if(!listName.equals("") && waitForElement(selenium, listName, WAIT_TIME) && selenium.isVisible(listName)){
				return selenium.getSelectedLabel(listName);
			}else{
				return "";
			}
		}catch (RuntimeException e) {
			return "";
		}
	}



	/**
	 * @Function 	: generateCharacters
	 * @Description : Function is used to generate a series of unique characters / uniqe strings again and again
	 * @param 		: stringLength - Length of the character string to be generated 
	 * @param 		: textTo Repeat - Text / Characters that must be repeated in the string
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 05, 2010
	 */
	public String generateCharacters(int stringLength, String textToRepeat){

		textToRepeat = textToRepeat != null ? textToRepeat.trim() : "";
		StringBuffer tempBuffer = new StringBuffer();
		if(stringLength > 0 && !textToRepeat.trim().equalsIgnoreCase("")){
			while (stringLength >0){
				tempBuffer.append(textToRepeat);
				stringLength--;
			}
			return tempBuffer.toString().trim();
		}else{
			return "";
		}
	}

	/**
	 * @Function 	: fetchProperty
	 * @Description : Function is used to fetch the property from a property file
	 * @param		: fileName - Name of the Property File Name 
	 * @param 		: propertyKey - Key using which the value needs to be searched for
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 01, 2010
	 */
	public String fetchProperty(String fileName, String propertyKey){		
		int check = 0;
		String propertyValue = "";
		FileInputStream in =null;
		fileName = fileName != null ? fileName.trim():"";
		propertyKey = propertyKey != null ? propertyKey.trim():"";

		if(fileName.equals("") && propertyKey.equals("")){
			return propertyValue;
		}else if(fileName.equals("")){
			return propertyValue;
		}else if(propertyKey.equals("")){
			return propertyValue;
		}else{
			if(!fileName.contains(".properties")){
				fileName = fileName+ ".properties";
			}
		
			try{
				while(check == 0){
					check = 1;
					File file = new File(fileName);
					if(file.exists()){
						Properties properties = new Properties();
						in = new FileInputStream(file);
						properties.load(in);
						propertyValue = properties.getProperty(propertyKey);
					}
					else{
						check = 0;
						break;
					}
				}
				in.close();
			}
			catch(IOException e){
				try {
					in.close();
				} catch (IOException e1) {
					
				}
			}
			finally{
				try {
					in.close();
				} catch (IOException e) {
					
				}
			}
		}
		return propertyValue;
	}

	/**
	 * @Function 	: waitForPageLoad
	 * @Description : Function is used to wait a page to load completely 
	 * @param		: selenium 
	 * @return		: boolean
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 27, 2010
	 */

	public boolean waitForPageLoad1(Selenium selenium) {

		boolean isPresent = false;
		try {
			Thread.sleep(2000);
			for(int i =1 ; i<= 60; i++){				
				if(selenium.isElementPresent("//img[@src='images/loading2.gif']")){
					isPresent = true;
					Thread.sleep(1000);
					continue;
				}else if (i<=60 && !selenium.isElementPresent("//img[@src='images/loading2.gif']")) {					
					isPresent = false;
					break;
				}else if (i>60 &&selenium.isElementPresent("//img[@src='images/loading2.gif']")) {
					isPresent = true;
					break;
				}
			}
			if(isPresent){
				return false;				
			}else {
				return true;
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean waitForPageLoad(Selenium selenium) {
		try {
			int i = 0;
			try {
				while(!selenium.isElementPresent("//img[@src='images/loading2.gif']") && i<20){
					Thread.sleep(100);
					i++;
				}
				int j = 0;
				while(selenium.isElementPresent("//img[@src='images/loading2.gif']") && j<400){
					Thread.sleep(100);
					j++;
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}



	public static void waitForAjaxRequestDone(final Selenium selenium,
			final String timeout)
	{
		selenium.waitForCondition(
				"selenium.browserbot.getCurrentWindow().jQuery.active == 0",
				timeout);
	}

	/**
	 * @Function 	: waitForElementToEnable
	 * @Description : Function is used to wait until the elemnt is enabled 
	 * @param		: selenium 
	 * @return		: boolean
	 * @Author 		: Aspire QA
	 * @Created on 	: May 05, 2010
	 */	
	public boolean waitForElementToEnable(Selenium selenium, String elementName){
		boolean isEditable = false;
		try {
			for(int i=1; i<=120;i++){
				if(!selenium.isEditable(elementName)){
					Thread.sleep(1000);
					continue;
				}else {
					isEditable = true;
					break;
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return isEditable;
	}

	public String getCurrentlyExecutingMethodName() {
		Throwable t = new Throwable(); 
		StackTraceElement[] elements = t.getStackTrace(); 
		if (elements.length <= 0) return "[No Stack Information Available]";
		if (elements.length < 2) return null;
		return elements[1].getMethodName();
	}
	public String getListCount(String listCount){
		listCount = listCount.substring(listCount.indexOf("(")+1,listCount.indexOf(")"));
		return listCount;
	}

	@AfterSuite
	public static void closeSessions(){
		try {
			for(int counter = 0;counter< seleniumSessions.size(); counter ++){
				if (null != seleniumSessions.get(counter)) {
					seleniumSessions.get(counter).stop();
				}
			}
		} catch (Exception e) {
			// Exception will occur while trying to close the crashed session. So no need to make it failed  
			e.printStackTrace();
		}
	}

	public Collection<String> getDataBaseIDs(Selenium selenium,String locator){

		Collection<String> firstList = new ArrayList<String>();
		int xpathCounter = (Integer) selenium.getXpathCount("//a[starts-with(@id, '"+locator+"')]");
		for(int i=0;i<xpathCounter;i++){
			// Replaced the XPATH with CSS path to increase the search speed
			firstList.add(selenium.getAttribute("css=a[id*="+locator+"]:nth("+i+")@id"));	
		}
		return firstList;

	}
	
	//New Method to get dynamic ids and avoid the looping in the existing method as well as reduce the selenium communication , Not yet started to use
	public Collection<String> getDataBaseIDs_New(Selenium selenium,String locator){
		Collection<String> firstList = new ArrayList<String>();
		String allLinkIds = Arrays.asList(selenium.getAllLinks()).toString(); 
		System.out.println("All link Ids  : " + allLinkIds);
		String dbIds[] = allLinkIds.substring(allLinkIds.indexOf(locator), allLinkIds.indexOf(", ", allLinkIds.lastIndexOf(locator))).split(", ");
		
		firstList.addAll(Arrays.asList(dbIds));
		
		System.out.println(" Val  : "+ dbIds.length);
		return firstList;//(Collection<String>)Arrays.asList(dbIds);

	}


}

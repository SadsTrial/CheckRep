package com.clinical.selenium.section.charts.Visit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clinical.selenium.genericlibrary.charts.AbstractChartsTest;
import com.clinical.selenium.genericlibrary.charts.ChartsLib;
import com.thoughtworks.selenium.Selenium;

public class VisitValidator extends AbstractChartsTest {

	private String valCondition[]=new String[100];
	private String valAlergy[]=new String[100];
	private String valMedication[]=new String[100];

	private int conditionCounter=0;
	private int alergyCounter=0;
	private int medicationCounter=0;

	private String valVisitCondition[]=new String[100];
	private String valVisitAlergy[]=new String[100];
	private String valVisitMedication[]=new String[100];

	private int valVisitConditionCounter=0;
	private int valVisitAlergyCounter=0;
	private int valVisitMedicationCounter=0;

	private Boolean conditonsStatus;
	private Boolean alergyStatus;
	private Boolean medicationStatus;

	private int commCounter = 0;

	@Test(groups = {"firefox", "iexplore", "safari", "default"  }, description = "Test for creating a New Visit")
	@Parameters( { "seleniumHost", "seleniumPort", "browser", "webSite"})
	public void visitValidate(String seleniumHost, int seleniumPort,String browser, String webSite){
		ChartsLib visitTestData = new ChartsLib();
		visitTestData.workSheetName = "VisitValidate";
		visitTestData.testCaseId = "TC_VV_001";
		visitTestData.fetchChartsTestData();
		validateVisitContent(seleniumHost, seleniumPort, browser, webSite, visitTestData);
	}

	/**
	 * @Function 	: validateVisitContent
	 * @Description : Function to verify various links in create new Visit Page
	 * @param 		: selinumHost
	 * @param		: seleninumPort
	 * @param 		: browser
	 * @param		: website
	 * @Author 		: Aspire QA
	 * @Created on 	: Jul 26, 2010
	 */
	public void validateVisitContent(String seleniumHost, int seleniumPort,String browser, String webSite, ChartsLib visitTestData){
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
			//  Step-2: Verifying the various details in create new Visit Page    //
			//--------------------------------------------------------------------//

			click(selenium,"conditions");
			waitForPageLoad(selenium);
			valCondition = getValues(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			conditionCounter=commCounter;
			click(selenium,"allergies");
			valAlergy = getValues(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			alergyCounter = commCounter;
			click(selenium,"medications");
			valMedication = getValues(selenium,"//div[@id='patientMedicationList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			medicationCounter = commCounter;

			click(selenium,"visits");
			waitForPageLoad(selenium);
			
			click(selenium,"visitsAdd");
			waitForPageLoad(selenium);
			
			commCounter=0;
			valVisitCondition =getValues(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			valVisitConditionCounter=commCounter;
			commCounter=0;
			valVisitAlergy =getValues(selenium,"//div[@id='patientAllergyList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			valVisitAlergyCounter=commCounter;
			commCounter=0;
			valVisitMedication =getValues(selenium,"//div[@id='patientMedicationList']/table/tbody[1]/tr[","]/td[1]/div/strong/a"); //til.getText(selenium,"//div[@id='patientConditionList']/table/tbody[1]/tr["+conditionCounter+"]/td[1]");
			valVisitMedicationCounter=commCounter;
			commCounter=0;
						
			if(conditionCounter==valVisitConditionCounter){
				if(conditionCounter>1){
					conditonsStatus=checkValues(valCondition,  valVisitCondition, conditionCounter);
				}else{
					conditonsStatus=true;	
				}
			}else{
				conditonsStatus=false;
			}

			if(alergyCounter==valVisitAlergyCounter){
				if(alergyCounter>1){
					alergyStatus=checkValues(valAlergy, valVisitAlergy, alergyCounter);
				}else{
					alergyStatus=true;	
				}
			}else{
				alergyStatus=false;
			}


			if(medicationCounter==valVisitMedicationCounter){
				if(medicationCounter>1){
					medicationStatus=checkValues(valMedication, valVisitMedication,medicationCounter);
				}else{
					medicationStatus=true;
				}
			}else{
				medicationStatus=false;	
			}

			Assert.assertTrue(conditonsStatus,"Validate the Visit : Conditions are not Matched; validation Failed; Detailed data:" + visitTestData.toString());
			Assert.assertTrue(alergyStatus,"Validate the Visit : Alergies are not Matched; validation Failed; Detailed data:" + visitTestData.toString());
			Assert.assertTrue(medicationStatus,"Validate the Visit :  SocialHistory are not Matched; validation Failed; Detailed data:" + visitTestData.toString());

		}catch(RuntimeException e){
			Assert.fail("Failed Due to the Exception; \n\t*) ExceptionDetails :"+e.getMessage()+"\n\t*); Detailed data:" + visitTestData.toString());	
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

	public Boolean checkValues(String[] temp1, String[] temp2, int tmpCounter)
	{
		Boolean Status=false;
		for(int i=0;i<tmpCounter-1;i++)
		{	if(temp1[i].trim().replaceAll(" ","").equalsIgnoreCase(temp2[i].replaceAll(" ",""))){
			Status=true;
		}else{
			Status=false;
			break;
		}
		}
		return Status;
	}

	public String[] getValues(Selenium sel,String Element1,String Element2)
	{
		String tempArray[]=new String[100];
		Boolean status=true;
		for(commCounter=0;status;commCounter++){
			tempArray[commCounter] = getText(sel,Element1+(commCounter+1)+Element2);
			if(tempArray[commCounter].length()==0){
				status=false;
			}
		}	
		return tempArray;
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

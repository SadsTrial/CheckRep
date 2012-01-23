package com.clinical.selenium.genericlibrary.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;

import com.clinical.selenium.genericlibrary.global.AbstractTest;
import com.thoughtworks.selenium.Selenium;

public class AbstractAuditTest extends AbstractTest {

	public void createAdvanceDirective(Selenium selenium, AuditLib directiveTestData){

		String allDirectiveCount = null;
		String activeDirectiveCount = null;
		try {
			click(selenium,"advDirectives");
			waitForPageLoad(selenium);

			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "AllDirectives", 120000), "Could not capture existing Advance Directives[All] Count; More Details" + directiveTestData.toString() );
			allDirectiveCount = getListCount(selenium.getText("AllDirectives"));

			Assert.assertTrue(waitForValue(selenium, "CurrentDirectives", 120000),"Could not capture existing Advance Directives[Active] Count; More Details" + directiveTestData.toString());
			activeDirectiveCount = getListCount(selenium.getText("CurrentDirectives"));

			Assert.assertTrue(waitForElement(selenium, "addAdvDirective", 10000),"Could not find Add Advance Directives Link; More Details" + directiveTestData.toString());

			click(selenium,"addAdvDirective");
			waitForPageLoad(selenium);			

			Assert.assertTrue(select(selenium, "//div[@id='patAdvDirectiveTypeCodeInput']/select", directiveTestData.directiveType), "Could not select Advance Directives For; More Details" + directiveTestData.toString());
			Assert.assertTrue(select(selenium,"//div[@id='patAdvDirectiveValueCodeInput']/select", directiveTestData.directiveName),"Could not select Directive Name; More Details" + directiveTestData.toString());
			Assert.assertTrue(select(selenium, "//div[@id='statusCodeInput']/select", directiveTestData.directiveStatus),"Could not select Directive Status; More Details" + directiveTestData.toString());
			Assert.assertTrue(enterDate(selenium, "startDateInput", directiveTestData.directiveStartDate),"Could not enter Directive Start Date; More Details" + directiveTestData.toString() );
			Assert.assertTrue(enterDate(selenium, "endDateInput", directiveTestData.directiveEndDate),"Could not enter Directive End Date; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianLastNameInput", directiveTestData.custodianLastName),"Could not enter Custodian Last Name; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianFirstNameInput", directiveTestData.custodianFirstName),"Could not enter Custodian First Name; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianOrganizationInput", directiveTestData.custodianOrganization),"Could not enter Custodian Organization; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium, "documentCustodianAddr1Input", directiveTestData.custodianAddress1),"Could not enter Custodian Address 1; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianAddr2Input", directiveTestData.custodianAddress2),"Could not enter Custodian Address 2; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianCityInput", directiveTestData.custodianCity),"Could not enter Custodian City; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianStateInput", directiveTestData.custodianState),"Could not enter Custodian State; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianZipInput", directiveTestData.custodianZIP),"Could not enter Custodian ZIP; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianTelNumInput", directiveTestData.custodianTel),"Could not enter Custodian Tel; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianExtNumInput", directiveTestData.custodianExt),"Could not enter Custodian Ext; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianMobileNumInput", directiveTestData.custodianMobile),"Could not enter Custodian Mobile; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianFaxNumInput", directiveTestData.custodianFax),"Could not enter Custodian Fax; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"documentCustodianEmailInput", directiveTestData.custodianEmail),"Could not enter Custodian Email; More Details" + directiveTestData.toString());
			Assert.assertTrue(type(selenium,"notesInput", directiveTestData.custodianNote),"Could not enter Custodian Note; More Details" + directiveTestData.toString());

			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link; More Details" + directiveTestData.toString());
			waitForPageLoad(selenium);	

			Assert.assertTrue(select(selenium, "workStatusInput", directiveTestData.taskName),"Could not select Work Status; More Details" + directiveTestData.toString());
			Assert.assertTrue(select(selenium, "taskUsersInput", directiveTestData.sendTaskTo),"Could not select Send To Task");
			Assert.assertTrue(type(selenium,"taskNotesInput", directiveTestData.taskNotes),"Could not enter Task Notes; More Details" + directiveTestData.toString());

			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Validate Button; More Details" + directiveTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent()|| (selenium.isElementPresent("//p")))
			{
				Assert.assertTrue(selenium.isAlertPresent(),"An unexpected alert has occured : " + selenium.getAlert().trim());
				Assert.assertTrue( (selenium.isElementPresent("//p")),"An unexpected Alert Occured - " + selenium.getText("//p").trim());
			}

			click(selenium,"AllDirectives");
			waitForPageLoad(selenium);

			if(waitForValue(selenium, "CurrentDirectives", 120000)){
				Assert.assertTrue(directiveTestData.directiveEndDate.equals(""),"Could not capture Advance Directives[Active] Count after saving a Advance Directives; More Details" + directiveTestData.toString());
				Assert.assertTrue((Integer.parseInt(getListCount(selenium.getText("CurrentDirectives"))) == Integer.parseInt(activeDirectiveCount)+1),"The Advance Directive[Active] is not Saved Successfully, Advance Directives count has no change after adding a new Advance Directive; More Details" + directiveTestData.toString());
				Assert.assertFalse((Integer.parseInt(getListCount(selenium.getText("CurrentDirectives"))) == Integer.parseInt(activeDirectiveCount)),"The Advance Directive[Active] is not Saved Successfully, Advance Directives count has no change after adding a new Advance Directive; More Details" + directiveTestData.toString());
			}

			Assert.assertTrue(waitForValue(selenium, "AllDirectives", 120000),"Could not capture Advance Directives[All] Count after saving a Advance Directives; More Details" + directiveTestData.toString());
			Assert.assertTrue((Integer.parseInt(getListCount(selenium.getText("AllDirectives"))) == Integer.parseInt(allDirectiveCount)+1),"The Advance Directive is not Saved Successfully, Advance Directives[All] count has no change after adding a new Advance Directive; More Details" + directiveTestData.toString());

		}
		catch (RuntimeException e){
			e.printStackTrace();
			Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_" + directiveTestData.testCaseId + ".log' for more info!; More Details" + directiveTestData.toString());
		}	

	}
	public void createMedication(Selenium selenium, AuditLib medicationTestData ){

		String allMedicationCount = null;
		String activeMedicationCount = null;

		Assert.assertTrue(click(selenium,"medications"),"Could Not click the New Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isElementPresent("AllMedications")){
			Assert.assertTrue(click(selenium,"AllMedications"),"Could Not Click All Medication Link; More Details" + medicationTestData.toString());
			waitForPageLoad(selenium);
		}

		Assert.assertTrue(waitForValue(selenium, "AllMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
		allMedicationCount = getListCount(getText(selenium,"AllMedications"));

		Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture existing Medication[All] Count; More Details" + medicationTestData.toString());
		activeMedicationCount = getListCount(getText(selenium,"CurrentMedications"));

		Assert.assertTrue(waitForElement(selenium, "addMedication", 10000),"Could not find Add Medication Link; More Details" + medicationTestData.toString());

		Assert.assertTrue(click(selenium,"addMedication"),"Could not Click Add Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForElement(selenium, "medicationBoxBox", 7000),"Medication Box is not loaded; More Details" + medicationTestData.toString());
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationBoxBox", medicationTestData.medicationName),"Could not enter Medication Name; More Details" + medicationTestData.toString());
		Assert.assertTrue(enterDate(selenium,"startdateInput", medicationTestData.startDate),"Could not enter Start Date; More Details" + medicationTestData.toString());

		if (medicationTestData.endDate != null && !medicationTestData.endDate.equals("")){
			Assert.assertTrue(enterDate(selenium,"enddateInput", medicationTestData.endDate),"Could not enter End Date; More Details" + medicationTestData.toString());
		}

		Assert.assertTrue(type(selenium,"notesInput", medicationTestData.medicationNote),"Could not enter Medication Notes; More Details" + medicationTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", medicationTestData.status),"Could not select Medication Status; More Details" + medicationTestData.toString());
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

		if(!selenium.isVisible("workStatusInput")){
			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + medicationTestData.toString());
			waitForPageLoad(selenium);
		}

		Assert.assertTrue(select(selenium,"workStatusInput", medicationTestData.taskName),"Could not select Medication work status; More Details" + medicationTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", medicationTestData.sendTaskTo),"Could not select Medication send task to; More Details" + medicationTestData.toString());

		if(!waitForElementToEnable(selenium, "saveButton")){
			Assert.assertFalse(selenium.getText("medicationBoxLabel").trim().equals("")|| selenium.getText("medicationBoxLabel").trim()== null,"Medication Name Not properly loaded; Please Check the Medication Name" );
			Assert.fail("Save button is not enabled; More Details" + medicationTestData.toString());
		}

		Assert.assertTrue(click(selenium,"saveButton"),"Could not click Save Button; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Medication not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		Assert.assertTrue(click(selenium,"medications"),"Could Not click the  Medication Link; More Details" + medicationTestData.toString());
		waitForPageLoad(selenium);

		click(selenium,"AllMedications");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForElement(selenium, "AllMedications", 120000),"Could not capture Medication Count[All] after saving a Medication");
		Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"AllMedications"))) == Integer.parseInt(allMedicationCount)+1,"The Medication is not Saved Successfully, Medication[All] count has no change after adding a new Medication; More Details" + medicationTestData.toString());
		Assert.assertTrue(waitForValue(selenium, "CurrentMedications", 120000),"Could not capture Medication Count[Active] after saving a Medication; More Details" + medicationTestData.toString());

		if(!medicationTestData.status.trim().equalsIgnoreCase("inactive")){
			Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"CurrentMedications"))) == Integer.parseInt(activeMedicationCount)+1,"The Medication is not Saved Successfully, Medication count[Active] has no change after adding a new Medication; More Details" + medicationTestData.toString());
		}else{
			Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"CurrentMedications"))) == Integer.parseInt(activeMedicationCount),"The Medication is not Saved Successfully, Medication count[Active] has no change after adding a new Medication; More Details" + medicationTestData.toString());
		}
	}


	public void createPrescription(Selenium selenium, AuditLib prescriptionTestData){

		Assert.assertTrue(click(selenium,"addPrescription"),"Could not find Add Prescription Link; More Details" + prescriptionTestData.toString());
		waitForPageLoad(selenium);

		if(!isElementPresent(selenium, "providersInput") || !selenium.isVisible("providersInput") ){
			click(selenium,"editProvider");
			waitForPageLoad(selenium);
		}

		Assert.assertTrue(select(selenium, "providersInput", prescriptionTestData.providerName),"Could not select Provider Name; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium, "locationsInput", prescriptionTestData.providerLocation),"Could not select Provider Location; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(selectValueFromAjaxList(selenium,"medicationboxBox", prescriptionTestData.medicationName),"Could not enter Medication Name; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(type(selenium,"unitQtyInput", prescriptionTestData.unitQuantityInput),"Could not enter Unit Quantity Input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium, "quantityTypeInput", prescriptionTestData.quantityTypeInput),"Could not select Quantity type input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium, "frequencyInput", prescriptionTestData.frequencyInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium, "instructionInput", prescriptionTestData.instructionInput),"Could not select Frequency Input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(type(selenium,"ss_daysSupplyInput", prescriptionTestData.daySupplyInput),"Could not enter Day supply input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(type(selenium,"ss_refillsInput", prescriptionTestData.refills),"Could not enter Refills; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(type(selenium,"ss_quantityInput", prescriptionTestData.quantity),"Could not enter the Quantity input; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", prescriptionTestData.status),"Could not enter status; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(type(selenium,"commentsInput", prescriptionTestData.comments),"Could not enter Comments; More Details" + prescriptionTestData.toString());
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  - ; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium,"workStatusInput", prescriptionTestData.taskName),"Could not enter work status; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", prescriptionTestData.sendTaskTo),"Could not select Medication send task to; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(waitForElementToEnable(selenium, "validateButton"),"Unable to click 'Save' - Save button is not enabled; More Details" + prescriptionTestData.toString());
		Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details" + prescriptionTestData.toString());

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		click(selenium,"AllPrescriptions");
		waitForPageLoad(selenium);

	}


	public void createReferral(Selenium selenium, AuditLib referralTestData  ){

		String referralCount = null;

		Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture existing Referrals Count; More Details" + referralTestData.toString());
		referralCount = getListCount(selenium.getText("Referrals"));

		Assert.assertTrue(click(selenium,"referralsAdd"),"Could not find Add Referral Link; More Details" + referralTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(selectValueFromAjaxList(selenium,"providersBoxBox", referralTestData.providerName),"Could not enter Provider Name; More Details" + referralTestData.toString());
		Assert.assertTrue(enterDate(selenium,"referraldateInput", referralTestData.startDate),"Could not enter Date; More Details" + referralTestData.toString());
		Assert.assertTrue(type(selenium,"notesInput",referralTestData.providerNotes),"Could not enter Notes; More Details" + referralTestData.toString());
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());
		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + referralTestData.toString());
		Assert.assertTrue(select(selenium,"workStatusInput", referralTestData.taskName),"Could not select Work Status; More Details" + referralTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", referralTestData.sendTaskTo),"Could not select Send Task To; More Details" + referralTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", referralTestData.taskNotes),"Could not enter Task Notes; More Details" + referralTestData.toString());

		Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details" + referralTestData.toString());				
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Referral not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
		Assert.assertTrue(waitForValue(selenium, "Referrals", 120000),"Could not capture Referral Count after saving a Referral; More Details" + referralTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(getText(selenium,"Referrals"))) == Integer.parseInt(referralCount)+1,"The Referral is not Saved Successfully, Referral count has no change after adding a new Referral; More Details" + referralTestData.toString());
	}


	public void createVisit (Selenium selenium, AuditLib visitTestData ){

		Assert.assertTrue(click(selenium,"visitsAdd"),"Could not find (+) Add Visit Link; More Details" + visitTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(enterDate(selenium, "visitdateInput", visitTestData.visitDate),"Could not enter the test data for Visit Date; More Details" + visitTestData.toString());
		Assert.assertTrue(select(selenium, "providersInput", visitTestData.providerName),"Could not select the test data for Provider Name; More Details" + visitTestData.toString());
		Assert.assertTrue(select(selenium, "locationsInput", visitTestData.providerLocation),"Could not select the test data for Provider Location; More Details" + visitTestData.toString());
		Assert.assertTrue(type(selenium,"subjectiveInput", visitTestData.patientSubjective),"Could not enter the test data for Patient Subjective; More Details" + visitTestData.toString());
		Assert.assertTrue(type(selenium,"objectiveInput", visitTestData.patientObjective),"Could not enter the test data for Patient Objective; More Details" + visitTestData.toString());
		Assert.assertTrue(type(selenium,"assessmentInput", visitTestData.patientAssessment),"Could not enter the test data for Patient Assessment; More Details" + visitTestData.toString());
		Assert.assertTrue(type(selenium,"planInput", visitTestData.patientPlan),"Could not enter the test data for Patient Plan; More Details" + visitTestData.toString());
		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + visitTestData.toString());
		Assert.assertTrue(select(selenium,"workStatusInput", visitTestData.taskName),"Could not enter work status; More Details" + visitTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", visitTestData.sendTaskTo),"Could not select Medication send task to; More Details" + visitTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", visitTestData.taskNotes),"Could not enter the test data for Task Notes; More Details" + visitTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" + visitTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Visit not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

	}

	public void createVitals (Selenium selenium, AuditLib vitalsTestData ){

		click(selenium,"vitals");
		waitForPageLoad(selenium);

		Assert.assertTrue(click(selenium,"vitalsAdd"),"Could not find Add Vitals Link; More Details" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		click(selenium,"unitsLinkLabel");
		waitForPageLoad(selenium);

		vitalsTestData.vitalDate = vitalsTestData.vitalDate.equals("") ? getValue(selenium,"startdateInput").trim(): vitalsTestData.vitalDate.trim() ;
		Assert.assertTrue(enterDate(selenium, "startdateInput", vitalsTestData.vitalDate),"Could not enter the test data for Vital Date; More Details" + vitalsTestData.toString());
		vitalsTestData.vitalTime = vitalsTestData.vitalTime.equals("") ? getValue(selenium,"vitalTimeInput").trim(): vitalsTestData.vitalTime.trim() ;
		Assert.assertTrue(type(selenium,"vitalTimeInput", vitalsTestData.vitalTime),"Could not enter the test data for Vital Time; More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"heightFeetsInput", vitalsTestData.patientHeightFeet));
		Assert.assertTrue(type(selenium,"heightInchesInput", vitalsTestData.patientHeightInches),"Could not enter the test data for Patient Height (Inches); More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"weightpoundsInput", vitalsTestData.patientWeightPounds),"Could not enter the test data for Patient Weight (Pounds); More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"weightouncesInput", vitalsTestData.patientWeightOunces),"Could not enter the test data for Patient Weight (Ounces); More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"temperatureInput", vitalsTestData.patientTemperature),"Could not enter the test data for Patient Temperature; More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"pulseInput", vitalsTestData.patientPulse),"Could not enter the test data for Patient Pulse; More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"systolicInput", vitalsTestData.patientBPSystolic),"Could not enter the test data for Patient Systolic Pressure; More Details" + vitalsTestData.toString());
		Assert.assertTrue(type(selenium,"diastolicInput", vitalsTestData.patientBPDiastolic),"Could not enter the test data for Patient Diastolic Pressure; More Details" + vitalsTestData.toString());
		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Validate Button; More Details" + vitalsTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Vitals not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));
	}


	public void createLabRequest (Selenium selenium, AuditLib labRequestTestData){

		String allLabrequestCount = null;
		String activeLabrequestCount = null;
		boolean isInactive =false;

		if(labRequestTestData.status.equalsIgnoreCase("inactive")){
			isInactive = true;
		}

		click(selenium,"AllLabRequests");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllLabRequests", 120000),"Could not capture existing Lab Requests[ALL] Count; More Details" + labRequestTestData.toString());
		allLabrequestCount = getListCount(selenium.getText("AllLabRequests"));

		Assert.assertTrue(waitForValue(selenium, "CurrentLabRequests", 120000),"Could not capture existing Lab Requests[Active] Count; More Details" + labRequestTestData.toString());
		activeLabrequestCount = getListCount(selenium.getText("CurrentLabRequests"));

		Assert.assertTrue(click(selenium,"addLabRequest"),"Could not find Add Lab Request Link; More Details" + labRequestTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium,"providersInput", labRequestTestData.providerName),"Could not enter Provider Name; More Details" + labRequestTestData.toString());
		Assert.assertTrue(enterDate(selenium,"expecteddateInput", labRequestTestData.expectedDate),"Could not enter Expected Date; More Details" + labRequestTestData.toString());
		Assert.assertTrue(type(selenium,"notesInput",labRequestTestData.providerNotes),"Could not enter Notes; More Details" + labRequestTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", labRequestTestData.status),"Could not enter status; More Details" + labRequestTestData.toString());

		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + labRequestTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium,"workStatusInput", labRequestTestData.taskName),"Could not select Work Status; More Details" + labRequestTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", labRequestTestData.sendTaskTo),"Could not select Send Tast To; More Details" + labRequestTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", labRequestTestData.taskNotes),"Could not enter Task Notes; More Details" + labRequestTestData.toString());
		Assert.assertTrue(selectTestBatteries(selenium, labRequestTestData.testBatteries.trim()),"Could not select Battery Value; More Details" + labRequestTestData.toString());

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details" + labRequestTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Lab Request not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}

		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		click(selenium,"AllLabRequests");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllLabRequests", 120000),"Could not capture Lab Request Count[All] after saving a Lab Request; More Details" + labRequestTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllLabRequests"))) == Integer.parseInt(allLabrequestCount)+1,"The Lab Request is not Saved Successfully, Lab Request count[All] has no change after adding a new Lab Request; More Details" + labRequestTestData.toString());
		Assert.assertTrue(waitForValue(selenium, "CurrentLabRequests", 120000),"Could not capture Lab Request Count[Active] after saving a Lab Request; More Details" + labRequestTestData.toString());

		if(!isInactive ){
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentLabRequests"))) == Integer.parseInt(activeLabrequestCount)+1,"The Lab Request is not Saved Successfully, Lab Request count[Active] has no change after adding a new Lab Request; More Details" + labRequestTestData.toString());

		}else{
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentLabRequests"))) == Integer.parseInt(activeLabrequestCount),"The Lab Request is not Saved Successfully, Lab Request count[Active] has no change after adding a new Lab Request; More Details" + labRequestTestData.toString());
		}
	}


	public void createImmunization (Selenium selenium, AuditLib immunizationTestData ){

		String immunizationCount = null;

		Assert.assertTrue(waitForValue(selenium, "Immunizations", 120000),"Could not capture existing Immunization Count; More Details" + immunizationTestData.toString());
		immunizationCount = getListCount(selenium.getText("Immunizations"));

		Assert.assertTrue(click(selenium,"addImmunization"),"Could not find Add Immunization Link; More Details" + immunizationTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(selectValueFromAjaxList(selenium,"productBoxInputBox", immunizationTestData.immunizationName),"Could not enter Immunization Name; More Details" + immunizationTestData.toString());
		Assert.assertTrue(enterDate(selenium,"administrationDateInput", immunizationTestData.administrationDate),"Could not enter Administration Date; More Details" + immunizationTestData.toString());
		Assert.assertTrue(enterDate(selenium,"administrationTimeInput", immunizationTestData.administrationTime),"Could not enter Administration Time; More Details" + immunizationTestData.toString());
		Assert.assertTrue(enterDate(selenium,"administeredAmountInput", immunizationTestData.administrationAmount),"Could not enter Administration Amount; More Details" + immunizationTestData.toString());
		Assert.assertTrue(type(selenium,"lotNumberInput",immunizationTestData.lotInput),"Could not enter Lot Number; More Details" + immunizationTestData.toString());
		Assert.assertTrue(select(selenium,"immunizationManufacturersInput", immunizationTestData.manufacturer),"Could not select Manufacturer; More Details" + immunizationTestData.toString());
		Assert.assertTrue(select(selenium,"fdaRoutesInput", immunizationTestData.routeOfAdministartion),"Could not select Manufacturer Route; More Details" + immunizationTestData.toString());
		Assert.assertTrue(select(selenium,"refusalReasonCodeInput", immunizationTestData.reason),"Could not select Reason Code; More Details" + immunizationTestData.toString());
		Assert.assertTrue(type(selenium,"notesInput", immunizationTestData.immunityNotes),"Could not enter Immunity Notes; More Details" + immunizationTestData.toString());
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + immunizationTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium, "workStatusInput", immunizationTestData.taskName),"Could not select Work Status; More Details" + immunizationTestData.toString());
		Assert.assertTrue(select(selenium, "taskUsersInput", immunizationTestData.sendTaskTo),"Could not select Send To Task; More Details" + immunizationTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", immunizationTestData.taskNotes),"Could not enter Task Notes; More Details" + immunizationTestData.toString());

		Assert.assertTrue(click(selenium,"validateButton"),"Could not Click Validate Button; More Details" + immunizationTestData.toString());				
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Immunization not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}
		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		Assert.assertTrue(waitForValue(selenium, "Immunizations", 120000),"Could not capture immunization Count after saving a immunization; More Details" + immunizationTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("Immunizations"))) == Integer.parseInt(immunizationCount)+1,"The immunization is not Saved Successfully, immunization count has no change after adding a new immunization; More Details" + immunizationTestData.toString());
	}


	public void createAllergy(Selenium selenium, AuditLib allergyTestData){		

		String activeAllergyCount = null;

		try {

			click(selenium,"allergies");
			waitForPageLoad(selenium);

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			Assert.assertTrue((waitForValue(selenium, "CurrentAllergies", 120000)),"Could not capture existing Allergies[Active] Count; More Details" + allergyTestData.toString());
			activeAllergyCount = getListCount(selenium.getText("CurrentAllergies"));

			Assert.assertTrue(waitForElement(selenium, "addAllergy", 10000),"Could not find Add Allergy Link; More Details" + allergyTestData.toString());
			click(selenium,"addAllergy");
			waitForPageLoad(selenium);

			Assert.assertTrue(selectValueFromAjaxList(selenium,"allergyBoxBox", allergyTestData.allergyName),"Could not enter Allergy Name; More Details" + allergyTestData.toString());
			Assert.assertTrue(select(selenium,"adverseeventtypeInput", allergyTestData.eventType),"Could not select Event Type; More Details" + allergyTestData.toString());
			Assert.assertTrue(select(selenium,"severityInput", allergyTestData.severity),"Could not select Allergy Severity; More Details" + allergyTestData.toString());
			Assert.assertTrue(enterDate(selenium,"startdateInput", allergyTestData.startDate),"Could not enter Start Date; More Details" + allergyTestData.toString());
			Assert.assertTrue(!allergyTestData.endDate.equals("") && (enterDate(selenium,"enddateInput", allergyTestData.endDate)),"Could not enter End Date; More Details" + allergyTestData.toString());
			Assert.assertTrue(select(selenium,"itemStatusInput", allergyTestData.status),"Could not select Allergy Status; More Details" + allergyTestData.toString());
			Assert.assertTrue((type(selenium,"notesInput", allergyTestData.allergyNotes)),"Could not enter Allergy Notes; More Details" + allergyTestData.toString());
			Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

			Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link; More Details" + allergyTestData.toString());
			waitForPageLoad(selenium);

			Assert.assertTrue(select(selenium, "workStatusInput", allergyTestData.taskName),"Could not select Work Status - " + allergyTestData.taskName);
			Assert.assertTrue(select(selenium, "taskUsersInput", allergyTestData.sendTaskTo),"Could not select Send To Task - " + allergyTestData.sendTaskTo);
			Assert.assertTrue(type(selenium,"taskNotesInput", allergyTestData.taskNotes),"Could not enter Task Notes; More Details" + allergyTestData.toString());

			Assert.assertTrue(click(selenium,"validateButton"),"Could not click Save Button; More Details" + allergyTestData.toString());
			waitForPageLoad(selenium);

			if(selenium.isAlertPresent() || selenium.isElementPresent("//p"))
			{
				Assert.assertTrue(selenium.isAlertPresent(),"Allergy not saved successfully, An unexpected Alert Occured - " + selenium.getAlert().trim());								
				Assert.assertTrue(selenium.isElementPresent("//p"), "An unexpected Alert Occured - "+ selenium.getText("//p").trim());
			}

			click(selenium,"AllAllergies");
			waitForPageLoad(selenium);

			Assert.assertTrue(waitForValue(selenium, "CurrentAllergies", 120000),"Could not capture Allergy[All] Count after saving a Allergy; More Details" + allergyTestData.toString());
			Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("CurrentAllergies"))) == Integer.parseInt(activeAllergyCount)+1,"The Allergy is not Saved Successfully, Allergy[Current] count has no change after adding a new Allergy; More Details" + allergyTestData.toString());

		} catch (RuntimeException e) {
			e.printStackTrace();
			org.testng.Assert.fail("some error has occured during execution, please reffer error log file 'ErrorLog_" + allergyTestData.testCaseId + ".log' for more info!; More Details" + allergyTestData.toString());
		}
	}

	public void createCondition(Selenium selenium, AuditLib conditionTestData){		

		String allConditionCount = null;
		String activeConditionsCount = null;

		click(selenium,"AllConditions");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllConditions", 120000),"Could not capture existing Allergies Count; More Details" + conditionTestData.toString());
		allConditionCount = getListCount(selenium.getText("AllConditions"));

		Assert.assertTrue(waitForValue(selenium, "CurrentConditions", 120000),"Could not capture existing Allergies Count; More Details" + conditionTestData.toString());
		activeConditionsCount = getListCount(selenium.getText("CurrentConditions"));

		Assert.assertTrue(click(selenium,"addCondition"),"Could not find Add Condition Link; More Details" + conditionTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(selectValueFromAjaxList(selenium,"conditionsBoxBox", conditionTestData.condition),"Could not enter the Condition; More Details" + conditionTestData.toString());
		Assert.assertTrue((enterDate(selenium,"startdateInput", conditionTestData.conditionStartDate)),"Could not enter the Condition Start Date; More Details" + conditionTestData.toString());
		Assert.assertTrue(enterDate(selenium,"enddateInput", conditionTestData.conditionEndDate),"Could not enter the Condition End Date; More Details" + conditionTestData.toString());
		Assert.assertTrue(type(selenium,"notesInput", conditionTestData.conditionNote),"Could not enter the Condition Note; More Details" + conditionTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", conditionTestData.status),"Could not select Allergy Status; More Details" + conditionTestData.toString());
		Assert.assertFalse(selenium.isElementPresent("//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div") ,"UnExpected Alert is Displayed  :- "+ getMessage(selenium, "//div[@class='dialogMiddleCenterInner dialogContent']/div/div[2]/ol/li/div").trim());

		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + conditionTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium, "workStatusInput", conditionTestData.taskName),"Could not select Work Status; More Details" + conditionTestData.toString());
		Assert.assertTrue(select(selenium, "taskUsersInput", conditionTestData.sendTaskTo),"Could not select Send To Task; More Details" + conditionTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", conditionTestData.taskNotes),"Could not enter Task Notes; More Details" + conditionTestData.toString());

		Assert.assertTrue((click(selenium,"validateButton")),"Could not click Save Button; More Details" + conditionTestData.toString());
		waitForPageLoad(selenium);			

		if(selenium.isAlertPresent() || (selenium.isElementPresent("//p")))
		{
			Assert.assertTrue(selenium.isAlertPresent(),"An unexpected alert has occured : " + selenium.getAlert().trim());
			Assert.assertTrue( (selenium.isElementPresent("//p")),"An unexpected Alert Occured - " + selenium.getText("//p").trim());
		}

		click(selenium,"AllConditions");
		waitForPageLoad(selenium);

		Assert.assertTrue((waitForValue(selenium, "CurrentConditions", 120000)),"Could not capture Active condition Count after saving a condition; More Details" + conditionTestData.toString());

		Boolean checkFlag = !conditionTestData.status.trim().equalsIgnoreCase("inactive");

		if(checkFlag)
			Assert.assertFalse(Integer.parseInt(getListCount(selenium.getText("CurrentConditions"))) == Integer.parseInt(activeConditionsCount),"The condition is not Saved Successfully, Active condition count has no change after adding a new condition; More Details" + conditionTestData.toString());
		else
			Assert.assertFalse(Integer.parseInt(getListCount(selenium.getText("CurrentConditions"))) == Integer.parseInt(activeConditionsCount)+1,"The condition is not Saved Successfully, Active condition count has no change after adding a new condition; More Details" + conditionTestData.toString());

		Assert.assertTrue(waitForValue(selenium, "AllConditions", 120000),"Could not capture All condition Count after saving a condition; More Details" + conditionTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllConditions"))) == Integer.parseInt(allConditionCount)+1,"The condition is not Saved Successfully, All condition count has no change after adding a new condition; More Details" + conditionTestData.toString());
	}


	public void createRadiologyOrder (Selenium selenium, AuditLib radiologyOrderTestData ){

		String allRadiologyOrderCount = null;

		click(selenium,"AllRadiologyOrders");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllRadiologyOrders", 120000),"Could not capture existing Radiology Order Count; More Details" + radiologyOrderTestData.toString());
		allRadiologyOrderCount = getListCount(selenium.getText("AllRadiologyOrders"));

		Assert.assertTrue(click(selenium,"addRadiologyOrder"),"Could not find Add Radiology Order Link; More Details" + radiologyOrderTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium,"providersInput", radiologyOrderTestData.providerName),"Could not enter Provider Name; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(enterDate(selenium,"expecteddateInput", radiologyOrderTestData.expectedDate),"Could not enter Expected Date; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(type(selenium,"notesInput",radiologyOrderTestData.providerNotes),"Could not enter Notes; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(select(selenium,"itemStatusInput", radiologyOrderTestData.status),"Could not enter status; More Details" + radiologyOrderTestData.toString());

		Assert.assertTrue(click(selenium, "addTask"),"Could not click the Add Task Link  -; More Details" + radiologyOrderTestData.toString());
		waitForPageLoad(selenium);

		Assert.assertTrue(select(selenium,"workStatusInput", radiologyOrderTestData.taskName),"Could not select Work Status; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(select(selenium,"taskUsersInput", radiologyOrderTestData.sendTaskTo),"Could not select Send Tast To; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(type(selenium,"taskNotesInput", radiologyOrderTestData.taskNotes),"Could not enter Task Notes; More Details" + radiologyOrderTestData.toString());

		if(!radiologyOrderTestData.MRI.equals("")){
			Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.MRI.trim()),"Could not select Radiology Panels : MRI Value; More Details" + radiologyOrderTestData.toString());
		}

		if(!radiologyOrderTestData.CT.equals("") ){
			Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.CT.trim()),"Could not select Radiology Panels : CT Value; More Details" + radiologyOrderTestData.toString());
		}

		if(!radiologyOrderTestData.radiology.equals("")){
			Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.radiology.trim()),"Could not select Radiology Panels : radiology Value; More Details" + radiologyOrderTestData.toString());
		}

		if(!radiologyOrderTestData.nuclearMedecine.equals("") ){
			Assert.assertTrue(selectRadiologyPanels(selenium, radiologyOrderTestData.nuclearMedecine.trim()),"Could not select Radiology Panels : Nuclear Medecine Value; More Details" + radiologyOrderTestData.toString());
		}

		Assert.assertTrue(click(selenium, "validateButton"),"Could not click Save Button; More Details" + radiologyOrderTestData.toString());
		waitForPageLoad(selenium);

		if(selenium.isAlertPresent()){
			Assert.assertFalse(selenium.isAlertPresent(),"Radiology Order not saved successfully, An unexpected Alert Occured - " + selenium.getAlert() );
		}
		Assert.assertFalse(selenium.isElementPresent("//p"),"An unexpected Alert Occured - "+getMessage(selenium,"//p"));

		click(selenium,"AllRadiologyOrders");
		waitForPageLoad(selenium);

		Assert.assertTrue(waitForValue(selenium, "AllRadiologyOrders", 120000),"Could not capture Radiology Order Count after saving a Radiology Order; More Details" + radiologyOrderTestData.toString());
		Assert.assertTrue(Integer.parseInt(getListCount(selenium.getText("AllRadiologyOrders"))) == Integer.parseInt(allRadiologyOrderCount)+1,"The Radiology Order is not Saved Successfully, Radiology Order count has no change after adding a new Radiology Order; More Details" + radiologyOrderTestData.toString());
	}

	public boolean selectTestBatteries(Selenium selenium, String selectValue){

		String idValue = null;
		String splitSelectValue[] = selectValue.split("\\(");
		selectValue = splitSelectValue[1];
		splitSelectValue = selectValue.split("\\)");
		idValue = splitSelectValue[0];
		click(selenium,"//table[@id='antibioticPanels']/tbody/tr/td/a/table/tbody/tr/td[2]");
		waitForPageLoad(selenium);
		if(isChecked(selenium, "//span[@id='"+idValue+"']/input")){
			return true;
		}else{ 
			if(check(selenium, "//span[@id='"+idValue+"']/input")){
				return true;
			}else{
				return false;
			}
		}
	}

	public boolean selectRadiologyPanels(Selenium selenium, String valRadiologyPanel){
		String panelVal[] = valRadiologyPanel.split("-");
		String elementName = "//input[@name='"+panelVal[0]+"']";
		return(check(selenium, elementName));

	}

	public Selenium doSearch(Selenium selenium,String startDate,String endDate) throws InterruptedException{

		Assert.assertFalse(startDate.equals(""),"Start Date Should Not be Empty");
		Assert.assertTrue(type(selenium, "//div[@id='fromDate']/input", startDate),"Could Not Enter Start Date    :- "+startDate);
		selenium.keyDown("//div[@id='fromDate']/input",  "\\9");
		Assert.assertFalse(endDate.equals(""),"EndDate Should Not be Empty" );
		Assert.assertTrue(type(selenium, "//div[@id='toDate']/input", endDate),"Could Not Enter End Date    :- "+endDate);
		selenium.keyDown("//div[@id='toDate']/input",  "\\9");
		Assert.assertTrue(click(selenium, "runAudit"),"Could not click the Dig Button");
		Thread.sleep(3000);
		waitForPageLoad(selenium);


		if(isElementPresent(selenium,"moreLink")){
			while(selenium.isElementPresent("moreLink") && selenium.isVisible("moreLink")){
				click(selenium,"moreLink");
				waitForPageLoad(selenium);
			}
		}
		return selenium;
	}
	
	@SuppressWarnings("deprecation")
	public String searchRecord(Selenium selenium,Date startDate,Date endDate,String action, String recordID, AuditLib  auditTestData){
		String auditRecords = "";
		String valCurrentDate ="";
		String valEndDate = "";
		try {
			click(selenium, "headerCharts");
			waitForPageLoad(selenium);
			click(selenium, "auditButton");
			waitForPageLoad(selenium);
			DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy - HH:mm:ss");
			Date currentDate =(Date) endDate.clone();
			currentDate.setSeconds(currentDate.getSeconds()-90);
			valCurrentDate = dateFormat.format(currentDate);
			valCurrentDate = valCurrentDate.replace("-", "at");
			valEndDate = dateFormat.format(endDate);
			valEndDate = valEndDate.replace("-", "at");
			selenium = doSearch(selenium, valCurrentDate, valEndDate);
			if(selenium.isElementPresent("tooManyRows") && selenium.isVisible("tooManyRows")){
				currentDate =(Date) endDate.clone();
				currentDate.setSeconds(currentDate.getSeconds()-30);
				valCurrentDate = dateFormat.format(currentDate);
				valCurrentDate = valCurrentDate.replace("-", "at");
				selenium = doSearch(selenium, valCurrentDate, valEndDate);
			}

			int timer =0;
			String elementID = "detail-"+ action +"-"+recordID;
			while(!selenium.isElementPresent(elementID)){
				Thread.sleep(7000);
				timer = timer + 7;
				endDate.setSeconds(endDate.getSeconds()+7);
				valEndDate = dateFormat.format(endDate);
				valEndDate = valEndDate.replace("-", "at");
				currentDate.setSeconds(currentDate.getSeconds()-7);
				valCurrentDate = dateFormat.format(currentDate);
				valCurrentDate = valCurrentDate.replace("-", "at");
				if(timer > 21){
					break;
				}
				selenium = doSearch(selenium, valCurrentDate, valEndDate);
			}
			if(selenium.isElementPresent(elementID)){
				auditRecords = selenium.getText(elementID);
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
		}
		Assert.assertTrue(!auditRecords.equals(""),"No Records Found in Audit Page; The time frame is : StartDate"+valCurrentDate + "End Date : "+ valEndDate +"; More Details : "+auditTestData.toString() );
		return auditRecords;
	}
	
	
	@SuppressWarnings("deprecation")
	public String searchRecordAlternative(Selenium selenium,Date startDate,Date endDate,String action, String recordID, AuditLib  auditTestData){
		String auditRecords = "";
		String valCurrentDate ="";
		String valEndDate = "";
		try {
			click(selenium, "headerCharts");
			waitForPageLoad(selenium);
			click(selenium, "auditButton");
			waitForPageLoad(selenium);

			DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy - HH:mm:ss");
			Date currentDate =(Date) endDate.clone();
			currentDate.setSeconds(currentDate.getSeconds()-90);
			valCurrentDate = dateFormat.format(currentDate);
			valCurrentDate = valCurrentDate.replace("-", "at");
			valEndDate = dateFormat.format(endDate);
			valEndDate = valEndDate.replace("-", "at");
			selenium = doSearch(selenium, valCurrentDate, valEndDate);
			if(selenium.isElementPresent("tooManyRows") && selenium.isVisible("tooManyRows")){
				currentDate =(Date) endDate.clone();
				currentDate.setSeconds(currentDate.getSeconds()-30);
				valCurrentDate = dateFormat.format(currentDate);
				valCurrentDate = valCurrentDate.replace("-", "at");
				selenium = doSearch(selenium, valCurrentDate, valEndDate);
			}

			int timer =0;
			String elementID ="//div[starts-with(@id, 'detail-"+ action +"-"+recordID+"')]";
			while(!selenium.isElementPresent(elementID)){
				Thread.sleep(7000);
				timer = timer + 7;
				endDate.setSeconds(endDate.getSeconds()+7);
				valEndDate = dateFormat.format(endDate);
				valEndDate = valEndDate.replace("-", "at");
				currentDate.setSeconds(currentDate.getSeconds()-7);
				valCurrentDate = dateFormat.format(currentDate);
				valCurrentDate = valCurrentDate.replace("-", "at");
				if(timer > 21){
					break;
				}
				selenium = doSearch(selenium, valCurrentDate, valEndDate);
			}
			if(selenium.isElementPresent(elementID)){
				auditRecords = selenium.getText(elementID);
			}


		} catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
		}
		return auditRecords;
	}
	
	public Boolean searchForRecords(Selenium selenium,String elementID){
		if(selenium.isElementPresent(elementID)){
			return true;
		}else{
			return false;
		}
	}
}

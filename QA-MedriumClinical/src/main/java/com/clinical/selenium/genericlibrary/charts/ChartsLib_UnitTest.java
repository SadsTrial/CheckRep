package com.clinical.selenium.genericlibrary.charts;

import java.lang.reflect.Field;
import java.util.Hashtable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.Assert;

import com.clinical.selenium.genericlibrary.global.ReadFromExcel;

/**
 * @Class  		: ChartsLib
 * @Description : This Class will contain reusable functions which can be used in New Clinical Interface
 * @Category 	: Charts
 * @Author 		: Aspire QA
 * @Created on 	: Apr 30, 2010
 */
public class ChartsLib_UnitTest {
	
	public String testCaseId = null;
	public String userAccount = null;
	public String userName = null;
	public String userPassword = null;
	public String patientID = null;
	public String medicationName = null;
	public String medicationNote = null;
	public String quantity = null;
	public String SIG = null;
	public String refills = null;
	public String comments = null;
	public String condition = null;
	public String conditionStartDate = null;
	public String conditionEndDate = null;
	public String conditionNote = null;
	public String allergyName = null;
	public String reaction = null; 
	public String eventType = null;
	public String severity = null;
	public String startDate = null;
	public String endDate = null;
	public String allergyNotes = null;	
	public String immunizationName = null;
	public String administrationDate = null;
	public String administrationTime = null;
	public String manufacturer = null;
	public String routeOfAdministartion = null;
	public String lotInput = null;
	public String reason = null;
	public String immunityNotes = null;
	public String description = null;
	public String alert = null;
	public String alertMessage = null;
	public String workSheetName = null;
	public String directiveType = null;
	public String directiveName = null;
	public String directiveStatus = null;
	public String directiveStartDate = null;
	public String directiveEndDate = null;
	public String custodianLastName = null;
	public String custodianFirstName = null;
	public String custodianOrganization = null;
	public String custodianAddress1 = null;
	public String custodianAddress2 = null;
	public String custodianCity = null;
	public String custodianState = null;
	public String custodianZIP = null;
	public String custodianTel = null;
	public String custodianExt = null;
	public String custodianMobile = null;
	public String custodianFax = null;
	public String custodianEmail = null;
	public String custodianNote = null;
	public String smokingType = null;
	public String smokingStartDate =  null;
	public String smokingNote = null;
	public String pregnancyType = null;
	public String pregnancyStartDate =  null;
	public String pregnancyNote = null;
	public String lactatingType = null;
	public String lactatingStartDate =  null;
	public String lactatingNote = null;
	public String visitDate = null;
	public String vitalDate = null;
	public String vitalTime = null;
	public String patientHeightFeet = null;
	public String patientHeightInches = null;
	public String patientWeightPounds = null;
	public String patientWeightOunces = null;
	public String patientTemperature = null;
	public String patientPulse = null;
	public String patientBPSystolic = null;
	public String patientBPDiastolic = null;
	public String patientCondition = null;
	public String patientSubjective = null;
	public String patientObjective = null;
	public String patientAssessment = null;
	public String patientPlan = null;
	public String providerName = null;
	public String providerLocation = null;
	public String providerNotes = null;
	public String taskName = null;
	public String sendTaskTo = null;
	public String taskNotes = null;
	public String status = null;
	public String units = null;
	public String noOfDays = null;
	public String expectedDate = null;
	public String MRI = null;
	public String CT = null;
	public String radiology = null;
	public String nuclearMedecine = null;
	public String workBookName = "TestData_UnitTest_Charts.xls";		
	public String sectionName = "charts";
	
	public Hashtable<String, Integer> excelHeaders = new Hashtable<String, Integer>();
	public Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<String, Integer>();
	

    public String toString() {
        StringBuffer listOfValues  = new StringBuffer();
        @SuppressWarnings("rawtypes")
		Class cls = this.getClass();
        Field[] fields = cls.getDeclaredFields( );

        Field field = null;
        try {
            for (int i=0; i < fields.length; i++) {
                field = fields[i];
                Object subObj = field.get(this);
                if (subObj != null && !field.getName().equals("logger") ) {
                    listOfValues.append(":");
                    listOfValues.append(field.getName());
                    listOfValues.append("=");
                    listOfValues.append(subObj.toString());
                }
            }
        }
        catch (RuntimeException e) {
            Assert.fail("toString",e);
        } catch (IllegalAccessException e) {
        	Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
		}   
        return listOfValues.toString();
    }

    
	/**
	 * @Function 	: fetchCodingInterfaceTestData
	 * @Description : Function for fetching Test data based on test case id  
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010	  
	 */
	public boolean fetchCodingInterfaceTestData(){
		
		ReadFromExcel readTestData = new ReadFromExcel();
		boolean isDataFound = false;
		testCaseId = testCaseId != null ? testCaseId.trim() : "";
		
		try{
			  //******************************************************************//
			  //Fetching the test data for New Clinical Interface
			  //******************************************************************//

			HSSFSheet sheet = null;
			// function call to initiate a connection to an excel sheet
			sheet = readTestData.initiateExcelConnection(workSheetName, sectionName, workBookName);

			// function to find number of rows and columns
			excelrRowColumnCount = readTestData.findRowColumnCount(sheet, excelrRowColumnCount);
			
			// function call to find excel header fields
			excelHeaders = readTestData.readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);

			HSSFRow row = null;
			HSSFCell cell = null;			
			String temptestCaseId = null;

			for(int r = 0; r < excelrRowColumnCount.get("RowCount"); r++) {
				row = sheet.getRow(r);  
				if(row != null) {
					for(int c = 0; c < excelrRowColumnCount.get("ColumnCount"); c++) {						
						cell = row.getCell(excelHeaders.get("TestID"));
						if(cell != null){
							temptestCaseId = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestID")));
							if(temptestCaseId.equals(testCaseId)){
								isDataFound = true;
								if(workSheetName.equalsIgnoreCase("Prescription" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));
									providerLocation = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Location")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
									quantity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Quantity")));
									units = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Units")));
									noOfDays = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("NoOfDays")));
									refills = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Refills")));
									comments = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Comments")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));							
								}else if(workSheetName.equalsIgnoreCase("Condition" )){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									condition = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Condition")));
									conditionStartDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ConditionStartDate")));
									conditionEndDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ConditionEndDate")));
									conditionNote = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ConditionNote")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Allergy")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									allergyName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AllergyName")));
									eventType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("EventType")));
									severity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Severity")));
									reaction = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Reaction")));
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AllergyStartDate")));
									endDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AllergyEndDate")));
									allergyNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Note")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Immunization")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									immunizationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ImmunizationName")));
									administrationDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AdministrationDate")));									
									administrationTime = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AdministrtionTime")));
									lotInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Lot")));									
									manufacturer = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Manufacturer")));
									routeOfAdministartion = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AdminstarionRoute")));
									immunityNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Note")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if (workSheetName.equalsIgnoreCase("Directive")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));									
									directiveType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DirectiveType")));
									directiveName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DirectiveName")));
									directiveStatus = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DirectiveStatus")));
									directiveStartDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DirectiveStartDate")));
									directiveEndDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DirectiveEndDate")));
									custodianLastName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("LastName")));
									custodianFirstName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("FirstName")));
									custodianOrganization = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Organization")));
									custodianAddress1 = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Address1")));
									custodianAddress2 = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Address2")));
									custodianCity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("City")));
									custodianState = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("State")));
									custodianZIP = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ZIP")));
									custodianTel = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Telephone")));
									custodianExt = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Extension")));
									custodianMobile = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Mobile")));
									custodianFax = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Fax")));
									custodianEmail = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Email")));
									custodianNote = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Note")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("SocialHistory")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));									
									smokingStartDate =  readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SmokingStartDate")));
									smokingNote = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SmokingNote")));									
									pregnancyStartDate =  readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PregnancyStartDate")));
									pregnancyNote = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PregnancyNote")));									
									lactatingStartDate =  readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("LactatingStartDate")));
									lactatingNote = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("LactatingNote")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Visit")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									visitDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("VisitDate")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));
									providerLocation = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderLocation")));
									patientSubjective = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Subjective")));
									patientObjective = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Objective")));
									patientAssessment = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Assessment")));
									patientPlan = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Plan")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Medication")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));									
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationStartDate")));
									endDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationEndDate")));									
									medicationNote= readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationNote")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Referral")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));									
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ReferralStartDate")));									
									providerNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Notes")));									
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("LabRequest")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));									
									expectedDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedDate")));
									providerNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("InstructionNotes")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes= readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("Vitals")){
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									vitalDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Date")));
									vitalTime = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Time")));
									patientHeightFeet = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("HeightFeet")));
									patientHeightInches = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("HeightInches")));
									patientWeightPounds = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("WeightPounds")));
									patientWeightOunces = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("WeightOunces")));
									patientTemperature = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Temperature")));
									patientPulse = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Pulse")));
									patientBPSystolic = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("BPSystolic")));
									patientBPDiastolic = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("BPDiastolic")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}else if(workSheetName.equalsIgnoreCase("RadiologyOrder")){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									description = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TestDescription")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));									
									expectedDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedDate")));
									providerNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("InstructionNotes")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
									taskNotes = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TaskNotes")));
									MRI = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MRI")));
									CT = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("CT")));
									radiology = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Radiology")));
									nuclearMedecine = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("NuclearMedecine")));
									alert = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AlertPresent")));
									alertMessage = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ExpectedMessage")));
								}
							}else {
								break;
							}							
						}else {
							break;
						}
					}
				}
				if(isDataFound){					
					break;
				}
			}
			if(!isDataFound){
				Assert.fail("\nTest Data not found in test data sheet for Test Case Id  : " + testCaseId);
			}
		}catch (RuntimeException e) {
			Assert.fail("Error During Execution; Execution Failed More detaisl " + e);
			e.printStackTrace();
		}
		return isDataFound;
	}
	
	/**
	 * @Function 	: checkAlertMessage
	 * @Description : Function for checking the alert message with the expected one  
	 * @Author 		: Aspire QA
	 * @Created on 	: Apr 30, 2010	  
	 */
	public boolean checkAlertMessage(String alertText, String actualText){
		
		if(alertText == null || actualText.equals("") ){
			return false;
		}
		alertText = alertText.replaceAll("\\s","");
		actualText = actualText.replaceAll("\\s", "");
		
		if(alertText.toLowerCase(new java.util.Locale("en", "US")).contains(actualText.toLowerCase(new java.util.Locale("en", "US")))){
			return true;
		}else {
			return false;
		}
	}

}


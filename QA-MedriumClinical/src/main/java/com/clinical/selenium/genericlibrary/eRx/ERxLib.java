package com.clinical.selenium.genericlibrary.eRx;

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
 * @Created on 	: Apr 04, 2010
 */
public class ERxLib {
	public String testCaseId = null;
	public String testID = null;
	public String testDescription = null;
	public String userAccount = null;
	public String userName = null;
	public String userPassword = null;
	public String patientID = null;
	public String visitDate = null;
	public String vitalDate = null;
	public String vitalTime = null;
	public String providerName = null;
	public String providerLocation = null;
	public String providerNotes = null;
	public String taskNotes = null;
	public String testBatteries = null;
	public String medicationName = null;
	public String medicationName1 = null;
	public String medicationName2= null;
	public String medicationNote = null;
	public String unitQuantityInput = null;
	public String quantityTypeInput = null;
	public String frequencyInput = null;
	public String instructionInput = null;
	public String daySupplyInput = null;	
	public String quantity = null;
	public String SIG = null;
	public String refills = null;
	public String comments = null;
	public String patientName = null;
	
	public String status = null;
	public String condition = null;
	public String conditionStartDate = null;
	public String conditionEndDate = null;
	public String conditionNote = null;
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
	public String sendTaskTo = null;
	public String allergyName = null;
	public String eventType = null;
	public String severity = null;
	public String startDate = null;
	public String endDate = null;
	public String expectedDate = null;
	public String allergyNotes = null;	
	public String immunizationName = null;
	public String administrationDate = null;
	public String administrationTime = null;
	public String administrationAmount = null;
	public String manufacturer = null;
	public String routeOfAdministartion = null;
	public String lotInput = null;
	public String reason = null;
	public String immunityNotes = null;
	public String smokingType = null;
	public String smokingStartDate =  null;
	public String smokingNote = null;
	public String pregnancyType = null;
	public String pregnancyStartDate =  null;
	public String pregnancyNote = null;
	public String lactatingType = null;
	public String lactatingStartDate =  null;
	public String lactatingNote = null;
	public String taskName = null;
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
	public String workSheetName = null;
	public String MRI = null;
	public String CT = null;
	public String radiology = null;
	public String nuclearMedecine = null;
	public String interactionMessage = null;
	public String reaction = null;
	public String pharmacyName = null;

	public String workBookName = "TestData_NewClinicalInterface_ERx.xls";		
	public String sectionName = "eRx";
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
	 * @Created on 	: Apr 04, 2010	  
	 */
	public boolean fetchErxTestData(){

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
									visitDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientVisitDate")));
									providerName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProviderName")));
									providerLocation = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Location")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
									unitQuantityInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("TakeEachTime")));
									quantityTypeInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("QuantityType")));
									frequencyInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("FreequencyInput")));
									instructionInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("InstructionInput")));
									daySupplyInput = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DaySuppyInput")));
									quantity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Quantity")));									
									refills = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Refills")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									comments = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Comments")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
								}else if(workSheetName.equalsIgnoreCase("VerifyUnMatchedRefillRequest" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									patientName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientName")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
								}							}else {
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
				Assert.fail("\nTest Data is not found in test data sheet for Test Case Id  : " + testCaseId);
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		return isDataFound;
	}
}


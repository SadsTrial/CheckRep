package com.clinical.selenium.genericlibrary.preferences;

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
public class PreferencesLib {

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
	public String altMedicationName = null;
	public String medicationName = null;
	public String severity = null;
	public String severityToUpdate = null;
	public String applyTo = null;
	public String productType = null;
	public String startDate = null;
	public String endDate = null;
	public String status = null;
	public String interactionWarnings = null;
	public String updateMedicationName = null;
	public String medicationNote = null;
	public String taskName = null;
	public String sendTaskTo = null;
	public String workSheetName = null;
	public String workBookName = "TestData_Preferences_ClinicalInterface.xls";		
	public String sectionName = "preferences";
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
	public boolean fetchPreferencesTestData(){

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
								if(workSheetName.equalsIgnoreCase("PreferredDrugList" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DrugName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PreferredDrugName")));
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
								}else if(workSheetName.equalsIgnoreCase("UpdatePreferredDrugList" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DrugName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PreferredDrugName")));
									updateMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("updateDrugName")));
								}else if(workSheetName.equalsIgnoreCase("DeletePreferredDrugList" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("DrugName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PreferredDrugName")));
								}else if(workSheetName.equalsIgnoreCase("InteractionWarnings" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AltMedicationName")));
									interactionWarnings = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Warning")));
									productType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProductType")));
									severity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Severity")));
									applyTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ApplyTo")));
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationStartDate")));
									endDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationEndDate")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									medicationNote= readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationNote")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
								}else if(workSheetName.equalsIgnoreCase("EditInteractionWarnings" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AltMedicationName")));
									interactionWarnings = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Warning")));
									productType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProductType")));
									severity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Severity")));
									severityToUpdate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SeverityToUpdate")));
									applyTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ApplyTo")));
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationStartDate")));
									endDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationEndDate")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									medicationNote= readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationNote")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
								}else if(workSheetName.equalsIgnoreCase("DeleteWarnings" )){
									userAccount = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AccountNumber")));
									userName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("UserName")));
									userPassword = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Password")));
									patientID = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("PatientID")));
									medicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationName")));
									altMedicationName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("AltMedicationName")));
									interactionWarnings = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Warning")));
									productType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ProductType")));
									severity = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Severity")));
									applyTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("ApplyTo")));
									startDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationStartDate")));
									endDate = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationEndDate")));
									status = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Status")));
									medicationNote= readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("MedicationNote")));
									taskName = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Task")));
									sendTaskTo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("SendTaskTo")));
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
		}
		return isDataFound;
	}
}


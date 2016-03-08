package executionEngine;
import java.lang.reflect.Method;
import java.io.FileInputStream;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

import java.util.Properties;

public class DriverScript {

	//public static WebDriver driver= null;
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	
	
	/*public DriverScript() throws NoSuchMethodException, SecurityException{
		actionKeywords=new ActionKeywords();
		method=actionKeywords.getClass().getMethods();
	}*/
	
	public static void main(String[] args) throws Exception {
	
		//Passing the excel path
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		
		//Declaring String variable for storing Object Repository path
		String Path_OR=Constants.Path_OR;
		//Creating file system object for OR property file
		FileInputStream fs=new FileInputStream(Path_OR);
		//Creating an object of properties 
		OR=new Properties(System.getProperties());
		//Loading all the properties from Object Repository property file into OR object
		OR.load(fs);
		
		//Loop to read the data in the Sheet
		/*for(int iRow=1;iRow<=11;iRow++){
			sActionKeyword=ExcelUtils.getCellData(iRow, Constants.Col_ActionKeyword);		
			sPageObject=ExcelUtils.getCellData(iRow, Constants.Col_PageObject);
			execute_Actions();		
		}*/
		
		DriverScript startEngine=new DriverScript();
		startEngine.execute_TestCase();
		
	}

	private void execute_TestCase() throws Exception {
		
		//Get the total test cases count
		int iTotalTestCases=ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		
		for(int iTestCase=1; iTestCase<=iTotalTestCases;iTestCase++){
			sTestCaseID=ExcelUtils.getCellData(iTestCase, Constants.Col_TCId, Constants.Sheet_TestCases);
			sRunMode=ExcelUtils.getCellData(iTestCase, Constants.Col_RunMode, Constants.Sheet_TestCases);
			if(sRunMode.equals("Y")){
				iTestStep=ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TCId, Constants.Sheet_TestSteps);
				iTestLastStep=ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				for(;iTestStep<=iTestLastStep;iTestStep++){
					System.out.println("Executing"+iTestStep);
					sActionKeyword=ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
					sPageObject=ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject,Constants.Sheet_TestSteps);
					execute_Actions();
				}
			}
		}
		
	}

	private static void execute_Actions() throws Exception {
		actionKeywords=new ActionKeywords();
		method=actionKeywords.getClass().getMethods();
		
		for(int i=0;i<method.length;i++)
		{			
			if(method[i].getName().equals(sActionKeyword))
			{
				method[i].invoke(actionKeywords,sPageObject);
				break;
			}
		}
	}

}

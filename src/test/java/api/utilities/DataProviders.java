package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="AllData")
	public String [][] AllDataProvider()
	{
		
		String fileName = System.getProperty("user.dir")+"//TestData//TestData1.xlsx";
		int ttlRowCnt = ReadExcelFile.getRowCount(fileName, "Sheet1");
		int ttlColCnt = ReadExcelFile.getColCount(fileName, "Sheet1");
		
		String userData[][] = new String[ttlRowCnt-1][ttlColCnt];
		
		for(int rowNo=1;rowNo<ttlRowCnt;rowNo++) {
			for(int colNo=0;colNo<ttlColCnt;colNo++) {
				userData[rowNo-1][colNo] = ReadExcelFile.getCellValue(fileName,"Sheet1",rowNo,colNo);
				
			}
		}
		return userData;
		
	}
	
	@DataProvider(name="UserNamesData")
	public String [] UserNameDataProvider()
	{
		
		String fileName = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttlRowCnt = ReadExcelFile.getRowCount(fileName, "Sheet1");
		//int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet1");
		
		String userNamesData[] = new String[ttlRowCnt-1];
		
		for(int rowNo=1;rowNo<ttlRowCnt;rowNo++) {
			 userNamesData[rowNo-1] = ReadExcelFile.getCellValue(fileName,"Sheet1",rowNo,1);
		}
		return userNamesData;
		
	}
}

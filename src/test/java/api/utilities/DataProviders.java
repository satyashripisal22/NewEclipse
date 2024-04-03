package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	XLUtility xl;
	@DataProvider(name="Data")
	public String [][] getAllData() throws IOException
	{
		//read data from excel
		String path = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		xl = new XLUtility(path);
		int rownum = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1",1);
		
		String apidata[][] = new String[rownum][colcount];//store data in 2-dimensional array
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apidata[i-1][j] = xl.getCellData("Sheet1",i,j);
				
			}
		}
		return apidata;
		
	}
	
	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException
	{
		
		String path = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		xl = new XLUtility(path);
		int rownum = xl.getRowCount("Sheet1");
		//int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet1");
		
		String apidata[] = new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
			 apidata[i-1] = xl.getCellData("Sheet1",i,1);
		}
		return apidata;
		
	}
}










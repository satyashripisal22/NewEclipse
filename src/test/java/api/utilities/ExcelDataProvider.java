package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	 XSSFWorkbook wb;
		public ExcelDataProvider() throws Exception {
			File src=new File(System.getProperty("user.dir")+"\\TestData\\TestData.xlsx");
			try {//C:/Users/ue/eclipse-workspace/WCI_Inc_API_Automation/TestData/Testdata.xlsx
				FileInputStream fis=new FileInputStream(src);
		         wb=new XSSFWorkbook(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		public String getStringData(String Sheetname,int row,int col) {
			return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		public double getNumericData(String Sheetname,int row,int col) {
			long numdata =  (long) wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
			return numdata;
		}
}

package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtility excelUtility = new ExcelUtility(path);
		int rowCount = excelUtility.getRowCount("Sheet1");
		int colCount = excelUtility.getCellCount("Sheet1",1);
		
		String loginData[][] = new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				loginData[i-1][j] = excelUtility.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}

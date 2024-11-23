package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		//Path od excel file
		String path="./testdata/logindata.xlsx";
		//Passing the path to the ExcelUtilities.
		//The path will assign in the ExcelUtility through constructor
		ExcelUtilities excel=new ExcelUtilities(path);
		//Retreiving the number of rows
		int totalRow=excel.getRowCount("sheet1");
		//Retreiving the number of cells
		int totalcell=excel.getCellCount("sheet1", 1);
		
		String[][] loginData=new String[totalRow][totalcell];
		//Exclude the header part
		for(int i=1;i<=totalRow;i++) {
			for(int j=0;j<totalcell;j++) {
	//Storing the array idex at the 0th position
				loginData[i-1][j]=excel.getCellData("sheet1", i, j);
			}
		}
		return loginData;
	}

}

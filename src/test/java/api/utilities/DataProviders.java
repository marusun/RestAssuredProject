package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="AllData")
	public String[][] getAllData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//TestData//UserDetails.xlsx";
		
		XLUtility utitlity=new XLUtility(path);
		
		int rowCount=utitlity.getRowCount("Sheet1");
		int cellCount=utitlity.getCellCount("Sheet1",1);//we can give rowcount also
		String data[][]=new String[rowCount][cellCount];
		
		for(int i=1;i<=rowCount;i++) {
			
			for(int j=0;j<cellCount;j++) {
				data[i-1][j]=utitlity.getCellData("Sheet1", i, j);
			}
		}
		return data;
	}
	
	
	@DataProvider(name="UserNames")
	public String[] geUserNames() throws IOException{
		
		String path=System.getProperty("user.dir")+"//TestData//UserDetails.xlsx";
		
		XLUtility utitlity=new XLUtility(path);
		
		int rowCount=utitlity.getRowCount("Sheet1");
		
		String data[]=new String[rowCount];
		//should give i<=rowCount for readig all data
		for(int i=1;i<=rowCount;i++) {
			
			
				data[i-1]=utitlity.getCellData("Sheet1", i, 1);
			
		}
		return data;
	}
	
	
}

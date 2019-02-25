package testCasePack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import operationPack.AbstractClass;
import operationPack.ReadObject;
import operationPack.UIOperation;

public class HybridTestCase  extends AbstractClass{
	
	@Test(dataProvider="HybridData")
	public void HybridUnderTest(String testcaseID, String testcaseName, String keyword, String objectName, String objectType, String data) throws Exception {
		ReadObject object=new ReadObject();
		Properties allObject=object.getObjectRepository();
		//contractor is driver
		UIOperation operation=new UIOperation(driver);
		operation.PerformKeyWord(allObject, keyword, objectName, objectType, data);
	}
	@DataProvider(name="HybridData")
	public Object[][] getDataByDataProvider() throws IOException{
		Object[][] object = null;
		//what is user.dir
	    
	    File file = new File(System.getProperty("user.dir")+"\\src\\testCasePack\\","HybridData.xlsx");
	    
	    FileInputStream fis=new FileInputStream(file);
	    
		Workbook wb =  new XSSFWorkbook(fis);
	    Sheet ws = wb.getSheet("Data2");
	    
	    int rowCount = ws.getLastRowNum()- ws.getFirstRowNum();
	    int colCount=6;
	    object = new Object[rowCount][colCount];
	    for (int i = 0; i <rowCount; i++) {
	        
	        Row row = ws.getRow(i+1);
	        
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            
	            object[i][j] = row.getCell(j).toString();
	        }
	   }
	    	
	    	return object;    
    }
		
	
}

package com.crm.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
    public static String testDataFilePath="/Users/chiragbansod/Documents/workspace/FreeCRMMavenProject/src/main/java/com/crm/qa/testdata/FreeCRMTestData.xlsx";
    static Workbook book;
    static Sheet sheet;
    
	public void switchtoFrame(String framename) {
		driver.switchTo().frame(framename);
	}

	public boolean waitforElement(WebElement element) {
		WebDriverWait waitforelement=new WebDriverWait(driver,Duration.ofSeconds(IMPLICIT_WAIT));
	    return waitforelement.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
	}
	
	public static Object[][] getTestData(String sheetname) throws FileNotFoundException{
		FileInputStream file=null;
        try {
        	file=new FileInputStream(testDataFilePath);
        }catch (FileNotFoundException e) {
			// TODO: handle exception
        	e.printStackTrace();
		}		
	    try {
	    	book=WorkbookFactory.create(file);
	    	
	    }catch(InvalidFormatException e) {
	    	e.printStackTrace();
	    }catch (IOException e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    sheet=book.getSheet(sheetname);
	    Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	    for(int i=0;i<sheet.getLastRowNum();i++) {
	    	for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
	    		data[i][j]=sheet.getRow(i+1).getCell(j).toString();
	    	}
	    }
	return data;
	
	}

	public static void takesScreenshotAtEndOfTest() throws IOException {
		File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDirectory=System.getProperty("user.dir");
		FileUtils.copyFile(scrfile, new File(currentDirectory +"/screenshots/"+System.currentTimeMillis()+".png"));
	}
}




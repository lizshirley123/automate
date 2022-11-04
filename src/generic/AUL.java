package generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class AUL {
	public static String getProperties(String path,String key) {
		String v="";
		try {
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			v = p.getProperty(key);
		}
		catch(Exception e) {}
		return v;
	}
	public static void takePhoto(String path,String testName,WebDriver driver)  {
		String dateTime=new Date().toString().replaceAll(":", "-");
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		String dstFile = path+testName+dateTime+".png";
		try {
			FileHandler.copy(srcFile,new File(dstFile));
		}
		catch(Exception e) {}
	}

	public static String getCellData(String path,String sheet,int r,int c) {
		String str="";
		try {
			Workbook wb = WorkbookFactory.create(new File(path));
			str = wb.getSheet(sheet).getRow(r).getCell(c).toString();
			wb.close();
		}
		catch(Exception e) {

		}
		return str;
	}
	public static int getRowCount(String path,String sheet) {
		int  rc=0;
		try {
			Workbook wb = WorkbookFactory.create(new File(path));
			rc = wb.getSheet(sheet).getLastRowNum();
			wb.close();
		}
		catch(Exception e) {}
		return rc;
	}
}
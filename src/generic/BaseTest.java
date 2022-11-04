package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class BaseTest implements IAutoConst{
	public WebDriver driver;
	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	@Parameters({"browser","ip"})
	@BeforeMethod(alwaysRun=true)
	public void openAp(String browser,String ip) throws MalformedURLException{
		URL remote=new URL("http://"+ip+":4444/wd/hub");
		MutableCapabilities mc;
		if(browser.equals("chrome")) {
			mc=new ChromeOptions();
			mc.setCapability("browserName","chrome");
		}
		else {
			mc=new FirefoxOptions();
			mc.setCapability("browserName","firefox");
		}
		driver=new RemoteWebDriver(remote,mc);
		String AUT=AUL.getProperties(SETTING_PATH, "AUT");
		Reporter.log(AUT, true);
		driver.get(AUT);
		String	SITO=AUL.getProperties(SETTING_PATH, "ITO");
		Long ITO=Long.parseLong(SITO);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
	}
	@AfterMethod
	public void closeAp(ITestResult res) {
		String testName=res.getName();
		if(res.getStatus()==2) {
			AUL.takePhoto(PHOTO_PATH, testName, driver);
		}
		driver.quit();
	}
}
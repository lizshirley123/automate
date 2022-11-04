package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.AUL;
import generic.IAutoConst;


public class LoginPage {
	@FindBy(id="username")
	private WebElement untb;

	@FindBy(name="pwd")
	private WebElement pwtb;

	@FindBy(xpath="//div[.='Login ']")
	private WebElement loginbtn;

	@FindBy(xpath="//span[contains(text(),'invalid')]")
	private WebElement errmsg;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public void setUsername(String un) {
		untb.sendKeys(un);
	}

	public void setPassword(String pd) {
		pwtb.sendKeys(pd);
	}

	public void clickLogin() {
		loginbtn.click();
	}

	public void verifyErrMsg(WebDriver driver) {
		String stETO=AUL.getProperties(IAutoConst.SETTING_PATH,"ETO");
		Long ETO=Long.parseLong(stETO);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(ETO));
		try {
			wait.until(ExpectedConditions.visibilityOf(errmsg));
			Reporter.log("Error message is displayed", true);
		}
		catch(Exception e) {
			Reporter.log("Error message not displayed", true);
			Assert.fail();
		}
	}
}

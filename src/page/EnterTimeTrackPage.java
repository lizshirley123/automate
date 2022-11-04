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


public class EnterTimeTrackPage {
	@FindBy(xpath="//div[contains(text(),'Help')]")
	private WebElement help;

	@FindBy(xpath="//a[contains(text(),'About your actiTIME')]")
	private WebElement abt;

	@FindBy(xpath="//span[starts-with(.,'actiTIME')]")
	private WebElement ver;

	@FindBy(xpath="//td[@class='close-button']")
	private WebElement close;

	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logout;

	public EnterTimeTrackPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public void clickHelp() {
		help.click();
	}
	public void clickAbout() {
		abt.click();
	}
	public void version(String eversion) {
		String aversion=ver.getText();
		Assert.assertEquals(eversion, aversion);
	}
	public void clickClose() {
		close.click();
	}
	public void clickLogout() {
		logout.click();
	}
	public void verifyTitle(WebDriver driver,String eTitle,Long ETO) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(ETO));
		try {
			wait.until(ExpectedConditions.titleIs(eTitle));
			Reporter.log("Home page loaded", true);
		}
		catch(Exception e) {
			Reporter.log("Home page not loaded", true);
			Assert.fail();
		}
	}
}
package script;

import org.testng.annotations.Test;

import generic.AUL;
import generic.BaseTest;
import page.EnterTimeTrackPage;
import page.LoginPage;

public class ValidLogin extends BaseTest {
	@Test(priority=1)
	public void testValidLogin() throws Exception{
		String un = AUL.getCellData(XL_PATH,"ValidLogin", 1, 0);
		String pd = AUL.getCellData(XL_PATH,"ValidLogin", 1, 1);
		String aTitle = AUL.getCellData(XL_PATH,"ValidLogin", 1, 2);
		String sETO = AUL.getProperties(SETTING_PATH,"ETO");
		Long ETO=Long.parseLong(sETO);
		LoginPage l=new LoginPage(driver);
		l.setUsername(un);
		l.setPassword(pd);
		l.clickLogin();
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.verifyTitle(driver,aTitle,ETO);
	}
}

package script;

import org.testng.annotations.Test;

import generic.AUL;
import generic.BaseTest;
import page.LoginPage;

public class InvalidLogin extends BaseTest {

	@Test(priority=2)
	public void testInvlaidlogin() {
		int rc=AUL.getRowCount(XL_PATH, "InvalidLogin");
		for(int i=1;i<=rc;i++) {
			String un=AUL.getCellData(XL_PATH,"InvalidLogin",i, 0);
			String pw=AUL.getCellData(XL_PATH,"InvalidLogin",i, 1);
			LoginPage l=new LoginPage(driver);
			l.setUsername(un);
			l.setPassword(pw);
			l.clickLogin();
			try {
				Thread.sleep(2000);
			} catch (Exception e) {}
				l.verifyErrMsg(driver);
			}
		}
	}

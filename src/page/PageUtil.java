package page;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import helper.Browser;
import test.TestBase;

public class PageUtil {
	public static void quickSignIn(RemoteWebDriver driver, Browser browser, String email, String password, boolean isStaging) {
		browser.goPage(SignInPage.pageUrl);
	    SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	    signInPage.fillEmail(email)
	              .fillPassword(password)
	              .clickLogIn();
	    browser.checkPageIsOpened(MyFeedPage.pageUrl, TestBase._t(MyFeedPage.getPageLoadedText(isStaging)));
	}
}

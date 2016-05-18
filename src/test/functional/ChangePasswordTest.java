package test.functional;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import page.ChangePasswordPage;
import page.PageUtil;
import page.SignInPage;
import test.ProductionTest;

public class ChangePasswordTest extends ProductionTest{
  private ChangePasswordPage page;
  private void bindingPageToHTML() {
    page = new ChangePasswordPage(driver, _t(ChangePasswordPage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() throws InterruptedException {
	PageUtil.quickSignIn(driver, browser, email, password, isStaging);
    browser.goPage(ChangePasswordPage.pageUrl);
    browser.checkPageIsOpened(ChangePasswordPage.pageUrl, _t(ChangePasswordPage.pageLoadedText));
    Thread.sleep(1000);
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(ChangePasswordPage.pageUrl);
    browser.waitPageLoaded(ChangePasswordPage.pageUrl);
    bindingPageToHTML();
  }

  @Test
  public void test01_emptyInput() {
    page.saveChange();
    assertTrue(driver.findElementByClassName("error-message").getText().contains(_t("Password is incorrect")));
  }

  @Test
  public void test02_successChangedPassword() throws InterruptedException {
    String newPassword = "12345678";

    page.typeOldPassword(password)
        .typeNewPassword(newPassword)
        .saveChange();
    browser.waitPageLoaded("/" + userDomain);
    browser.goPage("/sessions");
    driver.navigate().refresh();
    browser.goPage(SignInPage.pageUrl);

    SignInPage signInPage = new SignInPage(driver, _t(SignInPage.pageLoadedText));
    PageFactory.initElements(driver, signInPage);
    signInPage.fillEmail(email)
              .fillPassword(newPassword)
              .clickLogIn();
    before();
    page.typeOldPassword(newPassword)
        .typeNewPassword(password)
        .saveChange();
    browser.waitPageLoaded("/" + userDomain);
    browser.goPage("/sessions");
    driver.navigate().refresh();

    browser.goPage(SignInPage.pageUrl);
    signInPage = new SignInPage(driver, _t(SignInPage.pageLoadedText));
    PageFactory.initElements(driver, signInPage);
    signInPage.fillEmail(email)
              .fillPassword(password)
              .clickLogIn();
  }
}

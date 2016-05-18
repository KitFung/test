package test.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.MyFeedPage;
import page.SignInPage;
import page.SignUpPage;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInTest extends ProductionTest{
  private final String warningColor = "rgb(255, 0, 0)";
  private SignInPage page;
  /**
   * Call this when the page have been reload or just load.
   * @throws IOException 
   * @throws UnsupportedEncodingException 
   */
  private void bindingPageToHTML() {
    page = new SignInPage(driver, _t(SignInPage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Before
  public void before() throws InterruptedException {
    driver.manage().deleteAllCookies();
    //Clear tabs

    browser.goPage(SignInPage.pageUrl);
    browser.waitPageLoaded(SignInPage.pageUrl);
//    /* This page have strange behavior */
//    Thread.sleep(5000);
    bindingPageToHTML();
  }

  private void confirmTheErrorMessageIsClosable(WebElement errorMsg) {
    page.closeErrorMessage();

    (new WebDriverWait(driver, 10)).until(
        ExpectedConditions.not(ExpectedConditions.visibilityOf(errorMsg)));
  }

  private void confirmTheSignUpBoxIsAppear() throws InterruptedException {
    (new WebDriverWait(driver, 5)).until(
        ExpectedConditions.not(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("signup-container-wrapper"))));
    Thread.sleep(4000);
    (new WebDriverWait(driver, 5)).until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("signup-container-wrapper")));
  }

  @Test
  public void test01_emptyDetail() {
    page.clickLogIn();
    bindingPageToHTML();
    WebElement errorMsg = page.getErrorMessageElement();
    assertTrue(errorMsg.isDisplayed());
    assertEquals(_t("You did not provide any details for authentication."), errorMsg.getText());
    confirmTheErrorMessageIsClosable(errorMsg);
  }

  @Test
  public void test02_emptyEmail() {
    page.fillPassword(password).clickLogIn();
    bindingPageToHTML();
    assertEquals(warningColor, page.getBorderColorOfEmail());
    exception.expect(NoSuchElementException.class);
    page.getErrorMessageElement().isDisplayed();
  }

  @Test
  public void test03_notExistEmail() {
    String notExistEmail = "noexist";
    String expectedErrorMsg = _t("Email is not correct, we don't have any user registered with 'noexist'.");
    page.fillEmail(notExistEmail)
        .fillPassword(password)
        .clickLogIn();
    bindingPageToHTML();
    WebElement errorMsg = page.getErrorMessageElement();
    assertTrue(errorMsg.isDisplayed());
    assertEquals(expectedErrorMsg, errorMsg.getText());
    assertEquals(warningColor, page.getBorderColorOfEmail());
    confirmTheErrorMessageIsClosable(errorMsg);
  }

  @Test
  public void test04_wrongPassword() {
    String wrongPassword = "wrongPassword";
    page.fillEmail(email)
        .fillPassword(wrongPassword)
        .clickLogIn();
    bindingPageToHTML();
    WebElement errorMsg = page.getErrorMessageElement();
    assertTrue(errorMsg.isDisplayed());
    assertTrue(errorMsg.getText().contains(_t("Password is not correct.")));
    assertEquals(warningColor, page.getBorderColorOfPassword());
    confirmTheErrorMessageIsClosable(errorMsg);
  }

  @Test
  public void test05_emptyPassword() {
    page.fillEmail(email)
        .clickLogIn();
    bindingPageToHTML();
    WebElement errorMsg = page.getErrorMessageElement();
    assertTrue(errorMsg.isDisplayed());
    assertTrue(errorMsg.getText().contains(_t("Password is not correct.")));
    assertEquals(warningColor, page.getBorderColorOfPassword());
    confirmTheErrorMessageIsClosable(errorMsg);
  }

  @Test
  public void test06_successLogin() {
    page.fillEmail(email)
        .fillPassword(password)
        .clickLogIn();
    browser.checkPageIsOpened("", MyFeedPage.getPageLoadedText(isStaging));
  }

/**
 * This doesn't check the function of remember me.
 * This just check whether remember will affect regular login.
 * @throws IOException 
 * @throws UnsupportedEncodingException 
 */
  @Test
  public void test07_successLoginWithOutRememberMe() {
    page.fillEmail(email)
    .fillPassword(password)
    .enableRememberMe(false)
    .clickLogIn();
    browser.checkPageIsOpened("", MyFeedPage.getPageLoadedText(isStaging));
  }

  @Test
  public void test08_clickHomeBtnWithOutLogin() {
    page.clickHomeBtn();
    browser.checkPageIsOpened("", _t("Using Facebook makes it easier to find friends"));
  }

  @Test
  public void test09_clickMyFeedWithOutLogin() {
    page.clickMyFeed();
    browser.checkPageIsOpened("", _t("Using Facebook makes it easier to find friends"));
  }

  @Test
  public void test10_clickExploreWithOutLogin() throws InterruptedException {
    page.clickExplore();
    browser.checkPageIsOpened("/all_added", _t("Art recently added from across the ArtStack community"));
    confirmTheSignUpBoxIsAppear();
  }

  @Test
  public void test11_clickExhibitionsWithOutLogin() throws InterruptedException {
    page.clickExhibitions();
    browser.checkPageIsOpened("/exhibitions/on_show", _t("On Show Exhibitions"));
    confirmTheSignUpBoxIsAppear();
  }

  @Test
  public void test12_clickTrendingWithOutLogin() throws InterruptedException{
    page.clickTrending();
    browser.checkPageIsOpened("/trending", _t("Art trending from across the ArtStack community"));
    confirmTheSignUpBoxIsAppear();
  }

  @Test
  public void test13_clickAddArtworkWithOutLogin() {
    page.clickAddArtwork();
    browser.checkPageIsOpened(SignUpPage.pageUrl, SignUpPage.pageLoadedText);
  }

  @Test
  public void test14_clickAddFriendsWithOutLogin() {
    page.clickAddFriends();
    browser.checkPageIsOpened(SignUpPage.pageUrl, SignUpPage.pageLoadedText);
  }

  @Test
  public void test15_goForgetPasswordPage() {
    page.clickForgotPassword();
    browser.checkPageIsOpened("/users/forgot_password", _t("Reset your password in ArtStack"));
  }

}

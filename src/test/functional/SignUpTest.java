package test.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.SignUpPage;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTest extends ProductionTest{

  /* Please update this one every time you run test */
  private static String newEmail;

  private SignUpPage page;

  /**
   * Call this when the page have been reload or just load.
   * @throws IOException 
   * @throws UnsupportedEncodingException 
   */
  private void bindingPageToHTML() {
    page = new SignUpPage(driver, SignUpPage.pageLoadedText);
    PageFactory.initElements(driver, page);
  }

  @Before
  public void before() throws InterruptedException, UnsupportedEncodingException, IOException {
    newEmail = properties.getProperty("SIGNUP_NEW_EMAIL");

    driver.manage().deleteAllCookies();
    //Clear tabs

    browser.goPage(SignUpPage.pageUrl);
    browser.waitPageLoaded(SignUpPage.pageUrl);
    /* This page have strange behavior */
    Thread.sleep(5000);
    bindingPageToHTML();
  }

  @After
  public void after() {
    browser.closeAllOtherTab();
  }

  @Test
  public void test01_emptyFirstName() {
    page.fillFirstName("")
      .fillLastName(lastName)
      .fillEmail(newEmail)
      .fillPassword(password)
      .clickCreateAccount();

    WebElement errorMsg = driver.findElement(By.cssSelector(".left-col .error-message"));
    assertTrue(errorMsg.isDisplayed());
    assertEquals("Required field" ,errorMsg.getText());
  }

  @Test
  public void test02_emptyEmail() {
    page.fillFirstName(firstName)
    .fillLastName(lastName)
    .fillEmail("")
    .fillPassword(password)
    .clickCreateAccount();

    WebElement errorMsg = driver.findElement(By.cssSelector("#new_user > div:nth-child(3) .error-message"));
    assertTrue(errorMsg.isDisplayed());
    assertEquals("Required field" ,errorMsg.getText());
  }

  @Test
  public void test03_repeatedEmail() {
    page.fillFirstName(firstName)
    .fillLastName(lastName)
    .fillEmail(email)
    .fillPassword(password)
    .clickCreateAccount();
    WebElement errorMsg = driver.findElement(By.cssSelector("#new_user > div:nth-child(3) .error-message"));
    assertTrue(errorMsg.isDisplayed());
    assertEquals("Email already used - to sign in use the 'login' link at the top left of the screen",
        errorMsg.getText());
  }

  @Test
  public void test04_emptyPassword() {
    page.fillFirstName(firstName)
    .fillLastName(lastName)
    .fillEmail(newEmail)
    .fillPassword("")
    .clickCreateAccount();

    WebElement errorMsg = driver.findElement(By.cssSelector("#new_user > div:nth-child(4) .error-message"));
    assertTrue(errorMsg.isDisplayed());
    assertEquals("Required field" ,errorMsg.getText());
  }

  @Test
  public void test05_leastThan4WordPassword() {
    page.fillFirstName(firstName)
    .fillLastName(lastName)
    .fillEmail(newEmail)
    .fillPassword("123")
    .clickCreateAccount();

    WebElement errorMsg = driver.findElement(By.cssSelector("#new_user > div:nth-child(4) .error-message"));
    assertTrue(errorMsg.isDisplayed());
    assertEquals("Is too short (minimum is 4 characters)" ,errorMsg.getText());
  }

  @Test
  public void test06_successSignUp() {
    page.fillFirstName(firstName)
    .fillLastName(lastName)
    .fillEmail(newEmail)
    .fillPassword(password)
    .clickCreateAccount();

    WebElement chooseCategory = new WebDriverWait(driver, 30)
        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".header .tag-line")));
    assertTrue(chooseCategory.isDisplayed());
    assertEquals("Choose at least one category of art you like" ,chooseCategory.getText());
  }

  // Since below page isn't important, just check the URL and one element now.
  @Test
  public void test07_goContactPage() throws InterruptedException {
    page.clickContact();
    String keySentence = "We're here to answer any questions you might have and would love to hear your thoughts about ArtStack.";
    browser.checkPageIsOpenedInNewTag("/pages/contact", keySentence);
  }

  @Test
  public void test08_goAboutPage() throws InterruptedException {
    page.clickAbout();
    String keySentence = "Find new works that inspire you, follow interesting people and see all the art you like on your profile.";
    browser.checkPageIsOpenedInNewTag("/pages/about", keySentence);
  }

  @Test
  public void test09_goTermsPage() throws InterruptedException {
    page.clickTerms();
    String keySentence = "TERMS OF USE";
    browser.checkPageIsOpenedInNewTag("/pages/terms", keySentence);
  }

  @Test
  public void test10_goPrivacyPage() throws InterruptedException {
    page.clickPrivacy();
    String keySentence = "Questions, comments and requests regarding this privacy policy are welcome and should be addressed to hello@theartstack.com.";
    browser.checkPageIsOpenedInNewTag("/pages/privacy", keySentence);
  }

  @Test
  public void test11_translateZHCN() {
    page.switchLanguageCH();
    bindingPageToHTML();
    WebElement title = driver.findElement(By.cssSelector("#user-signup > h2"));
    assertEquals("创建您的帐户", title.getText());
  }

  @Test
  public void test12_translateZHTW() {
    page.switchLanguageTW();
    bindingPageToHTML();
    WebElement title = driver.findElement(By.cssSelector("#user-signup > h2"));
    assertEquals("創建您的帳戶", title.getText());
  }

  @Test
  public void test13_translateES() {
    page.switchLanguageES();
    bindingPageToHTML();
    WebElement title = driver.findElement(By.cssSelector("#user-signup > h2"));
    assertEquals("Crea tu cuenta", title.getText());
  }

  @Test
  public void test14_translateBackToEN() throws InterruptedException{
    page.switchLanguageES();
    bindingPageToHTML();
    WebElement titleBefore = driver.findElement(By.cssSelector("#user-signup > h2"));
    assertEquals("Crea tu cuenta", titleBefore.getText());
    page.switchLanguageENG();
    bindingPageToHTML();
    WebElement titleAfter = driver.findElement(By.cssSelector("#user-signup > h2"));
    assertEquals("or create your account", titleAfter.getText());
  }

  @Test
  public void test15_goLoginPage() {
    page.clickLoginUnderForm();
    browser.checkPageIsOpened("/signin", "Login to ArtStack");
  }

}

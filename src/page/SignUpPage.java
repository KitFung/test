package page;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage extends Page{

  public final static String pageUrl = "/signup";

  public final static String pageLoadedText = "The world's favourite art";

  @FindBy(id = "guest-nav")
  @CacheLookup
  private WebElement navBar;

  /* Item in sign up form*/
  @FindBy(id = "user_first_name")
  @CacheLookup
  private WebElement signUpFirstName;

  @FindBy(id = "user_last_name")
  @CacheLookup
  private WebElement signUpLastName;

  @FindBy(id = "user_email")
  @CacheLookup
  private WebElement signUpEmail;

  @FindBy(id = "user_password")
  @CacheLookup
  private WebElement signUpPassword;

  @FindBy(css = ".signup-content input[name='commit']")
  @CacheLookup
  private WebElement signUpCommit;

  /* Already have an account? Log in */
  @FindBy(css = ".login a")
  @CacheLookup
  private WebElement loginLinkUnderForm; 

  /* Four minor page in right-bottom */
  @FindBy(css = "a[href='/pages/contact']")
  @CacheLookup
  private WebElement contact;

  @FindBy(css = "a[href='/pages/about']")
  @CacheLookup
  private WebElement about;

  @FindBy(css = "a[href='/pages/terms']")
  @CacheLookup
  private WebElement terms;

  @FindBy(css = "a[href='/pages/privacy']")
  @CacheLookup
  private WebElement privacy;

  /* Language options */
  @FindBy(css = "a[href='/settings/set_locale/zh-CN']")
  @CacheLookup
  private WebElement zhcn;

  @FindBy(css = "a[href='/settings/set_locale/zh-TW']")
  @CacheLookup
  private WebElement zhtw;

  @FindBy(css = "a[href='/settings/set_locale/es']")
  @CacheLookup
  private WebElement es;

  @FindBy(css = "a[href='/settings/set_locale/en']")
  @CacheLookup
  private WebElement eng;

  public SignUpPage(WebDriver driver) {
    this(driver, pageLoadedText);
  }

  public SignUpPage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
  }

  public static String getPageurl() {
    return pageUrl;
  }

  public SignUpPage fillFirstName(String fname) {
    signUpFirstName.click();
    signUpFirstName.sendKeys(fname);
    return this;
  }

  public SignUpPage fillLastName(String lname) {
    signUpLastName.click();
    signUpLastName.sendKeys(lname);
    return this;
  }

  public SignUpPage fillEmail(String email) {
    signUpEmail.click();
    signUpEmail.sendKeys(email);
    return this;
  }

  public SignUpPage fillPassword(String password) {
    signUpPassword.click();
    signUpPassword.sendKeys(password);
    return this;
  }

  public SignUpPage clickCreateAccount() {
    signUpCommit.click();
    return this;
  }

  public SignUpPage clearForm() {
    signUpFirstName.clear();
    signUpLastName.clear();
    signUpEmail.clear();
    signUpPassword.clear();
    return this;
  }

  public SignUpPage clickLoginUnderForm() {
    loginLinkUnderForm.click();
    return this;
  }

  public SignUpPage openNavBar() {
    navBar.click();
    return this;
  }

  public SignUpPage clickContact() {
    contact.click();
    return this;
  }

  public SignUpPage clickAbout() {
    about.click();
    return this;
  }

  public SignUpPage clickTerms() {
    terms.click();
    return this;
  }

  public SignUpPage clickPrivacy() {
    privacy.click();
    return this;
  }

  public SignUpPage switchLanguageENG() {
    eng.click();
    return this;
  }

  public SignUpPage switchLanguageCH() {
    zhcn.click();
    return this;
  }

  public SignUpPage switchLanguageTW() {
    zhtw.click();
    return this;
  }

  public SignUpPage switchLanguageES() {
    es.click();
    return this;
  }
}

package page;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends Page{

  public final static String pageUrl = "/signin";
  public final static String pageLoadedText = "Login with your facebook account";

    /* Item in the login div */
    @FindBy(css = ".prettyform #user_session_email")
    @CacheLookup
    private WebElement loginEmail;

    @FindBy(css = ".prettyform #user_session_password")
    @CacheLookup
    private WebElement loginPassword;

    @FindBy(css = ".prettyform #user_session_remember_me")
    @CacheLookup
    private WebElement logInRememberMe;

    @FindBy(css = ".prettyform input[name='commit']")
    @CacheLookup
    private WebElement logInCommit;

    @FindBy(css = ".prettyform div:nth-of-type(4) a")
    @CacheLookup
    private WebElement forgotPassword;

    /* The useless navigation bar on the top of the page */
    @FindBy(css = "#left-console a")
    @CacheLookup
    private WebElement artstackHome;

    @FindBy(id = "q")
    @CacheLookup
    private WebElement searchBox;

    @FindBy(css = "#middle-menu a:nth-of-type(1)")
    @CacheLookup
    private WebElement myFeed;

    @FindBy(css = "#middle-menu a:nth-of-type(2)")
    @CacheLookup
    private WebElement explore;

    @FindBy(css = "#middle-menu a:nth-of-type(3)")
    @CacheLookup
    private WebElement exhibitions;

    @FindBy(css = "#middle-menu a:nth-of-type(4)")
    @CacheLookup
    private WebElement trending;

    @FindBy(css = "a[class='add-artwork ']")
    @CacheLookup
    private WebElement addArtwork;

    @FindBy(css = "a[class='invite ']")
    @CacheLookup
    private WebElement addFriends;

    @FindBy(css = ".message.error .text")
    private WebElement errorMessage;

    @FindBy(css = ".message.error .close")
    private WebElement closeErrorMessage;

    public SignInPage(WebDriver driver) {
      this(driver, pageLoadedText);
    }

    public SignInPage(WebDriver driver, String customPageLoadedText) {
      super(driver, pageUrl, customPageLoadedText);
    }

    public SignInPage fillEmail(String email) {
      loginEmail.click();
      loginEmail.sendKeys(email);
      return this;
    }

    public SignInPage fillPassword(String password) {
      loginPassword.click();
      loginPassword.sendKeys(password);
      return this;
    }

    public SignInPage enableRememberMe(boolean enable) {
      if(enable != logInRememberMe.isSelected()) {
        logInRememberMe.click();
      }
      return this;
    }

    public SignInPage clickForgotPassword() {
      forgotPassword.click();
      return this;
    }

    public SignInPage clickLogIn() {
      logInCommit.click();
      return this;
    }

    public SignInPage clickHomeBtn() {
      artstackHome.click();
      return this;
    }

    public SignInPage clickMyFeed() {
      myFeed.click();
      return this;
    }

    public SignInPage clickExplore() {
      explore.click();
      return this;
    }

    public SignInPage clickExhibitions() {
      exhibitions.click();
      return this;
    }

    public SignInPage clickTrending() {
      trending.click();
      return this;
    }

    public SignInPage clickAddArtwork() {
      addArtwork.click();
      return this;
    }

    public SignInPage clickAddFriends() {
      addFriends.click();
      return this;
    }

    public SignInPage closeErrorMessage() {
      closeErrorMessage.click();
      return this;
    }

    public String getBorderColorOfEmail() {
      return loginEmail.getCssValue("border-color");
    }

    public String getBorderColorOfPassword() {
      return loginPassword.getCssValue("border-color");
    }

    public WebElement getErrorMessageElement() {
      return errorMessage;
    }
}

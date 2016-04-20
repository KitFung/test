package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page.component.INavigationBarComponent;
import page.component.NavigationBarComponent;

public class ChangePasswordPage extends Page implements INavigationBarComponent {

  private NavigationBarComponent navigationBar;
  public final static String pageUrl = "/settings/password";
  
  public final static String pageLoadedText = "Change password";

  @FindBy(id = "user_old_password")
  @CacheLookup
  private WebElement oldPasswordField;

  @FindBy(id = "user_password")
  @CacheLookup
  private WebElement newPasswordField;

  @FindBy(css = ".actionset > input[type='submit']")
  @CacheLookup
  private WebElement saveBtn;

  public ChangePasswordPage(WebDriver driver) {
    this(driver, pageLoadedText);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public ChangePasswordPage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public ChangePasswordPage typeOldPassword(String password) {
    oldPasswordField.clear();
    oldPasswordField.sendKeys(password);
    return this;
  }

  public ChangePasswordPage typeNewPassword(String password) {
    newPasswordField.clear();
    newPasswordField.sendKeys(password);
    return this;
  }

  public ChangePasswordPage saveChange() {
    saveBtn.click();
    return this;
  }

  @Override
  public INavigationBarComponent searchArtwork(String query) {
    navigationBar.searchArtwork(query);
    return null;
  }
}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import constant.Language;
import page.component.INavigationBarComponent;
import page.component.NavigationBarComponent;

public class EditProfilePage extends Page implements INavigationBarComponent{
  private NavigationBarComponent navigationBar;
  public final static String pageUrl = "/settings/profile";
  
  public final static String pageLoadedText = "Edit Profile";

  @FindBy(id = "user_profile_attributes_avatar")
  @CacheLookup
  private WebElement chooseProfilePictureBtn;

  @FindBy(css = ".work-in-mind")
  @CacheLookup
  private WebElement chooseArtworkFromStackBtn;

  @FindBy(id = "user_profile_attributes_cover_picture")
  @CacheLookup
  private WebElement chooseArtworkFromLocalBtn;

  @FindBy(id = "user_username")
  @CacheLookup
  private WebElement usernameField;

  @FindBy(id = "user_profile_attributes_first_name")
  @CacheLookup
  private WebElement firstnameField;

  @FindBy(id = "user_profile_attributes_last_name")
  @CacheLookup
  private WebElement lastnameField;

  @FindBy(css = ".prettyform > div:nth-child(13) > a")
  @CacheLookup
  private WebElement editPasswordLink;

  @FindBy(id = "user_locale")
  @CacheLookup
  private WebElement languageSwitcher;

  @FindBy(css = "#mute-settings > div.actionset > input")
  @CacheLookup
  private WebElement saveBtn;

  public EditProfilePage(WebDriver driver) {
    this(driver, pageLoadedText);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public EditProfilePage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public EditProfilePage uploadProfileImage(String imagePath) {
    chooseProfilePictureBtn.sendKeys(imagePath);
    return this;
  }

  public EditProfilePage clickUploadProfilePictureFromStack(String artworkName) {
    chooseArtworkFromStackBtn.click();
    return this;
  }

  public EditProfilePage uploadProfilePictureFromLocal(String imagePath) {
    chooseArtworkFromLocalBtn.sendKeys(imagePath);
    return this;
  }

  public EditProfilePage editUserName(String newName) {
    usernameField.clear();
    usernameField.sendKeys(newName);
    return this;
  }

  public EditProfilePage clickEditPassword(String newPassword) {
    editPasswordLink.click();
    return this;
  }

  public EditProfilePage editFirstNameLastName(String firstName, String lastName) {
    firstnameField.clear();
    firstnameField.sendKeys(firstName);
    lastnameField.clear();
    lastnameField.sendKeys(lastName);
    return this;
  }

  public EditProfilePage changeLanguage(Language lang) {
    Select select = new Select(languageSwitcher);
    select.selectByValue(lang.toShortCode());
    return this;
  }

  public EditProfilePage saveChange() {
    saveBtn.click();
    return this;
  }

  @Override
  public INavigationBarComponent searchArtwork(String query) {
    navigationBar.searchArtwork(query);
    return null;
  }

}

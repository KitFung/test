package test.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import constant.Language;
import page.EditProfilePage;
import page.MyFeedPage;
import page.SignInPage;
import test.ProductionTest;
import test.TestBase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditProfileTest extends ProductionTest{
  private EditProfilePage page;

  private void bindingPageToHTML() {
    page = new EditProfilePage(driver, _t(EditProfilePage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() throws InterruptedException {
    browser.goPage(SignInPage.pageUrl);
    SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
    signInPage.fillEmail(email)
              .fillPassword(password)
              .clickLogIn();
    browser.checkPageIsOpened(MyFeedPage.pageUrl, _t(MyFeedPage.pageLoadedText));
    browser.goPage(EditProfilePage.pageUrl);
    browser.checkPageIsOpened(EditProfilePage.pageUrl, _t(EditProfilePage.pageLoadedText));
    Thread.sleep(1000);
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(EditProfilePage.pageUrl);
    browser.waitPageLoaded(EditProfilePage.pageUrl);
    bindingPageToHTML();
  }

  /**
   * Remind: This haven't check the similarity of the image before and after.
   */
  @Test
  public void test01_updateProfilePicture() throws InterruptedException {
    page.uploadProfileImage(this.fullFilePath("artwork2.jpg"));
    page.saveChange();
    browser.waitPageLoaded("/" + userDomain);
    before();
    page.uploadProfileImage(this.fullFilePath("artwork.jpg"));
    page.saveChange();
  }

  @Test
  public void test02_updateCoverPicture() throws InterruptedException {
    page.uploadProfilePictureFromLocal(this.fullFilePath("artwork2.jpg"));
    page.saveChange();
    browser.waitPageLoaded("/" + userDomain);
    before();
    page.uploadProfilePictureFromLocal(this.fullFilePath("artwork.jpg"));
    page.saveChange();
  }

  @Test
  public void test03_updateUserName() throws InterruptedException {
    page.editUserName("uniquenameunique");
    page.saveChange();
    browser.waitPageLoaded("/uniquenameunique");
    before();
    page.editUserName(userDomain);
    page.saveChange();
    browser.waitPageLoaded("/" + userDomain);
  }

  @Test
  public void test04_updateFirstNameLastName() throws InterruptedException {
    page.editFirstNameLastName("tmpFirst", "tmpLast");
    page.saveChange();
    browser.waitPageLoaded("/" + userDomain);
    assertEquals("tmpFirst tmpLast", driver.findElementByCssSelector("#display-name > a").getText());
    before();
    page.editFirstNameLastName(firstName, lastName);
    page.saveChange();
    browser.waitPageLoaded("/" + userDomain);
    assertEquals(firstName + " " + lastName, driver.findElementByCssSelector("#display-name > a").getText());
  }

  @Test
  public void test05_switchLanguage() throws InterruptedException {
    Language oldLang = TestBase.currentLanguage;
    page.changeLanguage(Language.CHINESE_TW);
    page.saveChange();
    TestBase.currentLanguage = Language.CHINESE_TW;
    browser.waitPageLoaded("/" + userDomain);
    before();
    page.changeLanguage(oldLang);
    page.saveChange();
    TestBase.currentLanguage = oldLang;
    browser.waitPageLoaded("/" + userDomain);
  }
}

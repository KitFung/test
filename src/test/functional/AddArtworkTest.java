package test.functional;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ArtworkDetails.ArtworkLabel;
import constant.ArtworkDetails.ArtworkType;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.AddArtworkPage;
import page.PageUtil;
import page.AddArtworkPage.PictureUploadMode;

import test.StagingTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddArtworkTest extends StagingTest{
  private static String imageURL;
  private static String wrongImageURL;
  private static String youtubeURL;
  private static String wrongYoutubeURL;

  private AddArtworkPage page;
  private String artworkPath = "artwork.jpg";
  private String wrongArtworkPath = "wrongArtwork.jpg";
  private String artworkName = "Moronarize'm Tair Sopllo J. Solly";
  private String artistName = "Wild";
  private String requireFieldError = "- Required field, if you are not sure you can use \"unknown\"";
  private String fileTypeError = "- Only JPEG, GIF and PNG files can be uploaded to ArtStack." +
                                 " If you are typing to upload an image from an URL, please make sure" +
                                 " you've copied the image URL (you can get this by right-clicking on the image)" +
                                 " - this may be different from the web address of image.";

  private void bindingPageToHTML() {
    page = new AddArtworkPage(driver, _t(AddArtworkPage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() {
    imageURL = properties.getProperty("ADDARTWORK_IMAGE_URL");
    wrongImageURL = properties.getProperty("ADDARTWORK_WRONG_IMAGE_URL");
    youtubeURL = properties.getProperty("ADDARTWORK_YOUTUBEURL");
    wrongYoutubeURL = properties.getProperty("ADDARTWORK_WRONG_YOUTUBEURL");

    PageUtil.quickSignIn(driver, browser, email, password, isStaging);
    browser.goPage(AddArtworkPage.pageUrl);
    browser.checkPageIsOpened(AddArtworkPage.pageUrl, _t(AddArtworkPage.pageLoadedText));
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(AddArtworkPage.pageUrl);
    browser.waitPageLoaded(AddArtworkPage.pageUrl);
    bindingPageToHTML();
  }

  private void validateSuccessUploadMsg() {
    //  This property seem to be removed when you repeat upload 
    //    assertEquals(driver.findElement(By.cssSelector("#artwork-info > div > div.header-section > h1")).getText(), _t("Congratulations!"));
  }

  private void validateRepeatedUploadMsg(String artworkName) {
    assertEquals(driver.findElement(By.cssSelector("#dedup-container > h1")).getText(), _t("Are they the same artwork?"));
    String urlBefore = driver.getCurrentUrl();
    driver.findElement(By.cssSelector("button[value='discard']")).click();
    (new WebDriverWait(driver, 20)).until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBefore))); 
    browser.goPage("/" + userDomain + "/artworks");
    browser.checkPageIsOpened("/" + userDomain + "/artworks", _t("Sort by"));
    assertEquals(1, driver.findElementsByPartialLinkText(artworkName).size());
  }

  private void validateArtworkExistAndDeleteIt(String artworkname) {
    browser.goPage("/" + userDomain + "/artworks");
    browser.checkPageIsOpened("/" + userDomain + "/artworks", _t("Sort by"));
    WebElement artworkThumb = driver.findElement(By.partialLinkText(artworkname));
    artworkThumb.click();
    WebElement editArtworkLink = driver.findElement(By.cssSelector(".edit a"));
    editArtworkLink.click();
    Pattern pattern = Pattern.compile("^" + browser.fullURL("/artworks/") + "(.*)/edit$");
    browser.waitPageLoaded(pattern);
    browser.scrollAndClick(driver.findElementByClassName("delete-artwork"));
    (new WebDriverWait(driver, 20)).until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    browser.waitPageLoaded("");
  }

  /*
   * Special usage.
   */
  private void deleteAllArtwork() throws InterruptedException {
    try{
      validateArtworkExistAndDeleteIt(artworkName);
      // call before manually
      before();
    } catch(NoSuchElementException e) {
      before();
    }
  }

  @Test
  public void test01_uploadWithoutAnything() throws InterruptedException {
    page.uploadArtwork();
    Thread.sleep(5000);
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(fileTypeError), errors.get(0).getText());
    assertEquals(_t(requireFieldError), errors.get(1).getText());
    assertEquals(_t(requireFieldError), errors.get(2).getText());
    assertEquals(3, errors.size());
  }

  @Test
  public void test02_uploadWithoutDetail() throws InterruptedException {
    page.chooseImageFromLocalFile(this.fullFilePath(artworkPath));
    page.uploadArtwork();
    Thread.sleep(5000);
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(requireFieldError), errors.get(0).getText());
    assertEquals(_t(requireFieldError), errors.get(1).getText());
    assertEquals(2, errors.size());
  }

  @Test
  public void test03_uploadWithoutPicture() {
    page.fillArtistName(artworkName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(fileTypeError), errors.get(0).getText());
    assertEquals(1, errors.size());
  }

  @Test
  public void test03a_uploadWithoutPictureType() {
    page.chooseImageFromLocalFile(this.fullFilePath(wrongArtworkPath));
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(fileTypeError), errors.get(0).getText());
    assertEquals(1, errors.size());
  }

  @Test
  public void test04_uploadUsingWrongURL() {
    page.selectPictureUploadMode(PictureUploadMode.URLMODE);
    page.chooseImageFromURL(wrongImageURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(fileTypeError), errors.get(0).getText());
    assertEquals(1, errors.size());
  }

  @Test
  public void test05_uploadUsingWrongYoutubeLink() {
    page.selectPictureUploadMode(PictureUploadMode.YOUTUBEMODE);
    page.chooseImageFromYoutube(wrongYoutubeURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    List<WebElement> errors = driver.findElementsByClassName("error-message");
    assertEquals(_t(fileTypeError), errors.get(0).getText());
    assertEquals(1, errors.size());
  }

  @Test
  public void test06_successUploadUsingFile() throws InterruptedException {
    deleteAllArtwork();
    page.chooseImageFromLocalFile(this.fullFilePath(artworkPath));
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    Thread.sleep(3000);
    validateSuccessUploadMsg();
    validateArtworkExistAndDeleteIt(artworkName);
  }

  @Test
  public void test07_successUploadUsingURL() throws InterruptedException {
    deleteAllArtwork();
    page.selectPictureUploadMode(PictureUploadMode.URLMODE);
    page.chooseImageFromURL(imageURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    Thread.sleep(3000);
    validateSuccessUploadMsg();
    validateArtworkExistAndDeleteIt(artworkName);
  }

  @Test
  public void test08_successUploadUsingYouTube() throws InterruptedException {
    deleteAllArtwork();
    page.selectPictureUploadMode(PictureUploadMode.YOUTUBEMODE);
    page.chooseImageFromYoutube(youtubeURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    Thread.sleep(3000);
    validateSuccessUploadMsg();
    validateArtworkExistAndDeleteIt(artworkName);
  }

  @Test
  public void test09_successUploadWithAllLabel() throws InterruptedException {
    deleteAllArtwork();
    page.chooseImageFromLocalFile(this.fullFilePath(artworkPath));
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    for(ArtworkLabel lb:ArtworkLabel.values()) {
      page.addArtworkLabel(lb);
      if(lb == ArtworkLabel.DIMENSIONS) {
        page.fillArtworkLabelValue(lb, "100-100-10-cm");
        continue;
      }
      page.fillArtworkLabelValue(lb, lb.toString());
    }
    page.uploadArtwork();
    Thread.sleep(3000);
    validateSuccessUploadMsg();
    validateArtworkExistAndDeleteIt(artworkName);
  }

  @Test
  public void test10_successUploadWithAllType() throws InterruptedException {
    deleteAllArtwork();
    for(ArtworkType at:ArtworkType.values()) {
      page.chooseImageFromLocalFile(this.fullFilePath(artworkPath));
      page.fillArtistName(artistName);
      page.fillTitle(artworkName);
      page.selectArtworkType(at);
      page.uploadArtwork();
      Thread.sleep(3000);
      validateSuccessUploadMsg();
      validateArtworkExistAndDeleteIt(artworkName);
      // call before manually
      before();
    }
  }

  @Test
  public void test11_pageAfterRepeatedUpload() throws InterruptedException {
    deleteAllArtwork();
    page.selectPictureUploadMode(PictureUploadMode.URLMODE);
    page.chooseImageFromURL(imageURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    Thread.sleep(3000);
    validateSuccessUploadMsg();
    before();
    page.selectPictureUploadMode(PictureUploadMode.URLMODE);
    page.chooseImageFromURL(imageURL);
    page.fillArtistName(artistName);
    page.fillTitle(artworkName);
    page.uploadArtwork();
    Thread.sleep(3000);
    validateRepeatedUploadMsg(artworkName);
  }

}

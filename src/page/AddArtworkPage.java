package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import constant.ArtworkDetails.ArtworkLabel;
import constant.ArtworkDetails.ArtworkType;

public class AddArtworkPage extends Page {
  public enum PictureUploadMode {
    FILEMODE,
    URLMODE,
    YOUTUBEMODE,
  };

  public final static String pageUrl = "/artworks/new";
  public final static String pageLoadedText = "Add Artwork Now";

  @FindBy(css = "#upload-by-file input[type='file']")
  private WebElement uploadUsingFileUpload;

  @FindBy(css = "#upload-by-file a[data-section='url']")
  private WebElement uploadUsingURL;

  @FindBy(css = "#upload-by-file a[data-section='oembed']")
  private WebElement uploadUsingYoutubeURL;

  @FindBy(css = "#upload-by-file input[type='file']")
  private WebElement choosePicture;

  @FindBy(id = "work_pictures_attributes_0_picture_url")
  private WebElement enterPictureURL;

  @FindBy(id = "work_pictures_attributes_0_embed_url")
  private WebElement enterPictureYoutubeURL;

  @FindBy(css = ".half.ui-autocomplete-input")
  @CacheLookup
  private WebElement artistField;

  @FindBy(id = "work_title")
  @CacheLookup
  private WebElement titleField;

  @FindBy(id = "work_artwork_type_list")
  @CacheLookup
  private WebElement artworkType;

  @FindBy(css = ".metadata-select")
  @CacheLookup
  private WebElement artworkLabels;

  @FindBy(css = "#new_work > div.actionset > input")
  @CacheLookup
  private WebElement upload;

  public AddArtworkPage(WebDriver driver) {
    this(driver, pageLoadedText);
  }

  public AddArtworkPage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
  }

  public AddArtworkPage selectPictureUploadMode(PictureUploadMode mode) {
    switch(mode) {
      case FILEMODE:
        uploadUsingFileUpload.click();
        break;
      case URLMODE:
        uploadUsingURL.click();
        break;
      case YOUTUBEMODE:
        uploadUsingYoutubeURL.click();
        break;
      default:
        throw new IllegalArgumentException();
    }
    return this;
  }

  public AddArtworkPage chooseImageFromLocalFile(String filePath) {
    choosePicture.sendKeys(filePath);
    return this;
  }

  public AddArtworkPage chooseImageFromURL(String url) {
    enterPictureURL.sendKeys(url);
    return this;
  }

  public AddArtworkPage chooseImageFromYoutube(String url) {
    enterPictureYoutubeURL.sendKeys(url);
    return this;
  }

  public AddArtworkPage fillArtistName(String name) {
    artistField.sendKeys(name);
    return this;
  }

  public AddArtworkPage fillTitle(String title) {
    titleField.sendKeys(title);
    return this;
  }

  public AddArtworkPage selectArtworkType(ArtworkType type) {
    Select select = new Select(artworkType);
    String value = type.getName();
    select.selectByValue(value);
    return this;
  }

  public AddArtworkPage addArtworkLabel(ArtworkLabel label) {
    String contextName = label.getContextName();
    String selector = String.format("li[data-context='%s']", contextName);
    artworkLabels.findElement(By.cssSelector(selector)).click();
    return this;
  }

  public AddArtworkPage fillArtworkLabelValue(ArtworkLabel label, String inputData) {
    if(label == ArtworkLabel.DIMENSIONS) {
      String[] arr = inputData.split("-");
      List<WebElement> fields = driver.findElements(By.cssSelector("#metadataset_dimension_list input"));
      fields.add(driver.findElement(By.cssSelector("#metadataset_dimension_list select")));
      for(int i = 0; i< arr.length; ++i) {
        fields.get(i).sendKeys(arr[i]);
      }
    } else {
      String elementId = String.format("work_%s", label.getContextName());
      driver.findElement(By.id(elementId)).sendKeys(inputData);
    }
    return this;
  }

  public AddArtworkPage uploadArtwork() {
    upload.click();
    return this;
  }
}

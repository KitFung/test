package page.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ArtworkListComponent implements IArtworkListComponent{

  protected WebDriver driver;

  @FindBy(css = ".feed-item")
  private List<WebElement> feedItems;

  @FindBy(id = "artwork-viewer")
  private WebElement feedViewer;

  @FindBy(css = "#artwork-viewer .stacking-button-small")
  private WebElement stackButtonOnViewer;

  @FindBy(css = "#tab-related > a")
  private WebElement  followBtnOnViewer;

  @FindBy(css = "#artwork-viewer .artwork-viewer_close")
  private WebElement  closeBtnOnViewer;

  @FindBy(css = "#artwork-viewer .work-title")
  private WebElement titleOnViewer;

  @FindBy(css = "#artwork-viewer .artist-name a:nth-child(1)")
  private WebElement artistNameOnViewer;

  @FindBy(css = ".work-container div.metadata-list > dl > dd > a")
  private WebElement artworkTypeNameOnViewer;
  
  @FindBy(id = "return-to-top")
  @CacheLookup
  private WebElement toTheTopBtn;

  public ArtworkListComponent(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement getFeedItemsAt(int index) {
    return feedItems.get(index);
  }

  @Override
  public IArtworkListComponent clickFeedItemsAt(int index) {
    WebElement feedItemImg = getFeedItemsAt(index).findElement(By.className("work-image"));
    feedItemImg.click();
    return this;
  }

  private WebElement getStackButtonAt(int index) {
    return getFeedItemsAt(index).findElement(By.className("stacking-button-small"));
  }

  private WebElement getArtistLinkAt(int index) {
    return getFeedItemsAt(index).findElement(By.cssSelector(".artist-name a:nth-child(1)"));
  }

  @Override
  public IArtworkListComponent clickSmallStackButtonOfItem(int index) {
    WebElement stackButton = getStackButtonAt(index);
    stackButton.click();
    return this;
  }

  @Override
  public IArtworkListComponent clickArtistNameOfItem(int index) {
    WebElement artistNameLink = getArtistLinkAt(index);
    artistNameLink.click();
    return this;
  }

  @Override
  public IArtworkListComponent clickToTheTop() {
    toTheTopBtn.click();
    return this;
  }

  @Override
  public IArtworkListComponent clickLargeArtistName(int index) {
    artistNameOnViewer.click();
    return this;
  }

  @Override
  public IArtworkListComponent clickLargeSlackButton() {
    stackButtonOnViewer.click();
    return this;
  }

  @Override
  public IArtworkListComponent clickLargeFollowButton() {
    followBtnOnViewer.click();
    return this;
  }

  @Override
  public IArtworkListComponent closeViewer() {
    closeBtnOnViewer.click();
    return this;
  }

  @Override
  public String nameOfItemFromThumb(int index) {
    return getFeedItemsAt(index).findElement(By.className("work-title")).getText();
  }

  @Override
  public String artistOfItemFromThumb(int index) {
    return getArtistLinkAt(index).getText();
  }

  @Override
  public String nameOfItemFromViewer() {
    return titleOnViewer.getText();
  }

  @Override
  public String artistOfItemFromViewer() {
    return artistNameOnViewer.getText();
  }

  @Override
  public String textOnTheStackButtonInThumb(int index) {
    WebElement stackButton = getStackButtonAt(index);
    return stackButton.getText();
  }

  @Override
  public String textOnTheStackButtonInViewer() {
    return stackButtonOnViewer.getText();
  }

  @Override
  public String textOnTheFollowBtnInViewer() {
    return followBtnOnViewer.getText();
  }

  @Override
  public int numberOfFeedItems() {
    return feedItems.size();
  }

  @Override
  public String artworkTypeFromViewer() {
	return artworkTypeNameOnViewer.getText();
  }

}

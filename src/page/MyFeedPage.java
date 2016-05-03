package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page.component.ArtworkListComponent;
import page.component.IArtworkListComponent;
import page.component.INavigationBarComponent;
import page.component.NavigationBarComponent;

public class MyFeedPage extends Page implements IArtworkListComponent, INavigationBarComponent{
  private ArtworkListComponent artworkList;
  private NavigationBarComponent navigationBar;
  public final static String pageUrl = "";

  public final static String pageLoadedTextStaging = "Most stacked artworks this week";
  public final static String pageLoadedText = "Stack art you love - this page has art from people, artists and labels you follow";

  @FindBy(css = "a.feed-toggle")
  @CacheLookup
  private WebElement listLayoutBtn;

  @FindBy(css = "a.mosaic-toggle ")
  @CacheLookup
  private WebElement gridLayoutBtn;

  public MyFeedPage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
    artworkList = PageFactory.initElements(driver, ArtworkListComponent.class);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public static String getPageLoadedText(boolean staging) {
    return !staging?MyFeedPage.pageLoadedText:MyFeedPage.pageLoadedTextStaging;
  }

  public MyFeedPage switchToGridMode() {
    gridLayoutBtn.click();
    return this;
  }

  public MyFeedPage switchToListMode() {
    listLayoutBtn.click();
    return this;
  }
  
  public MyFeedPage closeInviteAlert() {
	   if(driver.findElements(By.cssSelector("#invite-by-email .close")).size() > 0) {
		   driver.findElement(By.cssSelector("#invite-by-email .close")).click();
	   }
	   return this;
  }

  /* Delegate function */

  @Override
  public MyFeedPage clickFeedItemsAt(int index) {
    artworkList.clickFeedItemsAt(index);
    return this;
  }

  @Override
  public MyFeedPage clickSmallStackButtonOfItem(int index) {
    artworkList.clickSmallStackButtonOfItem(index);
    return this;
  }

  @Override
  public MyFeedPage clickArtistNameOfItem(int index) {
    artworkList.clickArtistNameOfItem(index);
    return this;
  }

  @Override
  public MyFeedPage clickToTheTop() {
    artworkList.clickToTheTop();
    return this;
  }

  @Override
  public MyFeedPage clickLargeArtistName(int index) {
    artworkList.clickLargeArtistName(index);
    return this;
  }

  @Override
  public MyFeedPage clickLargeSlackButton() {
    artworkList.clickLargeSlackButton();
    return this;
  }

  @Override
  public MyFeedPage clickLargeFollowButton() {
    artworkList.clickLargeFollowButton();
    return this;
  }

  @Override
  public MyFeedPage closeViewer() {
    artworkList.closeViewer();
    return this;
  }

  @Override
  public String nameOfItemFromThumb(int index) {
    return artworkList.nameOfItemFromThumb(index);
  }

  @Override
  public String artistOfItemFromThumb(int index) {
    return artworkList.artistOfItemFromThumb(index);
  }

  @Override
  public String nameOfItemFromViewer() {
    return artworkList.nameOfItemFromViewer();
  }

  @Override
  public String artistOfItemFromViewer() {
    return artworkList.artistOfItemFromViewer();
  }

  @Override
  public String textOnTheStackButtonInThumb(int index) {
    return artworkList.textOnTheStackButtonInThumb(index);
  }

  @Override
  public String textOnTheStackButtonInViewer() {
    return artworkList.textOnTheStackButtonInViewer();
  }

  @Override
  public String textOnTheFollowBtnInViewer() {
    return artworkList.textOnTheFollowBtnInViewer();
  }

  @Override
  public int numberOfFeedItems() {
    return artworkList.numberOfFeedItems();
  }
  
  @Override
  public String artworkTypeFromViewer() {
	return artworkList.artworkTypeFromViewer();
  }

  @Override
  public INavigationBarComponent searchArtwork(String query) {
    navigationBar.searchArtwork(query);
    return this;
  } 
}

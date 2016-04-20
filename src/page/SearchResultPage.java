package page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.ArtworkLabel;
import page.component.INavigationBarComponent;
import page.component.NavigationBarComponent;

public class SearchResultPage extends Page implements INavigationBarComponent{
  private NavigationBarComponent navigationBar;
  public static String pageUrl = "/search?utf8=✓&q=";

  public final static String pageLoadedText = "Search result";

  @FindBy(css = ".search-user-title .people")
  @CacheLookup
  private WebElement peopleTag;

  @FindBy(css = ".search-user-title:not(.people)")
  @CacheLookup
  private WebElement artisitTag;

  @FindBy(css = ".search-tags-title.artworks")
  @CacheLookup
  private WebElement artworkTag;

  @FindBy(css = ".search-tags-title.exhibitions")
  @CacheLookup
  private WebElement exhibitionTag;

  @FindBy(css = ".search-tags-title.galleries")
  @CacheLookup
  private WebElement galleryTag;

  @FindBy(css = ".search-tags-title.museums")
  @CacheLookup
  private WebElement museumTag;

  @FindBy(css = ".search-tags-title.collections")
  @CacheLookup
  private WebElement collectionTag;

  @FindBy(css = ".search-people .users-list-item")
  private List<WebElement> peopleResults;

  @FindBy(css = ".search-artists .users-list-item")
  private List<WebElement> artisitResults;

  @FindBy(css = ".search-artwork .works-grid-item")
  private List<WebElement> artworkResults;

  @FindBy(css = ".search-tags.exhibitions .users-list-item")
  private List<WebElement> exhibitionResults;

  @FindBy(css = ".search-tags.galleries .users-list-item")
  private List<WebElement> galleryResults;

  @FindBy(css = ".search-tags.museums .users-list-item")
  private List<WebElement> museumResults;

  @FindBy(css = ".search-tags.collections .users-list-item")
  private List<WebElement> collectionResults;

  public SearchResultPage(WebDriver driver, String query) {
    this(driver, query, pageLoadedText);
  }

  public SearchResultPage(WebDriver driver, String query, String customPageLoadedText) {
    super(driver, pageUrl + query, customPageLoadedText);
    SearchResultPage.pageUrl = pageUrl + query;
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  private List<String> getNameInPeoplePart(List<WebElement> lv) {
    return lv.stream().map(e -> e.findElement(By.cssSelector(".display-name a:nth-child(1)")).getText()).collect(Collectors.toList());
  }

  private List<String> getNameInTagPart(List<WebElement> lv) {
    return lv.stream().map(e -> e.findElement(By.cssSelector(".users-list-item .display-name")).getText()).collect(Collectors.toList());
  }

  public SearchResultPage clickPeopleTag() {
    peopleTag.click();
    return this;
  }

  public SearchResultPage clickArtistTag() {
    artisitTag.click();
    return this;
  }

  public SearchResultPage clickArtworkTag() {
    artworkTag.click();
    return this;
  }

  public SearchResultPage clickOtherTag(ArtworkLabel lb) {
    WebElement e;
    switch(lb) {
      case EXHIBITIONS:
        e = exhibitionTag;
        break;
      case GALLERIES:
        e = galleryTag;
        break;
      case MUSEUMS:
        e = museumTag;
        break;
      case COLLECTIONS:
        e = collectionTag;
        break;
      default:
        throw new IllegalArgumentException();
    }
    e.click();
    return this;
  }

  public List<String> getPeopleNameResult() {
    return getNameInPeoplePart(peopleResults);
  }
  public List<String> getArtisitNameResult() {
    return getNameInPeoplePart(artisitResults);
  }

  public List<String> getArtworkNameResult() {
    return artworkResults.stream().map(e -> e.findElement(By.cssSelector(".works-grid-item .work-title")).getText()).collect(Collectors.toList());
  }

  public List<String> getOtherResult(ArtworkLabel lb) {
    List<WebElement> we;
    switch(lb) {
      case EXHIBITIONS:
        we = exhibitionResults;
        break;
      case GALLERIES:
        we = galleryResults;
        break;
      case MUSEUMS:
        we = museumResults;
        break;
      case COLLECTIONS:
        we = collectionResults;
        break;
      default:
        throw new IllegalArgumentException();
    }
    return getNameInTagPart(we);
  }

  public SearchResultPage followPeopleInIndex(int index) {
    WebElement item = peopleResults.get(index);
    item.findElement(By.cssSelector(".follow-btn-remote a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage followArtistInIndex(int index) {
    WebElement item = artisitResults.get(index);
    item.findElement(By.cssSelector(".follow-btn-remote a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage slackArtworkInIndex(int index) {
    WebElement item = artworkResults.get(index);
    item.findElement(By.className("stacking-button-small")).click();
    return this;
  }

  public SearchResultPage followOtherInIndex(int index, ArtworkLabel lb) {
    List<WebElement> we;
    switch(lb) {
      case EXHIBITIONS:
        we = exhibitionResults;
        break;
      case GALLERIES:
        we = galleryResults;
        break;
      case MUSEUMS:
        we = museumResults;
        break;
      case COLLECTIONS:
        we = collectionResults;
        break;
      default:
        throw new IllegalArgumentException();
    }
    we.get(index).findElement(By.cssSelector(".follow-btn-remote a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage goPeopleProfilePageInIndex(int index) {
    peopleResults.get(index).findElement(By.cssSelector(".display-name a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage goArtistProfilePageInIndex(int index) {
    artisitResults.get(index).findElement(By.cssSelector(".display-name a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage goArtworkDetailPageInIndex(int index) {
    artworkResults.get(index).findElement(By.cssSelector(".work-title a:nth-child(1)")).click();
    return this;
  }

  public SearchResultPage goOtherDetailPageInIndex(int index, ArtworkLabel lb) {
    List<WebElement> we;
    switch(lb) {
      case EXHIBITIONS:
        we = exhibitionResults;
        break;
      case GALLERIES:
        we = galleryResults;
        break;
      case MUSEUMS:
        we = museumResults;
        break;
      case COLLECTIONS:
        we = collectionResults;
        break;
      default:
        throw new IllegalArgumentException();
    }
    we.get(index).findElement(By.cssSelector(".display-name a:nth-child(1)")).click();
    return this;
  }

  @Override
  public INavigationBarComponent searchArtwork(String query) {
    navigationBar.searchArtwork(query);
    return this;
  }

}

package page;

import java.util.List;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ArtworkDetails.ArtworkType;
import constant.ArtworkDetails.YearRange;
import page.component.ArtworkListComponent;
import page.component.IArtworkListComponent;
import page.component.INavigationBarComponent;
import page.component.NavigationBarComponent;

public class ExplorePage extends Page implements IArtworkListComponent, INavigationBarComponent{

  private ArtworkListComponent artworkList;
  private NavigationBarComponent navigationBar;
  public final static String pageUrl = "/all_added";

  public final static String pageLoadedText = "Art recently added from across the ArtStack community";

  @FindBy(css = "#filter-type > div")
  @CacheLookup
  private WebElement artworkTypeDropdownList;

  @FindBy(css = "#filter-year > div")
  @CacheLookup
  private WebElement yearDropdownList;

  @FindBy(css = "#filter-top > div")
  @CacheLookup
  private WebElement mostStackedDropdownList;

  public ExplorePage(WebDriver driver) {
    this(driver, pageLoadedText);
    artworkList = PageFactory.initElements(driver, ArtworkListComponent.class);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public ExplorePage(WebDriver driver, String customPageLoadedText) {
    super(driver, pageUrl, customPageLoadedText);
    artworkList = PageFactory.initElements(driver, ArtworkListComponent.class);
    navigationBar = PageFactory.initElements(driver, NavigationBarComponent.class);
  }

  public ExplorePage selectArtworkType(ArtworkType artWorkType) {
    artworkTypeDropdownList.findElement(By.cssSelector("a > div")).click();
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        List<String> allClass = Arrays.asList(artworkTypeDropdownList.getAttribute("class").split(" "));
        return allClass.contains("chosen-with-drop");
      }
    });
    String selector = String.format("li[data-option-array-index='%d']", artWorkType.getArrayIndex());
    driver.findElement(By.cssSelector(selector)).click();
    return this;
  }

  public ExplorePage selectArtworkYear(YearRange yRange) {
    yearDropdownList.findElement(By.cssSelector("a > div")).click();
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        List<String> allClass = Arrays.asList(yearDropdownList.getAttribute("class").split(" "));
        return allClass.contains("chosen-with-drop");
      }
    });
    String selector = String.format("li[data-option-array-index='%d']", yRange.getArrayIndex());
    yearDropdownList.findElement(By.cssSelector(selector)).click();
    return this;
  }

  public ExplorePage selectedMostedStacked(ArtworkType artWorkType) {
    mostStackedDropdownList.findElement(By.cssSelector("a > div")).click();
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        List<String> allClass = Arrays.asList(mostStackedDropdownList.getAttribute("class").split(" "));
        return allClass.contains("chosen-with-drop");
      }
    });
    String selector = String.format("li[data-option-array-index='%d']", artWorkType.getArrayIndex());
    mostStackedDropdownList.findElement(By.cssSelector(selector)).click();
    return this;
  }

  /* Delegate function */

  @Override
  public ExplorePage clickFeedItemsAt(int index) {
    artworkList.clickFeedItemsAt(index);
    return this;
  }

  @Override
  public ExplorePage clickSmallStackButtonOfItem(int index) {
    artworkList.clickSmallStackButtonOfItem(index);
    return this;
  }

  @Override
  public ExplorePage clickArtistNameOfItem(int index) {
    artworkList.clickArtistNameOfItem(index);
    return this;
  }

  @Override
  public ExplorePage clickToTheTop() {
    artworkList.clickToTheTop();
    return this;
  }

  @Override
  public ExplorePage clickLargeArtistName(int index) {
    artworkList.clickLargeArtistName(index);
    return this;
  }

  @Override
  public ExplorePage clickLargeSlackButton() {
    artworkList.clickLargeSlackButton();
    return this;
  }

  @Override
  public ExplorePage clickLargeFollowButton() {
    artworkList.clickLargeFollowButton();
    return this;
  }

  @Override
  public ExplorePage closeViewer() {
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
  public INavigationBarComponent searchArtwork(String query) {
    navigationBar.searchArtwork(query);
    return this;
  }
}

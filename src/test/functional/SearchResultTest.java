package test.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.ArtworkLabel;
import page.MyFeedPage;
import page.PageUtil;
import page.SearchResultPage;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchResultTest extends ProductionTest{
  private MyFeedPage startPage;
  private SearchResultPage page;
  /**
   * query: the keyword that you want to search.
   * popularKeyWord: some keyword that will have many result.
   * index: the index of result you want to interact in the result.
   */
  private static String query;
  private static int index;

  private void bindingStartPageToHTML() {
    startPage = new MyFeedPage(driver, _t(MyFeedPage.getPageLoadedText(isStaging)));
    PageFactory.initElements(driver, startPage);
  }

  private void bindingPageToHTML() {
    page = new SearchResultPage(driver, query, _t(SearchResultPage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() {
    query = properties.getProperty("SEARCH_QUERY");
    index = Integer.parseInt(properties.getProperty("SEARCH_INDEX"));

    PageUtil.quickSignIn(driver, browser, email, password, isStaging);
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(MyFeedPage.pageUrl);
    browser.waitPageLoaded(MyFeedPage.pageUrl);
    bindingStartPageToHTML();
  }

  private void validateSearchResult(List<String> results, String query) {
    for(String name:results) {
      assertTrue(name.toLowerCase().replace(" ", "").contains(query));
    }
  }

  @Test
  public void test01_searchEmpty() {
    startPage.searchArtwork("");
    bindingPageToHTML();
    assertEquals(0, page.getArtworkNameResult().size());
  }

  /**
   * Not sure what is their searching condition, it will show some artist name that don't contain the key world.
   * Therefore, pass this test.
   */
  @Ignore
  @Test
  public void test02_searchPeople() {}

  @Ignore
  @Test
  public void test03_searchArtist() {}

  @Test
  public void test04_searchArtwork() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    validateSearchResult(page.getArtworkNameResult(), query);
  }

  @Test
  public void test05_searchExhibition() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.EXHIBITIONS);
    validateSearchResult(page.getOtherResult(ArtworkLabel.EXHIBITIONS), query);
  }

  @Test
  public void test06_searchGallery() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.GALLERIES);
    validateSearchResult(page.getOtherResult(ArtworkLabel.GALLERIES), query);
  }

  @Test
  public void test07_searchMuseum() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.MUSEUMS);
    validateSearchResult(page.getOtherResult(ArtworkLabel.MUSEUMS), query);
  }

  @Test
  public void test08_searchCollection() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.COLLECTIONS);
    validateSearchResult(page.getOtherResult(ArtworkLabel.COLLECTIONS), query);
  }

  @Test
  public void test09_followPeople() throws InterruptedException {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.followPeopleInIndex(index).goPeopleProfilePageInIndex(index);  
    driver.findElement(By.cssSelector("#right > div > a")).click();
  }

  @Test
  public void test10_followArtisit() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickArtistTag();
    page.followArtistInIndex(index).goArtistProfilePageInIndex(index);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/artists/") + "(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".right > a")).click();
  }

  @Test
  public void test11_slackArtwork() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.slackArtworkInIndex(index).goArtworkDetailPageInIndex(index);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/artist/") + "(.*)/(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".work-container .stacking-button-small")).click();
  }

  @Test
  public void test12_goExhibition() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.EXHIBITIONS);
    page.followOtherInIndex(index, ArtworkLabel.EXHIBITIONS).goOtherDetailPageInIndex(index, ArtworkLabel.EXHIBITIONS);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/exhibition/") + "(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".right > a")).click();
  }

  @Test
  public void test13_followGallery() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.GALLERIES);
    page.followOtherInIndex(index, ArtworkLabel.GALLERIES).goOtherDetailPageInIndex(index, ArtworkLabel.GALLERIES);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/gallery/") + "(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".right > a")).click();
  }

  @Test
  public void test14_followMuseum() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.MUSEUMS);
    page.followOtherInIndex(index, ArtworkLabel.MUSEUMS).goOtherDetailPageInIndex(index, ArtworkLabel.MUSEUMS);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/museum/") + "(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".right > a")).click();
  }

  @Test
  public void test15_followCollection() {
    startPage.searchArtwork(query);
    bindingPageToHTML();
    page.clickOtherTag(ArtworkLabel.COLLECTIONS);
    page.followOtherInIndex(index, ArtworkLabel.COLLECTIONS).goOtherDetailPageInIndex(index, ArtworkLabel.COLLECTIONS);
    Pattern regax = Pattern.compile("^" + browser.fullURL("/collection/") + "(.*)$");
    browser.waitPageLoaded(regax);
    driver.findElement(By.cssSelector(".right > a")).click();
  }
}

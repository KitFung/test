package test.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.MyFeedPage;
import page.PageUtil;
import page.component.IArtworkListComponent;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore public abstract class ArtworkList extends ProductionTest{

  private final int[] testItemsIndex = {1,2};
  protected IArtworkListComponent page;

  @BeforeClass
  public static void beforeClass() {
	  PageUtil.quickSignIn(driver, browser, email, password, isStaging);
  }

  /**
   * This will a long test.
   * Step: Stack a item -> Click into it and get the real title -> Go to Profile -> Check whether it exist
   * @throws InterruptedException 
   */
  @Test
  public void test01_stackAItemInThumb() throws InterruptedException {
    List<String> originalText = new ArrayList<String>();
    List<String> itemNames = new ArrayList<String>();
    List<String> cleareditemNames = new ArrayList<String>();

    /* Stack Item */
    Thread.sleep(2000);
    for(int i=0; i<testItemsIndex.length; i++) {
      int itemIndex = testItemsIndex[i];
      originalText.add(page.textOnTheStackButtonInThumb(itemIndex));
      page.clickSmallStackButtonOfItem(itemIndex);
    }

    /* Check button text */
    for(int i=testItemsIndex.length-1; i>=0; i--) {
      int itemIndex = testItemsIndex[i];
      String btnText = originalText.get(i);
      boolean expectedState = !btnText.equals(_t("Stacked"));
      assertEquals(expectedState, page.textOnTheStackButtonInThumb(itemIndex).equals(_t("Stacked")));
      Thread.sleep(2000);
      page.clickFeedItemsAt(itemIndex);
      assertEquals(expectedState, page.textOnTheStackButtonInViewer().equals(_t("Stacked")));
      /* Click into it and get real title*/
      if(!expectedState) {
        itemNames.add(page.nameOfItemFromViewer());
      } else {
        cleareditemNames.add(page.nameOfItemFromViewer());
      }
      page.closeViewer();
    }
  }

  @Test
  public void test02_stackAItemInViewer() throws InterruptedException {
    List<String> itemNames = new ArrayList<String>();
    List<String> cleareditemNames = new ArrayList<String>();

    /* Stack Item */
    for(int i=0; i<testItemsIndex.length; i++) {
      int itemIndex = testItemsIndex[i];
      String btnText = page.textOnTheStackButtonInThumb(itemIndex); 
      boolean isStacked = btnText.equals(_t("Stacked"));
      page.clickFeedItemsAt(itemIndex);
      if(!isStacked) {
        itemNames.add(page.nameOfItemFromViewer());
      } else {
        cleareditemNames.add(page.nameOfItemFromViewer());
      }
      assertEquals(isStacked, page.textOnTheStackButtonInViewer().equals(_t("Stacked")));
      page.clickLargeSlackButton();
      assertEquals(!isStacked, page.textOnTheStackButtonInViewer().equals(_t("Stacked")));
      page.closeViewer();
      (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
          return !isStacked == page.textOnTheStackButtonInThumb(itemIndex).equals(_t("Stacked"));
        }
      });
      Thread.sleep(3000);
    }

    browser.goPage("/" + userDomain);
    browser.checkPageIsOpened("/" + userDomain, _t("Edit my profile"));

    List<WebElement> stackedItems = driver.findElementsByClassName("feed-item");
    for(WebElement item:stackedItems) {
      String itemTitle = item.findElement(By.className("work-title")).getText();
      itemNames.remove(itemTitle);
      assertEquals(false, cleareditemNames.contains(itemTitle));
    }
    assertEquals(0, itemNames.size());
  }

  @Test
  public void test03_checkTheDetailViewOfAnItem() throws InterruptedException {
    for(int i=0; i<testItemsIndex.length; i++) {
      int itemIndex = testItemsIndex[i];
      page.clickFeedItemsAt(itemIndex);
      Thread.sleep(2000);
      assertTrue(driver.findElementByClassName("picture-content").isDisplayed());
      assertFalse("".equals(driver.findElementByClassName("picture-content").getAttribute("src")));
      assertTrue(driver.findElement(By.cssSelector("#artwork-viewer .work-title")).isDisplayed());
      assertFalse("".equals(driver.findElement(By.cssSelector("#artwork-viewer .work-title a")).getAttribute("href")));
      assertFalse("".equals(driver.findElement(By.cssSelector("#artwork-viewer .work-title")).getText()));
      assertFalse("".equals(driver.findElement(By.cssSelector("#artwork-viewer .artist-name a:nth-child(1)")).getText()));
      assertTrue(driver.findElement(By.cssSelector("#artwork-viewer .stacking-button-small a")).isDisplayed());
      assertTrue(_t("Add to collection").equals(
          driver.findElement(By.cssSelector("#artwork-viewer .add-to-collection a:nth-child(1)")).getText()));
      assertTrue(driver.findElement(By.cssSelector("a[href='#tab-info']")).isDisplayed());
      assertTrue(_t("Artwork info").equals(driver.findElement(By.cssSelector("a[href='#tab-info']")).getText()));
      assertTrue(driver.findElement(By.cssSelector("a[href='#tab-related']")).isDisplayed());
      assertTrue(_t("Related artworks").equals(driver.findElement(By.cssSelector("a[href='#tab-related']")).getText()));
      assertTrue(driver.findElement(By.cssSelector("a[href='#tab-comment']")).isDisplayed());
      assertTrue(_t("Comment").equals(driver.findElement(By.cssSelector("a[href='#tab-comment']")).getText()));
      assertTrue(driver.findElementByXPath("//*[@id='tab-related']/a").isDisplayed());
      page.closeViewer();
    }
  }

  @Test
  public void test04_loadMoreItem() throws InterruptedException {
    int testRound = 3;
    while(testRound > 0) {
        int numberOfFeedsItemBefore = page.numberOfFeedItems();
        String jsCommand = "var body = document.body," +
                           "    html = document.documentElement;" +
                           "var height = Math.max( body.scrollHeight, body.offsetHeight, " +
                           "                       html.clientHeight, html.scrollHeight, html.offsetHeight );" +
                           "window.scrollTo(0, height);";
        driver.executeScript(jsCommand);
        Thread.sleep(4000);
        int numberOfFeedsItemAfter = page.numberOfFeedItems();
        assertTrue(numberOfFeedsItemAfter > numberOfFeedsItemBefore);
        testRound--;
    }
  }

  /**
   * This case may fail since there is a bug exist, but don't know how to reproduce.
   * @throws InterruptedException 
   */
  @Test
  public void test05_testFollowing() throws InterruptedException {
    //Consider it have high possibility that two picture have same author, so just pick one to test.
    int itemIndex = testItemsIndex[0];
    page.clickFeedItemsAt(itemIndex);
    Thread.sleep(2000);
    boolean isFollowing = page.textOnTheFollowBtnInViewer().equals(_t("Following"));
    page.clickLargeFollowButton();
    Thread.sleep(5000);
    page.clickLargeArtistName(itemIndex);
    String regax = "^" + baseURL + "/artists/(.*)$";
    Pattern pattern = Pattern.compile(regax); 
    browser.waitPageLoaded(pattern);
    assertEquals(!isFollowing, _t("Following").equals(
        driver.findElement(By.cssSelector(".profile-box-wrapper .right .btn-text")).getText()));
  }

  @Test
  public void test06_goTopBtn() throws InterruptedException {
    String jsCommand = "var body = document.body," +
                       "    html = document.documentElement;" +
                       "var height = Math.max( body.scrollHeight, body.offsetHeight, " +
                       "                       html.clientHeight, html.scrollHeight, html.offsetHeight );" +
                       "window.scrollTo(0, height);";
    driver.executeScript(jsCommand);
    Thread.sleep(2000);
    page.clickToTheTop();
    Thread.sleep(2000);
    Long value = (Long) driver.executeScript("return window.scrollY;");
    assertEquals(0, value.intValue());
  }

  @Test
  public void test07_compareItemInfoOfThumbAndDetail() {
    for(int i=0; i<testItemsIndex.length; i++) {
      int itemIndex = testItemsIndex[i];
      String artistName = null;
      String artworkName = null;
      artistName = page.artistOfItemFromThumb(itemIndex);
      artworkName = page.nameOfItemFromThumb(itemIndex);
      page.clickFeedItemsAt(itemIndex);
      /* The artist name, artwork name is same in thumb and the detail*/
      assertTrue(page.nameOfItemFromViewer().contains(artworkName));
      assertEquals(artistName, page.artistOfItemFromViewer());
      page.closeViewer();
    }
  }

  @Test
  public void test08_clickArtistNameUnderThumb() throws InterruptedException {
    for(int i = 0; i < testItemsIndex.length; i++) {
      Thread.sleep(2000);
      String artistName = page.artistOfItemFromThumb(testItemsIndex[i]);
      page.clickArtistNameOfItem(testItemsIndex[i]);
      String regax = "^" + baseURL + "/artists/(.*)$";
      Pattern pattern = Pattern.compile(regax); 
      browser.waitPageLoaded(pattern);

      String artistNameLater = driver.findElementByCssSelector(".profile-wrapper .text-info-wrapper h1").getText();
      assertEquals(artistName, artistNameLater);
      browser.goPage(MyFeedPage.pageUrl);
      browser.waitPageLoaded(MyFeedPage.pageUrl);
    }
  }
}

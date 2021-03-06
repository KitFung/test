package test.smoke;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.StagingTest;

/**
 * This is the smoke test.
 * This will run all the basic function of the website WITHOUT assertion.
 * Here will not use page object to minimize the complexity.
 * Moreover, nothing in here need to be reusable.
 *
 * When facing the option selection, it will select the first item automatically.
 * @author kitfung_Oursky
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StagingSmokeTest extends StagingTest {

  @Test
  public void stage00_goURL() throws InterruptedException {
    driver.get(browser.fullURL(""));

    browser.waitPageLoaded("");
    /**
     * Necessary since the page will have a strange behavior.
     * - Load a page with broken format
     * - Reload
     * Selenium will run before the reload which cause error.
     */
    Thread.sleep(5000);
  }

  @Test
  public void stage01_signup() {
	org.junit.Assume.assumeTrue("true".equals(System.getProperty("includesignup")));
    WebElement firstName = driver.findElement(By.id("user_first_name"));
    WebElement lastName = driver.findElement(By.id("user_last_name"));
    WebElement email = driver.findElement(By.id("user_email"));
    WebElement password = driver.findElement(By.id("user_password"));

    firstName.sendKeys("firstName");
    lastName.sendKeys("lastName");
    email.sendKeys(smokeTestEmail);
    password.sendKeys(smokeTestPassword);

    WebElement createBtn = driver.findElement(By.cssSelector("#new_user > div.actionset > input[value='Create Account']"));
    createBtn.click();
  }

  @Test
  public void stage01a_selectCategory() throws InterruptedException {
	org.junit.Assume.assumeTrue("true".equals(System.getProperty("includesignup")));
    Thread.sleep(9000);
    // Skip this part since staging server cannot select category
    browser.waitPageLoaded("/recommended_works");
    browser.goPage("");
    browser.waitPageLoaded("");
    
    if(driver.findElements(By.cssSelector("#fb-timeline-overlay_wrapper")).size() > 0) {
 	   driver.get(browser.fullURL(""));
    }
  }

  @Test
  public void stage02_logout() throws InterruptedException {
    org.junit.Assume.assumeTrue("true".equals(System.getProperty("includesignup")));
    Actions action = new Actions(driver);
    WebElement tri = driver.findElement(By.cssSelector("#line-nav > div.nav-box.dropdown-box > div"));
    action.moveToElement(tri).perform();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#nav2 > li:nth-child(7) > a")).click();
  }

  @Test
  public void stage03_login() {
    WebElement loginBtn = driver.findElement(By.cssSelector("#guest-nav > a.login"));
    loginBtn.click();
    WebElement email = driver.findElement(By.cssSelector("#user_session_email"));
    WebElement password = driver.findElement(By.cssSelector("#user_session_password"));
    email.sendKeys(smokeTestEmail);
    password.sendKeys(smokeTestPassword);

   WebElement loginConfirm = driver.findElement(By.cssSelector("#login-pane > form > div.actionset > input[name='commit']"));
   loginConfirm.click();
   
   if(driver.findElements(By.cssSelector("#fb-timeline-overlay_wrapper")).size() > 0) {
	   driver.get(browser.fullURL(""));
   }
  }

  @Test
  public void stage04_openCloseViewer() throws InterruptedException {
    driver.findElement(By.cssSelector("#left-console"));
    driver.findElement(By.cssSelector("div.work-image")).click();
    Thread.sleep(5000);
    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='artwork-viewer']/div[2]"))).get(0).click();
  }

  @Test
  public void stage05_stackArtworkInMyFeed() throws InterruptedException {
    new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.className("filter-loading")));
    List<WebElement> allAddSlackBtns = driver.findElements(By.className("stacking-button-small"));
    WebElement element = allAddSlackBtns.get(0);
    browser.scrollAndClick(element);

    Thread.sleep(3000);
    //unstack
    element.click();
    Thread.sleep(2000);
  }

  @Test
  public void stage06_addArtwork() throws InterruptedException {
    driver.findElement(By.cssSelector("#middle-menu > a.add-artwork")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#upload-by-file a[data-section='url']")).click();
    driver.findElement(By.id("work_pictures_attributes_0_picture_url")).sendKeys(
        "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTUED-r2LCHAWKV-M2yXdPgm5h3Iv1nrkOEMiOZCb2cwXZnLYNc");
    driver.findElement(By.cssSelector(".half.ui-autocomplete-input")).sendKeys("name");
    driver.findElement(By.id("work_title")).sendKeys("Title is great");
    driver.findElement(By.cssSelector("#new_work > div.actionset > input")).click();
    Thread.sleep(2000);
  }

  @Test
  public void stage07_deleteArtwork() throws InterruptedException {
    driver.findElement(By.cssSelector("#line-nav > div.nav-box.profile-hover-trigger > a")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("#container > div.main > div.profile-menu > div > div.menu-wrapper > nav > a:nth-child(2)")).click();
    Thread.sleep(3000);

    WebElement artworkThumb = driver.findElement(By.cssSelector("img[alt='Title is great']"));
    artworkThumb.click();
    Thread.sleep(2000);
    WebElement editArtworkLink = driver.findElement(By.cssSelector("#tab-info > div.edit > a"));
    editArtworkLink.click();
    Pattern pattern = Pattern.compile("^" + browser.fullURL("/artworks/") + "(.*)/edit$");
    browser.waitPageLoaded(pattern);
    browser.scrollAndClick(driver.findElementByClassName("delete-artwork"));
    (new WebDriverWait(driver, 20)).until(ExpectedConditions.alertIsPresent());
    driver.switchTo().alert().accept();
    browser.waitPageLoaded("");
  }

  @Test
  public void stage08_stackByArtWorkTypeAndYear() throws InterruptedException {

    WebElement exploreBtn = driver.findElement(By.cssSelector("#middle-menu > a:nth-child(2)"));
    exploreBtn.click();

    browser.waitPageLoaded("/all_added");

//    WebElement typeFilter = driver.findElement(By.cssSelector("#filter-type > div"));
//    WebElement yearFilter = driver.findElement(By.cssSelector("#filter-year > div"));

    new WebDriverWait(driver, 8).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".filter-loading")));
    List<WebElement> allStackBtns = driver.findElements(By.cssSelector(".artwork-stack-button-container > div"));
    browser.scrollAndClick(allStackBtns.get(0));

//    typeFilter.click();
//    new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
//        By.cssSelector("#filter-type > div > div > ul > li:nth-child(1)"))).click();
//    yearFilter.click();
//    new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(
//        By.cssSelector("#filter-year > div > div > ul > li:nth-child(3)"))).click();

    Thread.sleep(3000);

    allStackBtns = driver.findElements(By.cssSelector(".artwork-stack-button-container > div"));
    allStackBtns.get(0).click();
  }

  @Test
  public void stage09_stackByMostStackedOfAType() throws InterruptedException {

    WebElement topFilter = driver.findElement(By.cssSelector("#filter-top > div"));
    browser.scrollAndClick(topFilter);
    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("#filter-top > div > div > ul > li:nth-child(2)"))).click();

    browser.waitPageLoaded("/top/artwork-type/Painting");

    WebElement firstAddToStack = driver.findElement(By.cssSelector(".feed-item:nth-child(1) .stacking-button-small a"));
    browser.scrollAndClick(firstAddToStack);

  }

  @Test
  public void stage10_stackByTrending() throws InterruptedException {
    WebElement trendBtn = driver.findElement(By.cssSelector("#middle-menu > a:nth-child(4)"));
    trendBtn.click();

    browser.waitPageLoaded("/trending");

    WebElement firstArtwork = driver.findElement(By.xpath("//*[@id='trending-page']/div[3]/div/div/ul[1]/li[1]/div[1]/div/a"));
    WebElement firstAddBtn = driver.findElement(By.xpath("//*[@id='trending-page']/div[3]/div/div/ul[1]/li[1]/div[1]/div/div/div/a"));
    Actions action = new Actions(driver);
    action.moveToElement(firstArtwork).perform();
    action.click(firstAddBtn).perform();

    Thread.sleep(3000);

    //unstack;
    action.moveToElement(firstArtwork).perform();
    action.click(firstAddBtn).perform();

  }

  @Test
  public void stage11_homeButton() {
    WebElement homeBtn = driver.findElement(By.cssSelector("#left-console > a > img"));
    homeBtn.click();

    browser.waitPageLoaded("");
  }

  @Test
  public void stage12_searchAnArtworkAndFollowTheArtist(){
    WebElement searchField = driver.findElement(By.cssSelector("#q"));
    searchField.sendKeys("5566");
    searchField.submit();
    browser.waitPageLoaded("/search?utf8=%E2%9C%93&q=5566");
    WebElement firstArtist = driver.findElement(By.xpath("//*[@id='search']/em/div[1]/ul/li[1]/div[1]/a[1]"));
    firstArtist.click();

    WebElement followBtn = driver.findElement(By.cssSelector("#artist-section div.right > a"));
    followBtn.click();
  }

  @Test
  public void stage13_changeProfilePicture()  throws InterruptedException{
    WebElement nameSpan = driver.findElement(By.cssSelector("#line-nav > div.nav-box.profile-hover-trigger > a > span"));
    nameSpan.click();
    WebElement profileBtn = driver.findElement(By.cssSelector("#left > a.button"));
    profileBtn.click();
    browser.waitPageLoaded("/settings/profile");

    driver.findElement(By.id("user_profile_attributes_avatar")).sendKeys(this.fullFilePath("icon.jpeg"));

    driver.findElement(By.cssSelector(".actionset input[name='commit']")).click();
    /* Better wait for upload */
    Thread.sleep(5000);
  }

  @Test
  public void stage14_addACollection() {
    WebElement showCollectionBtn = driver.findElement(By.id("show-collection"));
    showCollectionBtn.click();
    WebElement addCollectionBtn = driver.findElement(By.cssSelector(".add-new-collection"));
    addCollectionBtn.click();
    WebElement newCollectionName = driver.findElement(By.id("stackgroup_name"));
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(newCollectionName));
    newCollectionName.click();
    newCollectionName.sendKeys("newCollection");
    newCollectionName.submit();

    Actions action = new Actions(driver);
    WebElement firstImg = driver.findElement(By.xpath("//*[@id='container']/div[2]/section/ul/li[1]/div/div[1]/div[1]/a/img"));
    WebElement collectionBanana = driver.findElement(By.cssSelector(".collection.drop-init"));
    action.dragAndDrop(firstImg, collectionBanana).perform();
  }

  @Test
  public void stage15_viewAdded() {
    WebElement addedBtn = driver.findElement(By.cssSelector("nav:not([id]) a:nth-child(2)"));
    addedBtn.click();
  }

  @Test
  public void stage16_viewFollowers() {
    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getCurrentUrl().contains("artworks");
      }
    });
    WebElement viewFollowerBtn = driver.findElement(By.cssSelector("nav:not([id]) a:nth-child(3)"));
    viewFollowerBtn.click();
    WebElement searchFollowerBar = driver.findElement(By.cssSelector(".user-search-box-wrapper .user-search-box"));
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchFollowerBar));
    driver.navigate().back();
  }

  @Test
  public void stage17_viewFollowings() {
    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        return d.getCurrentUrl().contains("artworks");
      }
    });
    WebElement viewFollowingBtn = driver.findElement(By.cssSelector("nav:not([id]) a:nth-child(4)"));
    viewFollowingBtn.click();

    WebElement firstArtists = driver.findElement(By.cssSelector("div.followers.following-list > ul > li:nth-child(1)"));
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(firstArtists));
  }

}

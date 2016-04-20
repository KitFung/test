package helper;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Let this take care of all the issue related to the browser.
 * @author kitfung_Oursky
 *
 */
public class Browser {

  private String baseURL;
  private RemoteWebDriver driver;

  public Browser(RemoteWebDriver driver, String baseURL) {
    this.driver = driver;
    this.baseURL = baseURL;
  }
  
  public String fullURL(String url) {
    return baseURL + url;
  }

  /**
   * Standardize the URL format for compare.
   * @param url ,unformatted URL
   * @return formatted URL
   */
  private String formattedUrl(String url) {
    if (url.endsWith("/")) {
      url = url.substring(0, url.length()-1);
    }
    return url;
  }

  /**
   * Input the relative url and wait until it match the current url.
   * The relative url should start with '/'
   * @param relativeUrl
   */
  public void waitPageLoaded(String relativeUrl) {
    (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        String url = formattedUrl(d.getCurrentUrl());
        return url.contentEquals(fullURL(relativeUrl));
      }
    });
  }

  /**
   * Input the url pattern and wait until it match the current url.
   * The pattern should match the full url instead of relative url.
   * @param pattern
   */
  public void waitPageLoaded(Pattern pattern) {
    (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        String url = formattedUrl(d.getCurrentUrl());
        Matcher m = pattern.matcher(url);
        return m.find();
      }
    });
  }

  public void goPage(String relativeUrl) {
    driver.get(fullURL(relativeUrl));
  }

  public void scrollAndClick(WebElement element) {
    int elementPosition = element.getLocation().getY();
    String js = String.format("window.scroll(0, %s - 100)", elementPosition);
    driver.executeScript(js);
    element.click();
  }

  public void checkPageIsOpened(String relativeURL, String keySentence) {
    this.waitPageLoaded(relativeURL);
    assertTrue(driver.getPageSource().contains(keySentence));
  }

  public void checkPageIsOpenedInNewTag(String relativeURL, String keySentence) throws InterruptedException {
    // Wait page open.
    Thread.sleep(2000);
    List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
    //switch to new tab
    driver.switchTo().window(browserTabs .get(1));
    checkPageIsOpened(relativeURL, keySentence);
  }

  public void closeAllOtherTab() {
    List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
    for(int i=1; i<browserTabs.size(); i++) {
      driver.switchTo().window(browserTabs.get(i));
      driver.close();
    }
    driver.switchTo().window(browserTabs.get(0));
  }
}

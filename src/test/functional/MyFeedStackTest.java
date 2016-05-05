package test.functional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import page.MyFeedPage;
import page.PageUtil;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyFeedStackTest extends ProductionTest{

  /** Config Part.
   * testItemsIndex: Modify it if you want to test more/different item
   */
  private MyFeedPage page;

  private void bindingPageToHTML() {
    page = new MyFeedPage(driver, _t(MyFeedPage.getPageLoadedText(isStaging)));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() {
	PageUtil.quickSignIn(driver, browser, email, password, isStaging);
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(MyFeedPage.pageUrl);
    browser.waitPageLoaded(MyFeedPage.pageUrl);
    bindingPageToHTML();
  }

  @Test
  public void test01_toogleLayout() {
    String jsCommand = "var feedItems = [].slice.call(document.getElementsByClassName('%s'), 0, 3);" +
                       "var result = feedItems.map(function(x) {return x.getBoundingClientRect().top});" +
                       "result = result.reduce(function(a, b){return (a === b)?a:false;});" +
                       "return result!= false";
    /* List first */
    page.switchToListMode();
    browser.waitPageLoaded("/?layout=feed");
    assertFalse((boolean)driver.executeScript(String.format(jsCommand, "text")));
    /* Then Grid */
    page.switchToGridMode();
    browser.waitPageLoaded("/?layout=mosaic");
    assertTrue((boolean)driver.executeScript(String.format(jsCommand, "text")));
  }
}

package test.component;

import org.junit.Before;
import org.openqa.selenium.support.PageFactory;

import page.MyFeedPage;

public class ArtworkListMyFeedTest extends ArtworkList{

  @Before
  public void before() throws InterruptedException {
    browser.goPage(MyFeedPage.pageUrl);
    browser.waitPageLoaded(MyFeedPage.pageUrl);
    page = new MyFeedPage(driver, _t(MyFeedPage.getPageLoadedText(isStaging)));
    PageFactory.initElements(driver, page);
  }
}

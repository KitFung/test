package test.component;

import org.junit.Before;
import org.openqa.selenium.support.PageFactory;

import page.ExplorePage;

public class ArtworkListExploreTest extends ArtworkList{
  @Before
  public void before() throws InterruptedException {
    browser.goPage(ExplorePage.pageUrl);
    browser.waitPageLoaded(ExplorePage.pageUrl);
    page = new ExplorePage(driver, _t(ExplorePage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }
}

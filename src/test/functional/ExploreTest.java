package test.functional;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.ArtworkType;
import constant.ArtworkDetails.YearRange;
import page.ExplorePage;
import page.PageUtil;
import test.ProductionTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExploreTest extends ProductionTest {
  private ExplorePage page;

  private void bindingPageToHTML() {
    page = new ExplorePage(driver, _t(ExplorePage.pageLoadedText));
    PageFactory.initElements(driver, page);
  }

  @BeforeClass
  public static void beforeClass() {
	  PageUtil.quickSignIn(driver, browser, email, password, isStaging);
  }

  @Before
  public void before() throws InterruptedException {
    browser.goPage(ExplorePage.pageUrl);
    browser.waitPageLoaded(ExplorePage.pageUrl);
    bindingPageToHTML();
  }

  @Test
  public void test01_artworkTypeFiltering() throws InterruptedException {

    for(ArtworkType t:ArtworkType.values()) {
      if(t == ArtworkType.OTHER) break;
      
      String tmpName = page.nameOfItemFromThumb(1);
      page.selectArtworkType(t);
      Thread.sleep(4000);
      assertFalse(tmpName.equals(page.nameOfItemFromThumb(1)));
    }
  }

  @Test
  public void test02_yearFiltering() throws InterruptedException {
    for(YearRange t:YearRange.values()) {
      String tmpName = page.nameOfItemFromThumb(1);
      page.selectArtworkYear(t);
      Thread.sleep(4000);
      assertFalse(tmpName.equals(page.nameOfItemFromThumb(1)));
    }
  }

  @Test
  public void test03_mostStackedFiltering() throws InterruptedException, UnsupportedEncodingException {
    for(ArtworkType t:ArtworkType.values()) {
      if(t == ArtworkType.OTHER) break;
      page.selectedMostedStacked(t);
      Thread.sleep(4000);
      browser.checkPageIsOpened("/top/artwork-type/" + t.getName().replaceAll(" ", "%20"),
          _t("Most Stacked"));
      before();
    }
  }

  /**
   * Just try all combination. Since there must have some case there are no
   * picture under specific condition, don't check the image name;
   */
  @Test
  public void test04_yearAndArtworkTypeFiltering() throws InterruptedException {
    for(YearRange t1:YearRange.values()) {
    	
      page.selectArtworkYear(t1);
      
      for(ArtworkType t2:ArtworkType.values()) {
        if(t2 == ArtworkType.OTHER) break;
        page.selectArtworkType(t2);
        Thread.sleep(2000);
      }
    }
  }

}

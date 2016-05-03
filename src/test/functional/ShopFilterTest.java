package test.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.ArtworkPrice;
import constant.ArtworkDetails.ArtworkType;
import page.MyFeedPage;
import page.ShopPage;
import page.SignInPage;
import test.StagingTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopFilterTest extends StagingTest{

	private ShopPage page;

	private void bindingPageToHTML() {
		page = new ShopPage(driver, _t(ShopPage.pageLoadedText));
		PageFactory.initElements(driver, page);
	}

	@BeforeClass
	public static void beforeClass() {
		browser.goPage(SignInPage.pageUrl);
		SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
		signInPage.fillEmail(email)
		.fillPassword(password)
		.clickLogIn();
		String keySentence = MyFeedPage.getPageLoadedText(isStaging);
		browser.checkPageIsOpened(MyFeedPage.pageUrl, _t(keySentence));
	}

	@Before
	public void before() throws InterruptedException {
		browser.goPage(ShopPage.pageUrl);
		browser.waitPageLoaded(ShopPage.pageUrl);
		bindingPageToHTML();
	}

	@Test
	public void test01_filterType() throws InterruptedException {
		page.filterByArtworkType(ArtworkType.NETART);
		page.clickProductsAt(0);
		Thread.sleep(3000);
		boolean checked = false;
		for(WebElement e : page.getArtworkTagFromViewer()) {
			if(e.getText().equals(ArtworkType.NETART.getName())); {
				checked = true;
				break;
			}
		}
		Assert.assertTrue( "The expect artwork type filter not work", checked);
		page.closeViewer();
		Thread.sleep(10000);
	}


}

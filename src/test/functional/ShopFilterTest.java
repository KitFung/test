package test.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.ArtworkType;
import constant.ShopFilterDetails.SortCondition;
import constant.ShopFilterDetails.SortCurrency;
import helper.StringDecoder;
import page.PageUtil;
import page.ShopPage;
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
		PageUtil.quickSignIn(driver, browser, email, password, isStaging);
	}

	@Before
	public void before() throws InterruptedException {
		browser.goPage(ShopPage.pageUrl);
		browser.waitPageLoaded(ShopPage.pageUrl);
		bindingPageToHTML();
	}

	@Test
	public void test01_artworkTypeFiltering() throws InterruptedException {
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
	}
	
	private ArrayList<Double> getPriceList() {
		ArrayList<Double> arr = new ArrayList<Double>();
		for(int i = 0; i < page.numberOfProducts(); ++i) {
			String fullPrice = page.getFullPriceAtThumb(i);
			double priceValue = 0;
			if(StringDecoder.isBusketExist(fullPrice))
				priceValue = StringDecoder.getNumberInBusket(fullPrice);
			else
				priceValue = StringDecoder.getNumberExceptBusket(fullPrice);
			arr.add(priceValue);
		}
		return arr;
	}

	@Test
	public void test02a_productSorting_low2high() throws InterruptedException {
		page.selectSortByCondition(SortCondition.PRICE_LOW_TO_HIGH);
		Thread.sleep(3000);
		ArrayList<Double> arr = getPriceList();
		for(int i = 0; i < arr.size() - 1; ++i) {
			assertTrue("The price order isn't from low to high", arr.get(i) <= arr.get(i + 1));
		}
	}
	
	@Test
	public void test02b_productSorting_high2low() throws InterruptedException {
		page.selectSortByCondition(SortCondition.PRICE_HIGH_TO_LOW);
		Thread.sleep(3000);
		ArrayList<Double> arr = getPriceList();
		for(int i = 0; i < arr.size() - 1; ++i) {
			assertTrue("The price order isn't from high to low", arr.get(i) >= arr.get(i + 1));
		}
	}
	
	@Test
	public void test02c_productSorting_relevance() throws InterruptedException {
		page.selectSortByCondition(SortCondition.RELEVANCE);
		Thread.sleep(3000);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < page.numberOfProducts() - 1; ++i) {
			arr.add(StringDecoder.getNumberofFollowerFromFullDesc(page.getFullFollowerInfoAtThumb(i)));
		}
		for(int i = 0; i < arr.size() - 1; ++i) {
			assertTrue("The price order isn't from high to low", arr.get(i) >= arr.get(i + 1));
		}
	}
	
	@Test
	public void test03_currencySorting() throws InterruptedException {
		page.selectSortByCurrency(SortCurrency.GBP);
		Thread.sleep(3000);
		for(int i = 0; i < page.numberOfProducts(); ++i) {
			assertTrue("It is missing the corresponding dollors sign", page.getFullPriceAtThumb(i).contains("£"));
		}
	}
	
	@Test
	public void test04_checkDetailBetweenThumbandViewer() throws InterruptedException {
		page.selectSortByCurrency(SortCurrency.NOK);
		Thread.sleep(3000);

		int productcount = page.numberOfProducts();
		int randomIndex = new Random().nextInt(productcount - 1);
		String productName = page.getProductNameAtThumb(randomIndex);
		String artistName = page.getArtisitNameAtThumb(randomIndex);
		String followerInfo = page.getFullFollowerInfoAtThumb(randomIndex);
		double priceInOriginalCurrency = StringDecoder.getNumberExceptBusket(page.getFullPriceAtThumb(randomIndex));
		double priceInLocalizedCurrency = StringDecoder.getNumberInBusket(page.getFullPriceAtThumb(randomIndex));
		
		page.clickProductsAt(randomIndex);
		assertTrue("The product name is different in thumb and viewer", page.getProductNameAtViewer().contains(productName));
		assertEquals("The artisit name is different in thumb and viewer", artistName, page.getArtisitNameAtViewer());
		assertEquals("The follower info is different in thumb and viewer", followerInfo, page.getFullFollowerInfoAtViewer());
		assertTrue("The price (original currency) e is different in thumb and viewer", 
				priceInOriginalCurrency == StringDecoder.getNumberExceptBusket(page.getOriginalPriceAtViewer()));
		assertTrue("The price (localized currency) e is different in thumb and viewer", 
				priceInLocalizedCurrency == StringDecoder.getNumberInBusket(page.getLocalizedPriceAtViewer()));
	}
	
	@Test
	public void test05_clickBuyButton() throws InterruptedException {
		int productcount = page.numberOfProducts();
		int pagecount = driver.getWindowHandles().size();
		int randomIndex = new Random().nextInt(productcount - 1);
		page.clickProductsAt(randomIndex);
		page.clickBuyAtViewer();
		Thread.sleep(2000);
		assertTrue("It haven't open the new page", pagecount < driver.getWindowHandles().size());
		browser.closeAllOtherTab();
	}
	
	/**
	 * Assumption: Your only followed limited users
	 * @throws InterruptedException
	 */
	@Test
	public void test06_switchBuyFromOption() throws InterruptedException {
		int productcountBefore = page.numberOfProducts();
		page.switchBuyFrom(true);
		Thread.sleep(3000);
		int productcountAfter = page.numberOfProducts();
		assertNotEquals("It haven't filter the product", productcountBefore, productcountAfter);
	}

}

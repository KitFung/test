package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import constant.ArtworkDetails.*;
import constant.ShopFilterDetails.*;
import page.component.IProductListComponent;
import page.component.ProductListComponent;

public class ShopPage extends Page implements IProductListComponent {

	private ProductListComponent artworkList;
	public final static String pageUrl = "/shop";

	public final static String pageLoadedText = "BUY FROM";

	@FindBy(css = "#ordering > div")
	@CacheLookup
	private WebElement sortByCondition;

	@FindBy(css = "#currencies > div")
	@CacheLookup
	private WebElement sortByCurrency;

	@FindBy(css = "#artwork_type_chosen")
	@CacheLookup
	private WebElement filterByType;

	@FindBy(css = "#artwork-type")
	@CacheLookup
	private WebElement filterByType_select;

	@FindBy(css = "#price_chosen")
	@CacheLookup
	private WebElement filterByPrice;

	@FindBy(css = "#size_chosen")
	@CacheLookup
	private WebElement filterBySize;

	public ShopPage(WebDriver driver) {
		this(driver, pageLoadedText);
	}

	public ShopPage(WebDriver driver, String customPageLoadedText) {
		super(driver, pageUrl, customPageLoadedText);
		artworkList = PageFactory.initElements(driver, ProductListComponent.class);
	}

	public ShopPage selectSortByCondition(SortCondition cond) {
		selectDropdownOptionByIndex(
				sortByCondition,
				sortByCondition,
				cond);
		return this;
	}

	public ShopPage selectSortByCurrency(SortCurrency cur) {
		selectDropdownOptionByIndex(
				sortByCurrency,
				sortByCurrency,
				cur);
		return this;
	}

	public ShopPage filterByArtworkType(ArtworkType type) {
		selectDropdownOptionByName(
				filterByType,
				filterByType,
				type.getName());
		return this;
	}

	public ShopPage filterByPrice(ArtworkPrice price) {
		selectDropdownOptionByIndex(
				filterByPrice,
				filterByPrice,
				price);
		return this;
	}

	public ShopPage filterBySize(ArtworkSize size) {
		selectDropdownOptionByIndex(
				filterBySize,
				filterBySize,
				size);
		return this;
	}

	@Override
	public IProductListComponent clickProductsAt(int index) {
		artworkList.clickProductsAt(index);
		return this;
	}

	@Override
	public ShopPage closeViewer() {
		artworkList.closeViewer();
		return this;
	}
	
	@Override
	public List<WebElement> getArtworkTagFromViewer() {
		return artworkList.getArtworkTagFromViewer();
	}

	@Override
	public int numberOfProducts() {
		return artworkList.numberOfProducts();
	}
}

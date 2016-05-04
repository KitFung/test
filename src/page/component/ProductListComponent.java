package page.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListComponent implements IProductListComponent{

	protected WebDriver driver;

	@FindBy(css = ".product")
	private List<WebElement> products;

	@FindBy(css = "#artwork-viewer")
	private WebElement  viewer;

	@FindBy(css = "#artwork-viewer .purchase-button")
	private WebElement  buyBtnOnViewer;

	@FindBy(css = "#artwork-viewer .artwork-viewer_close")
	private WebElement  closeBtnOnViewer;

	@FindBy(css = ".work-container div.metadata-list > dl > dd a")
	private List<WebElement> artworkTypeTagOnViewer;

	public ProductListComponent(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getProductsAt(int index) {
		return products.get(index);
	}

	@Override
	public IProductListComponent clickProductsAt(int index) {
		getProductsAt(index).click();
		return this;
	}

	@Override
	public String getFullPriceAtThumb(int index) {
		return getProductsAt(index).findElement(By.className("price")).getText();
	}

	@Override
	public String getProductNameAtThumb(int index) {
		return getProductsAt(index).findElement(By.cssSelector(".title a")).getText();
	}

	@Override
	public String getArtisitNameAtThumb(int index) {
		return getProductsAt(index).findElement(By.cssSelector(".artist a:nth-child(1)")).getText();
	}

	@Override
	public String getFullFollowerInfoAtThumb(int index) {
		return getProductsAt(index).findElement(By.className("followers")).getText();
	}

	@Override
	public String getOriginalPriceAtViewer() {
		String price = viewer.findElement(By.cssSelector(".price")).getText();
		String unit = viewer.findElement(By.cssSelector(".price > span")).getText();
		return price + unit;
	}

	public String getLocalizedPriceAtViewer() {		
		return viewer.findElement(By.className("price-localized")).getText();
	}

	@Override
	public String getProductNameAtViewer() {
		return viewer.findElement(By.cssSelector(".work-title > a")).getText();
	}

	@Override
	public String getArtisitNameAtViewer() {
		return viewer.findElement(By.cssSelector(".artist-name > a")).getText();
	}

	@Override
	public IProductListComponent clickBuyAtViewer() {
		buyBtnOnViewer.click();
		return this;
	}

	public String getFullFollowerInfoAtViewer() {
		return viewer.findElement(By.className("followers")).getText();
	}

	@Override
	public IProductListComponent closeViewer() {
		closeBtnOnViewer.click();
		return this;
	}

	@Override
	public List<WebElement> getArtworkTagFromViewer() {
		return artworkTypeTagOnViewer;
	}

	@Override
	public int numberOfProducts() {
		return products.size();
	}

}

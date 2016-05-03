package page.component;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListComponent implements IProductListComponent{

  protected WebDriver driver;

  @FindBy(css = ".product")
  private List<WebElement> products;

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

package page.component;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IProductListComponent {
	  public IProductListComponent clickProductsAt(int index);

	  public String getFullPriceAtThumb(int index);
	  
	  public String getProductNameAtThumb(int index);
	  
	  public String getArtisitNameAtThumb(int index);
	  
	  public String getFullFollowerInfoAtThumb(int index); 
	  
	  public String getOriginalPriceAtViewer();
	  
	  public String getLocalizedPriceAtViewer();
	  
	  public String getProductNameAtViewer();
	  
	  public String getArtisitNameAtViewer();
	  
	  public String getFullFollowerInfoAtViewer();
	  
	  public IProductListComponent clickBuyAtViewer();
	  
	  public IProductListComponent closeViewer();

	  public List<WebElement> getArtworkTagFromViewer();

	  public int numberOfProducts();
}

package page.component;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IProductListComponent {
	  public IProductListComponent clickProductsAt(int index);

	  public IProductListComponent closeViewer();

	  public List<WebElement> getArtworkTagFromViewer();

	  public int numberOfProducts();
}

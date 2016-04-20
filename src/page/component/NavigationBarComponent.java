package page.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class NavigationBarComponent implements INavigationBarComponent{

  protected WebDriver driver;

  @FindBy(css = "#left-console .search .ui-autocomplete-input")
  @CacheLookup
  private WebElement searchField;

  @FindBy(css = "#left-console .search input[type='submit']")
  @CacheLookup
  private WebElement searchButton;

  public NavigationBarComponent(WebDriver driver) {
    this.driver = driver;
  }
  @Override
  public INavigationBarComponent searchArtwork(String query) {
    searchField.sendKeys(query);
    searchButton.click();
    return this;
  }

}

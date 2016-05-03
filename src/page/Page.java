package page;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ISelectableOption;

/**
 * Selenium have support to page object pattern:
 *  https://code.google.com/p/selenium/wiki/PageFactory
 *
 * This page object just take the responsibility for the action/assertion
 * after that page was loaded.
 * 
 * @author kitfung_Oursky
 *
 */

public class Page {
	private int timeout;
	private String url;
	protected WebDriver driver;
	private String loadedText = "";

	/**
	 * Default Constructor.
	 * @param driver, the driver to do action and search element.
	 * @param url, the relative URL of that page, e.x. "/signup".
	 * @param pageLoadedText, the text that used to determine whether the page have been fully loaded.
	 */
	public Page(WebDriver driver, String url, String pageLoadedText) {
		this(driver, url, pageLoadedText, 15);
	}

	/**
	 * Constructor when you think that page need more wait time.
	 * It will fail to construct if the current page is not this page.
	 * @param driver
	 * @param url
	 * @param pageLoadedText
	 * @param timeout
	 */
	public Page(WebDriver driver, String url, String pageLoadedText, int timeout) {
		this.driver = driver;
		this.url = url;
		this.loadedText = pageLoadedText;
		this.timeout = timeout;
		verifyPageLoaded();
	}

	public String getUrl() {
		return this.url;
	}

	/**
	 * Verify that current page URL matches the expected URL.
	 *
	 * @return the SignUpPage class instance.
	 */
	public void verifyPageUrl() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getCurrentUrl().contains(url);
			}
		});
	}

	/**
	 * Verify that the page loaded completely.
	 *
	 * @return the SignUpPage class instance.
	 */
	public void verifyPageLoaded() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(loadedText);
			}
		});
	}

	public void openDropDownList(WebElement dropDownOpenDiv, WebElement dropDownDiv) {
		dropDownOpenDiv.click();
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				List<String> allClass = Arrays.asList(dropDownDiv.getAttribute("class").split(" "));
				return allClass.contains("chosen-with-drop");
			}
		});
	}
	public void selectDropdownOptionByIndex(WebElement dropDownOpenDiv, WebElement dropDownDiv, ISelectableOption option) {
		openDropDownList(dropDownOpenDiv, dropDownDiv);
		String selector = String.format("li[data-option-array-index='%d']", option.getArrayIndex());
		driver.findElement(By.cssSelector(selector)).click();
	}
	
	public void selectDropdownOptionByName(WebElement dropDownOpenDiv, WebElement dropDownDiv, String name) {
		openDropDownList(dropDownOpenDiv, dropDownDiv);
		String selector = String.format("//li[contains(text(), '%s')]", name);
		driver.findElement(By.xpath(selector)).click();
	}

}

package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import constant.Language;
import helper.Browser;
import helper.Translator;

/**
 * The TestBase setup all the basic configuration,
 * include implicitly wait, and some global data.
 * 
 * Browser take all responsibility for browser related action.
 * 
 * @author kitfung_Oursky
 *
 */
public class TestBase {
  protected static RemoteWebDriver driver;
  protected static Browser browser;
  protected static Language currentLanguage;
  protected static Properties properties;

  protected static String baseURL;

  protected static String firstName;
  protected static String lastName;
  protected static String email;
  protected static String password;
  protected static String userDomain;
  protected static boolean isStaging;
  
  /**
   * This must be new every time you run the test.
   */
  protected static String smokeTestEmail;
  protected static String smokeTestPassword;

  @Rule
  public FailureCapture screenShootRule = new FailureCapture(driver);

  private static void importConfig() throws FileNotFoundException, IOException {
    properties = new Properties();
    String filePath = new File("").getAbsolutePath();
    String configFile = "config.properties";
    filePath = filePath + "/" + configFile;
    properties.load(new FileInputStream(filePath));

    currentLanguage = Translator.getLanguageByCode(properties.getProperty("CURRENT_LANGUAGE"));
    smokeTestEmail = properties.getProperty("SMOKE_EMAIL");
    smokeTestPassword = properties.getProperty("SMOKE_PASSWORD");
  }
  
  private static DesiredCapabilities capabilitiesFactory(String browserName) {
	  switch(browserName) {
	  case "chrome":
		  return DesiredCapabilities.chrome();
	  case "firefox":
		  return DesiredCapabilities.firefox();
	  default:
		  throw new IllegalArgumentException("Invalid browser name");
	  }
  }
  
  private static RemoteWebDriver browserFactory(String browserName) {
	  switch(browserName) {
	  case "chrome":
		  return new ChromeDriver();
	  case "firefox":
		  return new FirefoxDriver();
	  default:
		  throw new IllegalArgumentException("Invalid browser name");
	  }
  }
  
  private static void buildDriver() throws MalformedURLException {
	  boolean existedDriver = Boolean.parseBoolean(properties.getProperty("EXISTED_DRIVER"));
	  if(existedDriver) {
		  String hostAddr = System.getenv("CHROME_PORT_4444_TCP_ADDR");
		  hostAddr = hostAddr==null?"localhost":hostAddr;
		  driver = new RemoteWebDriver(new URL("http://" + hostAddr + ":4444/wd/hub"), 
				  capabilitiesFactory(properties.getProperty("TARGET_BROWSER")));
		  driver.setFileDetector(new LocalFileDetector());
	  } else {
		  driver = browserFactory(properties.getProperty("TARGET_BROWSER"));
	  }
	  driver.manage().deleteAllCookies();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @BeforeClass
  public static void openBrowser() throws FileNotFoundException, IOException{
    importConfig();
    buildDriver();
  }

  @AfterClass
  public static void closeBrowser(){
    driver.manage().deleteAllCookies();
    driver.quit();
  }

  public String fullFilePath(String path) {
    return System.getProperty("user.dir") + "/static/" +path; 
  }

  public static String _t(String s){
    try {
      return Translator.translate(s, currentLanguage);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

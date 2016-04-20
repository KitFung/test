package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

  @BeforeClass
  public static void openBrowser() throws FileNotFoundException, IOException{
    importConfig();

    /* You can text other browser by changing this */
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--no-sandbox");

    driver = new ChromeDriver(options);
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    
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

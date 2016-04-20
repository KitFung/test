package test;

import org.junit.BeforeClass;

import helper.Browser;

public class ProductionTest extends TestBase{

  @BeforeClass
  public static void setUpBrowser() {
    isStaging = false;
    baseURL = properties.getProperty("PRODUCTION_URL");
    firstName = properties.getProperty("P_FIRST_NAME");
    lastName = properties.getProperty("P_LAST_NAME");
    email = properties.getProperty("P_EMAIL");
    password = properties.getProperty("P_PASSWORD");
    userDomain = properties.getProperty("P_USER_DOMAIN");

    browser = new Browser(driver, baseURL);
  }
}

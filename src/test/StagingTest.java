package test;

import org.junit.BeforeClass;

import helper.Browser;

public class StagingTest extends TestBase{

  @BeforeClass
  public static void setUpBrowser() {
    isStaging = true;
    baseURL = properties.getProperty("STAGING_URL");
    firstName = properties.getProperty("S_FIRST_NAME");
    lastName = properties.getProperty("S_LAST_NAME");
    email = properties.getProperty("S_EMAIL");
    password = properties.getProperty("S_PASSWORD");
    userDomain = properties.getProperty("S_USER_DOMAIN");

    browser = new Browser(driver, baseURL);
  }
}

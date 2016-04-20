package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FailureCapture extends TestWatcher{

  private WebDriver driver;
  public FailureCapture(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  protected void failed(Throwable e, Description description) {
    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

    File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
    String fileName = description.getClassName() + "-" + description.getMethodName();
    File destFile = getDestinationFile(fileName);
    try {
      FileUtils.copyFile(scrFile, destFile);
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }

  private File getDestinationFile(String fileName) {
    String destDir = "screenshots/Failures";
    String absoluteFileName = destDir + "/" + fileName + ".png";

    return new File(absoluteFileName);
  }
}

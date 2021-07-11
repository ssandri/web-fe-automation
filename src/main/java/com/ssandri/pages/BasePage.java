package com.ssandri.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  protected final WebDriver driver;
  final WebDriverWait wait;

  public BasePage(WebDriver driver) {

    this.driver = driver;
    this.wait = new WebDriverWait(driver, 5);
  }

  void waitForPageToLoad() {

    ExpectedCondition<Boolean> jQueryLoad = driver -> (
        (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
    wait.until(jQueryLoad);
  }

}

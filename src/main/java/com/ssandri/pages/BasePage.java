package com.ssandri.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

  protected final WebDriver driver;
  final WebDriverWait wait;
  protected static final Logger LOGGER = LoggerFactory.getLogger("web-fe-automation");

  public BasePage(WebDriver driver) {

    this.driver = driver;
    this.wait = new WebDriverWait(driver, 5);
    PageFactory.initElements(driver, this);
  }

  void waitForPageToLoad() {

    ExpectedCondition<Boolean> jQueryLoad = driver -> (
        (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
    wait.until(jQueryLoad);
  }

}

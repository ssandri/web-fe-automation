package com.ssandri.pages;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

  private final By homeMenuBy = By.cssSelector("a[href='index.html']");
  private final By productCategoryBy = By.id("itemc");
  private final By productTitleBy = By.cssSelector("h4.card-title");

  public HomePage(WebDriver driver) {

    super(driver);
  }

  public void open() {

    driver.findElement(homeMenuBy).click();
  }

  public void openProductCategory(String categoryName) {

    driver.findElements(productCategoryBy).stream()
        .filter(e -> e.getText().equalsIgnoreCase(categoryName)).findFirst().orElseThrow(
        () -> new NoSuchElementException(format("Cannot locate a category named '%s'", categoryName)))
        .click();
    super.waitForPageToLoad();
  }

  public void openProductDetailsPage(String productName) {

    driver.findElements(productTitleBy).stream()
        .filter(webElement -> webElement.getText().equalsIgnoreCase(productName)).findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in product list.", productName))).click();

    super.waitForPageToLoad();
  }

  public List<String> getProductList() {

    return driver.findElements(productTitleBy)
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}

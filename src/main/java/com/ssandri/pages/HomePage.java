package com.ssandri.pages;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {

    super(driver);
  }

  public void open() {

    driver.findElement(By.cssSelector("a[href='index.html']")).click();
  }

  public void openProductCategory(String categoryName) {

    driver.findElements(By.id("itemc")).stream()
        .filter(e -> e.getText().equalsIgnoreCase(categoryName)).findFirst().orElseThrow(
        () -> new NoSuchElementException(format("Cannot locate a category named '%s'", categoryName)))
        .click();
    super.waitForPageToLoad();
  }

  public void openProductDetailsPage(String productName) {

    driver.findElements(By.cssSelector("h4.card-title")).stream()
        .filter(webElement -> webElement.getText().equalsIgnoreCase(productName)).findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in product list.", productName))).click();

    super.waitForPageToLoad();
  }

  public List<String> getProductList() {

    return driver.findElements(By.cssSelector("h4.card-title"))
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}

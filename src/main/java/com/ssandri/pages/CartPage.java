package com.ssandri.pages;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

  public CartPage(WebDriver driver) {

    super(driver);
  }

  public void open() {

    driver.findElement(By.linkText("Cart")).click();
    super.waitForPageToLoad();
  }

  public void removeProduct(String productName) {

    WebElement productInCart = driver.findElements(By.className("success"))
        .stream()
        .filter(webElement -> webElement.getText().contains(productName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in cart.", productName)));
    productInCart.findElement(By.linkText("Delete")).click();

    super.waitForPageToLoad();
  }

  public String getTotalCost() {

    return driver.findElement(By.id("totalp")).getText();
  }

  public void proceedToCheckout() {

    driver.findElement(By.cssSelector("button[data-target='#orderModal']")).click();
    super.wait.until(visibilityOf(driver.findElement(By.cssSelector("#orderModal"))));
  }

}

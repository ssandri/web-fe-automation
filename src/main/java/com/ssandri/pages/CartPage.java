package com.ssandri.pages;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

  private final By cartMenuBy = By.linkText("Cart");
  private final By productRowBy = By.className("success");
  private final By deleteLinkBy = By.linkText("Delete");
  private final By totalCostTxtBy = By.id("totalp");
  private final By proceedToCheckoutBtnBy = By.cssSelector("button[data-target='#orderModal']");
  private final By checkoutModalBy = By.cssSelector("#orderModal");

  public CartPage(WebDriver driver) {

    super(driver);
  }

  public void open() {

    driver.findElement(cartMenuBy).click();
    super.waitForPageToLoad();
  }

  public void removeProduct(String productName) {

    WebElement productInCart = driver.findElements(productRowBy)
        .stream()
        .filter(webElement -> webElement.getText().contains(productName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in cart.", productName)));
    productInCart.findElement(deleteLinkBy).click();

    super.waitForPageToLoad();
  }

  public String getTotalCost() {

    return driver.findElement(totalCostTxtBy).getText();
  }

  public void proceedToCheckout() {
    
    driver.findElement(proceedToCheckoutBtnBy).click();
    super.wait.until(visibilityOf(driver.findElement(checkoutModalBy)));
  }

}

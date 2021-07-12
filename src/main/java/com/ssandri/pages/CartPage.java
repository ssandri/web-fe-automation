package com.ssandri.pages;

import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

  @FindBy(linkText = "Cart")
  private WebElement cartMenu;

  @FindBy(css = "tr.success")
  private List<WebElement> productRows;

  private final By deleteLinkBy = By.linkText("Delete");

  @FindBy(id = "totalp")
  private WebElement totalCostTxt;

  @FindBy(css = "button[data-target='#orderModal']")
  private WebElement proceedToCheckoutBtn;

  @FindBy(css = "#orderModal")
  private WebElement checkoutModal;

  public CartPage(WebDriver driver) {

    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {

    cartMenu.click();
    super.waitForPageToLoad();
    LOGGER.info("Cart page successfully loaded.");
  }

  public void removeProduct(String productName) {

    WebElement productInCart = productRows
        .stream()
        .filter(webElement -> webElement.getText().contains(productName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in cart.", productName)));
    LOGGER.info("Product '{}' found in cart page.", productName);
    productInCart.findElement(deleteLinkBy).click();
    super.waitForPageToLoad();
    LOGGER.info("Product '{}' deleted from cart page.", productName);
  }

  public String getTotalCost() {

    return totalCostTxt.getText();
  }

  public void proceedToCheckout() {

    proceedToCheckoutBtn.click();
    super.wait.until(visibilityOf(checkoutModal));
    LOGGER.info("Checkout page loaded.");
  }

}

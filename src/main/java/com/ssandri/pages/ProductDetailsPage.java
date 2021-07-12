package com.ssandri.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {

  @FindBy(css = "a.btn")
  private WebElement addProductBtn;

  public ProductDetailsPage(WebDriver driver) {

    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void addProductToCart() {

    addProductBtn.click();
    this.acceptAlert();
  }

  private void acceptAlert() {

    super.wait.until(alertIsPresent());
    LOGGER.info("Product added to cart.");
    driver.switchTo().alert().accept();
    LOGGER.info("Product added alert closed.");
    driver.switchTo().defaultContent();
  }
}

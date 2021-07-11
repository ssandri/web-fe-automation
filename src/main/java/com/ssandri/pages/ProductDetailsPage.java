package com.ssandri.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

  private final By addProductBtnBy = By.cssSelector("a.btn");

  public ProductDetailsPage(WebDriver driver) {

    super(driver);
  }

  public void addProductToCart() {

    driver.findElement(addProductBtnBy).click();
    this.acceptAlert();
  }

  private void acceptAlert() {

    super.wait.until(alertIsPresent());
    driver.switchTo().alert().accept();
    driver.switchTo().defaultContent();
  }
}

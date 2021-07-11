package com.ssandri.pages;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

  public CheckoutPage(WebDriver driver) {

    super(driver);
  }

  public void setCostumerName(String costumerName) {

    driver.findElement(By.id("name")).sendKeys(costumerName);
  }

  public void setCostumerCountry(String costumerCountry) {

    driver.findElement(By.id("country")).sendKeys(costumerCountry);
  }

  public void setCostumerCity(String costumerCity) {

    driver.findElement(By.id("city")).sendKeys(costumerCity);
  }

  public void setCostumerCardNumber(String cardNumber) {

    driver.findElement(By.id("card")).sendKeys(cardNumber);
  }

  public void setCostumerCardExpirationMonth(String cardExpirationMonth) {

    driver.findElement(By.id("month")).sendKeys(cardExpirationMonth);
  }

  public void setCostumerCardExpirationYear(String cardExpirationYear) {

    driver.findElement(By.id("year")).sendKeys(cardExpirationYear);
  }

  public void placeOrder() {

    driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
  }

  public boolean isOrderConfirmationMsgDisplayed() {

    return driver.findElement(By.cssSelector("div.sweet-alert")).isDisplayed();
  }

  public Map<String, String> getConfirmationMessage() {

    String orderConfirmationText = driver.findElement(By.cssSelector("p.lead")).getText();
    return Arrays.stream(orderConfirmationText.split(System.lineSeparator())).map(s -> s.split(": "))
        .collect(Collectors.toMap(e -> e[0], e -> e[1]));
  }

  public void closeConfirmationMessage() {

    driver.findElement(By.cssSelector("button.confirm")).click();
  }
}

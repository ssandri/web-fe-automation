package com.ssandri.pages;

import static java.util.Arrays.stream;

import com.ssandri.dto.CostumerInfo;
import com.ssandri.dto.OrderInfo;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

  public CheckoutPage(WebDriver driver) {

    super(driver);
  }

  public void completeCostumerInfo(CostumerInfo costumerInfo) {

    driver.findElement(By.id("name")).sendKeys(costumerInfo.getName());
    driver.findElement(By.id("country")).sendKeys(costumerInfo.getCountry());
    driver.findElement(By.id("city")).sendKeys(costumerInfo.getCity());
    driver.findElement(By.id("card")).sendKeys(costumerInfo.getCreditCardInfo().getNumber());
    driver.findElement(By.id("month")).sendKeys(costumerInfo.getCreditCardInfo().getExpirationMonth());
    driver.findElement(By.id("year")).sendKeys(costumerInfo.getCreditCardInfo().getExpirationYear());
  }

  public void placeOrder() {

    driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
  }

  public boolean isOrderConfirmationMsgDisplayed() {

    return driver.findElement(By.cssSelector("div.sweet-alert")).isDisplayed();
  }

  public OrderInfo getConfirmationMessage() {

    String orderConfirmationText = driver.findElement(By.cssSelector("p.lead")).getText();
    Map<String, String> orderConfirmationMap = stream(orderConfirmationText.split(System.lineSeparator()))
        .map(s -> s.split(": "))
        .collect(Collectors.toMap(e -> e[0], e -> e[1]));

    return new OrderInfo(
        orderConfirmationMap.get("Id"),
        orderConfirmationMap.get("Amount"),
        orderConfirmationMap.get("Card Number"),
        orderConfirmationMap.get("Name"),
        orderConfirmationMap.get("Date"));
  }

  public void closeConfirmationMessage() {

    driver.findElement(By.cssSelector("button.confirm")).click();
  }
}

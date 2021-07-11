package com.ssandri.pages;

import com.ssandri.dto.CostumerInfo;
import java.util.Arrays;
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

  public Map<String, String> getConfirmationMessage() {

    String orderConfirmationText = driver.findElement(By.cssSelector("p.lead")).getText();
    return Arrays.stream(orderConfirmationText.split(System.lineSeparator())).map(s -> s.split(": "))
        .collect(Collectors.toMap(e -> e[0], e -> e[1]));
  }

  public void closeConfirmationMessage() {

    driver.findElement(By.cssSelector("button.confirm")).click();
  }
}

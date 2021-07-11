package com.ssandri.pages;

import static java.util.Arrays.stream;

import com.ssandri.dto.CostumerInfo;
import com.ssandri.dto.OrderInfo;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

  private final By nameTxtBy = By.id("name");
  private final By countryTxtBy = By.id("country");
  private final By cityTxtBy = By.id("city");
  private final By cardNumberTxtBy = By.id("card");
  private final By cardMonthTxtBy = By.id("month");
  private final By cardYearTxtBy = By.id("year");
  private final By placeOrderBtnBy = By.cssSelector("button[onclick='purchaseOrder()']");
  private final By orderConfirmationMsgBy = By.cssSelector("div.sweet-alert");
  private final By confirmationMsgTxtBy = By.cssSelector("p.lead");
  private final By closeBtnBy = By.cssSelector("button.confirm");

  public CheckoutPage(WebDriver driver) {

    super(driver);
  }

  public void completeCostumerInfo(CostumerInfo costumerInfo) {

    driver.findElement(nameTxtBy).sendKeys(costumerInfo.getName());
    driver.findElement(countryTxtBy).sendKeys(costumerInfo.getCountry());
    driver.findElement(cityTxtBy).sendKeys(costumerInfo.getCity());
    driver.findElement(cardNumberTxtBy).sendKeys(costumerInfo.getCreditCardInfo().getNumber());
    driver.findElement(cardMonthTxtBy).sendKeys(costumerInfo.getCreditCardInfo().getExpirationMonth());
    driver.findElement(cardYearTxtBy).sendKeys(costumerInfo.getCreditCardInfo().getExpirationYear());
  }

  public void placeOrder() {

    driver.findElement(placeOrderBtnBy).click();
  }

  public boolean isOrderConfirmationMsgDisplayed() {

    return driver.findElement(orderConfirmationMsgBy).isDisplayed();
  }

  public OrderInfo getConfirmationMessage() {

    String orderConfirmationText = driver.findElement(confirmationMsgTxtBy).getText();
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

    driver.findElement(closeBtnBy).click();
  }
}

package com.ssandri.pages;

import static java.util.Arrays.stream;

import com.ssandri.dto.CostumerInfo;
import com.ssandri.dto.OrderInfo;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

  @FindBy(id = "name")
  private WebElement nameTxt;

  @FindBy(id = "country")
  private WebElement countryTxt;

  @FindBy(id = "city")
  private WebElement cityTxt;

  @FindBy(id = "card")
  private WebElement cardNumberTxt;

  @FindBy(id = "month")
  private WebElement cardMonthTxt;

  @FindBy(id = "year")
  private WebElement cardYearTxt;

  @FindBy(css = "button[onclick='purchaseOrder()']")
  private WebElement placeOrderBtn;

  @FindBy(css = "div.sweet-alert")
  private WebElement orderConfirmationMsg;

  @FindBy(css = "p.lead")
  private WebElement confirmationMsgTxt;

  @FindBy(css = "button.confirm")
  private WebElement closeBtn;

  public CheckoutPage(WebDriver driver) {

    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void completeCostumerInfo(CostumerInfo costumerInfo) {

    nameTxt.sendKeys(costumerInfo.getName());
    countryTxt.sendKeys(costumerInfo.getCountry());
    cityTxt.sendKeys(costumerInfo.getCity());
    cardNumberTxt.sendKeys(costumerInfo.getCreditCardInfo().getNumber());
    cardMonthTxt.sendKeys(costumerInfo.getCreditCardInfo().getExpirationMonth());
    cardYearTxt.sendKeys(costumerInfo.getCreditCardInfo().getExpirationYear());
  }

  public void placeOrder() {

    placeOrderBtn.click();
  }

  public boolean isOrderConfirmationMsgDisplayed() {

    return orderConfirmationMsg.isDisplayed();
  }

  public OrderInfo getConfirmationMessage() {

    String orderConfirmationText = confirmationMsgTxt.getText();
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

    closeBtn.click();
  }
}

package com.ssandri.stepdefinitions.parameters;

public class CreditCardInfo {

  private String number;
  private String expirationMonth;
  private String expirationYear;

  public CreditCardInfo(String number, String expirationMonth, String expirationYear) {

    this.number = number;
    this.expirationMonth = expirationMonth;
    this.expirationYear = expirationYear;
  }

  public String getNumber() {

    return number;
  }

  public String getExpirationMonth() {

    return expirationMonth;
  }

  public String getExpirationYear() {

    return expirationYear;
  }
}

package com.ssandri.dto;

public class CreditCardInfo {

  private final String number;
  private final String expirationMonth;
  private final String expirationYear;

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

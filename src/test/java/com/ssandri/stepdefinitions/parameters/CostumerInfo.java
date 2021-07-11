package com.ssandri.stepdefinitions.parameters;

public class CostumerInfo {

  private String name;
  private String country;
  private String city;
  private CreditCardInfo creditCardInfo;

  public CostumerInfo(String name, String country, String city,
      CreditCardInfo creditCardInfo) {

    this.name = name;
    this.country = country;
    this.city = city;
    this.creditCardInfo = creditCardInfo;
  }

  public String getName() {

    return name;
  }

  public String getCountry() {

    return country;
  }

  public String getCity() {

    return city;
  }

  public CreditCardInfo getCreditCardInfo() {

    return creditCardInfo;
  }
}

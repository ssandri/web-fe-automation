package com.ssandri.dto;

public class CostumerInfo {

  private final String name;
  private final String country;
  private final String city;
  private final CreditCardInfo creditCardInfo;

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

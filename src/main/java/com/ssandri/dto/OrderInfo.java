package com.ssandri.dto;

public class OrderInfo {

  private final String id;
  private final String amount;
  private final String cardNumber;
  private final String name;
  private final String date;

  public OrderInfo(String id, String amount, String cardNumber, String name, String date) {

    this.id = id;
    this.amount = amount;
    this.cardNumber = cardNumber;
    this.name = name;
    this.date = date;
  }

  public String getId() {

    return id;
  }

  public String getAmount() {

    return amount;
  }

  public String getCardNumber() {

    return cardNumber;
  }

  public String getName() {

    return name;
  }

  public String getDate() {

    return date;
  }
}

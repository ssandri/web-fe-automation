Feature: Navigate through the e-commerce

  Scenario: Navigate through the phone categories
    Given the costumer has opened the Demo online ship homepage
    When they select the "phones" category
    Then the following products should be listed in the page
      | Samsung galaxy s6 |
      | Nokia lumia 1520  |
      | Nexus 6           |
      | Samsung galaxy s7 |
      | Iphone 6 32gb     |
      | Sony xperia z5    |
      | HTC One M9        |

  Scenario: Navigate through the laptops categories
    Given the costumer has opened the Demo online ship homepage
    When they select the "laptops" category
    Then the following products should be listed in the page
      | Sony vaio i5        |
      | Sony vaio i7        |
      | MacBook air         |
      | Dell i7 8gb         |
      | 2017 Dell 15.6 Inch |
      | MacBook Pro         |

  Scenario: Navigate through the monitors categories
    Given the costumer has opened the Demo online ship homepage
    When they select the "monitors" category
    Then the following products should be listed in the page
      | Apple monitor 24 |
      | ASUS Full HD     |

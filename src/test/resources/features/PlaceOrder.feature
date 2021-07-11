Feature: Create an order in the ecommerce store

  Scenario: Place an order
    Given the costumer has opened the Demo online ship homepage
    And they added the following products to the cart
      | Category | Product      |
      | Laptops  | Sony vaio i5 |
      | Laptops  | Dell i7 8gb  |
    But they decided to remove the product "Dell i7 8gb" from the cart
    When they complete the checkout process
    Then the order should be created with the same cost that was displayed in the cart
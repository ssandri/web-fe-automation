package com.ssandri.stepdefinitions;

import com.ssandri.pages.CartPage;
import com.ssandri.pages.CheckoutPage;
import com.ssandri.pages.HomePage;
import com.ssandri.pages.ProductDetailsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class StoreSteps {

  private ChromeDriver driver;
  private String cartTotalCost;
  private HomePage homePage;
  private ProductDetailsPage productDetailsPage;
  private CartPage cartPage;
  private CheckoutPage checkoutPage;

  @Before
  public void setupScenarios() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  // GIVEN STEPS

  @Given("the costumer has opened the Demo online ship homepage")
  public void theCostumerHasOpenedTheDemoOnlineShipHomepage() {

    driver.get("https://www.demoblaze.com/index.html");
  }

  @And("they added the following products to the cart")
  public void theyAddedTheFollowingProductsToTheCart(List<Map<String, String>> productMapList) {

    homePage = new HomePage(driver);
    productDetailsPage = new ProductDetailsPage(driver);
    productMapList.forEach(productMap -> {
      homePage.open();
      homePage.openProductCategory(productMap.get("Category"));
      homePage.openProductDetailsPage(productMap.get("Product"));
      productDetailsPage.addProductToCart();
    });

  }

  @But("they decided to remove the product {string} from the cart")
  public void theyDecidedToRemoveTheProductFromTheCart(String productName) {
    
    cartPage = new CartPage(driver);
    cartPage.open();
    cartPage.removeProduct(productName);
    cartTotalCost = cartPage.getTotalCost();
  }

  // WHEN STEPS

  @When("they select the {string} category")
  public void theySelectTheCategory(String categoryName) {

    homePage = new HomePage(driver);
    homePage.openProductCategory(categoryName);
  }

  @When("they complete the checkout process")
  public void theyCompleteTheCheckoutProcess() {

    cartPage.proceedToCheckout();
    checkoutPage = new CheckoutPage(driver);
    checkoutPage.setCostumerName("asd");
    checkoutPage.setCostumerCountry("asd");
    checkoutPage.setCostumerCity("asd");
    checkoutPage.setCostumerCardNumber("asd");
    checkoutPage.setCostumerCardExpirationMonth("asd");
    checkoutPage.setCostumerCardExpirationYear("asd");
    checkoutPage.placeOrder();
  }

  // THEN STEPS

  @Then("the following products should be listed in the page")
  public void theFollowingProductsShouldBeListedInThePage(List<String> productList) {

    SoftAssert softAssert = new SoftAssert();
    List<String> actualProductList = homePage.getProductList();

    productList.forEach(product -> softAssert
        .assertTrue(actualProductList.contains(product), "Product validation failed. Expected product ["
            + product + "] to be present in product list."));
    softAssert.assertAll();
  }

  @Then("the order should be created with the same cost that was displayed in the cart")
  public void theOrderShouldBeCreatedWithTheSameCostThatWasDisplayedInTheCart() {

    SoftAssert softAssert = new SoftAssert();
    ;
    softAssert.assertTrue(checkoutPage.isOrderConfirmationMsgDisplayed());

    Map<String, String> orderConfirmationMap = checkoutPage.getConfirmationMessage();

    softAssert.assertTrue(orderConfirmationMap.get("Amount").contains(cartTotalCost));
    softAssert.assertAll();

    checkoutPage.closeConfirmationMessage();

    System.out.println("Purchase id: " + orderConfirmationMap.get("Id"));
    System.out.println("Order total cost: " + orderConfirmationMap.get("Amount"));
  }

  @After
  public void tearDownScenarios() {

    driver.close();
  }
}

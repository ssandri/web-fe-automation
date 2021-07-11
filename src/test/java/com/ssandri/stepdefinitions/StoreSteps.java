package com.ssandri.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class StoreSteps {

  private ChromeDriver driver;
  private String cartTotalCost;

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

    productMapList.forEach(productMap -> {
      driver.findElement(By.cssSelector("a[href='index.html']")).click();
      driver.findElements(By.id("itemc")).stream()
          .filter(e -> e.getText().equalsIgnoreCase(productMap.get("Category"))).findFirst().orElseThrow(
          () -> new NoSuchElementException(
              String.format("Cannot locate a category named '%s'", productMap.get("Category"))))
          .click();

      //ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
      ExpectedCondition<Boolean> jQueryLoad = driver -> (
          (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);

      //new WebDriverWait(driver, 5).until(jsLoad);
      new WebDriverWait(driver, 5).until(jQueryLoad);

      driver.findElements(By.cssSelector("h4.card-title")).stream()
          .filter(webElement -> webElement.getText().equalsIgnoreCase(productMap.get("Product"))).findFirst()
          .orElseThrow(() -> new NoSuchElementException(
              String.format("Cannot locate a product named '%s' in product list.", productMap.get("Product")))).click();

      new WebDriverWait(driver, 5).until(jQueryLoad);
      driver.findElement(By.cssSelector("a.btn")).click();
      new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
      driver.switchTo().alert().accept();
      driver.switchTo().defaultContent();
    });

  }

  @But("they decided to remove the product {string} from the cart")
  public void theyDecidedToRemoveTheProductFromTheCart(String productName) {

    ExpectedCondition<Boolean> jQueryLoad = driver -> (
        (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
    driver.findElement(By.linkText("Cart")).click();
    new WebDriverWait(driver, 5).until(jQueryLoad);
    WebElement productInCart = driver.findElements(By.className("success")).stream()
        .filter(webElement -> webElement.getText().contains(productName)).findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            String.format("Cannot locate a product named '%s' in cart.", productName)));
    productInCart.findElement(By.linkText("Delete")).click();
    new WebDriverWait(driver, 5).until(jQueryLoad);
    cartTotalCost = driver.findElement(By.id("totalp")).getText();
  }

  // WHEN STEPS

  @When("they select the {string} category")
  public void theySelectTheCategory(String categoryName) {

    driver.findElements(By.id("itemc")).stream()
        .filter(e -> e.getText().equalsIgnoreCase(categoryName)).findFirst().orElseThrow(
        () -> new NoSuchElementException(String.format("Cannot locate a category named '%s'", categoryName)))
        .click();

    //ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
    ExpectedCondition<Boolean> jQueryLoad = driver -> (
        (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);

    //new WebDriverWait(driver, 5).until(jsLoad);
    new WebDriverWait(driver, 5).until(jQueryLoad);
  }

  @When("they complete the checkout process")
  public void theyCompleteTheCheckoutProcess() {

    driver.findElement(By.cssSelector("button[data-target='#orderModal']")).click();
    new WebDriverWait(driver, 5)
        .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#orderModal"))));
    driver.findElement(By.id("name")).sendKeys("asd");
    driver.findElement(By.id("country")).sendKeys("asd");
    driver.findElement(By.id("city")).sendKeys("asd");
    driver.findElement(By.id("card")).sendKeys("asd");
    driver.findElement(By.id("month")).sendKeys("asd");
    driver.findElement(By.id("year")).sendKeys("asd");
    driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click();
  }

  // THEN STEPS

  @Then("the following products should be listed in the page")
  public void theFollowingProductsShouldBeListedInThePage(List<String> productList) {

    SoftAssert softAssert = new SoftAssert();
    List<String> actualProductList = driver.findElements(By.cssSelector("h4.card-title")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());

    productList.forEach(product -> softAssert
        .assertTrue(actualProductList.contains(product), "Product validation failed. Expected product ["
            + product + "] to be present in product list."));
    softAssert.assertAll();
  }

  @Then("the order should be created with the same cost that was displayed in the cart")
  public void theOrderShouldBeCreatedWithTheSameCostThatWasDisplayedInTheCart() {

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(driver.findElement(By.cssSelector("div.sweet-alert")).isDisplayed());

    String orderConfirmationText = driver.findElement(By.cssSelector("p.lead")).getText();
    Map<String, String> orderConfirmationMap = Arrays.stream(orderConfirmationText.split(System.lineSeparator()))
        .map(s -> s.split(": ")).collect(Collectors.toMap(e -> e[0], e -> e[1]));

    softAssert.assertTrue(orderConfirmationMap.get("Amount").contains(cartTotalCost));
    softAssert.assertAll();

    driver.findElement(By.cssSelector("button.confirm")).click();

    System.out.println("Purchase id: " + orderConfirmationMap.get("Id"));
    System.out.println("Order total cost: " + orderConfirmationMap.get("Amount"));
  }

  @After
  public void tearDownScenarios() {

    driver.close();
  }
}

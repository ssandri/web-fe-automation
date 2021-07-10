package com.ssandri.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class HomeSteps {

  private ChromeDriver driver;

  @Before
  public void setupScenarios() {

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  @Given("the costumer has opened the Demo online ship homepage")
  public void theCostumerHasOpenedTheDemoOnlineShipHomepage() {

    driver.get("https://www.demoblaze.com/index.html");
  }

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

  @Then("the following products should be listed in the page")
  public void theFollowingProductsShouldBeListedInThePage(List<String> productList) {

    SoftAssert softAssert = new SoftAssert();
    List<String> actualProductList = driver.findElements(By.cssSelector("h4.card-title")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());

    for (String product : productList) {
      softAssert
          .assertTrue(actualProductList.contains(product), "Product validation failed. Expected product ["
              + product + "] to be present in product list.");
    }
    softAssert.assertAll();
  }

  @After
  public void tearDownScenarios() {

    driver.close();
  }
}

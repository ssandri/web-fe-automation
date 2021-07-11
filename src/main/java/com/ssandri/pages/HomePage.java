package com.ssandri.pages;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

  @FindBy(css = "a[href='index.html']")
  private WebElement homeMenu;

  @FindBy(id = "itemc")
  private List<WebElement> productCategoryList;

  @FindBy(css = "h4.card-title")
  private List<WebElement> productTitleList;

  public HomePage(WebDriver driver) {

    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {

    homeMenu.click();
  }

  public void openProductCategory(String categoryName) {

    productCategoryList.stream()
        .filter(e -> e.getText().equalsIgnoreCase(categoryName)).findFirst().orElseThrow(
        () -> new NoSuchElementException(format("Cannot locate a category named '%s'", categoryName)))
        .click();
    super.waitForPageToLoad();
  }

  public void openProductDetailsPage(String productName) {

    productTitleList.stream()
        .filter(webElement -> webElement.getText().equalsIgnoreCase(productName)).findFirst()
        .orElseThrow(() -> new NoSuchElementException(
            format("Cannot locate a product named '%s' in product list.", productName))).click();

    super.waitForPageToLoad();
  }

  public List<String> getProductList() {

    return productTitleList
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}

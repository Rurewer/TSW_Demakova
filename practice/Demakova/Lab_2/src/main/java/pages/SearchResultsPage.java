package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    private static final By FIRST_PRODUCT = By.cssSelector("div.product-card:first-child a");
    @FindBy(css = "div.product-card")
    private List<WebElement> productCards;

    @FindBy(css = "h1.searching-results__title")
    private WebElement resultsTitle;

    @FindBy(css = "div.filter__item")
    private List<WebElement> filters;

    @FindBy(css = "div.sorting__item")
    private List<WebElement> sortingOptions;

    @FindBy(css = "div.pagination")
    private WebElement pagination;

    @FindBy(css = "h1.catalog-title")
    private WebElement categoryTitle;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCardsCount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(productCards));
        return productCards.size();
    }

    public String getResultsTitleText() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(resultsTitle))
                .getText();
    }

    public int getFiltersCount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(filters));
        return filters.size();
    }

    public int getSortingOptionsCount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(sortingOptions));
        return sortingOptions.size();
    }

    public boolean isPaginationDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(pagination))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSearchResultsTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1.search-title"))).getText();
    }



    public void clickFirstProduct() {
        click(FIRST_PRODUCT);
    }

    public void selectSortOption(String optionValue) {
        Select sortSelect = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("select.sort-select"))));
        sortSelect.selectByValue(optionValue);
        waitForPageLoad(driver);
    }

    public List<Integer> getAllPrices() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("span.price"))).stream()
                .map(el -> Integer.parseInt(el.getText().replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());
    }

    public WebElement getFirstProductCard() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.product-card:first-child")));
    }

    public boolean isQuickViewButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.product-card:hover button.quick-view"))).isDisplayed();
    }

    public boolean isWishlistButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.product-card:hover button.wishlist"))).isDisplayed();
    }
}
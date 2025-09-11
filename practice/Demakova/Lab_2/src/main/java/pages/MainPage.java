package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {

    @FindBy(css = "div.slider-main, div.banner-slider") // несколько вариантов
    private WebElement mainSlider;
    @FindBy(css = "input.search-catalog__input")
    private WebElement searchInput;

    @FindBy(css = "button.search-catalog__btn")
    private WebElement searchButton;


    @FindBy(css = "a.j-wba-header-item[href*='basket']")
    private WebElement basketButton;

    @FindBy(css = "button.catalog-button")
    private WebElement catalogButton;

    @FindBy(css = "footer.footer")
    private WebElement footer;

    @FindBy(css = "a.j-wba-header-item[href*='services']")
    private WebElement servicesLink;

    @FindBy(css = "a.j-wba-header-item[href*='brands']")
    private WebElement brandsLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchInput))
                .sendKeys(query);
        searchButton.click();
    }

    public boolean isMainSliderDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(mainSlider))
                .isDisplayed();
    }

    public boolean isFooterDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(footer))
                .isDisplayed();
    }

    public void clickServicesLink() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(servicesLink))
                .click();
    }

    public boolean isBasketButtonDisplayed() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(basketButton))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getSearchInputPlaceholder() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(searchInput))
                .getAttribute("placeholder");
    }


    public boolean areImagesHaveAltText() {
        List<WebElement> images = driver.findElements(By.cssSelector("img"));
        return images.stream().allMatch(img -> img.getAttribute("alt") != null && !img.getAttribute("alt").isEmpty());
    }

    public boolean doInteractiveElementsHaveFocusState() {
        // Проверка через CSS-атрибуты (пример)
        List<WebElement> buttons = driver.findElements(By.cssSelector("button, a, input"));
        return buttons.stream().allMatch(btn -> {
            String focusStyle = btn.getCssValue("outline");
            return focusStyle != null && !focusStyle.equals("none");
        });
    }
}
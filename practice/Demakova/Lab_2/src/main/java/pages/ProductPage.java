package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button.add-to-cart");
    private static final By CART_NOTIFICATION = By.cssSelector("div.cart-notification");
    private static final By BREADCRUMBS_FIRST = By.cssSelector("nav.breadcrumbs a:first-child");
    private static final By PRODUCT_TITLE = By.cssSelector("h1.product-page__title");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(d ->
                d.findElement(PRODUCT_TITLE).isDisplayed() ||
                        d.findElement(ADD_TO_CART_BUTTON).isDisplayed());
    }

    public void addToCart() {
        click(ADD_TO_CART_BUTTON);
    }

    public boolean isCartNotificationDisplayed() {
        return isDisplayed(CART_NOTIFICATION);
    }

    public String getFirstBreadcrumbText() {
        return getText(BREADCRUMBS_FIRST);
    }

    public void clickFirstBreadcrumb() {
        click(BREADCRUMBS_FIRST);
        waitForPageLoad();
    }

    public String getProductTitle() {
        return getText(PRODUCT_TITLE);
    }
}
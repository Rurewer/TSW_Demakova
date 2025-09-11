package tests;

import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.MainPage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.TestUtils;
import utils.WebDriverFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.isSortedAscending;

public class WildberriesTests {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.get(TestConfig.BASE_URL);
        mainPage = new MainPage(driver);
        TestUtils.waitForPageLoad(driver);
    }

    @Test
    public void testPageTitle() {
        String expectedTitle = "Wildberries — модный интернет-магазин одежды, обуви и аксессуаров";
        assertTrue(driver.getTitle().contains("Wildberries"),
                "Заголовок страницы не соответствует ожидаемому");
    }

    @Test
    public void testSearchInputVisibility() {
        assertTrue(mainPage.getSearchInputPlaceholder().contains("Найти"),
                "Плейсхолдер поисковой строки не содержит текст 'Найти'");
    }


    @Test
    public void testBasketButtonVisibility() {
        assertTrue(mainPage.isBasketButtonDisplayed(),
                "Кнопка корзины не видна или недоступна");
    }

    @Test
    public void testMainSliderVisibility() {
        assertTrue(mainPage.isMainSliderDisplayed(),
                "Главный слайдер не отображается");
    }


    @Test
    public void testFooterVisibility() {
        assertTrue(mainPage.isFooterDisplayed(),
                "Футер не отображается");
    }


    @Test
    public void testFooterLinksAreValid() {
        List<WebElement> footerLinks = driver.findElements(
                By.cssSelector("footer a[href]"));

        assertFalse(footerLinks.isEmpty(), "В футере нет ссылок");

        for (WebElement link : footerLinks) {
            String url = link.getAttribute("href");
            assertTrue(url != null && !url.isEmpty(),
                    "Найдена ссылка без атрибута href");
            assertFalse(url.equals("#"),
                    "Найдена нерабочая ссылка с '#'");
        }
    }

    @Test
    public void testFaviconExists() {
        WebElement favicon = driver.findElement(
                By.cssSelector("link[rel*='icon']"));
        String faviconUrl = favicon.getAttribute("href");

        assertNotNull(faviconUrl, "Favicon не найден");
        assertTrue(faviconUrl.startsWith("http"),
                "Некорректный URL favicon");
    }


    @Test
    public void testServicesLinkNavigation() {
        mainPage.clickServicesLink();
        assertTrue(driver.getCurrentUrl().contains("/services"),
                "Не удалось перейти на страницу услуг");
    }


    @Test
    public void testPaginationPresence() {
        mainPage.searchFor("книги");
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        TestUtils.waitForPageLoad(driver);
        assertTrue(resultsPage.isPaginationDisplayed(),
                "Пагинация не отображается");
    }

    @Test
    public void testPageHttpStatus() throws IOException {

        String currentUrl = driver.getCurrentUrl();

        HttpURLConnection connection = (HttpURLConnection) new URL(currentUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        assertTrue(responseCode == HttpURLConnection.HTTP_OK,
                "Страница вернула статус " + responseCode + " вместо 200");
    }


    @Test
    public void testBasicAccessibility() {
        assertAll(
                () -> assertNotNull(driver.findElement(By.cssSelector("html")).getAttribute("lang"),
                        "Язык страницы не указан"),
                () -> assertTrue(mainPage.areImagesHaveAltText(),
                        "Найдены изображения без alt текста"),
                () -> assertTrue(mainPage.doInteractiveElementsHaveFocusState(),
                        "Интерактивные элементы не имеют состояния фокуса")
        );
    }

    @Test
    public void testBreadcrumbsNavigation() {

        mainPage.searchFor("ноутбук");

        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        resultsPage.clickFirstProduct();
        ProductPage productPage = new ProductPage(driver);
        productPage.waitForPageLoad();

        String firstCrumb = productPage.getFirstBreadcrumbText();
        assertNotNull(firstCrumb, "Текст хлебной крошки не найден");

        productPage.clickFirstBreadcrumb();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.not(ExpectedConditions.titleIs("")));
        assertTrue(driver.getTitle().toLowerCase().contains(firstCrumb.toLowerCase()),
                "Заголовок страницы не соответствует хлебной крошке");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

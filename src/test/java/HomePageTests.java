import PageObject.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {

    private WebDriver driver;

    static Stream<Arguments> providedDataForImportantMenu() {
        return Stream.of(
                Arguments.of("accordion__heading-0", "//*[@id='accordion__panel-0']/p", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of("accordion__heading-1", "//*[@id='accordion__panel-1']/p", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.")
        );
    }

    @BeforeEach
    public void setUp() {
        // создали драйвер для браузера Chrome
        driver = new ChromeDriver();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru");
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @MethodSource("providedDataForImportantMenu")
    void checkOpeningImportantMenu(String importantQuestionMenu, String importantMenuText, String expectedResult) {
        String actualResult;
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadPage();
        actualResult = homePage.openImportantQuestionsMenuAndGetText(By.id(importantQuestionMenu), By.xpath(importantMenuText));
        assertEquals(expectedResult, actualResult);
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}

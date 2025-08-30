import PageObject.HomePage;
import PageObject.OrderPage;
import PageObject.SecondOrderPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderPageTests {
    private WebDriver driver;

    static Stream<Arguments> providedDataForOrder() {
        return Stream.of(
                Arguments.of("Header", "Петр", "Петров", "Москва, ул. Пушкина, д.1", "Пушкинская", "+71111111111", "22.09.2025", "двое суток", "чёрный жемчуг", "Оставить у двери", true),
                Arguments.of("Middle", "Сергеев", "Сергей", "Москва, ул. Кировоградская, д.3, кв 3", "Пражская", "+71111111111", "20.09.2025", "трое суток", "серая безысходность", "Оставить у двери", true)
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
    @MethodSource("providedDataForOrder")
    public void checkOrderFromHeaderButton(String button, String name, String surName, String address, String metroStation, String phone, String deliveryDate, String orderTime, String samokatColor, String commentText, boolean result) {
        boolean actualResult;
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        SecondOrderPage secondOrderPage = new SecondOrderPage(driver);
        homePage.waitForLoadPage();
        homePage.orderButtonClick(button);
        orderPage.waitForLoadPage();
        orderPage.fillInFields(name, surName, address, metroStation, phone);
        orderPage.clickNextButton();
        secondOrderPage.waitForLoadPage();
        secondOrderPage.fillInFields(deliveryDate, orderTime, samokatColor, commentText);
        secondOrderPage.orderButtonClick();
        secondOrderPage.orderConfirmationButtonClick();
        actualResult = secondOrderPage.checkSuccsessOrder().contains("Заказ оформлен");
        assertEquals(result, actualResult, "Должна открыться страница успешного заказа");
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}

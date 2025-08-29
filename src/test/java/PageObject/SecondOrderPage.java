package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class SecondOrderPage {

    private WebDriver driver;

    public SecondOrderPage(WebDriver driver){
        this.driver = driver;
    }

    // Метод ожидания загрузки второй страницы заказа
    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Header__BZXOb")));
    }

    // Поле выбора "Когда привезти заказ"
    private By deliveryDateInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input");
    // Стрелочка открытия панели с выбором времени аренды
    private By orderTimeOpen = By.className("Dropdown-arrow");
    // Выпадающий список выбора времени аренды
    private By dropDownOrderTimeSelect;
    // Выбор цвета самоката
    private By samokatColorSelect;
    // Поле "Комментарий для курьера"
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    // Кнопка подтверждения заказа
    private By confirmButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    // Всплывающее окно с текстом "Заказ оформлен"
    private By successOrderPanel = By.className("Order_ModalHeader__3FDaJ");

    // Метод составления xpath для срока аренды (для использования разных тестовых данных)
    public void setOrderTime(String orderTime) {
        // Строка для динамического составления xpath для поля "Срок аренды"
        String orderTimeXpath = String.format("//div[text()='%s']", orderTime);
        dropDownOrderTimeSelect = By.xpath(orderTimeXpath);
    }

    // Метод составления xpath для цвета самоката (для использования разных тестовых данных)
    public void setSamokatColor(String samokatColor) {
        // Строка для динамического составления xpath выбора "Цвет самоката"
        String samokatColorXpath = String.format("//label[text()='%s']", samokatColor);
        samokatColorSelect = By.xpath(samokatColorXpath);
    }

    // Метод заполнения данных на второй странице заказа
    public void fillInFields(String deliveryDate, String orderTime, String samokatColor, String commentText){
        setOrderTime(orderTime);
        setSamokatColor(samokatColor);
        driver.findElement(deliveryDateInput).sendKeys(deliveryDate);
        driver.findElement(orderTimeOpen).click();
        driver.findElement(dropDownOrderTimeSelect).click();
        driver.findElement(samokatColorSelect).click();
        driver.findElement(commentInput).sendKeys(commentText);
    }

    // Метод нажатия кнопки "Заказать" и подтверждения заказа
    public void orderButtonClick(){
        driver.findElement(orderButton).click();
    }

    public void orderConfirmationButtonClick(){
        driver.findElement(confirmButton).click();
    }

    // Метод проверки успешного оформления заказа
    public String checkSuccsessOrder(){
       return driver.findElement(successOrderPanel).getText();
    }

}

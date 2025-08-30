package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class OrderPage {

    private WebDriver driver;

    // Поле ввода имени
    private By nameInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    // Поле ввода фамилии
    private By surnameInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    // Поле ввода адреса
    private By adressInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    // Поле ввода станции метро
    private By metroStationInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    // Поле ввода телефона
    private By phoneInput = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");
    // Кнопка далее
    private By nextButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");
    private By metroStationSelect;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div[2]/div[3]/button")));
    }

    public void setMetroStation(String metroStation){
        String metroStationXpath = String.format("//div[text()='%s']", metroStation);
        metroStationSelect = By.xpath(metroStationXpath);
    }

    public void fillInFields(String name, String surname, String address, String metroStation, String phone){
        setMetroStation(metroStation);
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(adressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(metroStationSelect));
        driver.findElement(metroStationSelect).click(); //li[@class='select-search__row']//div[text()='Преображенская площадь']"
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }

}

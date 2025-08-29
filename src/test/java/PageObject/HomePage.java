package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class HomePage {

    private WebDriver driver;

    private By orderButtonInHeader = By.className("Button_Button__ra12g");
    private By orderButtonMiddle = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForLoadPage(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Home_SubHeader__zwi_E")));
    }

    public String openImportantQuestionsMenuAndGetText(By importantQuestionsMenu, By importantMenuText){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(importantQuestionsMenu));
        driver.findElement(importantQuestionsMenu).click();
        return driver.findElement(importantMenuText).getText();
    }

    public void orderButtonClick(String button){
        if(button.equals("Header")) {
            driver.findElement(orderButtonInHeader).click();
        } else if (button.equals("Middle")) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(orderButtonMiddle));
            driver.findElement(orderButtonMiddle).click();
        }
    }
}

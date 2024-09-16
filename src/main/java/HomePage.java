import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //локатор главной страницы
    private final By mainHeader = By.className("Header_Header__214zg");
    //локатор верхней кнопки Заказать
    private final By upOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //локатор нижней кнопки Заказать
    private final By downOrderButton = By.xpath(".//button[text()='Заказать']");
    //локатор вопроса
    private final String question = ".//div[@id='accordion__heading-%d']";
    //локатор ответа
    private final String answer = ".//div[contains(@id, 'accordion__panel')]/p[text()='%s']";
    //метод ожидания загрузки главной страницы
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(mainHeader).getText() != null
                && !driver.findElement(mainHeader).getText().isEmpty()
        ));
        return this;
    }
    //метод скрола до вопроса, ожидания клика на вопрос
    public void openQuestion(int index) {
        WebElement element = driver.findElement(By.xpath(String.format(question, index)));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //метод проверки отображения ответов
    public boolean answerIsDisplayed(String answerText) {
        WebElement element = driver.findElement(By.xpath(String.format(answer, answerText)));
        return element.isDisplayed();
    }
    //метод клика на верхнюю кнопку "Заказать"
    public void clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
    }
    //метод прокрутки к кнопке "Заказать"
    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }
    //метод клика на нижнюю кнопку "Заказать"
    public void clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
    }
}

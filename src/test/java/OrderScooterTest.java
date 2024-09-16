import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    private WebDriver driver;

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String data;
    private final String periodRent;
    private final String colour;
    private final String comment;

    public OrderScooterTest(String name, String surname, String address, String metroStation, String phone, String data, String periodRent, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.data = data;
        this.phone = phone;
        this.periodRent = periodRent;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {"Мария", "Иванова", "Москва, Усачёва,3-45", "Комсомольская", "+79456543212", "12.06.2024", "сутки", "серая безысходность", "Позвоните заранее"},
                {"Константин", "Белов", "Москва, Тверская, 5-78", "Бульвар Рокоссовского", "+79263452367", "15.06.2024", "трое суток", "чёрный жемчуг", "Без комментариев"},
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void newOrderPass() {
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.clickUpOrderButton();

        OrderScooterPage orderScooterPage = new OrderScooterPage(driver);
        orderScooterPage.setUserScooterDate(name, surname, address, metroStation, phone);
        orderScooterPage.setScooterRentDate(data, periodRent, colour, comment);

        assertTrue(orderScooterPage.findElementOrderPass());
    }

    @Test
    public void checkOrderUpButton() {
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.clickUpOrderButton();

        OrderScooterPage orderScooterPage = new OrderScooterPage(driver);
        assertTrue(orderScooterPage.checkOpenOrderPage());
    }

    @Test
    public void checkOrderDownButton() {
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.scrollToDownOrderButton()
                .clickDownOrderButton();

        OrderScooterPage orderScooterPage = new OrderScooterPage(driver);
        assertTrue(orderScooterPage.checkOpenOrderPage());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}

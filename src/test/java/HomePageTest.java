import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static constants.HomePageConstants.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HomePageTest {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final int index;
    private final String answerText;

    public HomePageTest (int index, String answerText) {
        this.index = index;
        this.answerText = answerText;
    }
    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0, text_0},
                {1, text_1},
                {2, text_2},
                {3, text_3},
                {4, text_4},
                {5, text_5},
                {6, text_6},
                {7, text_7},
        };
    }
    @After
    public void cleanUp() {
        driver.quit();
    }
    @Test
    public void checkTextQuestions (){
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadHomePage()
                .openQuestion(index);
        assertTrue(homePage.answerIsDisplayed(answerText));
    }
}

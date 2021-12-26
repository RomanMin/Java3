package HomeWork6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class AfishaTest {
    WebDriver driver;
    MainAfishaPage mainAfishaPage;
    LoginBlock loginBlock;
    private final static String AFISHA_BASE_URI = "https://www.afisha.ru/";

    @BeforeAll
    static  void registerDriver() {

        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setupDriver()  throws Exception  {
        driver = new ChromeDriver();
        mainAfishaPage = new MainAfishaPage(driver);
        loginBlock = new LoginBlock(driver);
        driver.get(AFISHA_BASE_URI);
        new MainAfishaPage(driver).clickCookieButton();

        new MainAfishaPage(driver)
                .clickLoginButton();

        new LoginBlock(driver)
                .switchToLoginFrame()
                .insertLoginInput("test@m-rv.ru")
                .inserPassInput("JavaTest01")
                .clickEnterButton();
        Thread.sleep(20000);

    }
    @Test //переходим в "избранное" и проверяем что в избранном ничего нет
    public void emptyFavoritesTest() {
        new PersonalPageBlock(driver)
                .enterPersonalPage()
                .switchToFavorites()
                .checkFavorites();

    }
    @Test //нажимаем в выпадающем меню кнопку "Выход" и проверяем что появилась кнопка "Вход"
    public void logOutTest()  throws Exception {
        new PersonalPageBlock(driver).hoverPersonalPageBtn();
        Thread.sleep(2000);
        new PersonalPageBlock(driver)
                .exitButtonClick();
        Thread.sleep(2000);
        assertThat(driver.findElement(By.xpath("//button[.='Войти']")),
                isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

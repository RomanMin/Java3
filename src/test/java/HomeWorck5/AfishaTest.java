package HomeWorck5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class AfishaTest {

    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String AFISHA_BASE_URI = "https://www.afisha.ru/";

    @BeforeAll
    static  void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
     void setupDriver()  throws Exception  {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(AFISHA_BASE_URI);
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[.='ОК']")));
        driver.findElement(By.xpath("//button[.='ОК']")).click();
        logIn();
    }
    @Test //переходим в "избранное" и проверяем что в избранном ничего нет
    public void emptyFavoritesTest()  throws Exception{
        driver.findElement(By.xpath("//div/a[contains(@href, 'personalpage')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/a[contains(@href, 'favorites')]")).click();
        Thread.sleep(1000);
        assertThat(driver.findElement(By.xpath("//div[text()=\"Пока у Вас нет избранного\"]")),
             isDisplayed());
    }

    @Test //нажимаем в выпадающем меню кнопку "Выход" и проверяем что появилась кнопка "Вход"
    public void logOutTest()  throws Exception {
        actions.moveToElement(driver.findElement(By.xpath("//div/a[contains(@href, 'personalpage')]")))
            .build()
            .perform();
        driver.findElement(By.xpath("//div[text()=\"Выход\"]")).click();
        Thread.sleep(1000);
        assertThat(driver.findElement(By.xpath("//button[.='Войти']")),
             isDisplayed());
    }

    @AfterEach
    void tearDown() {
    driver.quit();
    }

    void logIn() throws Exception {
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'rambler.ru/login')]")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.xpath("//input[@id=\"login\"]")).sendKeys("test@m-rv.ru");
        driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("JavaTest01");
        driver.findElement(By.xpath("//span[.='Войти']")).click();
        Thread.sleep(30000);// Вводим капчу
    }
}

package HomeWorck5;

import HomeWork7.CustomLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
   @Story("Афиша")
public class AfishaTest {

    WebDriver driver;
    EventFiringWebDriver eventFiringWebDriver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String AFISHA_BASE_URI = "https://www.afisha.ru/";

    @BeforeAll
    static  void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
     void setupDriver()  throws Exception  {
        //driver = new ChromeDriver();
        eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
        eventFiringWebDriver.register(new CustomLogger());
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(eventFiringWebDriver);
        eventFiringWebDriver.get(AFISHA_BASE_URI);
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[.='ОК']")));
        eventFiringWebDriver.findElement(By.xpath("//button[.='ОК']")).click();
        logIn();
    }
    @Test
    @Description("переходим в избранное и проверяем что в избранном ничего нет")
    @TmsLink("001")
    @Feature("Избранное")
    public void emptyFavoritesTest()  throws Exception{
        eventFiringWebDriver.findElement(By.xpath("//div/a[contains(@href, 'personalpage')]")).click();
        Thread.sleep(1000);
        eventFiringWebDriver.findElement(By.xpath("//div/a[contains(@href, 'favorites')]")).click();
        Thread.sleep(1000);
        assertThat(eventFiringWebDriver.findElement(By.xpath("//div[text()=\"Пока у Вас нет избранного\"]")),
             isDisplayed());
    }

    @Test
    @Description ("нажимаем в выпадающем меню кнопку Выход и проверяем что появилась кнопка Вход")
    @TmsLink("002")
    @Feature("Выход")
    public void logOutTest()  throws Exception {
        actions.moveToElement(eventFiringWebDriver.findElement(By.xpath("//div/a[contains(@href, 'personalpage/5074063')]")))
            .build()
            .perform();
        Thread.sleep(1000);
        eventFiringWebDriver.findElement(By.xpath("//div[text()=\"Выход\"]")).click();
        Thread.sleep(1000);
        assertThat(eventFiringWebDriver.findElement(By.xpath("//button[.='Войти']")),
             isDisplayed());
    }

    @AfterEach
    void tearDown() {
    eventFiringWebDriver.quit();
    }

    void logIn() throws Exception {
        eventFiringWebDriver.findElement(By.xpath("//button[.='Войти']")).click();
        eventFiringWebDriver.switchTo().frame(eventFiringWebDriver.findElement(By.xpath("//iframe[contains(@src, 'rambler.ru/login')]")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        eventFiringWebDriver.findElement(By.xpath("//input[@id=\"login\"]")).sendKeys("test@m-rv.ru");
        eventFiringWebDriver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("JavaTest01");
        Thread.sleep(1000);
        eventFiringWebDriver.findElement(By.xpath("//span[.='Войти']")).click();
        Thread.sleep(10000);// Вводим капчу
    }
}

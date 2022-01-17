package HomeWorck5;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RzdSiteTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private final static String RZD_BASE_URI = "https://www.rzd.ru/";

    @BeforeAll
    static  void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(RZD_BASE_URI);
        driver.findElement(By.xpath("//div/button[.='Принять']")).click();
    }
    @Test //Проверяем что после внесения изменений появилось сообщение об успешном обновлении данных
    public void sslSiteTest() {
        checkChangesAccepted();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void checkChangesAccepted()  {

        driver.findElement(By.xpath("//div/nav/ul/li/a/span[.='Вход']")).click();
        driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("java_test01");
        driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("Java0123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div/a[@class=\"j-profile-username\"]")));
        driver.findElement(By.xpath("//div/a[@class=\"j-profile-username\"]")).click();
        driver.findElement(By.name("lastName")).sendKeys("Test");
        driver.findElement(By.name("firstName")).sendKeys("Java");
        driver.findElement(By.xpath("(//form/div/button[@class=\"btn-primary\"])[1]")).click();
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[.=\"Готово\"]")));
        assertThat(driver.findElement(By.xpath("//div[.=\"Данные успешно обновлены\"]")),
                isDisplayed());
            }
}

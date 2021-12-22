package HomeWorck5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class SslCheckerTest {

    WebDriver driver;
    WebDriverWait webDriverWait;
       private final static String SSL_BASE_URI = "https://www.sslshopper.com/";

    @BeforeAll
    static  void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(SSL_BASE_URI);
    }
    @Test
    public void sslSiteTest() {
        checkSite();

    }

    @AfterEach
    void tearDown() {
      driver.quit();
    }

    void checkSite()  {

        WebElement buttonSslTools = driver.findElement(By.xpath("//h4/a[.='SSL Tools']"));
        buttonSslTools.click();
        WebElement buttonSslChecker = driver.findElement(By.linkText("SSL Checker"));
        buttonSslChecker.click();
        driver.findElement(By.id("hostname")).sendKeys("gb.ru");
        driver.findElement(By.id("checkButton")).click();
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("checkData")));
        assertThat(driver.findElement(By.xpath("//td/b[.=\"Issuer:\"]")),
                isDisplayed());

    }
}

package HomeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SslCheckerTestApp {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();
        WebDriver fireDriver = new FirefoxDriver();
        fireDriver.get("https://www.sslshopper.com");
        WebElement buttonSslTools = fireDriver.findElement(By.xpath("//h4/a[.='SSL Tools']"));
        buttonSslTools.click();
        WebElement buttonSslChecker = fireDriver.findElement(By.linkText("SSL Checker"));
        buttonSslChecker.click();
        fireDriver.findElement(By.id("hostname")).sendKeys("gb.ru");
        fireDriver.findElement(By.id("checkButton")).click();
        Thread.sleep(15000);
        fireDriver.quit();

    }
}

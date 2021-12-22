package HomeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RzdSiteTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("user-agent=Googlebot/2.1 (+http://google.com/bot.html)");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.rzd.ru");


        driver.findElement(By.xpath("//div/button[.='Принять']")).click();
        driver.findElement(By.xpath("//div/nav/ul/li/a/span[.='Вход']")).click();
        driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("java_test01");
        driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys("Java0123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/a[@class=\"j-profile-username\"]")).click();
        driver.findElement(By.name("lastName")).sendKeys("Test");
        driver.findElement(By.name("firstName")).sendKeys("Java");
        driver.findElement(By.xpath("(//form/div/button[@class=\"btn-primary\"])[1]")).click();
        Thread.sleep(10000);

        driver.quit();




    }
}



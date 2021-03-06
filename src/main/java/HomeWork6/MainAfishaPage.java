package HomeWork6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainAfishaPage extends BasePageView {
    private final static String COOKIE_BTN = "//button[.='ОК']";
    public MainAfishaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath =COOKIE_BTN)
    WebElement cookieButton;
    @Step("Нажимаем кнопку ОК на поп-ап сообщении")
    public MainAfishaPage clickCookieButton(){
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(COOKIE_BTN)));
        cookieButton.click();
        return this;
    }

    @FindBy(xpath = "//button[.='Войти']")
    WebElement loginButton;
    @Step("Нажимаем кнопку ВОЙТИ")
    public  MainAfishaPage clickLoginButton(){
        loginButton.click();
        return this;
    }
}

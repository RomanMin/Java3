package HomeWork6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginBlock extends BasePageView {
    private final static String  LOGIN_BTN_PATH = "//iframe[contains(@src, 'rambler.ru/login')]";
    public LoginBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = LOGIN_BTN_PATH )
    WebElement loginFrame;

    @Step("Переключится на фрейм для логина")
    public LoginBlock switchToLoginFrame () {
        webDriverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(LOGIN_BTN_PATH)));
        driver.switchTo().frame(loginFrame);
        return this;
    }
    @FindBy(id = "login")
    WebElement loginInput;

    @Step("Вводим логин")
    public LoginBlock insertLoginInput (String login) {
    loginInput.sendKeys(login);
    return this;
    }

    @FindBy(id = "password")
    WebElement passInput;

    @Step("Вводим пароль")
    public LoginBlock inserPassInput (String pass) {
        passInput.sendKeys(pass);
        return this;
    }
    @FindBy(xpath = "//span[.='Войти']")
    WebElement enterButton;

    @Step("Нажимаем кнопку ВОЙТИ")
    public LoginBlock clickEnterButton() {
        enterButton.click();
        return this;
    }

}

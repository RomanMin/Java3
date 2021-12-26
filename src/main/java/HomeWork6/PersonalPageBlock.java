package HomeWork6;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class PersonalPageBlock extends BasePageView {
    private final static String PERS_PG_BTN = "//div[@data-test=\"IMAGE FALLBACK\"]";
    private final static String FAVORITES_SECTION = "//div/a[contains(@href, 'favorites')]";
    private final static String CHECKED_ELEMENT = "//div[text()=\"Пока у Вас нет избранного\"]";
    private final static String EXIT_BTN = "//div[text()=\"Выход\"";

    public PersonalPageBlock (WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = PERS_PG_BTN)
    WebElement personalPageButton;
    public PersonalPageBlock enterPersonalPage() {
    personalPageButton.click();
        return this;
    }
    public PersonalPageBlock hoverPersonalPageBtn() {
        actions.moveToElement(personalPageButton)
                .build()
                .perform();
        return this;
    }

    @FindBy(xpath = FAVORITES_SECTION)
    WebElement favoritesButton;
    public PersonalPageBlock switchToFavorites() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FAVORITES_SECTION)));
        favoritesButton.click();
        return this;
    }

    @FindBy(xpath = CHECKED_ELEMENT)
    private WebElement checkedWords;
    public PersonalPageBlock checkFavorites() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CHECKED_ELEMENT)));
        assertThat(checkedWords, isDisplayed());
        return this;
    }
    @FindBy(xpath = EXIT_BTN)
    private WebElement exitButton;
    public PersonalPageBlock exitButtonClick() {
        exitButton.click();
        return this;
    }

}


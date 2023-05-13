package mk.finki.ukim.web.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ListBalloons extends AbstractPage{

    private WebElement name;

    public static BalloonsPage listBalloons(WebDriver driver, String name) {
        get(driver, "/balloons");
        ListBalloons listBalloons = PageFactory.initElements(driver, ListBalloons.class);
        listBalloons.name.sendKeys(name);

        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public ListBalloons(WebDriver driver) {
        super(driver);
    }
}

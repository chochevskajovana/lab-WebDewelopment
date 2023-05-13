package mk.finki.ukim.web.lab.selenium;

import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import mk.finki.ukim.web.lab.service.BalloonService;
import mk.finki.ukim.web.lab.service.ManufacturerService;
import mk.finki.ukim.web.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Manufacturer m2;

    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup(){
        this.driver = new HtmlUnitDriver(false);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m1", "m1","m1");//.get();
            m2 = manufacturerService.save("m2", "m2", "m1");//.get();

            String user = "user";
            String admin = "admin";

            regularUser = userService.register(user, user, user, user, user, LocalDate.now(), Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, LocalDate.now(), Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception{
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        balloonsPage.assertElements(0, 0, 0,  1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "name", "description", m1.getName());
        balloonsPage.assertElements(1, 1, 1, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "name1", "description1", m2.getName());
        balloonsPage.assertElements(2, 2, 2,  1);

        //
        String n1 = balloonsPage.getBalloonsAdded().get(0).getText();
        balloonsPage.assertBalloonName(n1);

        String n2 = balloonsPage.getBalloonsAdded().get(1).getText();
        balloonsPage.assertBalloonName(n2);
        //

        balloonsPage.getDeleteButtons().get(1).click();
        balloonsPage.assertElements(1, 1, 1,  1);

        balloonsPage = AddOrEditBalloon.editBalloon(this.driver, balloonsPage.getEditButtons().get(0), "test1", "200",  m2.getName());
        balloonsPage.assertElements(1, 1, 1,  1);

//        balloonsPage.getBalloonRows().get(0).findElement(By.name("name"));
//        balloonsPage.getBalloonRows().get(0).findElement(By.name(name));
//        balloonsPage.getBalloonRows().get(0).findElement(By.name("name1"));

        loginPage = LoginPage.logout(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        balloonsPage.assertElements(1, 0, 0,  0);

    }
}
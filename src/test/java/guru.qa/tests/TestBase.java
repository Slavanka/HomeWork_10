package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.helpers.Attach;
import guru.qa.pages.RegistrationPage;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    RegistrationPage registrationPages = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "91");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        String remoteUrl = System.getProperty("remoteUrl");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        Configuration.remote = "https://" + user + ":" + password + "@" + remoteUrl;
        Configuration.remote = System.getProperty ("remote_driver_url", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

//        String login = System.getProperty("login","user1");
//        String password = System.getProperty("password","1234");
//        String url = System.getProperty("url");
//        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
//        String remoteUrl = System.getProperty("remoteUrl");
//        //Configuration.remote = System.getProperty ("remote_driver_url", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
//        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs(System.getProperty("browser")+" "+System.getProperty("version"));
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}

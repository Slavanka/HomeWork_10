package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.pages.RegistrationPage;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;


public class FirstWithPageTest extends TestBase {

    @Tag("properties")
    @Test
    @DisplayName("Тест заполнения формы регистрации студента")
    @Severity(SeverityLevel.BLOCKER)
        //@Link(value = "Форма регистрации", url = "https://demoqa.com/automation-practice-form")
        //@Link(value = "Форма регистрации", url = "https://demoqa.com/automation-practice-form")
    void checkStudentRegistration() {
        Faker faker = new Faker(new Locale("ru"));
        RegistrationPage registrationPage = new RegistrationPage();

        String name = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress("com");
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String subject = "Biology";
        String gender = "Female";
//        String image = "cv.png";

//        registrationPage
//                .openPage();

        registrationPage
                .openPage()
                .setInputName(name)
                .setInputLastName(lastName)
                .clickCheckBoxFemale()
                .setInputEmail(email)
                .setInputPhone(phone)
                .chooseSubject(subject)
                .clickCheckBoxHobbies()
                //.upLoadImageOnPage(image)
                .setAddressInTextarea(address)
                .chooseState()
                .chooseCity()
                .clickButtonSubmit();

        registrationPage
                .assertSubmitTitleIsVisible()
                .assertTableAfterRegistrationHave(name)
                .assertTableAfterRegistrationHave(lastName)
                .assertTableAfterRegistrationHave(email)
                .assertTableAfterRegistrationHave(gender)
                .assertTableAfterRegistrationHave(phone)
                .assertTableAfterRegistrationHave(subject)
                .assertTableAfterRegistrationHave(address);
        //.assertTableAfterRegistrationHave(image);

    }
}


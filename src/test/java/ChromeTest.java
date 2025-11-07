import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

class ChromeTest {

    @BeforeAll
    public static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.headless = true; // или false для видимого браузера

    }

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[data-test-id=submit]").click();

        $("[data-test-id='callback-success']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ваша заявка успешно отправлена!"));
    }
}
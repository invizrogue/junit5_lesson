package study.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {

    @BeforeEach
    void setUp() {
        open("https://www.google.com");
    }

    @Disabled("JIRAID-00001")
    @DisplayName("Демонстрационный тест")
    @Test
    @Tags({@Tag("Blocker"), @Tag("UI_TEST")})
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

//    @ValueSource // используется, если нужно передатьодин параметр в тест

    @CsvSource({
            "Allure testops, https://qameta.io",
            "Selenide, https://ru.selenide.org"
    })

    @CsvFileSource(resources = "/testData.csv") // если надо указать параметры из файла
    @ParameterizedTest(name = "Адрес {1} должен бытьв выдаче google по запросу {0}")
    @Tags({@Tag("Blocker"), @Tag("UI_TEST")})
    void productSiteUrlShouldBePresentInResultOfSearchInGoogleByproductNameQuery(
            String productName,
            String productUrl
    ) {
        $("[name=q").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }

    @DisplayName("Адрес https://qameta.io должен бытьв выдаче google по запросу 'Allure testops'")
    @Test
    @Tags({@Tag("Blocker"), @Tag("UI_TEST")})
    void alluretestOpsSiteUrlShouldBePresentInResultOfSearchInGoogleByAllureQuery() {
        $("[name=q").setValue("Allure testops").pressEnter();
        $("[id=search]").shouldHave(text("https://qameta.io"));
    }
}

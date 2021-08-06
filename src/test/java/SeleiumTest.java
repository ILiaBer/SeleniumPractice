import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class SeleiumTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", ".//path/to/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @Test
    void CorrectTest() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Кросовок Илья");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+78005553535");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(cssSelector(".button__text")).click();
        String text = driver.findElement(cssSelector("p")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void TestWrongTel() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Кросовок Илья");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("88005553535");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(cssSelector(".button__text")).click();
        String text = driver.findElement(cssSelector(".input_invalid[data-test-id=phone] .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void TestWrongNameForEng() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Ilia");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+78005553535");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(cssSelector(".button__text")).click();
        String text = driver.findElement(cssSelector(".input_invalid[data-test-id=name] .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void TestEmptyCheckBox() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Кросовок Илья");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+78005553535");
        driver.findElement(cssSelector(".button__text")).click();
        String text = driver.findElement(cssSelector(".input_invalid .checkbox__text")).getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", text);

    }


}

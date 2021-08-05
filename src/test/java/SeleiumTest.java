import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleiumTest {
    @BeforeAll
    static void setUpAll(){
        System.setProperty("webdriver.chrome.driver","//path/to/chromedriver");
    }
@BeforeEach
    void setUp(){
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--no-sandbox");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
}
}

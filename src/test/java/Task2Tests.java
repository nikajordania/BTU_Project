import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Task2Tests {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testProgressBar() {
        driver.get("https://demoqa.com/progress-bar");

        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();

        WebElement progressBar = driver.findElement(By.cssSelector("#progressBar > div"));

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "100"));

        Assert.assertEquals(progressBar.getAttribute("aria-valuenow"), "100");

        System.out.println(progressBar.getText());
    }

    @Test(priority = 2)
    public void testDropdownMenuAndCheckboxElements() {
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        WebElement dropdown = driver.findElement(By.id("dropdowm-menu-1"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Python");

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Python");

        List<WebElement> allNotCheckedCheckbox = driver.findElements(By.xpath("//input[@type='checkbox' and not(@checked)]"));

        allNotCheckedCheckbox.forEach(WebElement::click);

        WebElement radioButton = driver.findElement(By.cssSelector("input[value='purple']"));

        radioButton.click();

        WebElement option = driver.findElement(By.cssSelector("div.thumbnail #fruit-selects > option[value='orange']"));

        Assert.assertFalse(option.isEnabled());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

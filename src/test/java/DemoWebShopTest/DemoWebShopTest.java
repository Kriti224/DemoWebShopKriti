package DemoWebShopTest;

import Pages.SeleniumCommands;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoWebShopTest{

    @Test
    public void loginAndPerformTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        SeleniumCommands cmd = new SeleniumCommands(driver);

        cmd.navigateToUrl("http://demowebshop.tricentis.com/");
        cmd.click(By.linkText("Log in"));
        cmd.sendKeys(By.id("Email"),"prasadkriti9@gmail.com");
        cmd.sendKeys(By.id("Password"),"Test1234");
        cmd.click(By.xpath("//input[@type='submit' and @value='Log in']"));
        cmd.click(By.partialLinkText("Computers"));
        cmd.click(By.xpath("//a[@title='Show products in category Desktops']"));
        cmd.click(By.xpath("//a[text()='Build your own cheap computer']/ancestor::div[@class='product-item']//input[@value='Add to cart']"));
        cmd.click(By.id("add-to-cart-button-72"));
        cmd.click(By.xpath("//span[ text()='Shopping cart']"));
        WebElement simpleDesk = driver.findElement(By.xpath("//td//a[text()='Build your own cheap computer']"));
        Assert.assertTrue(simpleDesk.isDisplayed());
        cmd.click(By.xpath("//a[text()='Edit']"));
        cmd.findElement(By.id("addtocart_72_EnteredQuantity")).clear();
        cmd.click(By.xpath("//input[@value='Update']"));
        cmd.click(By.xpath("//span[ text()='Shopping cart']"));
        WebElement cartEmptyMessage = cmd.findElement(By.xpath("//div[@class='order-summary-content']"));
        Assert.assertTrue(cartEmptyMessage.isDisplayed());
        cmd.click(By.xpath("//li//a[@class='ico-logout']"));
        cmd.quitBrowser();
    }
}

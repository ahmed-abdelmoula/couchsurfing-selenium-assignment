import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
class MainPage extends PageBase {

    private By searchBarTogglerBy = By.xpath("//a[@class='search-bar-toggler']/i");
    private By searchBarBy = By.name("search");
    private By loginBy = By.className("dropdown-toggle");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.workaway.info/");
    }

    public ResultPage search(String searchQuery) {
        this.waitAndReturnElement(By.xpath("//div[contains(@class, 'container nopadding-xs')]/div/ul[contains(@class, 'nav navbar-nav')]/li/a[@href='/en/hostlist']")).click();
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        this.waitAndReturnElement(By.xpath("//div[contains(@class, 'checkbox-custom checkbox-custom-inline')]"))
                .click();
        return new ResultPage(this.driver);
    }


    public String login() {
        this.waitAndReturnElement(By.xpath("//nav[contains(@class, 'navbar')]/ul[@id='navbar-user-nav']/li[contains(@class, 'dropdown')]/a[contains(@class, 'dropdown-toggle')]/div[contains(@class, 'navbar-user-item')]")).click();
        this.waitAndReturnElement(By.linkText("Login as Workawayer")).click();
        User u = new User();
        u.updateUserData();
        System.out.println(u.getuserName());
        this.waitAndReturnElement(By.name("un")).sendKeys(u.getuserName());
        try {
            Thread.sleep(1150);
            this.waitAndReturnElement(By.name("pw")).sendKeys(u.getpassword() + "\n");
            cookieRead cr= new cookieRead(this.driver);
            cr.read();
        } catch (InterruptedException ie) {
        }
       return (this.driver).getTitle();
    }

    public String logOut() {
        this.login();
        try {
            Thread.sleep(1000);
            this.waitAndReturnElement(By.xpath("//nav[contains(@class, 'navbar navbar-user-loggedin')]/ul[@id='navbar-user-nav']/li[@id='dropdown-account']/a/div[contains(@class,'navbar-user-item')]")).click();
            this.waitAndReturnElement(By.linkText("Logout")).click();
        } catch (InterruptedException ie) {
        }
        return (this.driver).getTitle();


    }
  
    //Uploading the file using sendKeys
    public ResultPage uploadPicture ()  {
        try {
            this.login();

            Thread.sleep(1000);
            this.driver.findElement(By.xpath(
                    "//nav[contains(@class, 'navbar navbar-user-loggedin')]/ul[@id='navbar-user-nav']/li[@id='dropdown-account']/a/div[contains(@class,'navbar-user-item')]"))
                    .click();
            this.driver.findElement(By.linkText("My profile photos")).click();
            String xpathExpression = "//form[@id='fileupload']/div[contains(@class, 'row fileupload-buttonbar')]/div[contains(@class, 'col-lg-12')]/span[contains(@class, 'btn btn-success fileinput-button')]/input[@type='file' and  @name='files[]']";
            // link text locator for uploading a photo..
            Thread.sleep(1000);
            WebElement addFile = this.driver.findElement(By.xpath(xpathExpression));
            // click on ‘Choose file’ to upload the desired file
            addFile.sendKeys(System.getProperty("user.dir") + "src\\otherfiles\\lmd.jpg");
            this.waitAndReturnElement(By.xpath("//button[@type='submit']")).click();
            
        } catch (InterruptedException ie) {
        }

        return new ResultPage(this.driver);
    }

 

}

import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class FirstSeleniumProject {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        // initializing driver variable using ChromeDriver
        driver = new ChromeDriver();
        // configurate the webdriver
        WebDriverManager.chromedriver().config().setProperties("src\\otherfiles\\webdrivermanager.properties");
        // Maximizes the browser window
        driver.manage().window().maximize();
        // define the periode for waiting
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
 
    
    @Test
    /* Login */
    public void login() {
        MainPage mainPage = new MainPage(this.driver);
        String expectedProfile = mainPage.login();
        readFile rd = new readFile();
        Assert.assertEquals(rd.loadProperties().getProperty("profile"), expectedProfile);
    }
    @Test
    /* LogOut */
    public void LogOut() {
        MainPage mainPage = new MainPage(this.driver);
        String expectedMainPage = mainPage.logOut();
        readFile rd = new readFile();
        Assert.assertEquals(rd.loadProperties().getProperty("main_page"), expectedMainPage);
    }
    @Test
    /* LogOut with Cookies */
    public void LoginWithCookies() {
        CookieWrite cw = new CookieWrite(this.driver);
        cw.readCo();
    }
    @Test
    /* Form sending with user */
    public void sendFormWithUser() {
        MainPage mainPage = new MainPage(this.driver);
        sendForm sender = new sendForm(this.driver);
        mainPage.login();
        String expectedMainPage = sender.sendIt();
        readFile rd = new readFile();
        Assert.assertEquals(rd.loadProperties().getProperty("profile"), expectedMainPage);
    }
    @Test
    /* Check Hover Button */
    public void hoverButton() {
        WorkAway wAway = new WorkAway(this.driver);
        ResultPage resultPage = wAway.hoverIt();
        System.out.println(resultPage.getBodyText());
        Assert.assertTrue(resultPage.getBodyText().contains("COVI-19"));

    }
    @Test
    /* Static Page test */
    public void searchForTheQueries() {
        String[] searchQueries = { "Hiking", "Children" };
        for (String searchQuery : searchQueries) {
            MainPage mainPage = new MainPage(this.driver);
            ResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            Assert.assertFalse(bodyText.contains("No results were found for this search."));
        }
    }
    @Test
    /* Back Button Click */
    public void backHistory() {
        WorkAway wAway = new WorkAway(this.driver);
        String expectedMainPage = wAway.Historytest();
        readFile rd = new readFile();
        Assert.assertEquals(rd.loadProperties().getProperty("main_page"), expectedMainPage);
    }
    @Test
    /* Check If Page Opened Or Not */
    public void OpenPage() {
        WorkAway wAway = new WorkAway(this.driver);
        wAway.checkPageOpened();
    }
    @Test
    /* Read The Page Title */
    public void readTitlePage() {
        WorkAway wAway = new WorkAway(this.driver);
        wAway.checkTitlePage();
    }
    @Test
    /* Upload Picture */
    public void uploadPicture() {
        MainPage mainPage = new MainPage(this.driver);
        ResultPage resultOfDownload = mainPage.uploadPicture();
        System.out.println(resultOfDownload.getBodyText());

    }

    @After
    public void teardown() {
    if (driver != null) {
    driver.quit();
    }
    }
}

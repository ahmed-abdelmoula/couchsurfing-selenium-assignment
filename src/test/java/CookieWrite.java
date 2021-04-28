import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieWrite extends PageBase {

    public CookieWrite(WebDriver driver) {
        super(driver);
    }    

    public void readCo() {
        try {

            File file = new File("Cookies.data");
            FileReader fileReader = new FileReader(file);
            BufferedReader Buffreader = new BufferedReader(fileReader);
            String strline;
            while ((strline = Buffreader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(strline, ";");
                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;
                    Date expdate= new Date();

                    String val;
                    if (!(val = token.nextToken()).equals("null")) {
                            
                    }
                  Boolean isSecure = Boolean.valueOf(token.nextToken());
                    Cookie ck = new Cookie(name, value, domain, path, expdate, isSecure);
                    // System.out.println(ck);
                    driver.manage().addCookie(ck); // This will add the stored cookie to your current session
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.driver.get("https://www.workaway.info/en/account/workawayer");
    }
}
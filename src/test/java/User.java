import java.io.FileReader;
import java.util.Properties;

public class User {

    private int id;

    private String  userName;

    private String  password;



  public String getuserName() {
      return userName;
  }

  public void setuserName(String userName) {
      this.userName = userName;
  }

  public String getpassword() {
      return password;
  }

  public void setpassword(String password) {
      this.password = password;
  }

  @Override
  public String toString() {
      return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
  }
  public User(){
    System.out.println("User is created");
}

public void updateUserData(){

 
    try (FileReader reader = new FileReader("src\\otherfiles\\config.txt")) {
        Properties properties = new Properties();
        properties.load(reader);
        this.setuserName(properties.getProperty("email"))  ;
        this.setpassword(properties.getProperty("password")); 

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
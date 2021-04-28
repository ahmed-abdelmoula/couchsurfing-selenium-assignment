import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

public class readFile { 

	public Properties properties;
	
	public Properties loadProperties() {
		try (FileReader reader = new FileReader("src\\otherfiles\\config.txt")) {
             this.properties = new Properties();
            properties.load(reader);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return properties;
	}
}


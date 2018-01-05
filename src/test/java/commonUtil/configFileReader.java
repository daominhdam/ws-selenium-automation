package commonUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configFileReader {

    Properties pro;
    FileInputStream input=null;

    public configFileReader() {
        try {
            File source = new File("resources/ToDoApplication/Properties/toDoApp.properties");

            FileInputStream input = new FileInputStream(source);

            pro = new Properties();

            pro.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
 
    public String getApplicationURL()
    {
        return pro.getProperty("baseurl");
    }

    public String getProperty(String key)
    {
        return pro.getProperty(key);
    }
}

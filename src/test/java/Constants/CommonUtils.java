package Constants;

import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
    public static void loadProperties(){


        Properties properties=new Properties();
        try {
            properties.load(CommonUtils.class.getResourceAsStream("/configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConstantsAPI.baseUrl=properties.getProperty("baseUrl");
        ConstantsAPI.userName=properties.getProperty("userName");
        ConstantsAPI.password=properties.getProperty("password");

    }
}

package testB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import testA.TestBase;
import testA.TestRunError;

import java.io.*;
import java.util.Properties;

public class TestJSONEmail {

    public static void main(String[] args) throws IOException, ParseException, TestRunError, InterruptedException {

        String projPath = System.getProperty("user.dir");
        System.out.println("ProjectPath:" + projPath);
        //JSON parser object to parse read file
        JSONObject emailUser = getTestInputJsonObject(projPath);
        System.out.println("firstName: " + emailUser.get("firstName"));
        System.out.println("lastName: " + emailUser.get("lastName"));
        System.out.println("email: " + emailUser.get("email"));
        System.out.println("address: " + emailUser.get("address"));

        InputStream file = new FileInputStream(projPath+"\\src\\testB\\appconfig.properties");
        Properties properties = new Properties();
        properties.load(file);
        String appURL = properties.getProperty("appURL");
        System.out.println("Application URL: "+ appURL);
        TestBase.invokeApp(appURL);
        TestBase testBase = TestBase.getInstance();
        testBase.clickElement("#createacc");
        testBase.setValue(".ureg-fname",emailUser.get("firstName").toString());
        testBase.setValue(".ureg-lname",emailUser.get("lastName").toString());
        testBase.setValue("#usernamereg-yid",emailUser.get("email").toString());
        testBase.hardWait(4);
        TestBase.closeAllBrowserInstances();
    }

    private static JSONObject getTestInputJsonObject(String projPath) throws IOException, ParseException {
        FileReader reader = new FileReader(projPath + "\\src\\testB\\emailAccountCreate.json");
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        return (JSONObject) obj;
    }


}

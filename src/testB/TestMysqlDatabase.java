package testB;

import org.openqa.selenium.WebDriver;
import testA.TestBase;
import testA.TestRunError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestMysqlDatabase {

    public static void main(String[] args) throws SQLException, TestRunError, InterruptedException {
        // Open a connection
        String JdbcURL = "jdbc:mysql://localhost:3306/testSelenium" + "?useSSL=false&useUnicode=true";
        String Username = "root";
        String password = "Test123";

        // Object of Connection from the Database
        Connection conn = null;
        // Object of Statement. It is used to create a Statement to execute the query
        Statement stmt = null;
        //Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection(JdbcURL, Username, password);
            System.out.println("Your JDBC URL is as follows:" + JdbcURL);
        } catch (Exception exec) {
            exec.printStackTrace();
        }

        // Execute a query
        stmt = conn.createStatement();

        resultSet = stmt.executeQuery("select * from customers");
        List<String> firstName = new ArrayList<String>();
        List<String> lastName = new ArrayList<String>();
        System.out.println("FirstName list size before adding data from DB: "+ firstName.size());
        System.out.println("LastName list size before adding data from DB:: "+ lastName.size());
        while (resultSet.next()) {
//            System.out.println(resultSet.getString(2)
//                    + "  " + resultSet.getString(3));
            firstName.add(resultSet.getString(2));
            lastName.add(resultSet.getString(3));
        }

        //retrieving data from collection
        System.out.println("FirstName list size: "+ firstName.size());
        System.out.println("LastName list size: "+ lastName.size());
//        System.out.println(firstName.get(0));
//        System.out.println(lastName.get(0));
        String fristNameValue = firstName.get(0);
        String lastNameValue = lastName.get(0);

        TestBase.invokeApp("https://login.yahoo.com/");
        TestBase testBase = TestBase.getInstance();

        testBase.clickElement("#createacc");
        testBase.setValue(".ureg-fname",fristNameValue);
        testBase.setValue(".ureg-lname",lastNameValue);
        testBase.selectDropdownItemByVisibleText("#usernamereg-month","May");

    }
}

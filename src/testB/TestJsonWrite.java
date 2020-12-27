package testB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class TestJsonWrite {

    public static void main(String[] args) {

        //First user
        JSONObject userDetails = new JSONObject();
        userDetails.put("firstName", "Lokesh");
        userDetails.put("lastName", "Gupta");
        userDetails.put("email", "lokesh123");

        JSONObject useObject = new JSONObject();
        useObject.put("user", userDetails);

        //Second user
        JSONObject userDetails2 = new JSONObject();
        userDetails2.put("firstName", "Brian");
        userDetails2.put("lastName", "Schultz");
        userDetails2.put("email", "brianBro2020");

        JSONObject useObject2 = new JSONObject();
        useObject2.put("user", userDetails);

        //Add usersList to list
        JSONArray usersList = new JSONArray();
        usersList.add(useObject);
        usersList.add(useObject2);

        //Write JSON file
        try (FileWriter file = new FileWriter("usersData.json")) {

            file.write(usersList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

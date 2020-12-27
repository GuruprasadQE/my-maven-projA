package testB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestJsonWriteSimple {

    public static void main(String[] args) throws IOException {

        String projPath = System.getProperty("user.dir");
        System.out.println("ProjectPath:" + projPath);
        //First user
        JSONObject userDetails = new JSONObject();
        userDetails.put("PNR", "BHIJDD");
        userDetails.put("lastName", "Ramesh");
        userDetails.put("email", "Ramesh454");

        //Write JSON file
        FileWriter file = new FileWriter(projPath + "\\src\\testB\\usersData.json");
        file.write(userDetails.toJSONString());
        file.flush();
    }

}

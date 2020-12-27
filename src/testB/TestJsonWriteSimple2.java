package testB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class TestJsonWriteSimple2 {

    public static void main(String[] args) throws IOException {

        String projPath = System.getProperty("user.dir");
        System.out.println("ProjectPath:" + projPath);
        //First user
        JSONObject userDetails = new JSONObject();
        userDetails.put("PNR", "BHIJDD");
        userDetails.put("lastName", "Ramesh");
        userDetails.put("email", "Ramesh454");

        JSONObject userDetails2 = new JSONObject();
        userDetails2.put("PNR", "HHHHFF");
        userDetails2.put("lastName", "Raja");
        userDetails2.put("email", "Raja74774");

        JSONArray jArray = new JSONArray();
        jArray.add(userDetails);
        jArray.add(userDetails2);

        //Write JSON file
        FileWriter file = new FileWriter(projPath + "\\src\\testB\\usersData.json");
        file.write(jArray.toJSONString());
        file.flush();
    }

}

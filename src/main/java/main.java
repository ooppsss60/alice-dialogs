import org.json.simple.*;
import org.json.simple.parser.*;
import spark.Spark;

import java.io.FileReader;
import java.io.IOException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        secure("deploy/keystore.jks", "12345678", null, null);
        Spark.ipAddress("0.0.0.0");
        post("/api", (req, res) -> {

            JSONParser parser = new JSONParser();
            JSONObject response = new JSONObject();
            JSONObject request = new JSONObject();
            try {
                response = (JSONObject) parser.parse(new FileReader("request.json"));
                System.out.println((String) response.get("version"));
                System.out.println(req);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            return response;
        });
    }
}

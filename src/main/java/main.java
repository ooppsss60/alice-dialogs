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
            JSONObject request = new JSONObject();
            JSONObject response = new JSONObject();
            try {
                response = DiaglogHandler.DialogHandler(request);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            return response;
        });

    }

}

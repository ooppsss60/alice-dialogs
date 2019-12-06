import org.json.simple.*;
import org.json.simple.parser.*;
import spark.Spark;
import java.io.IOException;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        secure("deploy/keystore.jks", "12345678", null, null);
        Spark.ipAddress("0.0.0.0");
        post("/api", (req, res) -> {
            System.out.println(req.body());
            JSONParser parser = new JSONParser();
            JSONObject response = new JSONObject();
            try {
                JSONObject request = (JSONObject) parser.parse(req.body());
                response = DialogHandler(request);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
            return response;
        });
        get("/", (req, res) -> {
            String ledState = "111";
            return ledState;
        });

    }

    static JSONObject DialogHandler(JSONObject request) throws IOException, ParseException {

        JSONObject response = new JSONObject();
        JSONParser parser = new JSONParser();
        return response;
    }

}

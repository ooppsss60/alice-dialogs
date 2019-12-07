import spark.Spark;
import java.io.IOException;
import org.json.*;
import static spark.Spark.*;

public class Main {
    public static JSONObject ledState = new JSONObject("{\"red\":1,\"green\":0,\"blue\":0}");

    public static void main(String[] args) throws IOException {
        secure("deploy/keystore.jks", "12345678", null, null);
        Spark.ipAddress("0.0.0.0");
        post("/api", (req, res) -> {
            System.out.println(req.body());
            JSONObject request = new JSONObject(req.body());
            JSONObject response = DialogHandler(request);
            return response;
        });
        get("/", (req, res) -> {
            return ledState;
        });

    }

    static JSONObject DialogHandler(JSONObject request) {
        JSONObject response = new JSONObject();
        String version = request.getString("version");
        JSONObject session = (JSONObject) request.get("session");
        response.put("version",version);
        response.put("session",session);
        JSONObject responseBody = new JSONObject();
        responseBody.put("text","test");
        responseBody.put("end_session",false);
        response.put("response",responseBody);

        System.out.println(request.get("version"));
        return response;
    }
}
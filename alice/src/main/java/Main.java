import spark.Spark;
import java.io.IOException;
import org.json.*;
import static spark.Spark.*;

public class Main {
    public static JSONObject ledState = new JSONObject("{\"red\":0,\"green\":0,\"blue\":0}");

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
        JSONObject requestBody = (JSONObject) request.get("request");
        response.put("version",version);
        response.put("session",session);
        JSONObject responseBody = new JSONObject();

        if(session.getBoolean("new")){
            responseBody.put("text","Привет");
        }else{
            switch (requestBody.getString("command").toLowerCase()) {
                case ("включи красный"):
                    responseBody.put("text","Включаю");
                    ledState.put("red",1);
                    break;
                case ("включи зелёный"):
                    responseBody.put("text","Включаю");
                    ledState.put("green",1);
                    break;
                case ("включи синий"):
                    responseBody.put("text","Включаю");
                    ledState.put("blue",1);
                    break;
                case ("включи все"):
                    responseBody.put("text","Включаю все");
                    ledState.put("red",1);
                    ledState.put("green",1);
                    ledState.put("blue",1);
                    break;
                case ("выключи красный"):
                    responseBody.put("text","Выключаю");
                    ledState.put("red",0);
                    break;
                case ("выключи зелёный"):
                    responseBody.put("text","Выключаю");
                    ledState.put("green",0);
                    break;
                case ("выключи синий"):
                    responseBody.put("text","Выключаю");
                    ledState.put("blue",0);
                    break;
                case ("выключи все"):
                    responseBody.put("text","Выключаю все");
                    ledState.put("red",0);
                    ledState.put("green",0);
                    ledState.put("blue",0);
                    break;
                case ("помощь"):
                    responseBody.put("text","Вот что я могу");
                    JSONArray buttons = new JSONArray();
                    buttons.put(new JSONObject("{\"title\": \"Включи красный\",\"hide\": true}"));
                    buttons.put(new JSONObject("{\"title\": \"Выключи синий\",\"hide\": true}"));
                    buttons.put(new JSONObject("{\"title\": \"Включи все\",\"hide\": true}"));
                    responseBody.put("buttons",buttons);
                    break;
                default:
                    responseBody.put("text","Я Вас не понимаю");
                    break;
            }
        }
        responseBody.put("end_session",false);
        response.put("response",responseBody);
        return response;
    }
}
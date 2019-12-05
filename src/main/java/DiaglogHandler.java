import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DiaglogHandler {
    static JSONObject DialogHandler(JSONObject request) throws IOException, ParseException {

        JSONObject response = new JSONObject();
        JSONParser parser = new JSONParser();
        response = (JSONObject) parser.parse(new FileReader("request.json"));
        return response;
    }
}

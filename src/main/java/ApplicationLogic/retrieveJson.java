package ApplicationLogic;

/**
 * @author gvince01
 */
import YAML.LoadYaml;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;


public class retrieveJson {
    public URL connectToApi(String api_url ,String api_id ,String api_key) throws IOException{
        //takes in the url, apiID and apiKEY from yaml file and returns HTTPURLConnection for other methods to work on
        LoadYaml connectYAML = new LoadYaml();
        return new URL(MessageFormat.format(connectYAML.getValue(api_url), connectYAML.getValue(api_id), connectYAML.getValue(api_key)));
    }

    public JsonObject getJSON(URL conn) {
        //takes in the url, opens a connection and returns the jsonObject
        JsonObject out = null;
        try {
            HttpURLConnection httpConnect = (HttpURLConnection) conn.openConnection();
            JsonParser jsonparse = new JsonParser();
            JsonElement jsonroot = jsonparse.parse(new InputStreamReader((InputStream) httpConnect.getContent()));
            out = jsonroot.getAsJsonObject();
        } catch (IOException IOex) {
            System.err.println(IOex);
        }
        return out;
    }

    public URL parseURL(JsonObject tfljson) throws MalformedURLException {
        //takes json returns url of webcam image
        return new URL(tfljson.get("additionalProperties").getAsJsonArray().get(1).getAsJsonObject().get("value").getAsString());
    }
}

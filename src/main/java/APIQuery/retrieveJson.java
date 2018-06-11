package APIQuery;

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
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.logging.Logger;


public class retrieveJson {
    public HttpURLConnection connectToApi(String api_url, String api_key, String api_id) throws IOException{
        //takes in the url, apiID and apiKEY from yaml file and returns HTTPURLConnection for other methods to work on
        LoadYaml connectYAML = new LoadYaml();
        URL url = new URL(MessageFormat.format(connectYAML.getAPI(api_url), connectYAML.getAPI(api_id), connectYAML.getAPI(api_key)));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return conn;
    }

    public JsonObject getJSON(HttpURLConnection conn) {
        System.out.println(conn);
        JsonObject out = null;
        HttpURLConnection connect = conn;
        try {
            JsonParser jsonparse = new JsonParser();
            JsonElement jsonroot = jsonparse.parse(new InputStreamReader((InputStream) connect.getContent()));
            out = jsonroot.getAsJsonObject();
        } catch (IOException IOex) {
            System.err.println(IOex);
        }
        return out;
    }

    public String parseURL(JsonObject tfljson){
        return tfljson.get("additionalProperties").getAsJsonArray().get(1).getAsJsonObject().get("value").toString();
    }

    public static void main(String[] args) throws IOException {
        retrieveJson my = new retrieveJson();
        HttpURLConnection myConn = my.connectToApi("tfl-url",  "tfl-api-key", "tfl-app-id");
        System.out.println(myConn);
        my.getJSON(myConn);
    }

}

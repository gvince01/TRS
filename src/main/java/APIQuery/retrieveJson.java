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
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

/**
 * @author gvince01
 */

public class retrieveJson {
    public JsonObject getJSON(){
        JsonObject out = null;
        LoadYaml tflyaml = new LoadYaml();
        try {
            String api_key = tflyaml.getAPI("tfl-api-key");
            String app_key = tflyaml.getAPI("tfl-app-id");
            URL url = new URL(MessageFormat.format("https://api.tfl.gov.uk/Place/JamCams_00001.07360?app_id={0}&app_key={1}", app_key, api_key));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            JsonParser jsonparse = new JsonParser();
            JsonElement jsonroot = jsonparse.parse(new InputStreamReader((InputStream) conn.getContent()));
            out = jsonroot.getAsJsonObject();
        } catch(IOException IOex){
            System.err.println(IOex);
        }
        return out;
    }

    public String parseURL(JsonObject tfljson){
        return tfljson.get("additionalProperties").getAsJsonArray().get(1).getAsJsonObject().get("value").toString();
    }
}

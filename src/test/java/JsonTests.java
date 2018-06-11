import APIQuery.retrieveJson;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class JsonTests {
    @Test
    public void connectionTest(){
        retrieveJson tst = new retrieveJson();
        try {
            HttpURLConnection conn = tst.connectToApi("tfl-url", "tfl-app-id", "tfl-api-key");
            assert conn.getResponseCode() == 200;
        } catch (IOException ex){
            System.err.println(ex);
        }
    }

    @Test
    public void jsonObject(){
        retrieveJson tst = new retrieveJson();
        try {
            JsonObject tstObj = tst.getJSON(tst.connectToApi("tfl-url", "tfl-app-id", "tfl-api-key"));
            System.out.println(tstObj);
        } catch (IOException ex){
            System.err.println(ex);
        }
    }

}

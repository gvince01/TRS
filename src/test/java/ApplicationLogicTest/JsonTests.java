package ApplicationLogicTest;

import ApplicationLogic.retrieveJson;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonTests {
    @Test
    public void connectionTest(){
        retrieveJson tst = new retrieveJson();
        try {
            HttpURLConnection conn = (HttpURLConnection) tst.connectToApi("tfl-url", "tfl-app-id", "tfl-api-key").openConnection();
            assert conn.getResponseCode() == 200;
        } catch (IOException ex){
            System.err.println(ex);
        }
    }

    @Test
    public void jsonObject(){
        try {
            retrieveJson my = new retrieveJson();
            URL myConn =  my.connectToApi("tfl-url",  "tfl-api-key", "tfl-app-id");
            assert my.getJSON(myConn).getClass().toString().equals("class com.google.gson.JsonObject");
        } catch (IOException ex){
            System.err.println(ex);
        }
    }

}

package Actors;

import ApplicationLogic.retrieveJson;
import akka.actor.UntypedActor;
import com.google.gson.JsonObject;

import java.net.URL;

/**
 * @author gvince01
 */

public class TFLQueryActor extends UntypedActor {

    public void onReceive(Object message) throws Exception{
        if(message instanceof String){
            String work = (String) message;
            getSender().tell(evaluateExpression());
        } else {
            unhandled(message);
        }
    }

    private URL evaluateExpression() throws Exception{
        retrieveJson tfl = new retrieveJson();
        URL connectionURL = tfl.connectToApi("tfl-url", "tfl-app-id","tfl-api-key");
        JsonObject connectionJSON = tfl.getJSON(connectionURL);
        return tfl.parseURL(connectionJSON);
    }
}

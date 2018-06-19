package Actors;

import ApplicationLogic.retrieveJson;
import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.gson.JsonObject;

import java.net.URL;

/**
 * @author gvince01
 */

public class TFLQueryActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("Retrieved String message: {}",s);
                    getSender().tell(evaluateExpression(), getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    public URL evaluateExpression() throws Exception{
        retrieveJson tfl = new retrieveJson();
        URL connectionURL = tfl.connectToApi("tfl-url", "tfl-app-id","tfl-api-key");
        JsonObject connectionJSON = tfl.getJSON(connectionURL);
        return tfl.parseURL(connectionJSON);
    }
}

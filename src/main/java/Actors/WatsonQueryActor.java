package Actors;

import ApplicationLogic.Watson;
import YAML.LoadYaml;
import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

import java.io.IOException;
import java.net.URL;

/**
 * @author gvince01
 */

public class WatsonQueryActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ClassifiedImages classify(URL message) throws IOException {
        LoadYaml classifier = new LoadYaml();
        return new Watson().classify(message, classifier.getValue("classifier-id"));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(URL.class, s -> {
                    log.info("Retrieved URL message: {}",s);
                    getSender().tell(classify(s), getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}

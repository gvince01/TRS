package Actors;

import Messages.Result;
import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author gvince01
 */

public class WebPageActor extends AbstractActor{
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private boolean classificationResult;

    private void updateWebPage(Boolean classificationBool){
        classificationResult = classificationBool;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Boolean.class, s -> {
                    log.info("Retrieved Boolean message: {}", s);
                    updateWebPage(s);
                })
                .match(Result.class, t -> {
                    log.info("Retrieved Result message: {}", t);
                    getSender().tell(classificationResult, getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}

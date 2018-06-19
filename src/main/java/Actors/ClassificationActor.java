package Actors;

import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

/**
 * @author gvince01
 */

public class ClassificationActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClassifiedImages.class, s -> {
                    log.info("Received ClassifiedImages message: {}", s);
                    getSender().tell(updatePage(s), getSelf());
                })
                .matchAny(o -> log.info("recived unknow message"))
                .build();
    }

    private boolean updatePage(ClassifiedImages imageClassification) {
        return imageClassification.getImages().get(0).getClassifiers().get(0).getClasses().toString() != "[]";
    }
}
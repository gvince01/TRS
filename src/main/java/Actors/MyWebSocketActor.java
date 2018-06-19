package Actors;

import Messages.Result;
import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyWebSocketActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props(ActorRef out) {
        return Props.create(MyWebSocketActor.class, out);
    }

    private final ActorRef out;

    public MyWebSocketActor(ActorRef out) {
        this.out = out;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Result.class, s -> {
                    log.info("Recieved result {}", s);
                    out.tell("I received your message " + s, self());
                })
                .build();
    }
}
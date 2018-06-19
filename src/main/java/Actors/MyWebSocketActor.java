package Actors;

import Messages.Result;
import akka.actor.*;

public class MyWebSocketActor extends AbstractActor {
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
                    out.tell("I received your message " + s, self());
                })
                .build();
    }
}
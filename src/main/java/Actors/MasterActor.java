package Actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

import java.net.URL;

/**
 * @author gvince01
 */

public class MasterActor extends UntypedActor {
    ActorRef TFLQueryActor = getContext().actorOf(new Props(Actors.TFLQueryActor.class), "TFL");
    ActorRef WatsonQueryActor = getContext().actorOf(new Props(Actors.WatsonQueryActor.class), "Watson");

    public void onReceive(Object message) throws Exception{
        if (message instanceof String){
            TFLQueryActor.tell(message, getSelf());
        } else if (message instanceof URL){
            WatsonQueryActor.tell(message, getSelf());
        } else {
            unhandled(message);
        }
    }
}

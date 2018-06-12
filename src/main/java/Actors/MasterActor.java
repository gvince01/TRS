package Actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

import java.net.URL;

/**
 * @author gvince01
 */

public class MasterActor extends UntypedActor {
    ActorRef TFLQueryActor = getContext().actorOf(new Props(Actors.TFLQueryActor.class), "TFL");
    ActorRef WatsonQueryActor = getContext().actorOf(new Props(Actors.WatsonQueryActor.class), "Watson");
    ActorRef PageUpdaterActor = getContext().actorOf(new Props(Actors.PageUpdaterActor.class), "PageUpdater");

    public void onReceive(Object message) throws Exception {
        if (message instanceof String){
            TFLQueryActor.tell(message, getSelf());
        } else if (message instanceof URL){
            WatsonQueryActor.tell(message, getSelf());
        } else if (message instanceof ClassifiedImages){
            PageUpdaterActor.tell(message, getSelf());
        } else {
            unhandled(message);
        }
    }
}

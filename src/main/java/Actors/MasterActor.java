package Actors;

import Messages.Result;
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
    ActorRef ClassificationActor = getContext().actorOf(new Props(Actors.ClassificationActor.class), "Classification");
    ActorRef WebPageActor = getContext().actorOf(new Props(Actors.WebPageActor.class), "WebPage");

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            TFLQueryActor.tell(message, getSelf());
        } else if (message instanceof URL) {
            WatsonQueryActor.tell(message, getSelf());
        } else if (message instanceof ClassifiedImages) {
            ClassificationActor.tell(message, getSelf());
        } else if (message instanceof Boolean) {
            WebPageActor.tell(message, getSelf());
        } else if (message instanceof Result) {
            WebPageActor.forward(message, getContext());
        }else {
            unhandled(message);
        }
    }
}

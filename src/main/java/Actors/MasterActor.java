package Actors;

import Messages.Result;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

import java.net.URL;

/**
 * @author gvince01
 */

public class MasterActor extends AbstractActor {
    ActorRef TFLQueryActor = getContext().actorOf(Props.create(Actors.TFLQueryActor.class), "TFL");
    ActorRef WatsonQueryActor = getContext().actorOf(Props.create(Actors.WatsonQueryActor.class), "Watson");
    ActorRef ClassificationActor = getContext().actorOf(Props.create(Actors.ClassificationActor.class), "Classification");
    ActorRef WebPageActor = getContext().actorOf(Props.create(Actors.WebPageActor.class), "WebPage");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> TFLQueryActor.tell(s, getSelf()))
                .match(URL.class, s -> WatsonQueryActor.tell(s, getSelf()))
                .match(ClassifiedImages.class, s -> ClassificationActor.tell(s, getSelf()))
                .match(Boolean.class, s -> WebPageActor.tell(s, getSelf()))
                .match(Result.class, s -> WebPageActor.forward(s, getContext()))
                .matchAny(o -> unhandled(o))
                .build();
    }
}

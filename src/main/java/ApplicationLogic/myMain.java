package ApplicationLogic;


import Actors.MasterActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Duration;
import akka.util.Timeout;

public class myMain{
    public static void main(String[] args) {
        Timeout timeout = new Timeout(Duration.parse("5 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        master.tell("go");
        timeout.duration();
    }
}
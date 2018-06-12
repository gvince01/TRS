package ApplicationLogic;


import Actors.MasterActor;
import Messages.Result;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;
import akka.dispatch.Future;

public class myMain{
    public static void main(String[] args) throws Exception{
        Timeout timeout = new Timeout(Duration.parse("10 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        master.tell("go");
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
//        String result = (String) Await.result(future, timeout.duration());
//        timeout.duration();
        System.out.println(future);
    }
}
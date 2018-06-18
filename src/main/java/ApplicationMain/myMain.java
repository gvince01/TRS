package ApplicationMain;


import Actors.MasterActor;
import Messages.Result;
//import WebPage.WebSocketUpdater;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;
import akka.dispatch.Future;

public class myMain {
    public static void main(String[] args) throws Exception {
//        WebSocketUpdater my = new WebSocketUpdater();
        Timeout timeout = new Timeout(Duration.parse("10 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        Cancellable cancellable = system.scheduler().schedule(Duration.parse("0 seconds"), Duration.parse("5 minutes"), master, "start");
        Thread.sleep(5000);
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        Boolean result = (Boolean) Await.result(future, timeout.duration());
        System.out.println(result);
    }
}
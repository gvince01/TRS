package ApplicationMain;

import Actors.MasterActor;
import Messages.Result;
import akka.actor.*;
import akka.dispatch.Await;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;
import akka.dispatch.Future;
import akka.dispatch.Await.*;

public class myMain {
    public static void main(String[] args) throws Exception {
        Timeout timeout = new Timeout(Duration.parse("10 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        Cancellable cancellable = system.scheduler().schedule(Duration.parse("0 seconds"), Duration.parse("5 minutes"), master, "start");
        Thread.sleep(5000);
        Boolean result = (Boolean) Await.result(Patterns.ask(master, new Result(), timeout), timeout.duration());
        System.out.println(result);
    }
}
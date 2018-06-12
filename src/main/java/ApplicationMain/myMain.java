package ApplicationMain;


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

public class myMain {
    public static void main(String[] args) throws Exception {
        Timeout timeout = new Timeout(Duration.parse("5 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        system.scheduler().schedule(Duration.parse("1 second"), Duration.parse("10 seconds"), master, "go");
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        Boolean result = (Boolean) Await.result(future, timeout.duration());
        System.out.println(result);
        system.shutdown();
    }
}
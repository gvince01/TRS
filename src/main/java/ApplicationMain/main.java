package ApplicationMain;

import Actors.MasterActor;
import ApplicationLogic.ConjestionResult;
import Messages.Result;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * @author gvince01
 */

public class main {
    public static void main(String[] args) throws Exception {
        Timeout timeout = new Timeout(10, TimeUnit.SECONDS);
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(Props.create(MasterActor.class), "master");
        system.scheduler().schedule(
                Duration.create(0, TimeUnit.MINUTES),
                Duration.create(10, TimeUnit.MINUTES),
                master,
                "start",
                system.dispatcher(),
                null);
        Thread.sleep(10000);
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        Boolean result = (Boolean) Await.result(future, timeout.duration());
        System.out.println(ConjestionResult.conjested(result));
    }
}

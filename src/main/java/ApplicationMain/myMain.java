package ApplicationMain;

import Actors.MasterActor;
import Messages.Result;
import akka.actor.*;
import akka.dispatch.Await;
import akka.dispatch.Future;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

public class myMain {
    public static void main(String[] args) throws Exception {
        Timeout timeout =  new Timeout(5, TimeUnit.SECONDS);
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(Props.create(MasterActor.class), "master");
        Cancellable cancel = system.scheduler().schedule(
                Duration.create(0, TimeUnit.MINUTES),
                Duration.create(10, TimeUnit.MINUTES),
                master,
                "Tick",
                system.dispatcher(),
                null);
        Thread.sleep(5000);

    }
}
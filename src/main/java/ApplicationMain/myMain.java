package ApplicationMain;


import Actors.MasterActor;
import Messages.Result;
import WebPage.Application;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;
import akka.dispatch.Future;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class myMain{
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
        Timeout timeout = new Timeout(Duration.parse("10 seconds"));
        ActorSystem system = ActorSystem.create("TRSapp");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");
        master.tell("go");
        Future<Object> future = Patterns.ask(master, new Result(), timeout);
        System.out.println(future);
    }
}
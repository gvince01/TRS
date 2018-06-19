package ActorsTest;

import Actors.TFLQueryActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActor;
import akka.testkit.TestActorRef;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * @author gvince01
 */

//public class TFLQueryActorTest {
//    ActorSystem system = ActorSystem.create();
//    TestActorRef<TFLQueryActor> actorRef = TestActorRef.apply(Props.create(TFLQueryActor.class), system);
//
//
//    @Test
//    public void test() throws Exception{
//        URL returnURL = new URL("https://s3-eu-west-1.amazonaws.com/jamcams.tfl.gov.uk/00001.07360.jpg");
//        TFLQueryActor actor = actorRef.underlyingActor();
//        Assert.assertTrue(returnURL.equals(actor.evaluateExpression()));
//    }
//}

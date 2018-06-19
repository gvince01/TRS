package WebPage;

import Actors.MyWebSocketActor;
import akka.actor.*;
import akka.stream.*;
import play.mvc.*;
import play.libs.streams.ActorFlow;

import javax.inject.Inject;

/**
 * @author gvince01
 */

public class WebSockets extends Controller {
    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public WebSockets(ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public WebSocket socket() {
        return WebSocket.Text.accept(request ->
                ActorFlow.actorRef(MyWebSocketActor::props,
                        actorSystem, materializer
                )
        );
    }

}
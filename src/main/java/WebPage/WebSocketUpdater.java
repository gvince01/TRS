package WebPage;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author gvince01
 */

@ServerEndpoint("/websocketendpoint")
public class WebSocketUpdater {

    @OnOpen
    public void onOpen(Session session){
        try{
            session.getBasicRemote().sendText("Hi there, Euston Road is currently ");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    @OnClose
    public void onClose(){
        System.out.println("Close Connection ...");
    }

    @OnError
    public void onError(Throwable e){
        e.printStackTrace();
    }
}


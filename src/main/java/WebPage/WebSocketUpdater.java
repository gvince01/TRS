package WebPage;

import ApplicationMain.myMain;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gvince01
 */

@ServerEndpoint("/websocketendpoint")
public class WebSocketUpdater {

    public String test(){
        myMain th = new myMain();
        return th.toString();
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println("hello world...");
        try{
            session.getBasicRemote().sendText(test());
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


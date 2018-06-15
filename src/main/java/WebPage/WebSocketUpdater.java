package WebPage;

import javax.websocket.OnOpen;
import javax.websocket.Session;
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

@ServerEndpoint("/")
public class WebSocketUpdater {
    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();

    private static Set<Session> allSessions;

    @OnOpen
    public void showTime(Session session){
        allSessions = session.getOpenSessions();
        // start the scheduler on the very first connection
        // to call sendTimeToAll every second
        if (allSessions.size()==1){
            timer.scheduleAtFixedRate(() -> sendTimeToAll(session),0,6,TimeUnit.MINUTES);
        }
    }

    private void sendTimeToAll(Session session){
        allSessions = session.getOpenSessions();
        for (Session sess: allSessions){
            try{
                sess.getBasicRemote().sendText("Local time: " + LocalTime.now());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}


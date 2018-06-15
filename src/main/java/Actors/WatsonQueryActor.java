package Actors;

import ApplicationLogic.Watson;
import akka.actor.UntypedActor;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

import java.io.IOException;
import java.net.URL;

/**
 * @author gvince01
 */

public class WatsonQueryActor extends UntypedActor {
    public void onReceive(Object message) throws IOException{
        if (message instanceof URL){
            URL imageURL = (URL) message;
            getSender().tell(classify(imageURL));
        } else {
            unhandled(message);
        }
    }

    private ClassifiedImages classify(URL message) throws IOException {
        return new Watson().classify(message);
    }
}

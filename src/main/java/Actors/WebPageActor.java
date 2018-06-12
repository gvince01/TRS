package Actors;

import WebPage.MessageController;
import akka.actor.UntypedActor;

/**
 * @author gvince01
 */

public class WebPageActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Boolean){
            Boolean classificationBool = (Boolean) message;
            getSender().tell(updateWebPage(classificationBool));
        } else {
            unhandled(message);
        }
    }

    private Double updateWebPage(Boolean classificationBool){
        System.out.println("step 4 - updated index " + classificationBool);
        return 0.0;
    }
}

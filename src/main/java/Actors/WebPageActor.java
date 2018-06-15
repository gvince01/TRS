package Actors;

import Messages.Result;
import akka.actor.UntypedActor;

/**
 * @author gvince01
 */

public class WebPageActor extends UntypedActor {
    private boolean classificationResult;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Boolean) {
            updateWebPage((Boolean) message);
        } else if (message instanceof Result){
            getSender().tell(classificationResult);
        } else {
            unhandled(message);
        }
    }

    private void updateWebPage(Boolean classificationBool){
        classificationResult = classificationBool;
    }
}

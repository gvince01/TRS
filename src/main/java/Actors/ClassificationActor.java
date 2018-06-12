package Actors;

import WebPage.MessageController;
import akka.actor.UntypedActor;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;

/**
 * @author gvince01
 */

public class ClassificationActor extends UntypedActor {

    public void onReceive(Object message) throws Exception {
        if(message instanceof ClassifiedImages){
            ClassifiedImages classifiedImage = (ClassifiedImages) message;
            getSender().tell(updatePage(classifiedImage));
        } else {
            unhandled(message);
        }
    }
    private boolean updatePage(ClassifiedImages imageClassification){
        return imageClassification.getImages().get(0).getClassifiers().get(0).getClasses().toString() != "[]";
    }
}

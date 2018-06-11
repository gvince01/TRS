package APIQuery;

import YAML.LoadYaml;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ListClassifiersOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author gvince01
 */

public class Watson {
    public void getClassifiers(){
        try {
            LoadYaml watsonyaml = new LoadYaml();
            String api_key = watsonyaml.getAPI("watson-api-key");
            VisualRecognition service = new VisualRecognition("2018-03-19");
            service.setApiKey(api_key);

            ListClassifiersOptions listClassifiersOptions = new ListClassifiersOptions.Builder()
                    .verbose(true)
                    .build();
            Classifiers classifiers = service.listClassifiers(listClassifiersOptions).execute();
            System.out.println(classifiers);
        }
        catch (IOException ex){
                System.err.println(ex);
            }
    }

    public static void main(String[] args) {
        Watson myWat = new Watson();
        myWat.getClassifiers();
    }
}


//    public void classifierTrain() {
//        VisualRecognition jam = new VisualRecognition("2018-05-22");
//        try {
//            LoadYaml watsonyaml = new LoadYaml();
//            String api_key = watsonyaml.getAPI("watson-api-key");
//            System.out.println(api_key);
//            jam.setApiKey(api_key);
//        } catch (IOException ex){
//            System.err.println(ex);
//        }
//        try {
//            CreateClassifierOptions createClassifierOptions = new CreateClassifierOptions
//                    .Builder()
//                    .name("TrafficJam")
//                    .addClass("Conjested", new File("./congested.zip"))
//                    .negativeExamples(new File("./notcongested.zip"))
//                    .build();
//            Classifier conjestion = jam.createClassifier(createClassifierOptions).execute();
//            System.out.println(conjestion);
//        } catch (FileNotFoundException ex){
//            System.err.println(ex);
//        }
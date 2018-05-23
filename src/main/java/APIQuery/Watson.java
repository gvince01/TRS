package APIQuery;

import YAML.LoadYaml;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author gvince01
 */

public class Watson {
    public void classifierTrain() {
        VisualRecognition jam = new VisualRecognition("2018-05-22");
        try {
            LoadYaml watsonyaml = new LoadYaml();
            String api_key = watsonyaml.getAPI("watson-api-key");
            jam.setApiKey(api_key);
        } catch (IOException ex){
            System.err.println(ex);
        }
        try {
            CreateClassifierOptions createClassifierOptions = new CreateClassifierOptions
                    .Builder()
                    .name("TrafficJam")
                    .addClass("Conjested", new File("./conjested.zip"))
                    .negativeExamples(new File("./nonconjested.zip"))
                    .build();
            Classifier conjestion = jam.createClassifier(createClassifierOptions).execute();
        } catch (FileNotFoundException ex){
            System.err.println(ex);
        }
    }
}

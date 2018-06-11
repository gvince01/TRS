package APIQuery;


import YAML.LoadYaml;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;
import java.io.*;

/**
 * @author gvince01
 */

public class Watson {
    public void classify(String image) throws IOException{
        FileInputStream imagesStream;
        LoadYaml watson_yaml = new LoadYaml();
        String watson_api = watson_yaml.getAPI("watson-api-key");
        IamOptions options = new IamOptions.Builder().apiKey(watson_api).build();
        VisualRecognition service = new VisualRecognition("2018-03-19", options);
        imagesStream = new FileInputStream("./14.jpg");
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename("image.jpg")
                .addClassifierId("congestion_58613629")
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        System.out.println(result);
    }


//    pulls all classifiers from IBM
//
//    public void classifiers() throws IOException{
//        LoadYaml my = new LoadYaml();
//        String ul = my.getAPI("watson-api-key");
//        IamOptions options = new IamOptions.Builder().apiKey(ul).build();
//
//        VisualRecognition service = new VisualRecognition("2018-03-19", options);
//
//        GetClassifierOptions getClassifierOptions = new GetClassifierOptions.Builder("congestion_58613629").build();
//        Classifier classifier = service.getClassifier(getClassifierOptions).execute();
//        System.out.println(classifier);
//    }

}
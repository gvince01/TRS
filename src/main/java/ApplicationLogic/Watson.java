package ApplicationLogic;


import YAML.LoadYaml;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.*;
import java.io.*;
import java.net.URL;

/**
 * @author gvince01
 */

public class Watson {
    public ClassifiedImages classify(URL imageURL, String classifierID) throws IOException{
        //takes url, classifies it against custom watson classfier
        LoadYaml watson_yaml = new LoadYaml();
        String watson_api = watson_yaml.getValue("watson-api-key");
        IamOptions options = new IamOptions.Builder().apiKey(watson_api).build();
        VisualRecognition service = new VisualRecognition("2018-03-19", options);
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .url(imageURL.toString())
                .imagesFilename("image.jpg")
                .addClassifierId(classifierID)
                .build();
        return service.classify(classifyOptions).execute();
    }
}
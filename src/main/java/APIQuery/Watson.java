package APIQuery;


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
    public String classify(URL image) throws IOException{
        //takes url, classifies it against custom watson classfier
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
        return result.toString();
    }
}
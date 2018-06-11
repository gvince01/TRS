package Messages;

/**
 * @author gvince01
 */

public class TFLMessage {
    private String imageURL;

    public TFLMessage(String url){
        this.imageURL = url;
    }

    public String getImageURL() {
        return imageURL;
    }
}

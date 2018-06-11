package Messages;

/**
 * @author gvince01
 */

public class watsonMessage {
    private final String IMAGEURL;
    private final String CLASSIFICATION;

    public watsonMessage(String image, String classification){
        this.CLASSIFICATION = classification;
        this.IMAGEURL = image;
    }

    public String getCLASSIFICATION() {
        return CLASSIFICATION;
    }
}

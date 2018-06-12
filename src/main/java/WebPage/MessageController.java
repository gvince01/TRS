package WebPage;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MessageController {

    @RequestMapping("/")
    public String index(Boolean arguement) {
        String outPut = "Euston Road is currently conjested";
        if (arguement) {
            outPut = "Euston Road is currently conjested";
        }
        return outPut;
    }
}
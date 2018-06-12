package WebPage;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MessageController {
    boolean defualt = false;

    @RequestMapping("/")
    public String index(boolean defualt) {
        String outPut = "Euston Road is currently conjested";
        return outPut + defualt;
    }

}
package springboot.rest.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "" +
                "<p>Springboot</p>" +
                "<ul>Endpoints" +
                "<li>greeting</li>" +
                "<li>users</li>" +
                "<li>user / {id}</li>" +
                "</ul>" +
                "";
    }
}

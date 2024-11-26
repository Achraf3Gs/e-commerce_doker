package e_commerce.e_commerce.configu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/login", "/Register", "/home", "/products"})
    public String redirect() {
        return "forward:/index.html"; // Serves Angular's index.html
    }
}
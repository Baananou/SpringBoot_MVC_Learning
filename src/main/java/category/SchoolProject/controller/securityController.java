package category.SchoolProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
    @GetMapping("/errorPage")
    public String errorPage(){
        return "errorPage";
    }
}

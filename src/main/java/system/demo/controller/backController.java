package system.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class backController {

    @RequestMapping("/backlogin")
    public String backlogin(){
        return "redirect:/login";
    }

    @RequestMapping("/backindex")
    public String backindex(){
        return "redirect:/index";
    }
}

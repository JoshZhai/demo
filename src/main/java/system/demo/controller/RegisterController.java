package system.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/registerCheck")
    public String registerCheck(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("phonenum") String phonenum){

        String sql = "insert into user(username,password,phonenum,isadmin) values(?,?,?,'N')";
        jdbcTemplate.update(sql,new Object[]{username,password,phonenum});

        return "redirect:/";
    }

    @RequestMapping("/adminregister")
    public String adminregister(){
        return "adminregister";
    }



    @RequestMapping("/adminregistercheck")
    public String adminregister(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("phonenum") String phonenum,
                                @RequestParam("isadmin") String isadmin){

        String sql = "insert into user(username,password,phonenum,isadmin) values(?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{username,password,phonenum,isadmin});

        return "redirect:/adminuser";


    }
}

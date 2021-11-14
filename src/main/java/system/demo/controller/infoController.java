package system.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class infoController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/info")
    public String info(){
        return "info";
    }

    @RequestMapping("/infoCheck")
    public String infoCheck(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("phonenum") String phonenum,
                            HttpServletRequest request,
                            HttpSession session,
                            Model model){

        String loginID = session.getAttribute("loginID").toString();

        session.setAttribute("loginName",username);
        session.setAttribute("loginPassword",password);
        session.setAttribute("loginPhoneNum",phonenum);

        String sql1 = "update user set username=? where id=?";
        String sql2 = "update user set password=? where id=?";
        String sql3 = "update user set phonenum=? where id=?";

        jdbcTemplate.update(sql1,new Object[]{username,loginID});
        jdbcTemplate.update(sql2,new Object[]{password,loginID});
        jdbcTemplate.update(sql3,new Object[]{phonenum,loginID});

        return "redirect:/index";
    }
}

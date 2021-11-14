package system.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import system.demo.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping({"/","/login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/loginCheck")
    public String loginCheck(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpSession session,
                        Model model){
        String sql = "select * from user where username=? and password=?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{username, password});

        if(!maps.isEmpty()){
            User user = JSONObject.parseObject(JSON.toJSONString(maps.get(0)), new TypeReference<User>() {});
            session.setAttribute("loginID",user.getId());
            session.setAttribute("loginName",user.getUsername());
            session.setAttribute("loginPassword",user.getPassword());
            session.setAttribute("loginPhoneNum",user.getPhonenum());
            session.setAttribute("loginIsAdmin",user.getIsadmin());
            model.addAttribute("login",user);
            if(user.getIsadmin().equals("Y")){
                return "adminindex";
            }else{
                return "redirect:/index";
            }

        }else{
            return "redirect:/";
        }

    }
}

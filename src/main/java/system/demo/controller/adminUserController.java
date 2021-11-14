package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class adminUserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/adminuser")
    public Object adminuser(Model model){

        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        model.addAttribute("userlist",list);

        return "adminuser";
    }

    @RequestMapping("/adminuserinfo")
    public String adminuserinfo(Integer id,
                                HttpSession session){

        String sql="select * from user where id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{id});

        session.setAttribute("adminuserid",id);
        session.setAttribute("loginName",map.get("username"));
        session.setAttribute("loginPassword",map.get("password"));
        session.setAttribute("loginPhoneNum",map.get("phonenum"));
        session.setAttribute("loginIsAdmin",map.get("isadmin"));

        return "adminuserinfo";
    }

    @RequestMapping("/adminuserinfocheck")
    public String adminuserinfocheck(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("phonenum") String phonenum,
                                     @RequestParam("isadmin") String isadmin,
                                     HttpSession session){

        String sql1 = "update user set username=? where id=?";
        String sql2 = "update user set password=? where id=?";
        String sql3 = "update user set phonenum=? where id=?";

        Integer id = (Integer) session.getAttribute("adminuserid");
        jdbcTemplate.update(sql1,new Object[]{username,id});
        jdbcTemplate.update(sql2,new Object[]{password,id});
        jdbcTemplate.update(sql3,new Object[]{phonenum,id});

        if(isadmin.equals("Y")){
            String sql="update user set isadmin='Y' where id=?";
            jdbcTemplate.update(sql,new Object[]{id});
        }else{
            String sql="update user set isadmin='N' where id=?";
            jdbcTemplate.update(sql,new Object[]{id});
        }


        return "redirect:/adminuser";

    }

    @RequestMapping("/adminuserdel")
    public String adminuserdel(Integer id){

        String sql="delete from user where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
        return "redirect:/adminuser";

    }
}

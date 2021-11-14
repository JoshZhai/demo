package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class adminScoreController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/adminscore")
    public Object adminuser(Model model){

        String sql = "select * from score";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        model.addAttribute("scorelist",list);

        return "adminscore";
    }

    @RequestMapping("/adminscoredel")
    public String adminscoredel(Integer id){

        String sql="delete from score where scoreID = ?";
        jdbcTemplate.update(sql,new Object[]{id});
        return "redirect:/adminscore";

    }

}
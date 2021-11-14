package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class adminImageController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/adminimage")
    public Object adminuser(Model model){

        String sql="select count(*) from image";
        Map<String, Object> imgsize = jdbcTemplate.queryForMap(sql);
        int size = Integer.parseInt(imgsize.get("count(*)").toString());

        for (int i=1;i<=size;i++){

            String sql1="select ROUND(AVG(imagescore),2) from score where imageID = ? and type = '绝对评价'";
            String sql2="select ROUND(AVG(imagescore),2) from score where imageID = ? and type = '相对评价'";
            Map<String, Object> DSCQSavg = jdbcTemplate.queryForMap(sql1,new Object[]{i});
            Map<String, Object> SSCQEavg = jdbcTemplate.queryForMap(sql2,new Object[]{i});
            double DSCQSv = Double.parseDouble(DSCQSavg.get("ROUND(AVG(imagescore),2)").toString());
            double SSCQEv = Double.parseDouble(SSCQEavg.get("ROUND(AVG(imagescore),2)").toString());


            sql1="update image set DSCQSrank=? where imageID=?";
            sql2="update image set SSCQErank=? where imageID=?";
            jdbcTemplate.update(sql1,new Object[]{DSCQSv,i});
            jdbcTemplate.update(sql2,new Object[]{SSCQEv,i});

        }

        sql = "select * from image";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        model.addAttribute("imagelist",list);

        return "adminimage";
    }


    @RequestMapping("/adminimageinfo")
    public String adminimageinfo(Integer id,
                                HttpSession session){

        String sql="select * from image where imageID=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{id});

        session.setAttribute("adminimageid",id);
        session.setAttribute("imagename",map.get("imagename"));
        session.setAttribute("imageinfo",map.get("imageinfo"));

        return "adminimageinfo";
    }

    @RequestMapping("/adminimageinfocheck")
    public String adminuserinfocheck(@RequestParam("imagename") String imagename,
                                     @RequestParam("imageinfo") String imageinfo,
                                     HttpSession session){

        String sql = "update image set imagename=? , imageinfo=? where imageID=?";

        Integer id = (Integer) session.getAttribute("adminimageid");
        jdbcTemplate.update(sql,new Object[]{imagename,imageinfo,id});

        return "redirect:/adminimage";

    }

    @RequestMapping("/adminimagedel")
    public String adminimagedel(Integer id){

        String sql="delete from image where imageID = ?";
        jdbcTemplate.update(sql,new Object[]{id});
        return "redirect:/adminimage";
    }

}
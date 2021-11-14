package system.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class downController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/downSSCQE")
    public String downSSCQE(HttpSession session){


        Integer size = (Integer) session.getAttribute("size");
        Integer imageID = (Integer) session.getAttribute("imageID");

        if (imageID < size){
            imageID++;
            session.setAttribute("imageID",imageID);

            String sql="select imageroute from image where imageID = ?";
            Map<String, Object> imgroute = jdbcTemplate.queryForMap(sql,new Object[]{imageID});
            String route = imgroute.get("imageroute").toString();
            session.setAttribute("route",route);
        }

        return "SSCQE";

    }


    @RequestMapping("/downDSCQS")
    public String downDSCQS(HttpSession session) {


        Integer size = (Integer) session.getAttribute("size");
        Integer imageID = (Integer) session.getAttribute("imageID");

        if (imageID < size) {
            imageID++;
            session.setAttribute("imageID", imageID);

            String sql = "select imageroute from image where imageID = ?";
            Map<String, Object> imgroute = jdbcTemplate.queryForMap(sql, new Object[]{imageID});
            String route = imgroute.get("imageroute").toString();
            session.setAttribute("route", route);
        }

        return "DSCQS";
    }
}

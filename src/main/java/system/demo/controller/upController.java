package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.jar.JarEntry;

@Controller
public class upController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/upSSCQE")
    public String upSSCQE(HttpSession session){

        Integer imageID = (Integer) session.getAttribute("imageID");

        if (imageID > 1){
            imageID--;
            session.setAttribute("imageID",imageID);

            String sql="select imageroute from image where imageID = ?";
            Map<String, Object> imgroute = jdbcTemplate.queryForMap(sql,new Object[]{imageID});
            String route = imgroute.get("imageroute").toString();
            session.setAttribute("route",route);
        }

        return "SSCQE";
    }


    @RequestMapping("/upDSCQS")
    public String upDSCQS(HttpSession session){

        Integer imageID = (Integer) session.getAttribute("imageID");

        if (imageID > 1){
            imageID--;
            session.setAttribute("imageID",imageID);

            String sql="select imageroute from image where imageID = ?";
            Map<String, Object> imgroute = jdbcTemplate.queryForMap(sql,new Object[]{imageID});
            String route = imgroute.get("imageroute").toString();
            session.setAttribute("route",route);
        }

        return "DSCQS";
    }
}

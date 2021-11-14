package system.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class DSCQSController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/DSCQS")
    public Object DSCQS(HttpSession session){

        String sql="select count(*) from image";
        Map<String, Object> imgsize = jdbcTemplate.queryForMap(sql);
        int size = Integer.parseInt(imgsize.get("count(*)").toString());

        session.setAttribute("size",size);
        session.setAttribute("imageID",1);

        sql="select imageroute from image where imageID = 1";
        Map<String, Object> imgroute = jdbcTemplate.queryForMap(sql);
        String route = imgroute.get("imageroute").toString();

        session.setAttribute("route",route);
        return "DSCQS";
    }

    @RequestMapping("/rankDSCQS")
    public String rankSSCQE(HttpSession session,
                            @RequestParam("rank") String rank){

        int imagescore = Integer.parseInt(rank);
        int imageID = (Integer)session.getAttribute("imageID");
        String sql = "insert into score(imageID,imagescore,type) values(?,?,'绝对评价')";

        jdbcTemplate.update(sql,new Object[]{imageID,imagescore});

        return "DSCQS";

    }
}

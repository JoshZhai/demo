package system.demo.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import system.demo.controller.FileUtil;

import java.util.*;


@RestController
public class testController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/ttest")
    public Object ttest(Integer id){

        return id;





    }



    public static void main(String[] args) {
        Random random = new Random();
        int j = 5;
        while(j-->=0){
            int i = random.nextInt(100)+1;
            System.out.println(i);
        }

    }

}


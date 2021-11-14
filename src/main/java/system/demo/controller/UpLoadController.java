package system.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpLoadController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/doUpload")
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            HttpSession session){

        if ((file.getOriginalFilename().isEmpty() )) {
            session.setAttribute("error","error");
            return "adminimage";
        }else{
            String fileName = file.getOriginalFilename();
            String route = "/img/" + fileName;
            // 图片存放的文件夹地址
            String filePath = "src\\main\\resources\\static\\img\\";
            try{
                // 图片上传的工具类
                // 参数一：图片的编码格式的字节数组
                // 参数二：图片存放的文件夹地址
                // 参数三：图片的名字
                FileUtil.uploadFile(file.getBytes(),filePath,fileName);
            } catch (Exception e){
            }

            String sql="insert into image(imagename,imageroute) values('img',?)";
            jdbcTemplate.update(sql,new Object[]{route});

            return "adminimage";
        }
    }


}

package org.example.webproject01_0225.contorller;

import cn.hutool.core.io.IoUtil;
import jakarta.annotation.Resource;
import org.example.webproject01_0225.Service.UserService;
import org.example.webproject01_0225.Service.impl.UserServiceImpl;
import org.example.webproject01_0225.userTXT.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
//import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//用户信息的controller
@RestController
public class userController{

////    Autowired方式一：直接作为一个属性注入。
//    @Autowired
//    private UserService userService;

////      Autowired方式二：构造器注入。
//    @Autowired
    @Resource(name = "userServiceImpl")
    private final UserService userService;
    public userController(UserService userService) {
        this.userService = userService;
    }

//      Autowired方式三：setter注入。
//    private UserService userService;
//    @Qualifier("userServiceImpl")
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping("/list")
    public List<user> list() throws Exception {
        return userService.findAll();


////        读取user.txt,获取用户数据
////        InputStream in = new FileInputStream(new File("user.txt"));
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
//        System.out.println(lines);
////        解析用户信息
////        封装信息为user对象
////        封装到list集合中
////        转化list为json格式返回给前端
//
//        return lines.stream().map(line -> {
//            String[] parts = line.split(",");
//            Integer id = Integer.parseInt(parts[0]);
//            String username = parts[1];
//            String password = parts[2];
//            String name = parts[3];
//            Integer age = Integer.parseInt(parts[4]);
//            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new user(id, username, password, name, age, updateTime);
//        }).toList();
    }
}

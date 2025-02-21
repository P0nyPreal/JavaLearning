package org.example.webproject01_0225.Service.impl;

import org.example.webproject01_0225.Dao.UserDao;
import org.example.webproject01_0225.Dao.impl.UserDaoImpl;
import org.example.webproject01_0225.Service.UserService;
import org.example.webproject01_0225.userTXT.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Primary
//primary可以提高注解优先级
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<user> findAll(){
        List<String> lines = userDao.findAll();

        List<user> users = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new user(id, username, password, name, age, updateTime);
        }).toList();

        return users;
    };
}

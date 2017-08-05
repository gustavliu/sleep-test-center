package com.gustav.controller;

import com.gustav.entity.UserDTO;
import com.gustav.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gustav on 2017/8/5.
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/getUsers")
    public List<UserDTO> getUsers() {
        List<UserDTO> users=userService.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public UserDTO getUser(Long id) {
        UserDTO user=userService.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserDTO user) {
        userService.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserDTO user) {
        userService.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}

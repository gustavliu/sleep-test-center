package com.gustav.service.impl;

import com.gustav.dao.UserDao;
import com.gustav.entity.UserDTO;
import com.gustav.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gustav on 2017/8/5.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserDao userDao;

    @Override
    public List<UserDTO> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserDTO getOne(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public void insert(UserDTO user) {
        userDao.insert(user);
    }

    @Override
    public void update(UserDTO user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}

package com.gustav.dao.impl;

import com.gustav.dao.UserDao;
import com.gustav.entity.UserDTO;
import com.gustav.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gustav on 2017/8/5.
 */
@Component
public class UserDaoImpl implements UserDao{

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserDTO> getAll() {
        return userMapper.getAll();
    }

    @Override
    public UserDTO getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public void insert(UserDTO user) {
        userMapper.insert(user);
    }

    @Override
    public void update(UserDTO user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }
}

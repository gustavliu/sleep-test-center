package com.gustav.mapper;

import com.gustav.entity.UserDTO;

import java.util.List;

/**
 * Created by gustav on 2017/8/5.
 */
public interface UserMapper {

    List<UserDTO> getAll();

    UserDTO getOne(Long id);

    void insert(UserDTO user);

    void update(UserDTO user);

    void delete(Long id);
}

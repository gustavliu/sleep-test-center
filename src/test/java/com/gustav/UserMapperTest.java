package com.gustav;

import com.gustav.entity.UserDTO;
import com.gustav.enums.UserSexEnum;
import com.gustav.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gustav on 2017/8/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    UserMapper userMapper;


    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new UserDTO("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new UserDTO("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new UserDTO("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<UserDTO> users = userMapper.getAll();
        if(users==null || users.size()==0){
            System.out.println("is null");
        }else{
            System.out.println(users.toString());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        UserDTO user = userMapper.getOne(6l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
        Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
    }
}

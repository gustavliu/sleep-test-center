package com.gustav.tcp;

import com.gustav.entity.UserDTO;
import com.gustav.enums.UserSexEnum;
import com.gustav.mapper.UserMapper;
import com.gustav.service.UserService;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.SocketAddress;

/**
 * Created by gustav on 2017/8/6.
 */
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    UserService userService;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(body);
        userDTO.setPassWord("fewf");
        userDTO.setUserName("fwef");
        userDTO.setUserSex(UserSexEnum.MAN);
        userService.insert(userDTO);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("connect");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

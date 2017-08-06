package com.gustav.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * Created by gustav on 2017/8/6.
 */
@Component
public class NettyServer implements ServletContextAware {

    private ServletContext servletContext;

    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    @Autowired
    ServerHandler serverHandler;


    @Value("${netty.server.host}")
    String host;

    @Value("${netty.server.port}")
    int port;

    @Value("${netty.server.backlog}")
    int backlog;

    @Value("${netty.server.ioThreadNum}")
    int ioThreadNum;

    //private Map<String, Object> exportServiceMap = new HashMap<String, Object>();
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @PostConstruct
    public void start() throws InterruptedException{
        logger.info("begin to start rpc server");
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(ioThreadNum);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, backlog)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());//拆包粘包定义结束字符串（第一种解决方案）
                        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));//在管道中加入结束字符串
                        //  sc.pipeline().addLast(new FixedLengthFrameDecoder(200));第二种定长
                        socketChannel.pipeline().addLast(new StringDecoder());//定义接收类型为字符串把ByteBuf转成String
                        socketChannel.pipeline().addLast(serverHandler);//在这里配置具体数据接收方法的处理
                    }
                });
        channel = serverBootstrap.bind(host,port).sync().channel();
        logger.info("NettyRPC server listening on port " + port + " and ready for connections...");
    }

    @PreDestroy
    public void stop() {
        logger.info("destroy server resources");
        if (null == channel) {
            logger.error("server channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

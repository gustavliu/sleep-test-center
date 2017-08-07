package com.gustav;

import com.gustav.controller.WebsocketController;
import com.gustav.model.Greeting;
import com.gustav.model.HelloMessage;
import org.influxdb.dto.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by gustav on 2017/8/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfluxDBTest {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private WebsocketController controller;

    @Test
    //@SendTo("/topic/greetings")
    public void influxdbTest() throws InterruptedException {
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement("dieesk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        influxDBTemplate.write(p);
        //return new Greeting("Hello, " + p.toString() + "!");


    }

}

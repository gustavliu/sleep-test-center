package com.gustav;

import org.influxdb.dto.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
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

    @Test
    public void influxdbTest(){
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        influxDBTemplate.write(p);
    }
}

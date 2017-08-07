package com.gustav.model;

import lombok.Data;

/**
 * Created by gustav on 2017/8/7.
 */
@Data
public class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }
}

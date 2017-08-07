package com.gustav.model;

import lombok.Data;

/**
 * Created by gustav on 2017/8/7.
 */
@Data
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }
}

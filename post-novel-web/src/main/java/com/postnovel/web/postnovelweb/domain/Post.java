package com.postnovel.web.postnovelweb.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post() { }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}

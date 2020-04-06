package com.postnovel.service.postnovelservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Post {
    public String userId;
    public String id;
    public String title;
    public String body;
}

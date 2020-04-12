package com.postnovel.web.postnovelweb.client;

import com.postnovel.web.postnovelweb.domain.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;

@Service
public class PostNovelService {
    private Integer port;
    private String host;
    private String scheme;

    public PostNovelService(
            @Value("${postnovel.client.port}") Integer port,
            @Value("${postnovel.client.host}") String host,
            @Value("${postnovel.client.scheme}") String scheme) {
        this.port = port;
        this.host = host;
        this.scheme = scheme;
    }

    public Post getPostById(int id) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path(format("/posts/%s", id))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Post.class);
    }
}

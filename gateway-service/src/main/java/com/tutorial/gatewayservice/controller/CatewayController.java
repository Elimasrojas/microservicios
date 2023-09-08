package com.tutorial.gatewayservice.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
public class CatewayController {

    //tiene que ver con la programacion reactiva webFlux
    @GetMapping("/")
    public Mono<String> index(WebSession webSession){
        return Mono.just(webSession.getId());
    }

    @GetMapping("/token")
    public Mono<String> getToken(@RegisteredOAuth2AuthorizedClient
                                     OAuth2AuthorizedClient client){
        return Mono.just(client.getAccessToken().getTokenValue());
    }
}

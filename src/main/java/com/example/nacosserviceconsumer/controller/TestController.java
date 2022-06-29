package com.example.nacosserviceconsumer.controller;

import com.example.nacosserviceconsumer.service.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final RestTemplate restTemplate;
    private final EchoService echoService;

    public TestController(RestTemplate restTemplate, EchoService echoService) {
        this.restTemplate = restTemplate;
        this.echoService = echoService;
    }

    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable String str) {
        return restTemplate.getForObject("http://service-provider/echo/" + str,
                String.class);
    }

    @GetMapping("/echo-feign/{str}")
    public String feign(@PathVariable String str) {
        return echoService.echo(str);
    }

}

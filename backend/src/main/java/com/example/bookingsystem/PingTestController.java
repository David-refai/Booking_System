package com.example.bookingsystem;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingTestController {

    private static  int counter = 0;

    record PingPongResponse(String response) {}

    @GetMapping("/ping")
    public PingPongResponse ping() {
        return new PingPongResponse("Pong: %s".formatted(counter++));
    }


    @GetMapping("/ping2")
    public String ping2() {
        return "pot2";
    }






}

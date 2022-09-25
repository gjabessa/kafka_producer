package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@EnableScheduling
@Service
public class UserService {

    String[] longitudeAndLatitudes = new String[]{"42.536457 -70.985786","42.328674 -72.664658","43.403500 -94.843323","43.680031 -70.310425"};
    @Autowired
    private KafkaTemplate kafkaTemplate;


    @Scheduled(fixedDelay = 15000)
    void produceUserRequests(){
        System.out.println("new user request");
        String msg = Math.random()*10 + " "+longitudeAndLatitudes[new Random().nextInt(longitudeAndLatitudes.length)]+" "
                + "user";
        kafkaTemplate.send("myTopic",msg);
    }

    @Scheduled(fixedDelay = 20000)
    void produceDriverRequests(){
        System.out.println("new driver request");
        String msg = Math.random()*10 + " "+longitudeAndLatitudes[new Random().nextInt(longitudeAndLatitudes.length)]+ " driver";
        kafkaTemplate.send("myTopic",msg);
    }
}

package com.redhat.ttsaas;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.redhat.ttsaas.model.TextToSynthesize;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<TextToSynthesize> textCache() {
        return new LinkedList<TextToSynthesize>();
    }
}

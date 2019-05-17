package com.jpasample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Bootによるアプリケーションを起動するための処理です。
 *
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
      SpringApplication.run(App.class, args);
    }
}

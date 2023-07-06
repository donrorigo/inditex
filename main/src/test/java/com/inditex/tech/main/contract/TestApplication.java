package com.inditex.tech.main.contract;

import com.inditex.tech.MsProductApplication;
import org.springframework.boot.SpringApplication;

public class TestApplication {

  public static void main(String[] args) {
    SpringApplication
        .from(MsProductApplication::main) //Application is main entrypoint class
        .with(TestContainersConfiguration.class)
        .run(args);
  }

}

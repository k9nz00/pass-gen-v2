package com.semka.pets.pass_gen_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class PassGenV2Application {

    public static void main(String[] args) {
        SpringApplication.run(PassGenV2Application.class, args);
    }
}

package org.firewall;

import org.firewall.core.DPIEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.firewall")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        DPIEngine.start();
    }
}

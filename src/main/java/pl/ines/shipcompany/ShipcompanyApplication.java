package pl.ines.shipcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class ShipcompanyApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShipcompanyApplication.class, args);
    }
}

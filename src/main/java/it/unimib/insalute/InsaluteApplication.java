package it.unimib.insalute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class used to build the spring boot application.
 */
@SpringBootApplication
public class InsaluteApplication {

    /**
     * Starter of the application.
     * @param args parameter passed to the main
     */
    public static void main(String[] args) {
        SpringApplication inSalute = new SpringApplication(InsaluteApplication.class);
        inSalute.setAdditionalProfiles("production");
        inSalute.run(args);
    }

}

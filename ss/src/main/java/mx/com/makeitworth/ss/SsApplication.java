package mx.com.makeitworth.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "mx.com.makeitworth.ss")
@EnableJpaRepositories(basePackages = "mx.com.makeitworth.ss.repositories")
public class SsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsApplication.class, args);
    }

}

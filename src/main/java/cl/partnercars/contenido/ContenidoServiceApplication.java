package cl.partnercars.contenido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class ContenidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContenidoServiceApplication.class, args);
    }
}

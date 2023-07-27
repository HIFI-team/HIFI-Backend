package Backend.HIFI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HifiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HifiApplication.class, args);
	}

}

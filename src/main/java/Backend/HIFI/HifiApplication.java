package Backend.HIFI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //jpa entity 자동 감시
public class HifiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HifiApplication.class, args);
	}

}

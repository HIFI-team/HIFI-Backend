package Backend.HIFI;

import Backend.HIFI.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HifiApplicationTests {

	@Autowired
    UserRepository userRepository;

	@Test
	void contextLoads() {
	}

}

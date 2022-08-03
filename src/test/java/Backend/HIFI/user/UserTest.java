package Backend.HIFI.user;

import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class UserTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    FollowService followService;

    @Test
    public void followTest() throws Exception {

        User user1 = User.builder()
                .email("ms")
                .password("test")
                .build();
        userRepository.saveAndFlush(user1);

        User user2 = User.builder()
                .email("test")
                .password("test")
                .build();
        userRepository.saveAndFlush(user2);

        followService.following(user1, user2);

    }

    @Test
    public void DBTest() throws Exception {
        for (User user : userService.findByEmail("ms").getFollowingList()) {
            System.out.println(user.getEmail());
        }
    }
}
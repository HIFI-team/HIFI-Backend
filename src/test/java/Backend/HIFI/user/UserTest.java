package Backend.HIFI.user;

import Backend.HIFI.domain.user.User;
import Backend.HIFI.domain.user.UserRepository;
import Backend.HIFI.domain.user.UserService;
import Backend.HIFI.domain.user.follow.FollowRepository;
import Backend.HIFI.domain.user.follow.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
                .email("A")
                .password("test")
                .anonymous(true)
                .build();
        userRepository.saveAndFlush(user1);

        User user2 = User.builder()
                .email("B")
                .password("test")
                .build();
        userRepository.saveAndFlush(user2);

        User user3 = User.builder()
                .email("C")
                .password("test")
                .build();
        userRepository.saveAndFlush(user3);

        followService.following(user1, user2);
        followService.following(user2, user1);

        System.out.println(userService.canWatchReview(user1, user2));
        System.out.println(userService.canWatchReview(user1, user3));
        System.out.println(userService.canWatchReview(user3, user1));

    }


    @Test
    public void SearchTest() throws Exception {
        User user1 = User.builder()
                .email("ms")
                .password("test")
                .build();
        userRepository.saveAndFlush(user1);

        User user2 = User.builder()
                .email("sm")
                .password("test")
                .build();
        userRepository.saveAndFlush(user2);

        User user3 = User.builder()
                .email("gm")
                .password("test")
                .build();
        userRepository.saveAndFlush(user3);

        userService.userSearch(user1, "test");
        userService.userSearch(user2, "test");
    }

    @Test
    public void searchTest() throws Exception {
        User user1 = User.builder()
                .email("a")
                .password("abc")
                .name("ms")
                .build();
        userRepository.saveAndFlush(user1);

        User user2 = User.builder()
                .email("b")
                .password("abc")
                .name("kms")
                .build();
        userRepository.saveAndFlush(user2);



    }
}
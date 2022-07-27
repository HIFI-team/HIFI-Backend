package Backend.HIFI.user;

import Backend.HIFI.user.follow.FollowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired
    FollowRepository followRepository;

    @Test
    public void followTest() throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HIFI");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        User sm = User.signUp("jsm6616","police");
        em.persist(sm);

        User ms = User.signUp("phanre", "don't know");
        em.persist(ms);

        User gm = User.signUp("gangmin2", "bwe");
        em.persist(gm);

        tx.commit();

        userService.userFollow(sm, gm);
        emf.close();


    }
}
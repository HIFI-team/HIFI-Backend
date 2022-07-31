//package Backend.HIFI.domain;
//
//import Backend.HIFI.user.follow.Follow;
//import Backend.HIFI.user.User;
//import Backend.HIFI.user.follow.FollowRepository;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//@SpringBootTest
//@Transactional
//public class UserTest {
//
//    private FollowRepository followRepository;
//    private FollowService followService;
//
//
//    @Test
//    public void followTest(){
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HIFI");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//        try {
//            User sm = User.signUp("jsm6616","police");
//            em.persist(sm);
//
//            User ms = User.signUp("phanre", "don't know");
//            em.persist(ms);
//
//            User gm = User.signUp("gangmin", "bwe");
//            em.persist(gm);
//
//            followService.save(sm.getId(), gm.getId());
//            em.persist(new Follow(sm, gm));
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//        emf.close();
//
//
//    }
//}
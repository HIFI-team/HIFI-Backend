package Backend.HIFI.domain;

import org.assertj.core.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class UserTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HIFI");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            User user = User.signUp("jsm6616","police");
            em.persist(user);

            User user1 = User.signUp("phanre", "police");
            em.persist(user1);

            user.addFollowing(user1);
            user1.addFollower(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();


    }
}
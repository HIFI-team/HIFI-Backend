package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@RequiredArgsConstructor
public class FollowRepository {

    private final EntityManager em;

    public void save(User follower, User following) {
        em.persist(new Follow(follower, following));
    }

}

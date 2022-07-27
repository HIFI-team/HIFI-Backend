package Backend.HIFI.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository{

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findUser(int id) { return em.find(User.class, id);}

}
package Backend.HIFI.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
    User findUserById(int id);


//    @Modifying
//    @Query(value = "INSERT INTO FOLLOWING(FOLLOWER, FOLLOWING) VALUES(:user1, :user2)", nativeQuery = true)
//    int follow(int user1, int user2);
}
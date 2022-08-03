package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager em;
    //리뷰 등록
    public void save(Review review){
        em.persist(review);
    }
    /**
     * QueryDsl 사용 필요성 확인중
     * */
    //리뷰 조회 - userID
    public List<Review> findByUser(User user){
        return em.createQuery("select r from Review r where r.user=:user",Review.class)
                .setParameter("user",user)
                .getResultList();
    }
    //리뷰 조회 - storeID
    public List<Review> findByStore(Store store){
        return em.createQuery("select r from Review r where r.store=:store",Review.class)
                .setParameter("store",store)
                .getResultList();
    }
    //리뷰 조회
    public List<Review> findAll(){
        return em.createQuery("select r from Review r",Review.class)
                .getResultList();
    }

}

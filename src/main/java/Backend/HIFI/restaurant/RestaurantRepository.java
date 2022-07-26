package Backend.HIFI.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {
    private final EntityManager em;

    public void save(Restaurant restaurant){
        em.persist(restaurant);
    }

    public Restaurant findOne(Long id){
        return em.find(Restaurant.class,id);
    }

    public List<Restaurant> findByName(String name){
        return em.createQuery("select r from Restaurant r where  r.name=:name",Restaurant.class)
                .setParameter("name",name)
                .getResultList();
    }


}

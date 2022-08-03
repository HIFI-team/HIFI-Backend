package Backend.HIFI.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;

    public void save(Store store){
        em.persist(store);
    }

    public Store findById(Long id){
        return em.find(Store.class,id);
    }

    public List<Store> findAll(){
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }

    public List<Store> findByName(String place_name){
        return em.createQuery("select s from Store s where  s.place_name=:place_name",Store.class)
                .setParameter("place_name",place_name)
                .getResultList();
    }
}

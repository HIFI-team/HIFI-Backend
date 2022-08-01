package Backend.HIFI.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public void registration(Store store){
        storeRepository.save(store);
    }

    public List<Store> findStores(){
        return storeRepository.findAll();
    }
}

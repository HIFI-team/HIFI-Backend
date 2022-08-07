package Backend.HIFI.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public void registration(Store store){
        storeRepository.save(store);
    }
    public Store findOneStore(Long id){
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));
        return store;
    }
    public List<Store> findStores(){
        return storeRepository.findAll();
    }
}

package Backend.HIFI.store;

import Backend.HIFI.common.DeleteStatus;
import Backend.HIFI.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    /**
     * 스토어 등록
     * */
    @Transactional
    public void registration(Store store){
        storeRepository.save(store);
    }
    /**
     * 스토어 찾기
     * */
    public Store findOneStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));
        //delete store 제외 필요함
        return store;
    }
    public List<Store> findStores(){
        return storeRepository.findAll();
    }
    /**
     * 스토어 삭제
     * */
    @Transactional
    public void deleteStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));
        store.changeDeleteStatus();
        store.updateReviews();
    }
}

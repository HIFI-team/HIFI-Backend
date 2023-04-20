package Backend.HIFI.domain.store.service;

import Backend.HIFI.domain.store.dto.request.PostStoreDto;
import Backend.HIFI.domain.store.dto.response.GetStoreDto;

import java.util.List;

public interface StoreService {
    GetStoreDto createStore(PostStoreDto postStoreDto, String userId);

    GetStoreDto getStore(Long storeId);

    List<GetStoreDto> getStores();

    void deleteStore(Long storeId, String userId);
}

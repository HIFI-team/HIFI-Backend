package Backend.HIFI.domain.store.service;

import Backend.HIFI.domain.store.dto.request.PostStoreDto;
import Backend.HIFI.domain.store.dto.response.GetStoreDto;
import Backend.HIFI.domain.store.entity.Category;

import java.util.List;

public interface StoreService {
    GetStoreDto createStore(PostStoreDto postStoreDto, String userId);

    GetStoreDto getStore(Long storeId);

    List<GetStoreDto> getStores();

    List<GetStoreDto> getStoresByType(Category category);

    void deleteStore(Long storeId, String userId);
}

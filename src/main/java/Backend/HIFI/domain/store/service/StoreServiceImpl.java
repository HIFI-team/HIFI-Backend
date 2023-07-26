package Backend.HIFI.domain.store.service;

import Backend.HIFI.domain.store.dto.request.PostStoreDto;
import Backend.HIFI.domain.store.dto.response.GetStoreDto;
import Backend.HIFI.domain.store.entity.Category;
import Backend.HIFI.domain.store.repository.StoreRepository;
import Backend.HIFI.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    /**
     * 스토어 등록
     */
    @Transactional
    public GetStoreDto createStore(PostStoreDto postStoreDto, String userId) {
        Store store = Store.builder()
                .name(postStoreDto.getName())
                .address(postStoreDto.getAddress())
                .category(postStoreDto.getCategory())
                .description(postStoreDto.getDescription())
                .latitude(postStoreDto.getLatitude())
                .longitude(postStoreDto.getLongitude())
                .build();
        Store saveStore = storeRepository.save(store);

        return GetStoreDto.toDto(saveStore);
    }

    /**
     * 스토어 찾기
     */
    public GetStoreDto getStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));

        return GetStoreDto.toDto(store);
    }

    public List<GetStoreDto> getStores() {
        List<GetStoreDto> getStoreDtos = new ArrayList<>();
        for (Store store : storeRepository.findAll()) {
            GetStoreDto getStoreDto = GetStoreDto.toDto(store);
            getStoreDtos.add(getStoreDto);
        }
        return getStoreDtos;
    }

    @Override
    public List<GetStoreDto> getStoresByType(Category category) {
        List<GetStoreDto> getStoreDtos = new ArrayList<>();
        for (Store store : storeRepository.findAllByCategory(category)) {
            GetStoreDto getStoreDto = GetStoreDto.toDto(store);
            getStoreDtos.add(getStoreDto);
        }
        return getStoreDtos;
    }

    /**
     * 스토어 삭제
     */
    @Transactional
    public void deleteStore(Long storeId, String userId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 스토어 입니다"));
        store.updateIsDeleted();
    }
}

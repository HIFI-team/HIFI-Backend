package Backend.HIFI.store;

import Backend.HIFI.store.dto.StoreRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StoreServiceTest {
    Logger log = (Logger) LoggerFactory.getLogger(StoreServiceTest.class);

    @Autowired StoreRepository storeRepository;
    @Autowired StoreService storeService;
    @Autowired ModelMapper mapper;

    @Test
    public void 정보_확인() throws Exception{
        //given
        Store store = Store.builder()
                .address_name("서울 마포구 서교동 360-22")
                .name("aaaa")
                .uid("aa")
                .categoryCode(StoreCategoryCode.cafe)
                .build();
        storeRepository.save(store);
        //when
        System.out.println("왜 또");
        StoreRequestDto dto = mapper.map(store, StoreRequestDto.class);
        System.out.println(dto);
        //then
        List<Store> stores = storeService.getStores();


    }

}
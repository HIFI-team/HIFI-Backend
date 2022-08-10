package Backend.HIFI.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StoreServiceTest {
    Logger log = (Logger) LoggerFactory.getLogger(StoreServiceTest.class);

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @Test
    public void 정보_확인() throws Exception{
        //given
        List<Store> stores = storeService.getStores();
        //when

        //then
        assertNotNull(stores);
        System.out.println("what?"+stores);

    }

}
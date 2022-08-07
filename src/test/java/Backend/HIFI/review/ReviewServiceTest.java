package Backend.HIFI.review;

import Backend.HIFI.store.Store;
import Backend.HIFI.store.StoreRepository;
import Backend.HIFI.store.StoreService;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class ReviewServiceTest {
    @Autowired UserRepository userRepository;
    @Autowired StoreService storeService;
    @Autowired ReviewService reviewService;
    @Autowired
    StoreRepository storeRepository;

    @Test
    public void 리뷰_삭제 () throws Exception{
        //given
        User user = User.builder()
                .email("police")
                .password("test")
                .build();
        userRepository.save(user);
        Store store = Store.builder()
                .address_name("서울 마포구 서교동 360-22")
                .place_name("aaaa")
                .place_uid("aa")
                .build();
        storeRepository.save(store);
        System.out.println(store.getId());
        reviewService.review(user.getId(),store.getId(),"Test");

        System.out.println(store.getReviews());
        reviewService.deleteReview(1L);
        System.out.println(store.getReviews());
        //when

        //then

    }
}
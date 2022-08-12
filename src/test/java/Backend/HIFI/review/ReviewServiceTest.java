package Backend.HIFI.review;

import Backend.HIFI.review.repository.ReviewRepository;
import Backend.HIFI.store.Store;
import Backend.HIFI.store.StoreRepository;
import Backend.HIFI.store.StoreService;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class ReviewServiceTest {
    @Autowired UserRepository userRepository;
    @Autowired StoreService storeService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired ReviewService reviewService;
    @Autowired StoreRepository storeRepository;

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
                .name("aaaa")
                .uid("aa")
                .build();
        storeRepository.save(store);
        Review review1=Review.builder()
                .user(user)
                .store(store)
                .content("hi")
                .build();
        reviewRepository.save(review1);
        Review review2=Review.builder()
                .user(user)
                .store(store)
                .content("bye")
                .build();
        reviewRepository.save(review2);
//        review2.changeDeleteStatus();
        System.out.println(review2.getDelStatus());

        List<Review> reviews = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        for (Review review:reviews) {
            System.out.println(review.getContent()+review.getCreatedAt()+review.getDelStatus());
        }
        //when

        //then

    }
}
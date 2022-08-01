package Backend.HIFI.restaurant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Backend.HIFI.restaurant.RestaurantCategoryCode.restaurant;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RestaurantServiceTest {
    Logger log = (Logger) LoggerFactory.getLogger(RestaurantServiceTest.class);

    @Autowired RestaurantRepository restaurantRepository;
    @Autowired RestaurantService restaurantService;

    @Test
    public void 가게_정보_등록() throws Exception{
        //given
        Restaurant restaurant1 = Restaurant.createRestaurant("서울 마포구 서교동 360-22",restaurant,"예티","13284457");
        //when
        restaurantService.registration(restaurant1);
        //then

    }

    @Test
    public void 정보_확인() throws Exception{
        //given
        List<Restaurant> stores = restaurantService.findStores();
        //when

        //then
        assertNotNull(stores);
        System.out.println("what?"+stores);

    }

}
package Backend.HIFI.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registration(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findStores(){
        return restaurantRepository.findAll();
    }
}

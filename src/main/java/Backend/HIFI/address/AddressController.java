package Backend.HIFI.address;

import Backend.HIFI.restaurant.RestaurantRepository;
import Backend.HIFI.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final RestaurantService restaurantService;
    @GetMapping
    public String address(Model model){
        model.addAttribute("stores",restaurantService.findStores());
        return "address";
    }
}

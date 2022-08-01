package Backend.HIFI.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @GetMapping
    public String address(Model model){
        model.addAttribute("stores", storeService.findStores());
        return "store";
    }
}

package Backend.HIFI.domain.store.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    RESTAURANT("음식점"),
    CAFE("카페"),
    BAKERY("베이커리"),
    PUB("주점"),
    BAR("바"),
    ETC("기타");

    private String viewName;

    @JsonCreator
    public static Category fromDto(String viewName) {
        for (Category category : Category.values()) {
            if (category.getViewName().equals(viewName)) {
                return category;
            }
        }
        return null;
    }
}

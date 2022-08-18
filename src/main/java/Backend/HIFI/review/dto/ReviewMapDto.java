package Backend.HIFI.review.dto;

import Backend.HIFI.auth.dto.UserMapDto;
import Backend.HIFI.store.dto.StoreMapDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMapDto {
    private Long id;
    private UserMapDto user;
    private StoreMapDto store;
}

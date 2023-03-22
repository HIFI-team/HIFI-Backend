package Backend.HIFI.domain.review.dto;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.store.dto.StoreMapDto;
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

package Backend.HIFI.domain.review.dto;

import Backend.HIFI.domain.auth.dto.UserMapDto;
import Backend.HIFI.domain.store.dto.StoreMapDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private String content;
    private UserMapDto user;
    private StoreMapDto store;
    private LocalDateTime createdAt;
    private int grade;
}

package Backend.HIFI.review.dto;

import Backend.HIFI.auth.dto.UserMapDto;
import Backend.HIFI.store.Store;
import Backend.HIFI.store.dto.StoreMapDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String content;
    private UserMapDto user;
    private StoreMapDto store;
    private LocalDateTime createdAt;
    private int grade;
}

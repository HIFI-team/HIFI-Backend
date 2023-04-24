package Backend.HIFI.domain.follow.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestDto {

    private Long follwerId;
    private Long followingId;

    public FollowRequestDto of(Long follwerId, Long followingId) {
        return FollowRequestDto.builder()
                .follwerId(follwerId)
                .followingId(followingId)
                .build();
    }
}
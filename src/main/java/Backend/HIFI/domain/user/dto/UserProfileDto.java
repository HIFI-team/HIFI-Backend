package Backend.HIFI.domain.user.dto;

import Backend.HIFI.domain.user.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String name;
    private String description;
    private String image;
    private Boolean anonymous;
    private Boolean followed;

    private int follower;
    private int following;

    public UserProfileDto of(UserProfile userProfile) {
        // 비공개 유저 처리 해야함
        // 얼마나 비공개 할 것인지?
        return UserProfileDto.builder()
                .name(userProfile.getName())
                .description(userProfile.getDescription())
                .image(userProfile.getImage())
                .anonymous(userProfile.getAnonymous())
                .build();
    }
}

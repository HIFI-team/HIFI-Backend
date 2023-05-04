package Backend.HIFI.domain.user.dto;

import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private Long userId;

    private String name;
    private String description;
    private String image;
    private Boolean anonymous;
    private Boolean followed;

    private int follower;
    private int following;

    // TODO 초기화 필요?
    private List<UserProfile> followerList;
    private List<UserProfile> followingList;
    private List<Review> reviewList;

    public UserProfileDto of(UserProfile userProfile) {
        // 비공개 유저 처리 해야함
        // 얼마나 비공개 할 것인지?
        return UserProfileDto.builder()
                .userId(userProfile.getUserId())
                .name(userProfile.getName())
                .description(userProfile.getDescription())
                .image(userProfile.getImage())
                .anonymous(userProfile.getAnonymous())
                .build();
    }

    public UserProfile toUserProfile() {
        return UserProfile.builder()
                .userId(userId)
                .name(name)
                .description(description)
                .image(image)
                .anonymous(anonymous)
                .build();
    }
}

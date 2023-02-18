package Backend.HIFI.user.dto;

import Backend.HIFI.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String email;
    private String name;
    private String description;
    private String image;
    private Boolean anonymous;
    private Boolean followed;

    public UserProfileDto of(User user) {
        // 비공개 유저 처리 해야함
        // 얼마나 비공개 할 것인지?
        return UserProfileDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .description(user.getDescription())
                .image(user.getImage())
                .anonymous(user.getAnonymous())
                .build();
    }
}

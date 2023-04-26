package Backend.HIFI.domain.user.dto.request;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {

    private String name;
    private String description;
    private String image;
    private Boolean anonymous;

//    public UserProfileUpdateDto of(UserProfileUpdateDto userProfileUpdateDto) {
//
//    }
}

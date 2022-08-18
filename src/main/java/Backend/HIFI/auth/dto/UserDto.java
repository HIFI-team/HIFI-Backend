package Backend.HIFI.auth.dto;

import Backend.HIFI.review.Review;
import Backend.HIFI.user.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserDto {

    private String email;
    private String name;
    private String description;
    private String image;
    private Boolean anonymous;

//    private int follower;
//    private int following;

    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .description(user.getDescription())
                .image(user.getImage())
                .anonymous(user.getAnonymous())
                .reviewList(user.getReviewList())
                .build();
    }

}

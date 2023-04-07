//package Backend.HIFI.domain.user.dto;
//
//import Backend.HIFI.domain.user.entity.User;
//import Backend.HIFI.domain.review.Review;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//public class UserDto {
//
//    private String email;
//    private String name;
//    private String description;
//    private String image;
//    private Boolean anonymous;
//
//    private int follower;
//    private int following;
//
//    @Builder.Default
//    private List<Review> reviewList = new ArrayList<>();
//
//    public UserDto of(User user) {
//        return UserDto.builder()
//                .email(user.getEmail())
//                .name(user.getName())
//                .description(user.getDescription())
//                .image(user.getImage())
//                .anonymous(user.getAnonymous())
//                .reviewList(user.getReviewList())
//                .build();
//    }
//
//}

// TODO 삭제하고 UserProfileDto 사용해도 될 듯

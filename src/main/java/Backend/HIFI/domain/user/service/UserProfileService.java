package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile toUserProfile(User user);

    UserProfile findUserProfileByEmail(String email);

    UserProfile findUserProfileByUserId(Long userId);

    void updateProfile(String userId, UserProfileDto userProfileDto);

    UserProfileDto getMyProfilePage(String userId);

    boolean isFollowed(UserProfile fromUser, UserProfile toUser);

    boolean isFollowForFollowed(UserProfile user1, UserProfile user2);

    boolean canWatchReview(UserProfile fromUser, UserProfile toUser);

    UserProfileDto getProfilePage(String userId, String email);

    void userSearch(UserProfile userProfile, String searchName);

    List<UserProfileDto> searchAllUserProfile(String userId);

    List<UserProfileDto> searchUserByName(String userId, SearchDto searchDto);

    List<Review> getMyReviewList(String userId);

    List<Review> getOtherReviewList(String userId, UserProfileDto userProfileDto);
}

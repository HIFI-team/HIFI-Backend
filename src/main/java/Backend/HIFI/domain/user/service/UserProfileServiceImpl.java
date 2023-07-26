package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.follow.entity.Follow;
import Backend.HIFI.domain.follow.repository.FollowRepository;
import Backend.HIFI.domain.follow.service.FollowServiceImpl;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.review.repository.ReviewRepository;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.Search;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    private final UserServiceImpl userServiceImpl;
    private final FollowRepository followRepository;
    private final FollowServiceImpl followServiceImpl;
    private final ReviewRepository reviewRepository;


    @Override
    public UserProfile toUserProfile(User user) {
        return findUserProfileByUserId(user.getId());
    }

    @Override
    public UserProfile findUserProfileByEmail(String email) {
        User user = userServiceImpl.findByEmail(email);
        UserProfile userProfile = toUserProfile(user);
        return userProfile;
    }

    @Override
    public UserProfile findUserProfileByUserId(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        return userProfile;
    }

    @Override
    public void updateProfile(String userId, UserProfileDto userProfileDto) {
        User user = userServiceImpl.findByEmail(userId);
        UserProfile userProfile = toUserProfile(user);
        userProfile.update(userProfileDto);

        // throw exception 추가할 것
        // TODO Save면 되나?;
        userProfileRepository.save(userProfile);

    }

    @Override
    public UserProfileDto getMyProfilePage(String userId) {
        UserProfile userProfile = findUserProfileByEmail(userId);
        UserProfileDto userProfileDto = new UserProfileDto().of(userProfile);


        // TODO 0407 getFollower 로직 변경 필요
        userProfileDto.setFollower(followServiceImpl.getFollower(userProfile).size());
        userProfileDto.setFollowing(followServiceImpl.getFollowing(userProfile).size());

        return userProfileDto;
    }

    @Override
    public boolean isFollowed(UserProfile fromUser, UserProfile toUser) {
        Long followerId = fromUser.getUserId();
        Long followingId = toUser.getUserId();
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingId(followerId, followingId);
        return follow != null;
    }

    /** 맞팔 확인 */
    @Override
    public boolean isFollowForFollowed(UserProfile user1, UserProfile user2) {
        return isFollowed(user1, user2) && isFollowed(user2, user1);
    }

    @Override
    public boolean canWatchReview(UserProfile fromUser, UserProfile toUser) {
        return isFollowForFollowed(fromUser, toUser) || !toUser.getAnonymous();
    }

    @Override
    public UserProfileDto getProfilePage(String userId, String email) {
        UserProfile toUserProfile = findUserProfileByEmail(email);
        UserProfile fromUserProfile = findUserProfileByEmail(userId);

        UserProfileDto userProfileDto = new UserProfileDto().of(toUserProfile);

        // TODO Review 로직 변경 필요
//        if (!canWatchReview(fromUserProfile, toUserProfile)) {
//            UserProfileDto.setReviewList(null);
//        }

        return userProfileDto;
    }


    /** 유저 검색 리스트에 추가 */
    @Override
    public void userSearch(UserProfile userProfile, String searchName) {
        Search search = new Search();
        search.setName(searchName);
        search.setUserProfile(userProfile);
        userProfile.getSearchList().add(search);
    }


    /** 모든 유저 return */
    @Override
    public List<UserProfileDto> searchAllUserProfile(String userId) {
        UserProfile fromUserProfile = findUserProfileByEmail(userId);
        List<UserProfile> userList = userProfileRepository.findAll();
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (UserProfile toUserProfile : userList) {
            if (fromUserProfile == toUserProfile)
                continue;
            UserProfileDto upd = new UserProfileDto().of(toUserProfile);
            upd.setFollowed(isFollowed(fromUserProfile, toUserProfile));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }

    // TODO Follow 변경 필요
    /** user.name 통한 유저 검색 */
    @Override
    public List<UserProfileDto> searchUserByName(String userId, SearchDto searchDto) {
        UserProfile fromUser = findUserProfileByEmail(userId);
        String name = searchDto.getName();
        // 유저 검색 리스트에 추가
        userSearch(fromUser, name);

        List<UserProfile> userProfileList = userProfileRepository.findUserProfileListByName(name)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 유저가 없습니다."));
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (UserProfile toUser : userProfileList) {
            UserProfileDto upd = new UserProfileDto().of(toUser);
            upd.setFollowed(isFollowed(fromUser, toUser));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }




    @Override
    public List<Review> getMyReviewList(String userId) {
        User user = userServiceImpl.findByEmail(userId);
        List<Review> reviewList = reviewRepository.findByUser(user);

        return reviewList;
    }
    // UserProfileDto followed 추가 <- 왜? 안해도 될듯

    @Override
    public List<Review> getOtherReviewList(String userId, UserProfileDto userProfileDto) {
        UserProfile fromUser = toUserProfile(userServiceImpl.findByEmail(userId));
        UserProfile toUser = userProfileDto.toUserProfile();
        List<Review> reviewList = null;
        if (canWatchReview(fromUser, toUser)) {
            // User 얻는 과정 바꾸면 좋을 것 같음
            User user = userServiceImpl.findById(toUser.getUserId());
            reviewList = reviewRepository.findByUser(user);
        }

        return reviewList;
    }
}

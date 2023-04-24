package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.follow.entity.Follow;
import Backend.HIFI.domain.follow.repository.FollowRepository;
import Backend.HIFI.domain.follow.service.FollowService;
import Backend.HIFI.domain.review.entity.Review;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.Search;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    private final UserService userService;
    private final FollowRepository followRepository;
    private final FollowService followService;


    public UserProfile toUserProfile(User user) {
        return findUserProfileById(user.getId());
    }

    public UserProfile findUserProfileByEmail(String email) {
        User user = userService.findUserByEmail(email);
        UserProfile userProfile = toUserProfile(user);
        return userProfile;
    }

    public UserProfile findUserProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        return userProfile;
    }

    public UserProfile findUserProfileByAuth(Authentication authentication) {
        User user = userService.findUserByAuth(authentication);
        UserProfile userProfile = toUserProfile(user);
//        UserProfile userProfile = userProfileRepository.findByEmail(authentication.getName())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        System.out.println(userProfile);
        return userProfile;
    }


    public void updateProfile(Authentication authentication, UserProfileDto userProfileDto) {

        User user = userService.findUserByAuth(authentication);
        UserProfile userProfile = toUserProfile(user);
        userProfile.update(userProfileDto);

        // throw exception 추가할 것
        // TODO Save면 되나?;
        userProfileRepository.save(userProfile);

    }

    public UserProfileDto getMyProfilePage(Authentication auth) {
        UserProfile userProfile = findUserProfileByAuth(auth);
        UserProfileDto userProfileDto = new UserProfileDto().of(userProfile);


        // TODO 0407 getFollower 로직 변경 필요
        userProfileDto.setFollower(followService.getFollower(userProfile).size());
        userProfileDto.setFollowing(followService.getFollowing(userProfile).size());

        return userProfileDto;
    }

    public boolean isFollowed(UserProfile fromUser, UserProfile toUser) {
        Long followerId = fromUser.getUserId();
        Long followingId = toUser.getUserId();
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingId(followerId, followingId);
        return follow != null;
    }

    /** 맞팔 확인 */
    public boolean isFollowForFollowed(UserProfile user1, UserProfile user2) {
        return isFollowed(user1, user2) && isFollowed(user2, user1);
    }

    public boolean canWatchReview(UserProfile fromUser, UserProfile toUser) {
        return isFollowForFollowed(fromUser, toUser) || !toUser.getAnonymous();
    }

    public UserProfileDto getProfilePage(Authentication auth, String email) {
        UserProfile toUserProfile = findUserProfileByEmail(email);
        UserProfile fromUserProfile = findUserProfileByAuth(auth);

        UserProfileDto userProfileDto = new UserProfileDto().of(toUserProfile);

        // TODO Review 로직 변경 필요
//        if (!canWatchReview(fromUserProfile, toUserProfile)) {
//            UserProfileDto.setReviewList(null);
//        }

        return userProfileDto;
    }


    /** 유저 검색 리스트에 추가 */
    public void userSearch(UserProfile userProfile, String searchName) {
        Search search = new Search();
        search.setName(searchName);
        search.setUserProfile(userProfile);
        userProfile.getSearchList().add(search);
    }


    /** 모든 유저 return */
    public List<UserProfileDto> searchAllUserProfile(Authentication auth) {
        UserProfile fromUserProfile = findUserProfileByAuth(auth);
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
    public List<UserProfileDto> searchUserByName(Authentication auth, SearchDto searchDto) {
        UserProfile fromUser = findUserProfileByAuth(auth);
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




    public List<Review> getReviewListFromUser(Authentication auth) {

        // TODO 고칠 것
        List<Review> reviewList = null;

//        User user = findByAuth(auth);
//        List<Review> reviewList = user.getReviewList();
//
//        // 리뷰 테스트
//        for (Review review : reviewList) {
//            System.out.println(review.getImage());
//        }

        return reviewList;
    }

    // UserProfileDto followed 추가 <- 왜? 안해도 될듯
}

package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.follow.service.FollowService;
import Backend.HIFI.domain.user.dto.SearchDto;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.Search;
import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserProfileRepository;
import Backend.HIFI.domain.user.repository.UserRepository;
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
    private final FollowService followService;


    public UserProfile findUserProfileByEmail(String email) {
        UserProfile userProfile = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
        return userProfile;
    }

    public UserProfile findUserProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        return userProfile;
    }

    public UserProfile findUserProfileByAuth(Authentication authentication) {
        System.out.println("^&^&^&^\n" + authentication.getName());
        UserProfile userProfile = userProfileRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        System.out.println(userProfile);
        return userProfile;
    }


    public void updateProfile(UserProfileDto userProfileDto) {
        // token 못받아와서 userProfileDto email 추가하여 이용
//        User userProfile = userService.findByAuth(auth);
        UserProfile userProfile = findUserProfileByEmail(userProfileDto.getEmail());
        userProfile.update(userProfileDto);


        // throw exception 추가할 것
        userProfileRepository.save(userProfile);
        System.out.println("***************\n"+userProfileDto);

    }

    public UserProfileDto getMyProfilePage(Authentication auth) {
        UserProfile userProfile = findUserProfileByAuth(auth);
        UserProfileDto userProfileDto = new UserProfileDto().of(userProfile);


        // TODO 0407 getFollower 로직 변경 필요
        UserProfileDto.setFollower(followService.getFollower(user).size());
        UserProfileDto.setFollowing(followService.getFollowing(user).size());

        return userProfileDto;
    }

    // TODO Follow 분리 후 처리 필요
    public UserProfileDto getProfilePage(Authentication auth, String email) {
        UserProfile toUserProfile = findUserProfileByEmail(email);
        UserProfile fromUserProfile = findUserProfileByAuth(auth);

        UserProfileDto userProfileDto = new UserProfileDto().of(toUserProfile);

        if (!userService.canWatchReview(fromUserProfile, toUserProfile)) {
            UserProfileDto.setReviewList(null);
        }

        return userProfileDto;
    }


    /** 유저 검색 리스트에 추가 */
    public void userSearch(UserProfile userProfile, String searchName) {
        Search search = new Search();
        search.setName(searchName);
        search.setUserProfile(userProfile);
        userProfile.getSearchList().add(search);
    }


    // TODO Follow 변경 필요
    /** 모든 유저 return */
    public List<UserProfileDto> searchAllUserProfile(Authentication auth) {
        UserProfile fromUserProfile = findUserProfileByAuth(auth);
        List<UserProfile> userList = userRepository.findAll();
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (UserProfile toUserProfile : userList) {
            if (fromUserProfile == toUserProfile)
                continue;
            UserProfileDto upd = new UserProfileDto().of(toUserProfile);
            upd.setFollowed(userService.isFollowed(fromUserProfile, toUserProfile));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }

    // TODO Follow 변경 필요
    /** user.name 통한 유저 검색 */
    public List<UserProfileDto> searchUserByName(Authentication auth, SearchDto searchDto) {
        User fromUser = findByAuth(auth);
        String name = searchDto.getName();
        // 유저 검색 리스트에 추가
        userSearch(fromUser, name);

        List<User> userList = userRepository.findUserListByName(name)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 유저가 없습니다."));
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (User toUser : userList) {
            UserProfileDto upd = new UserProfileDto().of(toUser);
            upd.setFollowed(userService.isFollowed(fromUser, toUser));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }




    // 유저 리뷰 리스트
    // TODO 0407 리뷰리스트 삭제 -> 변경 필요
//    public List<Review> getReviewListFromUser(Authentication auth) {
//        User user = findByAuth(auth);
//        List<Review> reviewList = user.getReviewList();
//
//        // 리뷰 테스트
//        for (Review review : reviewList) {
//            System.out.println(review.getImage());
//        }
//
//        return reviewList;
//    }

    // UserProfileDto followed 추가 <- 왜? 안해도 될듯
}

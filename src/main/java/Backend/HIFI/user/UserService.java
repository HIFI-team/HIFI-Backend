package Backend.HIFI.user;


import Backend.HIFI.review.Review;
import Backend.HIFI.user.dto.UserDto;
import Backend.HIFI.user.dto.UserProfileDto;
import Backend.HIFI.user.follow.Follow;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import Backend.HIFI.user.search.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final FollowService followService;


    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
        return user;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        return user;
    }

    public User findByAuth(Authentication authentication) {
        System.out.println("^&^&^&^\n"+authentication.getName());
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        System.out.println(user);
        return user;
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        if (findUser == user) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public void deleteUser(User user) {
        // trigger 통해 리뷰 지워지면 store.review 지워지도록
//        userRepository.deleteReviewByUserId(user.getId());
        userRepository.deleteFollowByUserId(user.getId());
//        userRepository.delete(user);
        user.changeDeleteStatus();
    }

    public void userSearch(User user, String searchName) {
        Search search = new Search();
        search.setName(searchName);
        search.setUser(user);
        user.getSearchList().add(search);
    }

    public void updateProfile(User user, UserProfileDto userProfileDto) {
        user.update(userProfileDto);
        userRepository.save(user);
    }

    public boolean isFollowed(User fromUser, User toUser) {
        Follow follow = followRepository.findFollowByFollowerAndFollowing(fromUser, toUser);
        return follow != null;
    }
    /** 맞팔 확인 */
    public boolean isFollowForFollowed(User user1, User user2) {
//        Follow follow1 = followRepository.findFollowByFollowerAndFollowing(user1, user2);
//        Follow follow2 = followRepository.findFollowByFollowerAndFollowing(user2, user1);

        return isFollowed(user1, user2) && isFollowed(user2, user1);
    }
    public boolean canWatchReview(User fromUser, User toUser) {

        return isFollowForFollowed(fromUser, toUser) || !toUser.getAnonymous();
    }

    // 유저 리뷰 리스트
    public List<Review> getReviewListFromUser(User user) {
        List<Review> reviewList = user.getReviewList();

        return reviewList;
    }

    // UserProfileDto followed 추가

    /** 모든 유저 return */
    public List<UserProfileDto> searchAllUser(User fromUser) {
        List<User> userList = userRepository.findAll();
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (User toUser : userList) {
            if (fromUser == toUser)
                continue;
            UserProfileDto upd = new UserProfileDto().of(toUser);
            upd.setFollowed(isFollowed(fromUser, toUser));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }

    /** user.name 통한 유저 검색 */
    public List<UserProfileDto> searchUserByName(User fromUser, String name) {
        List<User> userList = userRepository.findUserListByName(name)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 유저가 없습니다."));
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (User toUser : userList) {
            UserProfileDto upd = new UserProfileDto().of(toUser);
            upd.setFollowed(isFollowed(fromUser, toUser));
            userProfileDtoList.add(upd);
        }
        return userProfileDtoList;
    }


}

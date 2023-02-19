package Backend.HIFI.domain.user;


import Backend.HIFI.domain.user.search.Search;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.follow.Follow;
import Backend.HIFI.domain.user.follow.FollowRepository;
import Backend.HIFI.domain.user.follow.FollowService;
import lombok.RequiredArgsConstructor;
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
        Long userId = Long.parseLong(authentication.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
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

    /** 맞팔 확인 */
    public boolean F4F(User user1, User user2) {
        Follow follow1 = followRepository.findFollowByFollowerAndFollowing(user1, user2);
        Follow follow2 = followRepository.findFollowByFollowerAndFollowing(user2, user1);

        return follow1 != null && follow2 != null;
    }
    public boolean canWatchReview(User fromUser, User toUser) {

        return F4F(fromUser, toUser) || !toUser.getAnonymous();
    }

    // 유저 리뷰 리스트

    /** user.name 통한 유저 검색 */
    public List<UserProfileDto> searchUserByName(String name) {
        List<User> userList = userRepository.findUserListByName(name)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 유저가 없습니다."));
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for (User user : userList) {
            userProfileDtoList.add(new UserProfileDto().toUserProfileDto(user));
        }
        return userProfileDtoList;
    }


}

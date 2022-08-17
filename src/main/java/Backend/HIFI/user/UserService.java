package Backend.HIFI.user;


import Backend.HIFI.auth.dto.UserProfileDto;
import Backend.HIFI.auth.dto.UserProfileUpdateDto;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import Backend.HIFI.user.search.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 유저 리뷰 리스트


}

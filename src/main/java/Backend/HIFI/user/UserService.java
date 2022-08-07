package Backend.HIFI.user;


import Backend.HIFI.auth.dto.UserProfileUpdateDto;
import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
import Backend.HIFI.user.search.Search;
import lombok.RequiredArgsConstructor;
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

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        if (findUser == user) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public void userSearch(User user, String searchName) {
        Search search = new Search();
        search.setName(searchName);
        search.setUser(user);
        user.getSearchList().add(search);
    }

    public void updateProfile(User user, UserProfileUpdateDto userProfileUpdateDto) {
        user.update(userProfileUpdateDto);
    }

    // 유저 리뷰 리스트


}

package Backend.HIFI.user;


import Backend.HIFI.user.follow.FollowRepository;
import Backend.HIFI.user.follow.FollowService;
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

    public Long saveUser(User user) {

        // 따로 email 중복 회원 검증 필요
//        validateDuplicateUser(user);

        userRepository.save(user);
        return user.getId();
    }

    public User findByEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
        return user;
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findUserById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
        if (findUser == user) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
}

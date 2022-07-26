package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {
    public FollowService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    UserRepository userRepository;
    FollowRepository followRepository;

    @Transactional
    public Follow save(int fromId, int toId) {
        User fromUser = userRepository.findUserById(fromId);
        User toUser = userRepository.findUserById(toId);

        return followRepository.save(Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build());
    }

    @Transactional
    public void follow(int fromUserId, int toUserId) {
        try {
            followRepository.mFollow(fromUserId, toUserId);
        } catch (Exception e) {
            System.out.println("TEST");
        }
    }
}

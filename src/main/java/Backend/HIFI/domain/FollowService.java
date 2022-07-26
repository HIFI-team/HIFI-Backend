package Backend.HIFI.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;

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

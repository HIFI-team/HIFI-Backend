package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.user.entity.User;
import Backend.HIFI.domain.user.entity.UserProfile;
import Backend.HIFI.domain.user.repository.UserRepository;
import Backend.HIFI.domain.user.entity.Search;
import Backend.HIFI.domain.user.dto.UserProfileDto;
import Backend.HIFI.domain.user.entity.Follow;
import Backend.HIFI.domain.user.repository.FollowRepository;

import Backend.HIFI.domain.user.dto.SearchDto;
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

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다"));
        return user;
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다"));
        return user;
    }

    public User findUserByAuth(Authentication authentication) {
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
        user.updateIsDeleted();
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

    // TODO 처리 필요
//    public boolean canWatchReview(User fromUser, User toUser) {
//
//        return isFollowForFollowed(fromUser, toUser) || !toUser.getAnonymous();
//    }

}

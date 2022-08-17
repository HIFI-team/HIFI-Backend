package Backend.HIFI.heart;

import Backend.HIFI.review.Review;
import Backend.HIFI.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    /**좋아요 등록*/
    @Transactional
    public void heart(User user, Review review){
        Heart heart = Heart.builder().user(user).review(review).build();
        heartRepository.save(heart);
    }
    /**좋아요 삭제*/
    @Transactional
    public void unHeart(Long userId,Long reviewId){
        heartRepository.deleteByUserIdAndReviewId(userId,reviewId);
    }
    /**좋아요 유저 리스트*/
    public List<Heart> findHeart(Long reviewId){
        return heartRepository.findHeartByReviewId(reviewId);
    }
}

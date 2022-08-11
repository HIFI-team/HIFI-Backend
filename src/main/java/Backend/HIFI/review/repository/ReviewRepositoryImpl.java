package Backend.HIFI.review.repository;

import Backend.HIFI.common.DeleteStatus;
import Backend.HIFI.review.Review;
import Backend.HIFI.review.repository.ReviewRepositoryCustom;
import Backend.HIFI.store.Store;
import Backend.HIFI.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static Backend.HIFI.review.QReview.review;

/**QuerydslSupport 페이징 시 필요*/
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * QueryDsl 사용 필요성 확인중
     * */
    //리뷰 조회 - userID
    @Override
    public List<Review> findByUser(User user){
        return jpaQueryFactory.selectFrom(review)
                .where(
                        review.user.eq(user),
                        review.delStatus.eq(DeleteStatus.N)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    /**동적 쿼리로 생성해야하나? */

    //리뷰 조회 - storeID
    @Override
    public List<Review> findByStore(Store store){
        return jpaQueryFactory.selectFrom(review)
                .where(
                        review.store.eq(store),
                        review.delStatus.eq(DeleteStatus.N)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }
    //리뷰 조회
    @Override
    public List<Review> findAllByDelStatus(){
        return jpaQueryFactory.selectFrom(review)
                .where(
                    review.delStatus.eq(DeleteStatus.N)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

}

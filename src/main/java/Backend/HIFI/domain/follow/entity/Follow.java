package Backend.HIFI.domain.follow.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "FOLLOW_UQ", columnNames = {"FOLLOWER_ID", "FOLLOWING_ID"})})
@Getter
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "FOLLOWER_ID")
    private Long followerId;

    @Column(nullable = false, name = "FOLLOWING_ID")
    private Long followingId;

    @Builder
    public Follow(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }
}

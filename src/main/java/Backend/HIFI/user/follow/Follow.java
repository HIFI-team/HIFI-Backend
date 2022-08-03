package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "FOLLOW_UQ", columnNames = {"follower_id", "following_id"})})
@Getter
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User following;

    @Column(name = "follower_id")
    private Long followerId;

    @Column(name = "following_id")
    private Long followingId;

    @Builder
    public Follow(User follower, User following) {
        this.follower = follower;
        this.followerId = follower.getId();

        this.following = following;
        this.followingId = following.getId();
    }
}

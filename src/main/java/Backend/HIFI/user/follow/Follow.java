package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "FOLLOW_UQ", columnNames = {"FOLLOWER", "FOLLOWING"})})
@Getter
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "FOLLOWER")
    private User follower;

    @ManyToOne
    @JoinColumn(nullable = false, name = "FOLLOWING")
    private User following;

    @Builder
    public Follow(User follower, User following) {
        this.follower = follower;

        this.following = following;
    }
}

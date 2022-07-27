package Backend.HIFI.user.follow;

import Backend.HIFI.user.User;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "FOLLOW_UQ", columnNames = {"FOLLOWER", "FOLLOWING"})})
public class Follow {

    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "FOLLOWING", nullable = false)
    private User following;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}

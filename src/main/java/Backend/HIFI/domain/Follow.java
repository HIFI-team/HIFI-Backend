package Backend.HIFI.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "FOLLOW_UQ", columnNames = {"FOLLOWER", "FOLLOWING"})})
public class Follow {

    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "FOLLOWING")
    private User following;

    @Builder
    public Follow(User fromUser, User toUser) {
        this.follower = fromUser;
        this.following = toUser;
    }
}

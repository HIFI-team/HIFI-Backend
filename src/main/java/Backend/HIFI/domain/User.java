package Backend.HIFI.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_IMAGE")
    private String image;
    @Column(name = "USER_DESCRIPTION")
    private String description;

    @Column(name = "USER_CREATEAT", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "USER_PUBLIC", nullable = false)
    private Boolean userPublic;

//    public Follow follow(User user) {
//        Follow following = new Follow();
//        following.setFollower(this);
//        following.setFollowing(user);
//        return following;
//    }
    private User(String email, String password) {
        this.email = email;
        this.password = password;
        this.createAt = LocalDateTime.now();
        this.userPublic = true;
    }

    // 임시
    public static User signUp(String email, String password) {
        return new User(email, password);
    }


}

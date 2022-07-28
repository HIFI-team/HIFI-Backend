package Backend.HIFI.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "`User`")
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name")
    private String name;

    @Column(name="user_email", nullable = false)
    private String email;

    @Column(name="user_password", nullable = false)
    private String password;

    @Column(name="user_image")
    private String image;

    @Column(name="user_description")
    private String description;

    @Column(name="user_createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="user_annonymous",nullable = false)
    private Boolean annonymous;

    //    public Follow follow(User user) {
//        Follow following = new Follow();
//        following.setFollower(this);
//        following.setFollowing(user);
//        return following;
//    }


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.annonymous = true;
    }

}
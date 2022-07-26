package Backend.HIFI.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "USER_FOLLOWING")
    private User userFollowing = this;

    @ManyToOne
    @JoinColumn(name = "USER_FOLLWER")
    private User userFollower = this;

    @OneToMany(mappedBy = "userFollowing")
    private List<User> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "userFollower")
    private List<User> followerList = new ArrayList<>();

    public void addFollowing(User following) {
        this.followingList.add(following);
        following.getFollowerList().add(this);
        following.getUserFollower().getFollowerList().add(this);
    }

    public void addFollower(User follower) {
        this.followerList.add(follower);
        follower.getFollowingList().add(this);
        follower.getUserFollowing().getFollowingList().add(this);
    }

    public User getUserFollowing() {
        return userFollowing;
    }

    public User getUserFollower() {
        return userFollower;
    }

    public List<User> getFollowingList() {
        return followingList;
    }

    public List<User> getFollowerList() {
        return followerList;
    }


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

    protected User() {}

    // 임시
    public static User signUp(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setCreateAt(LocalDateTime.now());
        return newUser;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

}

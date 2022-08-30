package Backend.HIFI.auth.oauth.kakao;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class KakaoProfileDto {
    private String email;
    private String nickname;
}
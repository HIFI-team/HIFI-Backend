package Backend.HIFI.auth.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.security.Timestamp;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserDto {

    @JsonProperty("id")
    private String authenticationCode;

    @JsonProperty("connected_at")
    private Timestamp connectedAt;

    @JsonProperty("kakao_account")
    private KakaoProfileDto kakaoAccount;

}
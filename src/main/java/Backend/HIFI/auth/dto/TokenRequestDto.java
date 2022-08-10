package Backend.HIFI.auth.dto;

import lombok.*;

/** JwtToken 요청 Dto
 * @author gengminy (220728) */
@Getter
@ToString
@NoArgsConstructor
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}

package Backend.HIFI.auth.dto;

import lombok.*;

/** JwtToken 응답 Dto
 * @author gengminy (220728) */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}

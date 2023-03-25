package Backend.HIFI.domain.user.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestDto {

    private String fromEmail;
    private String toEmail;

    public FollowRequestDto of(String fromEmail, String toEmail) {
        return FollowRequestDto.builder()
                .fromEmail(fromEmail)
                .toEmail(toEmail)
                .build();
    }
}
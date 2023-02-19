package Backend.HIFI.domain.auth.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {

    private Long id;
    private String email;
    private String name;
    private String image;
    private Boolean followState;

    public FollowDto toFollowDto(Long id, String email, String name, String image, Boolean followState) {
        return FollowDto.builder()
                .id(id)
                .email(email)
                .name(name)
                .image(image)
                .followState(followState)
                .build();
    }
}
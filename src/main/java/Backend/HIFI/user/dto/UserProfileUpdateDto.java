package Backend.HIFI.user.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {

    private String name;
    private String description;
    private String image;
    private Boolean anonymous;
}

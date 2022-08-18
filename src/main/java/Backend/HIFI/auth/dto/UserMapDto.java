package Backend.HIFI.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMapDto {
    private Long id;
    private String email;
    private String name;
    private String image;
}

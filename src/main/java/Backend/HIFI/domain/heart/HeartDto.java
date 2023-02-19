package Backend.HIFI.domain.heart;

import Backend.HIFI.domain.auth.dto.UserMapDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartDto {
    private UserMapDto user;
}

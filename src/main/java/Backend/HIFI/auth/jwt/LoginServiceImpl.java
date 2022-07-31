package Backend.HIFI.auth.jwt;

import Backend.HIFI.common.exception.NotFoundException;
import Backend.HIFI.user.User;
import Backend.HIFI.user.UserRepository;
import Backend.HIFI.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username은 email로 사용하기로 함
        Optional<User> userOptional =  userRepository.findUserByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        }
        return null;
    }
}
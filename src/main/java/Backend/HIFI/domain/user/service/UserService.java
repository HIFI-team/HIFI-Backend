package Backend.HIFI.domain.user.service;

import Backend.HIFI.domain.user.entity.User;

public interface UserService {
    User findByEmail(String email);

    User findById(Long id);

    void deleteUser(User user);
}

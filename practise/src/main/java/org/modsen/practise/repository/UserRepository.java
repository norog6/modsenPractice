package org.modsen.practise.repository;

import org.modsen.practise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByLogin(String login);

    User findById(long id);
}

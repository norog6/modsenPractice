package com.modsen.practise.repository;

import com.modsen.practise.entity.Product;
import com.modsen.practise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository  extends JpaRepository <User,Long> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String login);
}

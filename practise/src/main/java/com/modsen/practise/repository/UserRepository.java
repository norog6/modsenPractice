package com.modsen.practise.repository;

import com.modsen.practise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository <User,Long> {
}

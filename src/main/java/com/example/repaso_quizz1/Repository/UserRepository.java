package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

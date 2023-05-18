package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Answer;
import com.wrc.QueryUs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    long countAllByUser(User user);
}

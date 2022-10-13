package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}

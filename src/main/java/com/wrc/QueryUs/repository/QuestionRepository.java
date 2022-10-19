package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionTextContainingIgnoreCase(String questionText,Pageable pageable);
}

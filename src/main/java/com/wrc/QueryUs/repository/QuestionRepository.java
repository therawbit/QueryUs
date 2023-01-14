package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionTitleContainingIgnoreCase(String questionText,Pageable pageable);

}

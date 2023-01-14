package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.entity.QuestionTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionTitleContainingIgnoreCase(String questionText,Pageable pageable);

    List<Question> findByQuestionTagsIn(List<QuestionTag> tags,Pageable pageable);
}

package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import com.wrc.QueryUs.entity.QuestionTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByOrderByTimestampDesc(Pageable pageable);
    List<Question> findByQuestionTagsIn(List<QuestionTag> tags,Pageable pageable);
    @Query(value = "SELECT * FROM question WHERE to_tsvector('english', question_title || ' ' || question_text) @@ to_tsquery('english', ?1)" ,
                     countQuery = "SELECT count(*) FROM question WHERE to_tsvector('english', question_title || ' ' || question_text) @@ to_tsquery('english', ?1)",nativeQuery = true)
    List<Question> search(String query,Pageable pageable);

}

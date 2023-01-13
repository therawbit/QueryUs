package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionTextContainingIgnoreCase(String questionText,Pageable pageable);

    @Query(value = "update question set views=views+1 where id=?1",nativeQuery = true)
    void updateView(int id);
}

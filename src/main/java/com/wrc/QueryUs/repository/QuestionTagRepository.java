package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface QuestionTagRepository extends JpaRepository<QuestionTag,Integer> {

    Optional<QuestionTag> findByTag(String tag);

    @Query(value = "select * from question_tag where tag in ?1",nativeQuery = true)
    Set<QuestionTag> findAllByTags(Set<String> tags);
}

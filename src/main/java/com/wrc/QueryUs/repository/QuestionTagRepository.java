package com.wrc.QueryUs.repository;

import com.wrc.QueryUs.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionTagRepository extends JpaRepository<QuestionTag,Integer> {

    Optional<QuestionTag> findByTag(String tag);
}

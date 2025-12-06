package com.mili.questionService.Repository;

import com.mili.questionService.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long>  {
    @Query(value = "SELECT q.id FROM questions q WHERE q.topic = :topic ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Long> findRandomQuestionByTopic(@Param("topic") String topic, @Param("numberOfQuestions") Integer numberOfQuestions);
    List<Question> findByTopic(String topic);
}

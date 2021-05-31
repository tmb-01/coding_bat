package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.coding_bat.entity.Problem;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query(value = "select name, competed from problem where topic_id=:topic_id", nativeQuery = true)
    List<Problem> findForTopicByTopicId(@Param("topic_id") Long topic_id);
}

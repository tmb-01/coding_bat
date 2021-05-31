package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Topic;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByLanguageId(Long language_id);
}

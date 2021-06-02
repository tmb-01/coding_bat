package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Answer;
import uz.pdp.coding_bat.entity.UserEntity;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

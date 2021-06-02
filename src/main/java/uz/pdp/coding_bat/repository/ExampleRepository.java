package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Example;
import uz.pdp.coding_bat.entity.UserEntity;

public interface ExampleRepository extends JpaRepository<Example, Long> {
}

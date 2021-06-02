package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}

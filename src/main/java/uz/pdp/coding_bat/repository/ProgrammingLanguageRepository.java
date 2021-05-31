package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {
}

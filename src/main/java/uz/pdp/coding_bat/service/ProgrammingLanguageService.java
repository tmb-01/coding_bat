package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.repository.ProgrammingLanguageRepository;
import uz.pdp.coding_bat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    public List<ProgrammingLanguage> getAll() {
        return programmingLanguageRepository.findAll();
    }

    public ProgrammingLanguage getById(Long id) {
        Optional<ProgrammingLanguage> byId = programmingLanguageRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new ProgrammingLanguage();
    }

    public ResponseEntity<ApiResponse> add(ProgrammingLanguage programmingLanguage) {
        if (!programmingLanguage.getName().isEmpty()) {
            programmingLanguageRepository.save(programmingLanguage);
            return ResponseEntity.ok(new ApiResponse("programming language saved", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("name have not to be empty", false));
    }

    public ResponseEntity<ApiResponse> update(Long id, ProgrammingLanguage programmingLanguage) {
        Optional<ProgrammingLanguage> byId = programmingLanguageRepository.findById(id);
        if (byId.isPresent()) {
            if (!programmingLanguage.getName().isEmpty()) {
                ProgrammingLanguage getProgrammingLanguage = byId.get();
                getProgrammingLanguage.setName(programmingLanguage.getName());
                programmingLanguageRepository.save(getProgrammingLanguage);
                return ResponseEntity.ok(new ApiResponse("programming language updated", true));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("name have not to be empty", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("programming language not found", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean existsById = programmingLanguageRepository.existsById(id);
        if (existsById) {
            programmingLanguageRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("programming language deleted", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("programming language not found", false));
    }


}

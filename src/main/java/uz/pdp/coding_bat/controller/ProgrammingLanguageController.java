package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.service.ProgrammingLanguageService;

import java.util.List;

@RestController
@RequestMapping("api/programming-language")
public class ProgrammingLanguageController {
    @Autowired
    ProgrammingLanguageService programmingLanguageService;

    @GetMapping
    public List<ProgrammingLanguage> getAll() {
        return programmingLanguageService.getAll();
    }

    @GetMapping("{id}")
    public ProgrammingLanguage getById(@PathVariable Long id) {
        return programmingLanguageService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody ProgrammingLanguage programmingLanguage) {
        return programmingLanguageService.add(programmingLanguage);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ProgrammingLanguage programmingLanguage) {
        return programmingLanguageService.update(id, programmingLanguage);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return programmingLanguageService.delete(id);
    }

}

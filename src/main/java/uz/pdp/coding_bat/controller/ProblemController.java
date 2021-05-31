package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDto;
import uz.pdp.coding_bat.service.ProblemService;

import java.util.List;

@RestController
@RequestMapping("api/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @GetMapping
    public List<Problem> getAll() {
        return problemService.getAll();
    }

    @GetMapping("topic/{id}")
    public List<Problem> getAllByTopicId(@PathVariable Long id) {
        return problemService.getAllByTopicId(id);
    }

    @GetMapping("{id}")
    public Problem getById(@PathVariable Long id) {
        return problemService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody ProblemDto problemDto) {
        return problemService.add(problemDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ProblemDto problemDto) {
        return problemService.update(id, problemDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return problemService.delete(id);
    }
}

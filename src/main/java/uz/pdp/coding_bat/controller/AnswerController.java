package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.Answer;
import uz.pdp.coding_bat.payload.AnswerDto;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public List<Answer> getAll() {
        return answerService.getAll();
    }


    @GetMapping("{id}")
    public Answer getById(@PathVariable Long id) {
        return answerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody AnswerDto answerDto) {
        return answerService.add(answerDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody AnswerDto answerDto) {
        return answerService.update(id, answerDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return answerService.delete(id);
    }
}

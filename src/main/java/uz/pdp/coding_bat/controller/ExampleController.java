package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.Answer;
import uz.pdp.coding_bat.entity.Example;
import uz.pdp.coding_bat.payload.AnswerDto;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ExampleDto;
import uz.pdp.coding_bat.repository.ExampleRepository;
import uz.pdp.coding_bat.service.AnswerService;
import uz.pdp.coding_bat.service.ExampleService;

import java.util.List;

@RestController
@RequestMapping("api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    @GetMapping
    public List<Example> getAll() {
        return exampleService.getAll();
    }

    @GetMapping("{id}")
    public Example getById(@PathVariable Long id) {
        return exampleService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody ExampleDto exampleDto) {
        return exampleService.add(exampleDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody ExampleDto exampleDto) {
        return exampleService.update(id, exampleDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return exampleService.delete(id);
    }
}

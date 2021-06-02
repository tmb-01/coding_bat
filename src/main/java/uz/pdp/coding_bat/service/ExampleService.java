package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Answer;
import uz.pdp.coding_bat.entity.Example;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.UserEntity;
import uz.pdp.coding_bat.payload.AnswerDto;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ExampleDto;
import uz.pdp.coding_bat.repository.AnswerRepository;
import uz.pdp.coding_bat.repository.ExampleRepository;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    ExampleRepository exampleRepository;

    public Example getById(Long id) {
        Optional<Example> exampleById = exampleRepository.findById(id);
        if (exampleById.isPresent()) {
            return exampleById.get();
        }
        return new Example();
    }

    public List<Example> getAll() {
        return exampleRepository.findAll();
    }

    public ResponseEntity<ApiResponse> add(ExampleDto exampleDto) {
        Optional<Problem> problemById = problemRepository.findById(exampleDto.getProblemId());
        if (problemById.isPresent()) {
            Example example = new Example();
            example.setText(example.getText());
            example.setProblem(problemById.get());
            exampleRepository.save(example);
            return ResponseEntity.ok(new ApiResponse("saved", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not problem found with this id", false));
    }

    public ResponseEntity<ApiResponse> update(Long id, ExampleDto exampleDto) {
        Optional<Example> exampleById = exampleRepository.findById(id);
        if (exampleById.isPresent()) {
            Optional<Problem> problemById = problemRepository.findById(exampleDto.getProblemId());
            if (problemById.isPresent()) {
                Example example = new Example();
                example.setText(example.getText());
                example.setProblem(problemById.get());
                exampleRepository.save(example);
                return ResponseEntity.ok(new ApiResponse("saved", true));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not problem found with this id", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not example found with this id", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean existsById = exampleRepository.existsById(id);
        if (existsById) {
            exampleRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("deleted", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("example with this id not exist", false));
    }
}

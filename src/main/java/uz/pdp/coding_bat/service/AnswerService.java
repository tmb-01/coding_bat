package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Answer;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;
import uz.pdp.coding_bat.entity.UserEntity;
import uz.pdp.coding_bat.payload.AnswerDto;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDto;
import uz.pdp.coding_bat.repository.AnswerRepository;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.repository.ProgrammingLanguageRepository;
import uz.pdp.coding_bat.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    AnswerRepository answerRepository;

    public Answer getById(Long id) {
        Optional<Answer> answerById = answerRepository.findById(id);
        if (answerById.isPresent()) {
            return answerById.get();
        }
        return new Answer();
    }

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public ResponseEntity<ApiResponse> add(AnswerDto answerDto) {
        Optional<Problem> problemById = problemRepository.findById(answerDto.getProblemId());
        Optional<UserEntity> userById = userEntityRepository.findById(answerDto.getUserId());

        if (userById.isPresent()) {
            if (problemById.isPresent()) {
                Answer answer = new Answer();
                answer.setCorrect(answerDto.isCorrect());
                answer.setProblem(problemById.get());
                answer.setText(answerDto.getText());
                answer.setUser(userById.get());
                answerRepository.save(answer);
                return ResponseEntity.ok(new ApiResponse("saved", true));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not problem found with this id", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not user found with this id", false));
    }

    public ResponseEntity<ApiResponse> update(Long id, AnswerDto answerDto) {
        Optional<Answer> answerById = answerRepository.findById(id);
        if (answerById.isPresent()) {
            Optional<Problem> problemById = problemRepository.findById(answerDto.getProblemId());
            Optional<UserEntity> userById = userEntityRepository.findById(answerDto.getUserId());

            if (userById.isPresent()) {
                if (problemById.isPresent()) {
                    Answer answer = answerById.get();
                    answer.setCorrect(answerDto.isCorrect());
                    answer.setProblem(problemById.get());
                    answer.setText(answerDto.getText());
                    answer.setUser(userById.get());
                    answerRepository.save(answer);
                    return ResponseEntity.ok(new ApiResponse("saved", true));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not problem found with this id", false));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not user found with this id", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not answer found with this id", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean answerExistsById = answerRepository.existsById(id);
        if (answerExistsById) {
            answerRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("deleted", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("answer with this id not exist", false));
    }
}

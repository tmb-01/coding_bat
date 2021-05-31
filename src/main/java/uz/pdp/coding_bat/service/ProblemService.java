package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.Topic;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDto;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    TopicRepository topicRepository;

    public Problem getById(Long id) {
        Optional<Problem> problemById = problemRepository.findById(id);
        if (problemById.isPresent()) {
            return problemById.get();
        }
        return new Problem();
    }

    public List<Problem> getAll() {
        return problemRepository.findAll();
    }

    public List<Problem> getAllByTopicId(Long id) {
        return problemRepository.findForTopicByTopicId(id);
    }

    public ResponseEntity<ApiResponse> add(ProblemDto problemDto) {
        String name = problemDto.getName();
        String question = problemDto.getQuestion();
        Long topicId = problemDto.getTopicId();

        if (!name.isEmpty()) {
            if (question.isEmpty()) {
                if (topicId != 0) {
                    Optional<Topic> topicById = topicRepository.findById(topicId);
                    if (topicById.isPresent()) {
                        Problem problem = new Problem();
                        problem.setName(problemDto.getName());
                        problem.setEditor(problemDto.getEditor());
                        problem.setQuestion(problemDto.getQuestion());
                        problem.setHint(problemDto.getHint());
                        problem.setSolution(problemDto.getSolution());
                        problem.setTopic(topicById.get());
                        problem.setCompleted(problemDto.isCompleted());
                        problemRepository.save(problem);
                        return ResponseEntity.ok(new ApiResponse("saved", true));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not topic found with this id", false));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("topic id have not to be empty", false));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("question have not to be empty", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("name have not to be empty", false));
    }

    public ResponseEntity<ApiResponse> update(Long id, ProblemDto problemDto) {
        Optional<Problem> problemById = problemRepository.findById(id);

        if (problemById.isPresent()) {
            String name = problemDto.getName();
            String question = problemDto.getQuestion();
            Long topicId = problemDto.getTopicId();

            if (!name.isEmpty()) {
                if (question.isEmpty()) {
                    if (topicId != 0) {
                        Optional<Topic> topicById = topicRepository.findById(topicId);
                        if (topicById.isPresent()) {
                            Problem problem = new Problem();
                            problem.setName(problemDto.getName());
                            problem.setEditor(problemDto.getEditor());
                            problem.setQuestion(problemDto.getQuestion());
                            problem.setHint(problemDto.getHint());
                            problem.setSolution(problemDto.getSolution());
                            problem.setTopic(topicById.get());
                            problem.setCompleted(problemDto.isCompleted());
                            problemRepository.save(problem);
                            return ResponseEntity.ok(new ApiResponse("updated", true));
                        }
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not topic found with this id", false));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("topic id have not to be empty", false));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("question have not to be empty", false));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("name have not to be empty", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not problem found with this id", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean problemExistsById = problemRepository.existsById(id);
        if (problemExistsById) {
            problemRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("deleted", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("problem with this id not exist", false));
    }
}

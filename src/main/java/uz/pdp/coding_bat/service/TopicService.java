package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;
import uz.pdp.coding_bat.entity.Topic;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDto;
import uz.pdp.coding_bat.payload.TopicDto;
import uz.pdp.coding_bat.repository.ProblemRepository;
import uz.pdp.coding_bat.repository.ProgrammingLanguageRepository;
import uz.pdp.coding_bat.repository.TopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    @Autowired
    TopicRepository topicRepository;

    public Topic getById(Long id) {
        Optional<Topic> topicById = topicRepository.findById(id);
        if (topicById.isPresent()) {
            return topicById.get();
        }
        return new Topic();
    }

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public List<Topic> getAllByPLId(Long id) {
        return topicRepository.findAllByLanguageId(id);
    }

    public ResponseEntity<ApiResponse> add(TopicDto topicDto) {
        String title = topicDto.getTitle();
        if (title.isEmpty()) {
            Optional<ProgrammingLanguage> language = programmingLanguageRepository.findById(topicDto.getLanguageId());
            if (language.isPresent()) {
                String description = topicDto.getDescription();
                if (description.isEmpty()) {
                    int rating = topicDto.getRating();
                    boolean completed = topicDto.isCompleted();

                    Topic topic = new Topic();
                    topic.setCompleted(completed);
                    topic.setDescription(description);
                    topic.setLanguage(language.get());
                    topic.setRating(rating);
                    topic.setTitle(title);
                    topicRepository.save(topic);
                    return ResponseEntity.ok(new ApiResponse("saved", true));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("description have not to be empty", false));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not language found with this id", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("title have not to be empty", false));
    }

    public ResponseEntity<ApiResponse> update(Long id, TopicDto topicDto) {
        Optional<Topic> topicById = topicRepository.findById(id);
        if (topicById.isPresent()) {
            String title = topicDto.getTitle();
            if (title.isEmpty()) {
                Optional<ProgrammingLanguage> language = programmingLanguageRepository.findById(topicDto.getLanguageId());
                if (language.isPresent()) {
                    String description = topicDto.getDescription();
                    if (description.isEmpty()) {
                        int rating = topicDto.getRating();
                        boolean completed = topicDto.isCompleted();

                        Topic topic = topicById.get();
                        topic.setCompleted(completed);
                        topic.setDescription(description);
                        topic.setLanguage(language.get());
                        topic.setRating(rating);
                        topic.setTitle(title);
                        topicRepository.save(topic);
                        return ResponseEntity.ok(new ApiResponse("updated", true));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("description have not to be empty", false));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not language found with this id", false));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("title have not to be empty", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not topic found with this id", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean topicExistsById = topicRepository.existsById(id);
        if (topicExistsById) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("deleted", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("topic with this id not exist", false));
    }
}

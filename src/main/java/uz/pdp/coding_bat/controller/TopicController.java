package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.Topic;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ProblemDto;
import uz.pdp.coding_bat.payload.TopicDto;
import uz.pdp.coding_bat.repository.TopicRepository;
import uz.pdp.coding_bat.service.ProblemService;
import uz.pdp.coding_bat.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("api/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @GetMapping("{id}")
    public Topic getById(@PathVariable Long id) {
        return topicService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody TopicDto topicDto) {
        return topicService.add(topicDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody TopicDto topicDto) {
        return topicService.update(id, topicDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return topicService.delete(id);
    }
}

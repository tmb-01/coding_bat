package uz.pdp.coding_bat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.coding_bat.entity.Example;
import uz.pdp.coding_bat.entity.UserEntity;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ExampleDto;
import uz.pdp.coding_bat.service.ExampleService;
import uz.pdp.coding_bat.service.UserEntityService;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserEntityService userService;

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public UserEntity getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody UserEntity userEntity) {
        return userService.add(userEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return userService.update(id, userEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}

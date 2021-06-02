package uz.pdp.coding_bat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.coding_bat.entity.Example;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.UserEntity;
import uz.pdp.coding_bat.payload.ApiResponse;
import uz.pdp.coding_bat.payload.ExampleDto;
import uz.pdp.coding_bat.repository.UserEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;

    public UserEntity getById(Long id) {
        Optional<UserEntity> userById = userEntityRepository.findById(id);
        if (userById.isPresent()) {
            return userById.get();
        }
        return new UserEntity();
    }

    public List<UserEntity> getAll() {
        return userEntityRepository.findAll();
    }

    public ResponseEntity<ApiResponse> add(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
        return ResponseEntity.ok(new ApiResponse("saved", true));
    }

    public ResponseEntity<ApiResponse> update(Long id, UserEntity userEntity) {
        Optional<UserEntity> userById = userEntityRepository.findById(id);
        if (userById.isPresent()) {
            UserEntity user = userById.get();
            user.setEmail(userEntity.getEmail());
            user.setPassword(userEntity.getPassword());
            userEntityRepository.save(userById.get());
            return ResponseEntity.ok(new ApiResponse("saved", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("not user found with this id", false));
    }

    public ResponseEntity<ApiResponse> delete(Long id) {
        boolean existsById = userEntityRepository.existsById(id);
        if (existsById) {
            userEntityRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("deleted", false));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("user with this id not exist", false));
    }
}

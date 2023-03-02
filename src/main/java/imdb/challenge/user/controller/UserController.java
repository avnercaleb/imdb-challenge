package imdb.challenge.user.controller;

import imdb.challenge.user.model.User;
import imdb.challenge.user.model.UserDTO;
import imdb.challenge.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(x -> new UserDTO(x)).toList());
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO(service.getUser(id)));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserDTO dto, UriComponentsBuilder builder) {
        User user = service.createUser(dto);
        URI uri = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserDTO> changePassword(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(new UserDTO(service.changePassword(id, dto)));
    }
}

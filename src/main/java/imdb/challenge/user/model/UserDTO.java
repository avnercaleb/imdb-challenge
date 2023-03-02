package imdb.challenge.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
                        @Email
                        @NotBlank
                        String username,
                        @NotBlank
                        String password) {

    public UserDTO(User user){
        this(user.getUsername(), user.getPassword());
    }
}

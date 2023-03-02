package imdb.challenge.user.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class UserExceptions extends RuntimeException{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> userNotFound() {
        return ResponseEntity.badRequest().body("Não foi possível encontrar um usuário com o ID selecionado");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> userNotFoundForDelete() {
        return ResponseEntity.badRequest().body("Não foi possível executar a ação para o ID selecionado");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<WrongFields>> validationError(MethodArgumentNotValidException ex) {

        List<FieldError> errorList = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errorList
                .stream()
                .map(WrongFields::new).toList());
    }

    private record WrongFields(String campo, String mensagem) {
        public WrongFields(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}

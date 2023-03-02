package imdb.challenge.user.controller;

import imdb.challenge.user.model.User;
import imdb.challenge.user.model.UserDTO;
import imdb.challenge.user.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = authManager.authenticate(authToken);

        var tokenJwt = tokenService.createToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(tokenJwt);
    }
}

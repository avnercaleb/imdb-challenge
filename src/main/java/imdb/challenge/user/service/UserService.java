package imdb.challenge.user.service;

import imdb.challenge.user.model.User;
import imdb.challenge.user.model.UserDTO;
import imdb.challenge.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        return repository.getReferenceById(id);
    }

    public User createUser(UserDTO dto) {
        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        try {
            if(getUser(id) != null){
                repository.deleteById(id);
            }
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException("Usuario não encontrado" + e.getMessage());
        }
    }

    public User changePassword(Long id, UserDTO dto) {
        User user = getUser(id);

        try {
            if(user.getPassword() != dto.password()) {
                user.setPassword(passwordEncoder.encode(dto.password()));
                return repository.save(user);
            }
        }
        catch (RuntimeException e) {
            throw new IllegalArgumentException("As senhas precisam ser diferentes " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}

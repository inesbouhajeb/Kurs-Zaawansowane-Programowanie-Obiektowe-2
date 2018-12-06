package pl.ines.shipcompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.model.UserRole;
import pl.ines.shipcompany.repository.UserRepository;
import pl.ines.shipcompany.repository.UserRoleRepository;

import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void update(String firstName, String lastName, Long id){
    Optional<User> byId=userRepository.findById(id);
    User user=byId.get();
    user.setFirstname(firstName);
    user.setLastname(lastName);
    }


    public void saveUser(User user){
        user.setPassword("{noop}"+user.getPassword());
        user.setEnabled(true);
        userRepository.save(user);

        UserRole userRole=new UserRole();
        userRole.setUsername(user.getUsername());
        userRole.setRole("ROLE_USER");
        userRoleRepository.save(userRole);
    }
}

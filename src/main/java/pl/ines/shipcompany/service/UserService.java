package pl.ines.shipcompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.model.UserRole;
import pl.ines.shipcompany.repository.UserRepository;
import pl.ines.shipcompany.repository.UserRoleRepository;

import java.security.Principal;
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
    public void update(User user){
    Optional<User> byId=userRepository.findById(user.getId());
    User editedUser=byId.get();
        editedUser.setFirstname(user.getFirstname());
        editedUser.setLastname(user.getLastname());
        //editedUser.setPassword(user.getPassword());
    }

    @Transactional
    public void updatePassword(String password, Principal principal){
        UserRole princUserRole=userRoleRepository.findUserByUsername(principal.getName());
        Long id=princUserRole.getId();
        User princUser=userRepository.findUserById(id);
        princUser.setPassword("{noop}"+password);
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

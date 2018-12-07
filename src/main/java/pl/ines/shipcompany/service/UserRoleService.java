package pl.ines.shipcompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.model.UserRole;
import pl.ines.shipcompany.repository.UserRoleRepository;

import java.util.Optional;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public void updatePassword(String password, String userName){
        //Optional<UserRole> byName=userRoleRepository.
    }
}

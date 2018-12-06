package pl.ines.shipcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ines.shipcompany.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

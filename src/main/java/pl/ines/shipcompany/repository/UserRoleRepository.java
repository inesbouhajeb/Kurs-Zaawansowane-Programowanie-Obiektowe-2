package pl.ines.shipcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ines.shipcompany.model.UserRole;

public interface UserRoleRepository  extends JpaRepository<UserRole, Long> {
}

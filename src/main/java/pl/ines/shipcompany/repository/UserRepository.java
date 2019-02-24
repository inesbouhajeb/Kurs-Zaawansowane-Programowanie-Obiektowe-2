package pl.ines.shipcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Modifying
//    @Query("update User u set u.firstname=:first, u.lastname=:last where u.id=:id")
//    List<Product> updateUser(@Param("first") String first,@Param("last") String last,@Param("id") Long id);
}

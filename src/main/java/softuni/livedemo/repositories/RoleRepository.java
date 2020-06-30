package softuni.livedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.livedemo.models.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    Optional<Role> findByName(String s);

}

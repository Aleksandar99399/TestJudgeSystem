package softuni.livedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.livedemo.models.entities.User;
import softuni.livedemo.models.serviceModel.UserServiceModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String name);
}

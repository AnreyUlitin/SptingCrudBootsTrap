package SpringCrudBootsTrap.repository;


import SpringCrudBootsTrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}

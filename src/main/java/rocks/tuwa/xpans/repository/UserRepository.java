package rocks.tuwa.xpans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.tuwa.xpans.pojo.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default Optional<User> findByUserId(String userId) {
        return null;
    }
}


package uz.mohirdev.spring.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.mohirdev.spring.entity.User;

@Registered
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);

    User findByLogin(String login);
}

package uz.coding.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.codingbat.entity.Users;

import javax.validation.constraints.Email;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    boolean existsAllByEmail(String email);
    boolean existsAllByEmailAndIdNot(@Email(message = "Email should be valid") String email, Integer id);
}

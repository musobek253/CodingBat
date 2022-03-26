package uz.coding.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.codingbat.entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByText(String text);
}

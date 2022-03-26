package uz.coding.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.codingbat.entity.Example;
@Repository
public interface ExampleRepository extends JpaRepository<Example, Integer> {

    boolean existsById(Integer id);
}

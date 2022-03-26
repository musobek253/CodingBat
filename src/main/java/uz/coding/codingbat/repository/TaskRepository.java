package uz.coding.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.codingbat.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByName(String name);

}

package uz.coding.codingbat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.coding.codingbat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select (count(c) > 0) from Category c where c.name = ?1")
    boolean existsByName(String name);

    Page<Category> findAllByOrderById(Pageable pageable);
}

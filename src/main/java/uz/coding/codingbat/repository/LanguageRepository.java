package uz.coding.codingbat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.coding.codingbat.entity.Language;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsByName(String name);

    boolean existsAllByIdIn(List<Integer> ids);

    boolean existsByNameAndIdNot(String name, Integer id);

    List<Language> getAllByIdIn(List<Integer> id);
}

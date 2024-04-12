package ch.cern.todo.repository;

import ch.cern.todo.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

    Optional<TaskCategory> findByName(String name);

    boolean existsByName(String name);

}

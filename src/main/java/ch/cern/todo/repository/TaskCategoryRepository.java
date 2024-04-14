package ch.cern.todo.repository;

import ch.cern.todo.entity.TaskCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long>, PagingAndSortingRepository<TaskCategory, Long> {

    Optional<TaskCategory> findByName(String name);

    boolean existsByName(String name);

    @Query("select case when tc.tasks is empty then false else true end from TaskCategory tc where tc.id = :id")
    boolean hasTasksById(@Param("id") Long id);

    @Query("select tk from TaskCategory tk where lower(tk.name) like lower(concat('%', :nameToFind,'%'))")
    List<TaskCategory> findAllByNameLimit(@Param("nameToFind") String nameToFind, Pageable pageable);

}

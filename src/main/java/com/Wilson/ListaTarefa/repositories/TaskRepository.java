package com.Wilson.ListaTarefa.repositories;

import com.Wilson.ListaTarefa.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser_Id(Long id);

    // Query de busca usando comandos SQLS e Springs;
    //  @Query(value = "SELECT TASK FROM tb_task Task WHERE TASK.user.id = :user_id")
    //    List<Task> findUserTask(@Param("user_id") Long user_id);

    // Query de busca usando totalmente comandos SQLS;
    //    @Query(value = "SELECT * FROM tb_task Task WHERE Task.user_id = :user_id", nativeQuery = true)
    //    List<Task> findUserTask(@Param("user_id") Long user_id);


}

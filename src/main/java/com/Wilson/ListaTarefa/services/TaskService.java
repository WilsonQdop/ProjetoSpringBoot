package com.Wilson.ListaTarefa.services;

import com.Wilson.ListaTarefa.models.Task;
import com.Wilson.ListaTarefa.models.User;
import com.Wilson.ListaTarefa.repositories.TaskRepository;
import com.Wilson.ListaTarefa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Task buscarPorId(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        if(task.isPresent()) {
            return task.get();
        } else {
            throw new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName());
        }
    }
    @Transactional
    public Task adicionarTask(Task objeto) {
        User user = this.userService.buscarPorId(objeto.getUser().getId());
        objeto.setId(null);
        objeto.setUser(user);
        objeto =  this.taskRepository.save(objeto);
        return objeto;
    }

    @Transactional
    public Task atualizarTask(Task objeto) {
        Task novaTask = buscarPorId(objeto.getId());
        novaTask.setDescricao(objeto.getDescricao());
        return this.taskRepository.save(novaTask);
    }
    public void deletarTask(Long id) {
        buscarPorId(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há tarefas relacionadas");
        }
    }
    public List<Task> buscarAllTaskUserId(Long userId) {
        List<Task> taskList = this.taskRepository.findByUser_Id(userId);
        return taskList;
    }

}

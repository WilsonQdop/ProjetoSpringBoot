package com.Wilson.ListaTarefa.controllers;

import com.Wilson.ListaTarefa.models.Task;
import com.Wilson.ListaTarefa.services.TaskService;
import com.Wilson.ListaTarefa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> buscarPorId(@PathVariable Long id) {
        Task object = this.taskService.buscarPorId(id);
        return ResponseEntity.ok().body(object);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> buscarAllTaskUserId(@PathVariable Long userId) {
        userService.buscarPorId(userId);
        List<Task> objectList = this.taskService.buscarAllTaskUserId(userId);
        return ResponseEntity.ok().body(objectList);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> adicionar(@Valid @RequestBody Task object) {
        this.taskService.adicionarTask(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Task object, @PathVariable Long id) {
        object.setId(id);
        this.taskService.atualizarTask(object);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long  id) {
        this.taskService.deletarTask(id);
        return ResponseEntity.noContent().build();
    }


}

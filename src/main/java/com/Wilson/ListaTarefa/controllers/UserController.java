package com.Wilson.ListaTarefa.controllers;

import com.Wilson.ListaTarefa.models.User;
import com.Wilson.ListaTarefa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorid(@PathVariable Long id) {
        User objeto = this.userService.buscarPorId(id);
        return ResponseEntity.ok().body(objeto);
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> adicionar(@Valid @RequestBody User object) {
        this.userService.adicionarUser(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody User objec, @PathVariable Long id) {
        objec.setId(id);
        this.userService.atualizarUser(objec);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.userService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}

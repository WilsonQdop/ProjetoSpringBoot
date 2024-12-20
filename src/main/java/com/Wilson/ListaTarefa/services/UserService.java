package com.Wilson.ListaTarefa.services;

import com.Wilson.ListaTarefa.exception.UserExistsException;
import com.Wilson.ListaTarefa.models.User;
import com.Wilson.ListaTarefa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User buscarPorId(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
         throw  new RuntimeException("Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName());
        }
    }
    @Transactional
    public User adicionarUser(User objeto) {
        objeto.setId(null);
        if(userRepository.findByUserName(objeto.getUserName()).isPresent()) {
            throw new UserExistsException("O nome do usuário já está em uso");
        }
        objeto = this.userRepository.save(objeto);
        return objeto;
    }

    @Transactional
    public User atualizarUser(User objeto) {
        User newObjeto = buscarPorId(objeto.getId());
        newObjeto.setPassword(objeto.getPassword());
        return this.userRepository.save(newObjeto);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há tarefas relacionadas");
        }
    }


}

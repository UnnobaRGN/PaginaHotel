/*
package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImp implements  IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user){

        if (repository.findByUserName(user.getEmail())==null){
            user = repository.save(user);
        }
        return user;

    }

}

 */

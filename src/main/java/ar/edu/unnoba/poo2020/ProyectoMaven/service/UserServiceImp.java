
package ar.edu.unnoba.poo2020.ProyectoMaven.service;

import ar.edu.unnoba.poo2020.ProyectoMaven.model.User;
import ar.edu.unnoba.poo2020.ProyectoMaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements  IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user){

        if (repository.findByEmail(user.getEmail())==null){
            user = repository.save(user);
        }
        return user;

    }

}


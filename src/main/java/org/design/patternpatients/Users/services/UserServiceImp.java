package org.design.patternpatients.Users.services;

import org.design.patternpatients.Users.Repository.UserRepository;
import org.design.patternpatients.Users.Users;
import org.design.patternpatients.healthCareModule.entities.Patients;
import org.design.patternpatients.healthCareModule.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Users> getAllUsers()
    {
        return repository.findAll();
    }

    @Override
    public Users saveUser(Users user) {
        return repository.save(user);
    }
}
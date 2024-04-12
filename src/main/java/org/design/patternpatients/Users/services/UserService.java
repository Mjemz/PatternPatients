package org.design.patternpatients.Users.services;
import org.design.patternpatients.Users.Users;
import org.design.patternpatients.healthCareModule.entities.Patients;

import java.util.List;

public interface UserService {
    Users saveUser(Users user);
    List<Users> getAllUsers();
}

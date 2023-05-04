package com.zwash.service;

import java.io.Serializable;

<<<<<<< HEAD
=======

import org.springframework.stereotype.Service;

>>>>>>> e11520111fcd663b55fb6071772482e77369f442
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.User;

<<<<<<< HEAD
=======

@Service
>>>>>>> e11520111fcd663b55fb6071772482e77369f442
public interface UserService extends Serializable {
    
    LoggedUser signIn(String username, String password) throws Exception;
    
    User register(User user) throws Exception;
    
    boolean changePassword(String username, String password) throws Exception;
    
    boolean validateSignIn(String token);
    
    String getSecretQuestionAnswer(String username);
    
    User getUser(long id) throws UserIsNotFoundException;
    
    User getUserFromToken(String token) throws UserIsNotFoundException;
    
    boolean resetPassword(String username, String secretAnswer, String newPassword) throws Exception;
}

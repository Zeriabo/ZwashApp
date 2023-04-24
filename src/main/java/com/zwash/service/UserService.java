package com.zwash.service;

import java.io.Serializable;

import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.User;

public interface UserService extends Serializable {
    
    LoggedUser signIn(String username, String password) throws Exception;
    
    User register(User user) throws Exception;
    
    boolean changePassword(String username, String password) throws Exception;
    
    boolean validateSignIn(String token);
    
    String getSecretQuestionAnswer(String username);
    
    User getUser(long id) throws UserIsNotFoundException;
    
    boolean resetPassword(String username, String secretAnswer, String newPassword) throws Exception;
}

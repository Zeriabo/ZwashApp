package com.zwash.service;

import java.io.Serializable;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

=======
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.LoggedUser;
import com.zwash.pojos.User;

<<<<<<< HEAD
@Service
=======
>>>>>>> a2afc0be40c6155e33ba4979de7d8d2e1954be41
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

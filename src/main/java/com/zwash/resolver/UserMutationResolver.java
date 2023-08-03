//package com.zwash.resolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.zwash.pojos.LoggedUser;
//import com.zwash.pojos.SignInfo;
//import com.zwash.pojos.User;
//import com.zwash.service.UserService;
//
//import graphql.annotations.annotationTypes.GraphQLName;
//import graphql.kickstart.tools.GraphQLMutationResolver;
//
//@Component
//@GraphQLName("MutationResolver")
//public class UserMutationResolver implements GraphQLMutationResolver {
//
//	@Autowired
//   UserService userService;
//
//    public UserMutationResolver(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    public LoggedUser signIn(SignInfo signInfo)throws Exception {
//        // Implement the logic to call the UserController's signIn method
//        LoggedUser loggedUser = userService.signIn(signInfo.getUsername(),signInfo.getPassword());
//        return loggedUser;
//    }
//
//
//    public User register(User user) throws Exception {
//        // Implement the logic to call the UserController's register method
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        // Set other user properties as needed
//        return userService.register(newUser,false);
//    }
//
//
//    public User registerAdmin(User user) throws Exception {
//        // Implement the logic to call the UserController's registerAdmin method
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        // Set other user properties as needed
//        return userService.register(newUser,true);
//    }
//
//
//    public Boolean changePassword(String username, String password) throws Exception {
//        // Implement the logic to call the UserController's changePassword method
//        return userService.changePassword(username, password);
//    }
//}

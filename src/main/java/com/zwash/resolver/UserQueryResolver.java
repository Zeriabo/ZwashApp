//package com.zwash.resolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.zwash.exceptions.UserIsNotFoundException;
//import com.zwash.pojos.User;
//import com.zwash.service.UserService;
//
//import graphql.annotations.annotationTypes.GraphQLName;
//import graphql.kickstart.annotations.GraphQLQueryResolver;
//
//
//@Component
//@GraphQLName("QueryResolver")
//public class UserQueryResolver implements GraphQLQueryResolver {
//
//	@Autowired
//	 UserService userService;
//
//    public UserQueryResolver(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    public User getUser(Long id) throws UserIsNotFoundException {
//
//        return userService.getUser(id);
//
//    }
//
//    @Override
//    public Class annotationType() {
//        return GraphQLQueryResolver.class;
//    }
//}

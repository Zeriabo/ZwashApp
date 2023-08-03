//package com.zwash.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.zwash.resolver.UserMutationResolver;
//import com.zwash.resolver.UserQueryResolver;
//
//import graphql.ExecutionResult;
//import graphql.GraphQL;
//import graphql.schema.GraphQLSchema;
//import io.leangen.graphql.GraphQLSchemaGenerator;
//
//
//@RestController
//public class GraphQLController {
//
//    private final GraphQL graphQL;
//
//    @Autowired
//    public GraphQLController(UserQueryResolver queryResolver, UserMutationResolver mutationResolver) {
//        GraphQLSchema schema = new GraphQLSchemaGenerator()
//                .withOperationsFromSingleton(queryResolver)
//                .withOperationsFromSingleton(mutationResolver)
//                .generate();
//        this.graphQL = GraphQL.newGraphQL(schema).build();
//    }
//
//    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> executeGraphQL(@RequestBody String query) {
//        ExecutionResult executionResult = graphQL.execute(query);
//        return ResponseEntity.ok(executionResult.toSpecification());
//    }
//}

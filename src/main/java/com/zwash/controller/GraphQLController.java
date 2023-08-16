package com.zwash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zwash.resolver.BookingMutationResolver;
import com.zwash.resolver.BookingResolver;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParserBuilder;
import graphql.schema.GraphQLSchema;


@RestController
public class GraphQLController {


	 private final GraphQL graphQL;

	    @Autowired
	    public GraphQLController(BookingResolver bookingQueryResolver, BookingMutationResolver bookingMutationResolver) {
	    	  GraphQLSchema schema = new SchemaParserBuilder()
	                  .file("graphql/schema.graphqls")
	                  .resolvers(bookingQueryResolver, bookingMutationResolver)
	                  .build()
	                  .makeExecutableSchema();

	    	  this.graphQL = GraphQL.newGraphQL(schema).build();
	    }
    @GetMapping("/graphiql")
    @ResponseBody
    public String graphiql() {
        return "<html><head><link href='https://cdnjs.cloudflare.com/ajax/libs/graphiql/0.13.2/graphiql.min.css' rel='stylesheet' /></head><body><div id='graphiql'></div><script src='https://cdnjs.cloudflare.com/ajax/libs/react/15.5.4/react.min.js'></script><script src='https://cdnjs.cloudflare.com/ajax/libs/react/15.5.4/react-dom.min.js'></script><script src='https://cdnjs.cloudflare.com/ajax/libs/graphiql/0.13.2/graphiql.min.js'></script><script>function graphQLFetcher(graphQLParams) { return fetch('/graphql', { method: 'post', headers: { 'Content-Type': 'application/json', 'Accept': 'application/json', }, body: JSON.stringify(graphQLParams), }).then(function (response) { return response.json(); }); } ReactDOM.render(React.createElement(GraphiQL, { fetcher: graphQLFetcher }), document.getElementById('graphiql'));</script></body></html>";
       }
    @PostMapping("/graphql")
    public ExecutionResult executeGraphQL(@RequestBody Map<String, Object> request) {
        String query =  (String) request.get("query");
        Map<String, Object> variables = (Map<String, Object>) request.get("variables");

        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .variables(variables)
                .build();

        return graphQL.execute(executionInput);
    }
}

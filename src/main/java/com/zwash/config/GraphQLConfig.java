package com.zwash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zwash.resolver.BookingMutationResolver;
import com.zwash.resolver.BookingResolver;

import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParserBuilder;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfig {

	@Autowired
   BookingResolver bookingQueryResolver;
	@Autowired
   BookingMutationResolver bookingMutationResolver;

    @Autowired
    public GraphQLConfig(
    		BookingResolver bookingQueryResolver,
            BookingMutationResolver bookingMutationResolver) {
        this.bookingQueryResolver = bookingQueryResolver;
        this.bookingMutationResolver = bookingMutationResolver;
    }

    @Bean
    public GraphQL graphQL() {


        GraphQLSchema schema = new SchemaParserBuilder()
                .file("graphql/schema.graphqls")
                .resolvers(bookingQueryResolver, bookingMutationResolver)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(schema).build();
    }
}

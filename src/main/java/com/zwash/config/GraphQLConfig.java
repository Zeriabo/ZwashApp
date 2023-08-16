package com.zwash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zwash.resolver.BookingMutationResolver;
import com.zwash.resolver.BookingResolver;
import com.zwash.resolver.CarMutationResolver;
import com.zwash.resolver.CarResolver;

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
	CarResolver carQueryResolver;

	@Autowired
	CarMutationResolver carMutationResolver;

    @Autowired
    public GraphQLConfig(
    		
    		BookingResolver bookingQueryResolver,
            BookingMutationResolver bookingMutationResolver,CarResolver carResolver) {
        this.bookingQueryResolver = bookingQueryResolver;
        this.bookingMutationResolver = bookingMutationResolver;
        this.carQueryResolver=carResolver;
   
    }

    @Bean
    public GraphQL graphQL() {


        GraphQLSchema schema = new SchemaParserBuilder()
                .file("graphql/schema.graphqls")
                .resolvers(bookingQueryResolver, bookingMutationResolver,carQueryResolver)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(schema).build();
    }
}

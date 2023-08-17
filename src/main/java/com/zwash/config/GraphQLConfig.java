package com.zwash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zwash.resolver.BookingMutationResolver;
import com.zwash.resolver.BookingResolver;
import com.zwash.resolver.CarMutationResolver;
import com.zwash.resolver.CarResolver;
import com.zwash.resolver.CarWashProgramMutationResolver;
import com.zwash.resolver.CarWashProgramResolver;

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
	CarWashProgramResolver carWashProgramQueryResolver;

	@Autowired
	CarWashProgramMutationResolver carWashProgramMutationResolver;

	@Autowired
	CarMutationResolver carMutationResolver;

    @Autowired
    public GraphQLConfig(

    		BookingResolver bookingQueryResolver,
            BookingMutationResolver bookingMutationResolver,CarMutationResolver carMutationResolver,CarResolver carResolver,CarWashProgramResolver carWashProgramQueryResolver,CarWashProgramMutationResolver carWashProgramMutationResolver) {
        this.bookingQueryResolver = bookingQueryResolver;
        this.bookingMutationResolver = bookingMutationResolver;
        this.carQueryResolver=carResolver;
        this.carMutationResolver=carMutationResolver;
        this.carWashProgramMutationResolver=carWashProgramMutationResolver;
        this.carWashProgramQueryResolver=carWashProgramQueryResolver;

    }

    @Bean
    public GraphQL graphQL() {


        GraphQLSchema schema = new SchemaParserBuilder()
                .file("graphql/schema.graphqls")
                .resolvers(bookingQueryResolver, bookingMutationResolver,carQueryResolver,carMutationResolver,carWashProgramQueryResolver,carWashProgramMutationResolver)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(schema).build();
    }
}

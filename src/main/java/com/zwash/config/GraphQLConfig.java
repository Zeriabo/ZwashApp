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
import com.zwash.resolver.StationMutationResolver;
import com.zwash.resolver.StationResolver;
import com.zwash.resolver.UserCarWashMutationResolver;
import com.zwash.resolver.UserCarWashResolver;

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
	StationMutationResolver stationMutationResolver;
	@Autowired
	StationResolver stationQueryResolver;
	
	@Autowired
	UserCarWashResolver userCarWashResolver;
	
	@Autowired UserCarWashMutationResolver userCarWashMutationResolver;
	

    @Autowired
    public GraphQLConfig(

    		BookingResolver bookingQueryResolver,
            BookingMutationResolver bookingMutationResolver,CarMutationResolver carMutationResolver,CarResolver carResolver,CarWashProgramResolver carWashProgramQueryResolver,CarWashProgramMutationResolver carWashProgramMutationResolver,
            StationMutationResolver stationMutationResolver,StationResolver stationQueryResolver,UserCarWashResolver userCarWashResolver, UserCarWashMutationResolver userCarWashMutationResolver) {
        this.bookingQueryResolver = bookingQueryResolver;
        this.bookingMutationResolver = bookingMutationResolver;
        this.carQueryResolver=carResolver;
        this.carMutationResolver=carMutationResolver;
        this.carWashProgramMutationResolver=carWashProgramMutationResolver;
        this.carWashProgramQueryResolver=carWashProgramQueryResolver;
        this.stationMutationResolver=stationMutationResolver;
        this.stationQueryResolver=stationQueryResolver;
        this.userCarWashResolver=userCarWashResolver;
        this.userCarWashMutationResolver=userCarWashMutationResolver;


    }

    @Bean
    public GraphQL graphQL() {


        GraphQLSchema schema = new SchemaParserBuilder()
                .file("graphql/schema.graphqls")
                .resolvers(bookingQueryResolver, bookingMutationResolver,carQueryResolver,carMutationResolver,carWashProgramQueryResolver,carWashProgramMutationResolver,stationMutationResolver,stationQueryResolver,userCarWashResolver,userCarWashMutationResolver)
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(schema).build();
    }
}

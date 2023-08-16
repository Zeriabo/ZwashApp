package com.zwash.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.pojos.Car;
import com.zwash.service.CarService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class CarResolver implements GraphQLQueryResolver {

    private final CarService carService;

    @Autowired
    public CarResolver(CarService carService) {
        this.carService = carService;
    }

    public Car getCar(Long id)
    {
    	return carService.getCar(id);
    }

    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
    //Mutation
//    public Car registerCar(UserCar car) throws Exception
//    {
//    	return carService.register(car);
//    }
}

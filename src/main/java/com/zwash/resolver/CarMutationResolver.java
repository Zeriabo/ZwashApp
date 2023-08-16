package com.zwash.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.security.JwtUtils;
import com.zwash.service.CarService;
import com.zwash.service.UserService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.jsonwebtoken.Claims;

@Component
public class CarMutationResolver implements GraphQLMutationResolver {

    private final CarService carService;
    private final UserService userService;

    @Autowired
    public CarMutationResolver(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    public Boolean registerCar(UserCar userCar) throws Exception {
        Car car = carService.register(userCar);
        return car instanceof Car;
    }

    public Boolean setCar(UserCar userCar) throws IncorrectTokenException {
        try {
            String registrationPlate = userCar.getRegisterationPlate();
            String token = userCar.getToken();
            Claims claims = new JwtUtils().verifyJWT(token);

            Car car = carService.getCar(registrationPlate);
            User user = userService.getUser(Long.parseLong(claims.getId()));
            car.setUser(user);

            return carService.updateCar(car);
        } catch (Exception ex) {
            throw new IncorrectTokenException("The token is not valid!");
        }
    }
}

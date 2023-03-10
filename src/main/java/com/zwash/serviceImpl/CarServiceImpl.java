package com.zwash.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zwash.exceptions.CarExistsException;
import com.zwash.exceptions.IncorrectTokenException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.repository.CarRepository;
import com.zwash.repository.UserRepository;
import com.zwash.security.JwtUtils;
import com.zwash.service.CarService;

import io.jsonwebtoken.Claims;

import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Car> getCarsOfUser(User user) throws UserIsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car register(UserCar car) throws Exception {

		try {
			Car newCar = new Car();
			Car foundcar = carRepository.findByRegisterationPlate(car.getRegisterationPlate());
			
			if (foundcar != null) {
				throw new CarExistsException();
			}
			String userToken = car.getToken();
			JwtUtils jwtUtils = new JwtUtils();

			try {

			Claims claim =	jwtUtils.verifyJWT(userToken);
			
			User user=userRepository.findByUsername(claim.getId());
			
			 newCar.setUser(user);
			 
			} catch (Exception ex) {
				throw new IncorrectTokenException("The token is not valid!");
			}
				
				newCar.setDateOfManufacture(car.getDateOfManufacture());
				newCar.setManufacture(car.getManufacture());
				newCar.setRegisterationPlate(car.getRegisterationPlate());
				
				 return carRepository.save(newCar);
			

		} catch (Exception e) {
			throw e;
		}
		

	}

	public Car finCar(Car car) throws Exception {

		try {
			car = carRepository.save(car);
		} catch (Exception e) {
			throw e;
		}

		return car;
	}

	@Override
	public Car getCar(String registerationPlate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setCar(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}

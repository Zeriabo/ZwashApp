package com.zwash.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@SuppressWarnings("serial")
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Car getCar(long id) {
		return carRepository.findById(id).get();
	}
	@Override
	public List<Car> getCarsOfUser(User user) throws UserIsNotFoundException {
		   return carRepository.findByUser(user);
	}

	@Override
	public List<Car> getAllCars() {

		 return (List<Car>) carRepository.findAll();
	}

	@Override
	public Car register(UserCar car) throws Exception {

		try {
			Car newCar = new Car();
			Car foundcar = carRepository.findByRegisterationPlate(car.getRegisterationPlate());

			if (foundcar != null) {
				throw new CarExistsException(foundcar.getRegisterationPlate());
			}
			String userToken = car.getToken();
			JwtUtils jwtUtils = new JwtUtils();

			try {

			Claims claim =	jwtUtils.verifyJWT(userToken);

			Optional<User> user=userRepository.findByUsername(claim.getSubject());

			 newCar.setUser(user.get());

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


	@Override
	public Car getCar(String registerationPlate) {
	    return carRepository.findByRegisterationPlate(registerationPlate);
	}

	@Override
	public boolean setCar(User user, Car car) throws Exception {
	    try {
	        List<Car> existingCars = carRepository.findByUser(user);
	        if (!existingCars.isEmpty()) {
	            for (Car existingCar : existingCars) {
	                if (existingCar.getCarId() == car.getCarId()) {
	                    throw new CarExistsException("Car already exists!");
	                }
	            }
	        }

	        car.setUser(user);
	        carRepository.save(car);
	        return true;
	    } catch (Exception e) {
	        // log the error or handle it as appropriate for your application
	    	   throw e;
	    }
	}

	@Override
	public boolean updateCar(Car car) throws Exception {
		 Optional<Car> carOptional = carRepository.findById((car.getCarId()));
		  if (carOptional.isPresent()) {
		        Car existingCar = carOptional.get();
		        existingCar.setManufacture(car.getManufacture());
		        existingCar.setDateOfManufacture(car.getDateOfManufacture());
		        existingCar.setRegisterationPlate(car.getRegisterationPlate());
		        existingCar.setUser(car.getUser());
		        carRepository.save(existingCar);
		        return true;
		    }
		    return false;

	}



}

package com.zwash.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zwash.exceptions.CarExistsException;
import com.zwash.exceptions.UserIsNotFoundException;
import com.zwash.pojos.Car;
import com.zwash.pojos.User;
import com.zwash.pojos.UserCar;
import com.zwash.repository.CarRepository;
import com.zwash.service.CarService;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;


	@Override
	public List<Car> getCarsOfUser(User user) throws UserIsNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(UserCar car) throws Exception {
		
		try {
		
			Car foundcar = carRepository.findByRegisterationPlate(car.getRegisterationPlate());
			
			if(foundcar !=null)
			{
				throw new CarExistsException();
			}else {
				Car newCar = new Car();
				newCar.setDateOfManufacture(car.getDateOfManufacture());
				newCar.setManufacture(car.getManufacture());
				newCar.setRegisterationPlate(car.getRegisterationPlate());
				
				// must create a JWT 
//				//newCar.setOwnerId(car.getUser());
			}
		
		}
		catch (Exception e) {
			throw e;
		}
		return true;
	}

    public Car finCar(Car car) throws Exception {
		
		try {
		car =	carRepository.save(car);
		}
		catch (Exception e) {
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

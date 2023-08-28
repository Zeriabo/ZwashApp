package com.zwash.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.exceptions.CarDoesNotExistException;
import com.zwash.exceptions.NoNonExecutedBookingsFoundException;
import com.zwash.pojos.Booking;
import com.zwash.pojos.Car;
import com.zwash.service.BookingService;
import com.zwash.service.CarService;
import com.zwash.service.CarWashService;
import com.zwash.service.RegistrationPlateMonitorService;

@Service
public class RegistrationPlateMonitorServiceImpl implements RegistrationPlateMonitorService {
    private String[] registeredPlates;
	@Autowired
	private CarWashService carWashService;
	@Autowired
	CarService carService;
	@Autowired
	BookingService bookingService;


    public RegistrationPlateMonitorServiceImpl() {
        registeredPlates = new String[0];
    }

    @Override
    public void startMonitoring() {
        // Simulating an API call to get a list of car registration plates
 //       String registrationPlate = getCarRegistrationPlateFromAPI();

//        if (registrationPlate != null) {
//            Timer timer = new Timer();
//
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    String registrationPlate = getCarRegistrationPlateFromAPI();
//                    if (registrationPlate != null) {
//
//                    	Car car =  carService.getCar(registrationPlate);
//                    	if(car!=null)
//                    	{
//                    		 performCarWash(car);
//                    	}
//
//                    }
//                }
//            }, 0, 5000);
//
//        }
    }


    @Override
    public void addRegistrationPlate(String plateNumber) throws CarDoesNotExistException  {

    	Car car =  carService.getCar(plateNumber);
    	if(car!=null)
    	{
    	     String[] updatedPlates = new String[registeredPlates.length + 1];
    	        System.arraycopy(registeredPlates, 0, updatedPlates, 0, registeredPlates.length);
    	        updatedPlates[registeredPlates.length] = plateNumber;
    	        registeredPlates = updatedPlates;

    	        performCarWash(car);
    	}

    }

    @Override
    public void performCarWash(Car car) {


    	     List<Booking> bookings =   bookingService.getBookingsByCarId(car.getCarId());

    	       Booking latestBooking = null;

    	       for (Booking booking : bookings) {
    	           if (!booking.isExecuted()) {
    	               if (latestBooking == null ) {
    	                   latestBooking = booking;
    	               }
    	           }
    	       }

    	       if (latestBooking != null) {
    	    	    // Found the latest non-executed booking
    	    	    // You can perform further operations with the booking
    	           System.out.println("Car wash initiated for plate number: " + car.getRegisterationPlate());


    	       carWashService.executeCarWash(latestBooking);
    	    	} else {
    	    	    // No non-executed bookings found
    	    	    throw new NoNonExecutedBookingsFoundException("No non-executed bookings found. for:"+car.getRegisterationPlate());

    	    	}
    	        // Perform the car wash steps here
    	        System.out.println("Car wash completed for plate number: " + car.getRegisterationPlate());


    }

    private String getCarRegistrationPlateFromAPI() {
        // Simulating an API request to get the car registration plate
        // You would replace this code with your actual API integration code
        // In this example, we're generating a random plate number
        int randomInt = (int) (Math.random() * 5000);
        return "ABC" + randomInt;
    }
}

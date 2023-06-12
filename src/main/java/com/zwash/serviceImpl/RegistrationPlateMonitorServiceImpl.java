package com.zwash.serviceImpl;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.zwash.service.RegistrationPlateMonitorService;

@Service
public class RegistrationPlateMonitorServiceImpl implements RegistrationPlateMonitorService {
    private String[] registeredPlates;

    public RegistrationPlateMonitorServiceImpl() {
        registeredPlates = new String[0];
    }

    @Override
    public void startMonitoring() {
        // Simulating an API call to get a list of car registration plates
        String registrationPlate = getCarRegistrationPlateFromAPI();

        if (registrationPlate != null) {
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    String registrationPlate = getCarRegistrationPlateFromAPI();
                    if (registrationPlate != null) {
                        performCarWash(registrationPlate);
                    }
                }
            }, 0, 5000);
            
        }
    }


    @Override
    public void addRegistrationPlate(String plateNumber) {
        String[] updatedPlates = new String[registeredPlates.length + 1];
        System.arraycopy(registeredPlates, 0, updatedPlates, 0, registeredPlates.length);
        updatedPlates[registeredPlates.length] = plateNumber;
        registeredPlates = updatedPlates;

        performCarWash(plateNumber);
    }

    @Override
    public void performCarWash(String plateNumber) {
        System.out.println("Car wash initiated for plate number: " + plateNumber);
        // Perform the car wash steps here
        System.out.println("Car wash completed for plate number: " + plateNumber);
    }
    
    private String getCarRegistrationPlateFromAPI() {
        // Simulating an API request to get the car registration plate
        // You would replace this code with your actual API integration code
        // In this example, we're generating a random plate number
        int randomInt = (int) (Math.random() * 1000);
        return "ABC" + randomInt;
    }
}

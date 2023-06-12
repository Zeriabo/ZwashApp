package com.zwash.service;



import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.zwash.exceptions.CarDoesNotExistException;
import com.zwash.pojos.Car;

@Service
public interface RegistrationPlateMonitorService {

    void startMonitoring();

    void addRegistrationPlate(String plateNumber);

    void  performCarWash(Car car)  throws CarDoesNotExistException;

    class MonitorTask extends TimerTask {
        private final RegistrationPlateMonitorService monitorService;
        private final String plateNumber;

        public MonitorTask(RegistrationPlateMonitorService monitorService, String plateNumber) {
            this.monitorService = monitorService;
            this.plateNumber = plateNumber;
        }

        @Override
        public void run() {
            monitorService.addRegistrationPlate(plateNumber);
        }
    }
}

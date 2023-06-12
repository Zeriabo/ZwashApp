package com.zwash.service;



import java.util.TimerTask;

import org.springframework.stereotype.Service;

@Service
public interface RegistrationPlateMonitorService {

    void startMonitoring();

    void addRegistrationPlate(String plateNumber);

    void performCarWash(String plateNumber);

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

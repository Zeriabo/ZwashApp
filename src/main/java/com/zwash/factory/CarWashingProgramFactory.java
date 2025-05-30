package com.zwash.factory;

import com.zwash.pojos.CarWashingProgram;
import com.zwash.pojos.FoamCarWashingProgram;
import com.zwash.pojos.HighPressureCarWashingProgram;
import com.zwash.pojos.TouchlessCarWashingProgram;

public abstract class CarWashingProgramFactory {

    public static CarWashingProgram createCarWashingProgram(String dtype) {
        switch (dtype) {
            case "foam":
                return new FoamCarWashingProgram();
            case "high_pressure":
                return new HighPressureCarWashingProgram();
            case "touch_less":
                return new TouchlessCarWashingProgram();
            default:
                throw new IllegalArgumentException("Invalid washing program : " + dtype);
        }
    }
}

package com.step.model;

import com.step.enums.FuelType;
import java.time.LocalDate;

public class Car {
    String manufacturerAuto;
    String modelAuto;
    LocalDate yearAuto;
    int motorCapacity;
    FuelType fuelAuto;

    public String getManufacturerAuto() {
        return manufacturerAuto;
    }

    public void setManufacturerAuto(String manufacturerAuto) {
        this.manufacturerAuto = manufacturerAuto;
    }

    public String getModelAuto() {
        return modelAuto;
    }

    public void setModelAuto(String modelAuto) {
        this.modelAuto = modelAuto;
    }

    public LocalDate getYearAuto() {
        return yearAuto;
    }

    public void setYearAuto(LocalDate yearAuto) {
        this.yearAuto = yearAuto;
    }

    public int getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(int motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public FuelType getFuelAuto() {
        return fuelAuto;
    }

    public void setFuelAuto(FuelType fuelAuto) {
        this.fuelAuto = fuelAuto;
    }

    public Car(String manufacturerAuto, String modelAuto, LocalDate yearAuto, int motorCapacity, FuelType fuelAuto) {
        this.manufacturerAuto = manufacturerAuto;
        this.modelAuto = modelAuto;
        this.yearAuto = yearAuto;
        this.motorCapacity = motorCapacity;
        this.fuelAuto = fuelAuto;
    }
    
}

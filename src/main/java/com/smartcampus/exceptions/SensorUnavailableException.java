/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.smartcampus.exceptions;

/**
 *
 * @author anudhi
 */

public class SensorUnavailableException extends RuntimeException {
    public SensorUnavailableException(String sensorId, String status) {
        super("Sensor " + sensorId + " is under maintenance and cannot accept new readings.");
    }
}

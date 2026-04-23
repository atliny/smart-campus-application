/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anudhi
 */

public class Room {
    private String id;      
    private String name;     
    private int capacity;    
    private List<String> sensorIds = new ArrayList<>(); 

    //constructors
    public Room() {}

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    //getters
    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public int getCapacity() { 
        return capacity; 
    }

    public List<String> getSensorIds() { 
        return sensorIds; 
    }

    //setters
    public void setId(String id) { 
        this.id = id; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public void setCapacity(int capacity) { 
        this.capacity = capacity; 
    }
    
    public void setSensorIds(List<String> sensorIds) { 
        this.sensorIds = sensorIds; 
    }
    
    //helpers
    public void addSensorId(String sensorId) {
        if (!this.sensorIds.contains(sensorId)) {
            this.sensorIds.add(sensorId);
        }
    }

    public void removeSensorId(String sensorId) {
        this.sensorIds.remove(sensorId);
    }

    @Override
    public String toString() {
        return "Room{id='" + id + "', name='" + name + "', capacity=" + capacity
                + ", sensorIds=" + sensorIds + "}";
    }
}
package com.georgematlis.travelingsalesmanproblem;

import java.util.ArrayList;

public class RoutePath {

    private String name;
    private ArrayList<Double> coordinates;

    public RoutePath(String name, ArrayList<Double> coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }
}

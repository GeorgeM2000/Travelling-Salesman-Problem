package com.georgematlis.travelingsalesmanproblem;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelingSalesmanProblem {

    // Method to split a string and get the latitude and longitude
    public static ArrayList<Double> get_coordinates_from_string(String coordinates_string) {
        String[] split = coordinates_string.split("\\s+");
        return new ArrayList<>() {{
            add(Double.parseDouble(split[0]));
            add(Double.parseDouble(split[1]));
        }};
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Coordinates list
        ArrayList<ArrayList<Double>> coordinates = new ArrayList<>();

        // List to store the names of the locations
        ArrayList<String> identifications = new ArrayList<>();

        String exit;

        // User current location prompt
        System.out.println("Enter the current location(coordinates) and assign it a name.");
        System.out.print("Name: ");
        String current_location_name = scanner.nextLine();
        System.out.print("Location: ");
        String current_location = scanner.nextLine();

        // Add location info to lists.
        coordinates.add(get_coordinates_from_string(current_location));
        identifications.add(current_location_name);

        System.out.println();

        // Location of points
        do {
            System.out.println("Enter a location(coordinates) and assign it a name.");
            System.out.print("Name: ");
            String point_location_name = scanner.nextLine();
            System.out.print("Location: ");
            String point_location = scanner.nextLine();

            // Add location info to lists.
            coordinates.add(get_coordinates_from_string(point_location));
            identifications.add(point_location_name);

            System.out.println();

            System.out.println("Would you like to exit? y/n");
            exit = scanner.nextLine();

        } while (!exit.equals("y"));

        // Base fixed location prompt
        System.out.println("Enter the base location(coordinates) and assign it a name.");
        System.out.print("Name: ");
        String base_location_name = scanner.nextLine();
        System.out.print("Location: ");
        String base_location = scanner.nextLine();

        // Add location info to lists.
        coordinates.add(get_coordinates_from_string(base_location));
        identifications.add(base_location_name);


        TSP tsp = new TSP(coordinates, identifications);

        // Get the optimal path and store it in a list
        ArrayList<RoutePath> route_paths = tsp.solve();

        // Print the optimal route path
        System.out.println("\nOptimal path is: ");
        System.out.println(identifications.get(0));
        for(RoutePath routePath: route_paths) {
            System.out.println(routePath.getName());
        }
        System.out.println(identifications.get(identifications.size()-1));

    }
}

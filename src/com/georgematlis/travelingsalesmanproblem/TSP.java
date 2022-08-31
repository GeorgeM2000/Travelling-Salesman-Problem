package com.georgematlis.travelingsalesmanproblem;

import java.util.ArrayList;
import java.util.Objects;

public class TSP {
    private final ArrayList<ArrayList<Double>> coordinates;
    private final ArrayList<String> identifications;

    public TSP(ArrayList<ArrayList<Double>> coordinates, ArrayList<String> identifications) {
        this.identifications = identifications;
        this.coordinates = coordinates;
    }

    private Integer[] slice(Integer[] array, int start, int end) {
        if(start >= array.length) return null;
        Integer[] slice = new Integer[end-start];
        System.arraycopy(array, start, slice, 0, slice.length);

        return slice;
    }

    private Integer[] concat(Integer[] A, Integer[] B) {
        if(B == null && A == null) {
            return null;
        }else if(A == null) {
            return B;
        } else if(B == null) {
            return A;
        }

        Integer[] concatArray = new Integer[A.length + B.length];
        int position = 0;
        for (Integer value : A) {
            concatArray[position] = value;
            position++;
        }

        for (Integer integer : B) {
            concatArray[position] = integer;
            position++;
        }

        return concatArray;
    }


    // Method to find all possible permutations given a vertex of integers.
    private ArrayList<Integer[]> permutations(ArrayList<Integer> vertex) {
        int n = vertex.size();

        Integer[] indices = new Integer[n];
        for(int i=0; i<n; i++) {
            indices[i] = i;
        }

        Integer[] cycles = new Integer[n];
        for(int i = n; i>0; i--) {
            cycles[n-i] = i;
        }

        ArrayList<Integer[]> results = new ArrayList<>();
        Integer[] res = new Integer[n];
        for(int i = 0; i< n; i++) {
            res[i] = vertex.get(indices[i]);
        }
        results.add(res);

        boolean broken  = false;
        if(n > 0) {
            do {
                for (int i = n - 1; i >= 0; i--) {
                    cycles[i]--;
                    if (cycles[i] == 0) {
                        indices = concat(slice(Objects.requireNonNull(indices), 0, i), concat(slice(indices, i + 1, n), slice(indices, i, i + 1)));

                        cycles[i] = n - i;
                        broken = false;
                    } else {
                        int j = cycles[i];
                        int x = Objects.requireNonNull(indices)[i];
                        indices[i] = indices[n - j];
                        indices[n - j] = x;
                        Integer[] res1 = new Integer[n];
                        for (int k = 0; k < n; k++) {
                            res1[k] = vertex.get(indices[k]);
                        }
                        results.add(res1);
                        broken = true;
                        break;
                    }
                }
            } while (broken);
        }
        return results;
    }

    // Method to calculate the distance between two points on earth.
    private double distance(double latitude_A, double latitude_B, double longitude_A, double longitude_B) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        longitude_A = Math.toRadians(longitude_A);
        longitude_B = Math.toRadians(longitude_B);
        latitude_A = Math.toRadians(latitude_A);
        latitude_B = Math.toRadians(latitude_B);

        // Haversine formula
        double longitude_distance = longitude_B - longitude_A;
        double latitude_distance = latitude_B - latitude_A;
        double a = Math.pow(Math.sin(latitude_distance / 2), 2)
                + Math.cos(latitude_A) * Math.cos(latitude_B)
                * Math.pow(Math.sin(longitude_distance / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }


    // Method to create a distance matrix
    private ArrayList<ArrayList<Double>> distance_matrix() {
        ArrayList<ArrayList<Double>> dis_matrix = new ArrayList<>();

        for(int i=0; i<this.coordinates.size(); i++) {
            dis_matrix.add(new ArrayList<>());
            for (ArrayList<Double> coordinate : this.coordinates) {
                dis_matrix.get(i).add(distance(this.coordinates.get(i).get(0), coordinate.get(0), coordinates.get(i).get(1), coordinate.get(1)));
            }
        }
        return dis_matrix;
    }

    // Method to calculate the optimal route path.
    private ArrayList<Integer> calculate_route_path(ArrayList<ArrayList<Double>> graph, int V) {
        ArrayList<Integer> vertex = new ArrayList<>();
        for(int i=0; i<V-1; i++) {
            if(i != 0) {
                vertex.add(i);
            }
        }

        ArrayList<Integer> route_path = new ArrayList<>();
        double minimum_path = 1000000000.0;
        ArrayList<Integer[]> next_permutations = permutations(vertex);   // List of all possible paths


        for(Integer[] permutation: next_permutations) {
            double current_path_weight = 0.0; // The weight of the current path
            ArrayList<Integer> currentPath = new ArrayList<>(); // The indexes of the path

            int k = 0;
            for(Integer element: permutation) {
                currentPath.add(element);
                current_path_weight += graph.get(k).get(element);
                k = element;
            }

            current_path_weight += graph.get(k).get(V-1);

            if(current_path_weight < minimum_path) {
                minimum_path = current_path_weight;
                route_path = currentPath;
            }
        }
        return route_path;
    }



    public ArrayList<RoutePath> solve() {
        ArrayList<ArrayList<Double>> graph = distance_matrix();

        ArrayList<RoutePath> route_paths = new ArrayList<>();
        ArrayList<Integer> optimal_path = calculate_route_path(graph, coordinates.size());

        for (Integer integer : optimal_path) {
            route_paths.add(new RoutePath(identifications.get(integer), coordinates.get(integer)));
        }

        return route_paths;
    }

}

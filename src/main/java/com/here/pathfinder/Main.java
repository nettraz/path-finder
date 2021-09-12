package com.here.pathfinder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/main/resources/scenarios/graph.txt";
        String startNode = "A";
        String endNode = "F";
        if (args.length == 3) {
            inputFile = args[0];
            startNode = args[1];
            endNode = args[2];
        }
        PathFinder pf = new PathFinder(inputFile, startNode, endNode);
        System.out.println(pf.getPath());
        System.out.println("Total distance: " + pf.getDistance());
    }
}

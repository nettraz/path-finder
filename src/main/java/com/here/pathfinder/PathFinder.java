package com.here.pathfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PathFinder {
    private String inputFile;
    private String startNode;
    private String endNode;
    private Map<String, Integer> distanceMap;
    private Map<String, String> nodeLinkMap;
    private PriorityQueue<Node> unvisited;
    private Set<String> visited;
    private int n; // Number of nodes
    private Map<String, List<Node>> adjacencyMap;

    public PathFinder(String inputFile, String startNode, String endNode) throws IOException {
        this.inputFile = inputFile;
        this.startNode = startNode;
        this.endNode = endNode;
        this.distanceMap = new HashMap<>();
        this.nodeLinkMap = new HashMap<>();
        this.unvisited = new PriorityQueue<>();
        this.visited = new HashSet<>();
        this.n = 0;
        this.adjacencyMap = new HashMap<>();
        readInputFile();
        dijkstra();
    }

    private void readInputFile() throws IOException {
        Path path = Paths.get(inputFile);

        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        while (line != null && !line.equals("---")) {
            String[] lineSplit = line.split(":");
            String nodeName = lineSplit[0];
            String[] adjacentNodesArray = lineSplit[1].split(";");
            List<Node> adjacentNodesList = new ArrayList<>();
            for (String adjacentNode : adjacentNodesArray) {
                String[] adjacentNodeSplit = adjacentNode.split("-");
                int cost = Integer.parseInt(adjacentNodeSplit[1]);
                adjacentNodesList.add(new Node(adjacentNodeSplit[0], cost));
            }
            adjacencyMap.put(nodeName, adjacentNodesList);
            line = reader.readLine();
            this.n++;
        }
    }

    private void dijkstra() {
        for (String nodeName : adjacencyMap.keySet()) {
            distanceMap.put(nodeName, Integer.MAX_VALUE);
        }
        distanceMap.put(startNode, 0);
        unvisited.add(new Node(startNode, 0));

        while (visited.size() < n) {
            if (unvisited.isEmpty()) {
                return;
            }

            String nodeName = unvisited.remove().getName();
            if (visited.contains(nodeName)) {
                continue;
            }
            visited.add(nodeName);
            processNeighbours(nodeName);
        }
    }

    private void processNeighbours(String nodeName) {
        int distance;
        int newDistance;

        for (Node neighbour : adjacencyMap.get(nodeName)) {
            if (!visited.contains(neighbour.getName())) {
                distance = neighbour.getCost();
                int nodeDistance = distanceMap.get(nodeName) == Integer.MAX_VALUE ? 0 : distanceMap.get(nodeName);
                newDistance = nodeDistance + distance;
                if (newDistance < distanceMap.get(neighbour.getName())) {
                    distanceMap.put(neighbour.getName(), newDistance);
                    nodeLinkMap.put(neighbour.getName(), nodeName);
                }
                unvisited.add(new Node(neighbour.getName(), distanceMap.get(neighbour.getName())));
            }
        }
    }

    public String getPath() {
        if (!nodeLinkMap.containsKey(endNode)) {
            return "Couldn't find path from node " + startNode + " to node " + endNode;
        }
        StringBuilder sb = new StringBuilder(endNode);
        String prevNode = nodeLinkMap.get(endNode);
        while (!prevNode.equals(startNode)) {
            sb.append("-");
            sb.append(prevNode);
            prevNode = nodeLinkMap.get(prevNode);
        }
        sb.append("-");
        sb.append(startNode);
        return sb.reverse().toString();
    }

    public int getDistance() {
        if (!distanceMap.containsKey(endNode)) {
            return -1;
        }
        return distanceMap.get(endNode);
    }
}

class Node implements Comparable<Node> {
    private String name;
    private int cost;

    public Node(String name, int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Node distance can't be negative");
        }
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

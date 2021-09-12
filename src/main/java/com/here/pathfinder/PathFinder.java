package com.here.pathfinder;

import java.util.Comparator;
import java.util.List;

public class PathFinder {

}

class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node() {}

    public Node(int node, int cost) {
        if (cost < 0) {

        }
        this.node = node;
        this.cost = cost;
    }

    @Override public int compare(Node node1, Node node2){
        return Integer.compare(node1.cost, node2.cost);
    }
}

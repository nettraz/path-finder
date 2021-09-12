package com.here.pathfinder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathFinderFunctionalTest {
    private static final String INPUT_FILE_1 = "src/main/resources/scenarios/graph.txt";
    private static final String INPUT_FILE_2 = "src/main/resources/scenarios/graph-2.txt";

    @Test
    void shouldFindPathAtoF() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_1, "A", "F");
        assertEquals(pf.getPath(), "A-E-G-F");
        assertEquals(pf.getDistance(), 5);
    }

    @Test
    void shouldFindPathAtoD() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_1, "A", "D");
        assertEquals(pf.getPath(), "A-E-G-F-D");
        assertEquals(pf.getDistance(), 7);
    }

    @Test
    void shouldFindPathBtoC() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_1, "B", "C");
        assertEquals(pf.getPath(), "B-A-C");
        assertEquals(pf.getDistance(), 4);
    }

    @Test
    void shouldFindPathCtoD() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_1, "C", "D");
        assertEquals(pf.getPath(), "C-A-E-G-F-D");
        assertEquals(pf.getDistance(), 8);
    }

    @Test
    void shouldFindPathAtoFInFile2() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_2, "A", "F");
        assertEquals(pf.getPath(), "A-D-E-G-F");
        assertEquals(pf.getDistance(), 9);
    }

    @Test
    void shouldFindPathCtoDInFile2() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_2, "C", "D");
        assertEquals(pf.getPath(), "C-G-E-D");
        assertEquals(pf.getDistance(), 6);
    }

    @Test
    void shouldFindPathBtoDInFile2() throws Exception {
        PathFinder pf = new PathFinder(INPUT_FILE_2, "B", "D");
        assertEquals(pf.getPath(), "B-A-D");
        assertEquals(pf.getDistance(), 8);
    }
}

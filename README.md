# path-finder
Find shortest path in a graph

## How to build
Change directory to the root folder of the project:
```bash
cd <project-folder>
```
Build with maven:
```bash
mvn clean package
```
## How to run
Run the jar file without any arguments:
```bash
java -jar target/path-finder-1.0-SNAPSHOT.jar
```
Run the jar file with the following arguments <path-to-graph-file>, <start-node>, <end-node>
```bash
java -jar target/path-finder-1.0-SNAPSHOT.jar "src/main/resources/scenarios/graph-2.txt" "A" "G""
```
Sample output:
```bash
A-D-E-G
Total distance: 7
```
or:
```bash
Couldn't find path from node A to node H
Total distance: -1
```
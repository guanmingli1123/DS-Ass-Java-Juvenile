import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Navigation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Number of the test cases
        int num = Integer.valueOf(scanner.nextLine());
        for(int i=0;i<num;i++){
            // Number of the connection in this graph
            int numberOfConnections = Integer.valueOf(scanner.nextLine());
            Graph graph = new Graph();
            for(int j=0;j<numberOfConnections;j++){
                String[] stations = scanner.nextLine().split(" => ");
                String source = stations[0];
                String destination = stations[1];
                graph.addVertex(source);
                graph.addVertex(destination);
                graph.addEdge(source,destination);
                graph.addEdge(destination,source);
            }
            //The number of queries from user
            int queries = Integer.parseInt(scanner.nextLine());
            for(int z=0;z<queries;z++){
                String[] query = scanner.nextLine().split(" -> ");
                String from = query[0];
                String to = query[1];
                graph.bfs(from,to);
            }
        }
    }
}

class Vertex<T extends Comparable<T>,N extends Comparable <N>> {
    /***
     * To collect the information of this vertex
     */
    T vertexInfo;

    /***
     * store the link to next Vertex
     */
    Vertex<T,N> nextVertex;

    /***
     * store all the information that this vertex is connected to another Vertex
     */
    Edge<T,N> firstEdge;

    /***
     * To check whether this vertex is visited or not
     */
    boolean visited = false;

    /***
     * Store the path information for previous vertex
     */
    Vertex<T,N> prevVertex;

    /***
     * Initiliaze the vertex
     */
    public Vertex(){
        vertexInfo = null;
        nextVertex = null;
        firstEdge = null;
        prevVertex = null;
    }

    /***
     * Initialize vertex with the information and next Vertex to it
     * @param vInfo the information of this vertex
     * @param next the vertex next to this vertex
     */
    public Vertex(T vInfo, Vertex<T,N> next){
        vertexInfo = vInfo;
        nextVertex = next;
        firstEdge = null;
        prevVertex = null;
    }
}

class Edge<T extends Comparable<T>,N extends Comparable <N>> {
    /***
     * Vertex information
     */
    Vertex<T,N> toVertex;

    /***
     * Edge that is connect to next Vertex
     */
    Edge<T,N> nextEdge;

    /***
     * Initialise the edge
     */
    public Edge(){
        toVertex = null;
        nextEdge = null;
    }

    /***
     * Initialize the edge with the next vertex and next edge
     * @param destination next Vertex
     * @param a next edge
     */
    public Edge(Vertex<T,N> destination, Edge<T,N> a){
        toVertex = destination;
        nextEdge = a;
    }
}

class Graph <T extends Comparable<T>,N extends Comparable <N>>{
    /***
     * Head of the vertex
     */
    Vertex<T,N> head;
    /***
     * size of the graph
     */
    int size;

     /***
     * Initialize the graph
     */
    public Graph(){
        head = null;
        size = 0;
    }

    /***
     * To get the size of graph
     * @return size of graph
     */
    public int getSize(){
        return size;
    }

    /***
     * Check whether the vertex is exist inside the graph or not
     * @param v Vertex Information
     * @return boolean
     */
    public boolean hasVertex(T v){
        if(head == null){
            return false;
        }
        Vertex<T,N> temp = head;
        while(temp != null){
            if(temp.vertexInfo.compareTo(v) == 0){
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }

    /***
     * Add vertex to the graph
     * @param v Vertex Information
     * @return boolean
     */
    public boolean addVertex(T v){
        if(hasVertex(v) == false){
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v, null);
            if(head == null){
                head = newVertex;
            }else{
                Vertex<T,N> previous = head;
                while(temp!= null){
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        }
        else
            return false;
    }

    /***
     * Check whether the edge is exist or not
     * @param source the source Vertex
     * @param destination the destination vertex
     * @return boolean
     */
    public boolean hasEdge(T source, T destination){
        if(head == null){
            return false;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return false;
        }
        Vertex <T,N> sourceVertex = head;
        while(sourceVertex != null){
            if(sourceVertex.vertexInfo.compareTo(source)== 0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge != null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)== 0){
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    /***
     * Add the edge to the graph
     * @param source Source vertex to be added
     * @param destination the link to the destination
     * @return boolean
     */
    public boolean addEdge(T source, T destination){
        if(head == null){
            return false;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return false;
        }
        Vertex <T,N> sourceVertex = head;
        while(sourceVertex != null){
            if(sourceVertex.vertexInfo.compareTo(source)== 0){
                Vertex<T,N> destinationVertex = head;
                while(destinationVertex != null){
                    if(destinationVertex.vertexInfo.compareTo(destination)== 0){
                        Edge<T,N> currentEdge = sourceVertex.firstEdge;
                        if(currentEdge == null){
                            Edge<T,N> newEdge = new Edge<>(destinationVertex,currentEdge);
                            sourceVertex.firstEdge = newEdge;
                            return true;
                        }
                        while(currentEdge.nextEdge!= null){
                            currentEdge = currentEdge.nextEdge;
                        }
                        Edge<T,N> newEdge = new Edge<>(destinationVertex,null);
                        currentEdge.nextEdge = newEdge;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    /***
     * Get all the connection for the vertex
     * @param v Vertex as a starting point
     * @return a list of vertex that have connection with this vertex
     */
    public ArrayList<Vertex<T,N>> getNeighbours(Vertex<T,N> v){
        if(!hasVertex(v.vertexInfo)){
            return null;
        }
        ArrayList<Vertex<T,N>> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while(temp != null){
            if(temp.vertexInfo.compareTo(v.vertexInfo) == 0){
                Edge<T,N> currentEdge = temp.firstEdge;
                while(currentEdge != null){
                    list.add(currentEdge.toVertex);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    /***
     * Breath First Search Algorithm to search shortest path
     * @param source Starting point of the user
     * @param destination Destination of the user want to reach
     */
    public void bfs(T source, T destination){
        //Create queue
        LinkedList<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> clearPath = new ArrayList<>();
        if(head == null){
            return;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return;
        }
        Vertex <T,N> sourceVertex = head;
        //Visit and add start node to the queue
        while(sourceVertex != null){
            if(sourceVertex.vertexInfo.compareTo(source)== 0){
                sourceVertex.visited = true;
                queue.add(sourceVertex);
                break;
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        //BFS until queue is empty and not reached to the end node
        while(!queue.isEmpty()){
            //pop a node from queue for search operation
            Vertex current_node = queue.removeFirst();
            //Loop through neighbors node to find the 'end'
            ArrayList<Vertex> neighbour = getNeighbours(current_node);
            for(int i=0;i<neighbour.size();i++){
                clearPath.add(neighbour.get(i));
            }
            clearPath.add(sourceVertex);
            for(Vertex node: neighbour){
                if(node.visited != true){
                    //Visit and add the node to the queue
                    node.visited = true;
                    queue.add(node);
                    //update its precedings nodes
                    node.prevVertex = current_node;
                    //If reached the end node then stop BFS
                    if(node.vertexInfo.compareTo(destination)==0){
                        queue.clear();
                        break;
                    }
                }
            }
        }
        trace_route(source,destination);
        for(int i=0;i<clearPath.size();i++){
            clearPath.get(i).visited = false;
            clearPath.get(i).prevVertex = null;
        }
    }

    /***
     * Function to trace the route using preceding nodes and print the routes
     * @param source Starting point of the path
     * @param destination End point of the path
     */
    private void trace_route(T source,T destination){
        Vertex<T,N> destinationVertex = head;
        while(destinationVertex != null){
            if(destinationVertex.vertexInfo.compareTo(destination) == 0){
                break;
            }
            destinationVertex = destinationVertex.nextVertex;
        }
        ArrayList<Vertex> route = new ArrayList<>();
        //Loop until node is null to reach start node
        //becasue start.prev == null
        while(destinationVertex != null){
            route.add(destinationVertex);
            destinationVertex = destinationVertex.prevVertex;
        }

        //Reverse the route - bring start to the front
        //Output the route
        for(int i= route.size()-1;i>=0;i--){
            if(i == 0){
                System.out.print(route.get(i).vertexInfo + "\n");
                break;
            }
            System.out.print(route.get(i).vertexInfo + " -> ");
        }
    }
}

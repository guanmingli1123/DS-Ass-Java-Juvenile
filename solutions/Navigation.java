import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Navigation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        for(int i=0;i<num;i++){
            int numberOfConnections = Integer.parseInt(scanner.nextLine());
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
    T vertexInfo;
    int indeg;
    int outdeg;
    Vertex<T,N> nextVertex;
    Edge<T,N> firstEdge;
    boolean visited = false;
    Vertex<T,N> prevVertex;

    public Vertex(){
        vertexInfo = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
        prevVertex = null;
    }

    public Vertex(T vInfo, Vertex<T,N> next){
        vertexInfo = vInfo;
        indeg =0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
        prevVertex = null;
    }

    public void unVisit(){
        this.visited = false;
    }
}

class Edge<T extends Comparable<T>,N extends Comparable <N>> {
    Vertex<T,N> toVertex;
    Edge<T,N> nextEdge;

    public Edge(){
        toVertex = null;
        nextEdge = null;
    }

    public Edge(Vertex<T,N> destination, Edge<T,N> a){
        toVertex = destination;
        nextEdge = a;
    }
}

class Graph <T extends Comparable<T>,N extends Comparable <N>>{
    Vertex<T,N> head;
    int size;

    public Graph(){
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

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

    public int getIndeg(T v){
        if(hasVertex(v) == true){
            Vertex<T,N> temp = head;
            while(temp != null){
                if(temp.vertexInfo.compareTo(v) == 0){
                    return temp.indeg;
                }
                temp = temp.nextVertex;
            }
        }
        return -1;
    }

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
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

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

    public void bfs(T source, T destination){
        //Create queue
        Queue<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> clearpath = new ArrayList<>();
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
            Vertex current_node = queue.poll();
            //Loop through neighbors node to find the 'end'
            ArrayList<Vertex> neighbour = getNeighbours(current_node);
            for(int i=0;i<neighbour.size();i++){
                clearpath.add(neighbour.get(i));
            }
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
        trace_route(destination);
        for(int i=0;i<clearpath.size();i++){
            clearpath.get(i).unVisit();
            clearpath.get(i).prevVertex = null;
        }
    }

    //Function to trace the route using preceding nodes
    private void trace_route(T destination){
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
        Collections.reverse(route);
        //Output the route
        for(int i=0;i<route.size();i++){
            if(i == route.size()-1){
                System.out.print(route.get(i).vertexInfo + "\n");
                break;
            }
            System.out.print(route.get(i).vertexInfo + " -> ");
        }
    }
}

import java.sql.*;

public class NavigationDatabase {
    private String jdbcURL = "jdbc:mysql://localhost:3306/mrtstation";
    private String username = "root";
    private String password = "Lhtmilk2027";
    public Graph graph = new Graph();

    /***
     * Retrieve the information from station to station and adding them into graph
     */
    public void retrieve(){
        try{
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);

            String sql = "SELECT Origin, Destination FROM book1";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()){
                String origin = result.getString("Origin");
                String destination = result.getString("Destination");
                graph.addVertex(origin);
                graph.addVertex(destination);
                graph.addEdge(origin, destination);
                graph.addEdge(destination, origin);
            }
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}

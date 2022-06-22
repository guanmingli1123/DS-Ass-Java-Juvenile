import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistogramDatabase {
    private String jdbcURL = "jdbc:mysql://localhost:3306/histogram";
    private String username = "root";
    private String password = "Lhtmilk2027";

    /***
     * The double type of array list store the data
     */
    private ArrayList<Double> dataPoints = new ArrayList<>();

    /***
     * Number of data and number of bins
     */
    private int bin,data;

    /***
     * Retrieve number of data and the number of bins
     */
    public void retrieveNumberOfData(){
        try{
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);

            String sql = "SELECT numberdata, bins FROM histogramdata";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.next()) {
                data = result.getInt("numberdata");
                bin = result.getInt("bins");
            }
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /***
     * Retrieve all the data inside the database
     */
    public void retrieveData(){
        try{
            Connection connection = DriverManager.getConnection(jdbcURL,username,password);

            String sql = "SELECT data FROM histogramdata";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()){
                double data = result.getInt("data");
                dataPoints.add(data);
            }
            Collections.sort(dataPoints);
            connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    /***
     * Gather all the data inside the database
     * @return all the data in the database
     */
    public double[] getData(){
        double[] datapoint = new double[dataPoints.size()];
        for(int i=0;i<dataPoints.size();i++) {
            datapoint[i] = dataPoints.get(i);
        }
        return datapoint;
    }

    /***
     * Get the number of bins
     * @return number of bins
     */
    public int getBin(){
        return bin;
    }

}

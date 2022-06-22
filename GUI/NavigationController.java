import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {
    /***
     * Initialise navigation database
     */
    private NavigationDatabase a = new NavigationDatabase();

    /***
     * A Text Area to show a shortest path to reach destination
     */
    @FXML private TextArea Direction;

    /***
     * A Button that go back to homepage
     */
    @FXML private Button BackButton;

    /***
     * A ComboBox to show all the Vertex Information and let the user choose it
     */
    @FXML private ComboBox<String> CurrentLocationButton;

    /***
     * A ComboBox to show all the Vertex Information and let the user choose the destination
     */
    @FXML private ComboBox DestinationButton;

    /***
     * A button to let user run the search when user done input for origin and destination
     */
    @FXML private Button SearchButton;

    /***
     * Gather all the information from database and show inside the comboBox
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a.retrieve();
        ObservableList<String> places = FXCollections.observableArrayList();
        Vertex start = a.graph.head;
        while(start!=null){
            places.add((String) start.vertexInfo);
            start = start.nextVertex;
        }
        CurrentLocationButton.setItems(places);
        DestinationButton.setItems(places);
    }

    /***
     * Perform the searching for the shortest path to destination
     * @param mouseEvent
     * @throws IOException
     */
    public void SearchButtonClicked(MouseEvent mouseEvent) throws IOException {
        String origin = CurrentLocationButton.getValue();
        String destination = DestinationButton.getValue().toString();
        a.retrieve();
        Direction.setText(a.graph.bfs(origin,destination));
    }

    /***
     * Go back to homepage after user clicked it
     * @param mouseEvent
     * @throws IOException
     */
    public void goToHomePage(MouseEvent mouseEvent) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Home-Page.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(loader));
    }

}

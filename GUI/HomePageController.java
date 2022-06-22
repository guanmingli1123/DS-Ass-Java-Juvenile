import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    /***
     * A button to go to the main page
     */
    @FXML private Button BackButton;
    /***
     * A button to go to histogram page
     */
    @FXML private Button HistogramButton;
    /***
     * A button to go to navigation page
     */
    @FXML private Button NavigationButton;

    /***
     * Main page is shown after user click the button
     * @param mouseEvent
     * @throws IOException
     */
    public void goToMainPage(MouseEvent mouseEvent) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Main-Page.fxml"));
        Stage window = (Stage) BackButton.getScene().getWindow();
        window.setScene(new Scene(loader));
    }

    /***
     * Directed to navigation page after the button clicked
     * @param mouseEvent
     * @throws IOException
     */
    public void goToNavigation(MouseEvent mouseEvent) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Navigation.fxml"));
        Stage window = (Stage) NavigationButton.getScene().getWindow();
        window.setScene(new Scene(loader));
    }

    /***
     * Directed to histogram page after the button clicked
     * @param mouseEvent
     * @throws IOException
     */
    public void goToHistogram(MouseEvent mouseEvent) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Histogram.fxml"));
        Stage window = (Stage) HistogramButton.getScene().getWindow();
        window.setScene(new Scene(loader));
    }
}

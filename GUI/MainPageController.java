import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    /***
     * A button to Homepage
     */
    @FXML private Button DirectButton;

    /***
     * Perform the action to show Homepage
     * @param mouseEvent
     * @throws IOException
     */
    public void DirectButtonClicked(MouseEvent mouseEvent) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Home-Page.fxml"));
        Stage window = (Stage) DirectButton.getScene().getWindow();
        window.setScene(new Scene(loader));
    }
}

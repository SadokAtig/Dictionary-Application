import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class splach implements Initializable {
    @FXML
    private Button exlpore;

    @FXML
    private AnchorPane parent;

    @FXML
    void onexplore(ActionEvent event) throws IOException {
        Stage stage = (Stage) exlpore.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("choose.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }

    
   
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            
        }





}
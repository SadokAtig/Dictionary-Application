import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class login  {

    @FXML
    private TextField pass;

    @FXML
    private TextField name;
    @FXML
    private Button login;

    @FXML
    void onlogin(ActionEvent event) throws IOException {
    String nom = name.getText();
    String mot = pass.getText();
    if(nom.equals("sadok")&& mot.equals("atig") || nom.equals("saif")&& mot.equals("benarfa") ){

        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("ad_frensh.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }
         else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("INVALID ERROR ");
            alert1.setHeaderText(" Invalid UserName and Password!");
            alert1.showAndWait(); 
            name.clear();
            pass.clear();
         }
    }
    @FXML
    private Button Admin;

    @FXML
    private Button ex;

    @FXML
    private Button user;

    @FXML
    void onadmin(ActionEvent event) throws IOException {
        Stage stage = (Stage) Admin.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("ad_login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }

    @FXML
    void onex(ActionEvent event) throws IOException {
        Stage stage = (Stage) ex.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("export_import_frensh.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }

    @FXML
    void onuser(ActionEvent event) throws IOException{
        Stage stage = (Stage) user.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("userfrensh.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }









}

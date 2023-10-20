import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class controluserf implements Initializable {
    
    PreparedStatement stmtt= null ;
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;

    @FXML
    private TextField text_exemplee;

    @FXML
    private TextField text_exemplef;

    @FXML
    private TextField text_idf;

    @FXML
    private TextField text_traductionf;

    @FXML
    private TextField text_typef;

    @FXML
    private TextField text_wordf;



    
    @FXML
    void clear(ActionEvent event) {
        clear();
    }

   

    
    void clear(){
        text_idf.setText(null);
        text_wordf.setText(null);
        text_traductionf.setText(null);
        text_typef.setText(null);
        text_exemplef.setText(null);
        text_exemplee.setText(null);
    }
 


    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    
    }


    @FXML
    private TextField ser;

    @FXML
    void search(KeyEvent event) throws ClassNotFoundException {
     String select = "select * from frensh where wordf=?";
     con= connecte.connect();
     try {
        stmtt = con.prepareStatement(select);
        stmtt.setString(1, ser.getText() );
        rs = stmtt.executeQuery();
        if(rs.next()){
            String id = rs.getString("idf");
            text_idf.setText(id);
            String word = rs.getString("wordf");
            text_wordf.setText(word);
            String tr  = rs.getString("traductionf");
            text_traductionf.setText(tr);
            String typ = rs.getString("typef");
            text_typef.setText(typ);
            String exe = rs.getString("exemplef");
            text_exemplef.setText(exe);
            String exm = rs.getString("exemplee");
            text_exemplee.setText(exm);

        }
         

     } catch (Exception e) {
        // TODO: handle exception
     }
    }


    @FXML
    private Button close;


@FXML
    void onclose(ActionEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("choose.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }
    @FXML
    private Button next;
    @FXML
    void onnext(ActionEvent event) throws IOException {
        Stage stage = (Stage) next.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("userenglish.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
    }

   
   




}



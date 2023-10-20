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

public class controlusere implements Initializable {
    
    PreparedStatement stmtt= null ;
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;

    @FXML
    private TextField text_exemple_e;

    @FXML
    private TextField text_exemple_f;

    @FXML
    private TextField text_id;

    @FXML
    private TextField text_traduction;

    @FXML
    private TextField text_type;

    @FXML
    private TextField text_word;



    
    @FXML
    void clear(ActionEvent event) {
        clear();
    }

   

    
    void clear(){
        text_id.setText(null);
        text_word.setText(null);
        text_traduction.setText(null);
        text_type.setText(null);
        text_exemple_e.setText(null);
        text_exemple_f.setText(null);
    }
 


    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
    
    }
    @FXML
    private TextField ser;


    @FXML
    void search(KeyEvent event) throws ClassNotFoundException {

    
    String select = "select * from english where word=?";
     con= connectionbd.connect();
     try {
        stmtt = con.prepareStatement(select);
        stmtt.setString(1, ser.getText() );
        rs = stmtt.executeQuery();
        if(rs.next()){
            String id = rs.getString("ide");
            text_id.setText(id);
            String word = rs.getString("word");
            text_word.setText(word);
            String tr  = rs.getString("traduction");
            text_traduction.setText(tr);
            String typ = rs.getString("type");
            text_type.setText(typ);
            String exe = rs.getString("exemplee");
            text_exemple_e.setText(exe);
            String exm = rs.getString("exemplef");
            text_exemple_f.setText(exm);

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
    void next(ActionEvent event) throws IOException {
        Stage stage = (Stage) next.getScene().getWindow();
        stage.close();
    
        Stage primaryStage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("userfrensh.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }
}
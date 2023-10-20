import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class maincontrolf implements Initializable {
    
    PreparedStatement stmtt= null ;
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;


    @FXML
    private TableView<dictionnaire_frensh> table;
    @FXML
    private Button add_data;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    

    @FXML
    private TableColumn<dictionnaire_frensh, String> idwordf;

  

    @FXML
    private Button updat;

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
    private TableColumn<dictionnaire_frensh, String>wordf;
    @FXML
    private TableColumn<dictionnaire_frensh, String>traductionf;

    @FXML
    private TableColumn<dictionnaire_frensh, String> typef;
    @FXML
    private TableColumn<dictionnaire_frensh, String> exemplef;
    @FXML
    private TableColumn<dictionnaire_frensh, String>exemple;

    @FXML
    void add(ActionEvent event) throws ClassNotFoundException {
        String insert = "insert into frensh (idf,wordf,traductionf,typef,exemplef,exemplee) values (?,?,?,?,?,?)";
        con = connecte.connect();
        try {
       stmtt = con.prepareStatement(insert);
          stmtt.setString(1, text_idf.getText());
          stmtt.setString(2, text_wordf.getText());
          stmtt.setString(3, text_traductionf.getText());
          stmtt.setString(4, text_typef.getText());
          stmtt.setString(5, text_exemplef.getText());
          stmtt.setString(6, text_exemplee.getText());
          stmtt.executeUpdate();
          showdic();
        } catch (Exception e) {
          System.out.println("laaaaaaaa");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        clear();
    }

    @FXML
    void del(ActionEvent event) throws ClassNotFoundException {
        String delete = "delete from frensh where idf=?";
        con = connecte.connect();
        try {
            stmtt = con.prepareStatement(delete);
          stmtt.setString(1, text_idf.getText());
            stmtt.executeUpdate();
            showdic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update(ActionEvent event) throws ClassNotFoundException {
        String update = "update frensh set idf =? , wordf=? ,traductionf=?,typef=?,exemplef=?,exemplee=? where idf=?";
        con = connecte.connect();
        try {
            stmtt = con.prepareStatement(update);
            
            stmtt.setString(1, text_idf.getText());
            stmtt.setString(2, text_wordf.getText());
            stmtt.setString(3, text_traductionf.getText());
            stmtt.setString(4, text_typef.getText());
            stmtt.setString(5, text_exemplef.getText());
            stmtt.setString(6, text_exemplee.getText());
            stmtt.setString(7, text_idf.getText());
               stmtt.executeUpdate();
               showdic();
             } catch (SQLException e) {
                e.printStackTrace();
             }
    }
    void clear(){
        text_idf.setText(null);
        text_wordf.setText(null);
        text_traductionf.setText(null);
        text_typef.setText(null);
        text_exemplef.setText(null);
        text_exemplee.setText(null);
    }
    @FXML
    void get(MouseEvent event) {
        dictionnaire_frensh d = table.getSelectionModel().getSelectedItem();
        text_idf.setText(d.getIdf());
        text_wordf.setText(d.getWordf());
        text_traductionf.setText(d.getTraductionf());
        text_typef.setText(d.getTypef());
        text_exemplef.setText(d.getExemplee());
        text_exemplee.setText(d.getExemplef());
    }



    public ObservableList<dictionnaire_frensh> dic() throws ClassNotFoundException{
        ObservableList<dictionnaire_frensh> diction = FXCollections.observableArrayList();
        String query = "select * from frensh";
       con = connecte.connect();
       try {
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery(query);
       
           while(rs.next()){
               dictionnaire_frensh s =new dictionnaire_frensh();
               s.setIdf(rs.getString("idf"));
                s.setWordf(rs.getString("wordf"));
               s.setTraductionf(rs.getString("traductionf"));
               s.setTypef(rs.getString("typef"));
               s.setExemplee(rs.getString("exemplef"));
               s.setExemplef(rs.getString("exemplee"));
               diction.add(s);
           }
       
       
       } catch (Exception e) {
          
       }
       return diction;
    }
       

    public void showdic() throws ClassNotFoundException{
        ObservableList<dictionnaire_frensh> list = dic();
        table.setItems(list);
    idwordf.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("idf"));
    wordf.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("wordf"));
    traductionf.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("traductionf"));
    typef.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("typef") );
    exemplef.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("exemplef"));
    exemple.setCellValueFactory(new PropertyValueFactory<dictionnaire_frensh,String>("exemplee"));
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
        try {
            showdic();
        } catch (ClassNotFoundException e) {
           
            e.printStackTrace();
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
    private Button move;
    
    @FXML
        void move(ActionEvent event) throws IOException {
            Stage stage = (Stage) move.getScene().getWindow();
            stage.close();
        
            Stage primaryStage = new Stage ();
            Parent root = FXMLLoader.load(getClass().getResource("ad_english.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
        }










}



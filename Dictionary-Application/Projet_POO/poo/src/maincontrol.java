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

public class maincontrol implements Initializable {
    PreparedStatement stmtt= null ;
    Connection con = null ;
    Statement stmt = null ;
    ResultSet rs = null ;
    @FXML
    private Button add_data;

    @FXML
    private Button clear;

    @FXML
    private Button delt;

    @FXML
    private TableColumn<dictionnaire_english, String> exemplee;

    @FXML
    private TableColumn<dictionnaire_english, String> exemple;

    @FXML
    private TableColumn<dictionnaire_english, String>idword;

    @FXML
    private TableView<dictionnaire_english> tablee;

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
    private TableColumn<dictionnaire_english, String> traduction;

    @FXML
    private TableColumn<dictionnaire_english, String> type;

    @FXML
    private Button update;

    @FXML
    private TableColumn<dictionnaire_english, String> word;

    @FXML
     
    void add(ActionEvent event) throws ClassNotFoundException {
        String insert = "insert into english (ide,word,traduction,type,exemplee,exemplef) values (?,?,?,?,?,?)";
        con = connectionbd.connect();
        try {
       stmtt = con.prepareStatement(insert);
          stmtt.setString(1, text_id.getText());
          stmtt.setString(2, text_word.getText());
          stmtt.setString(3, text_traduction.getText());
          stmtt.setString(4, text_type.getText());
          stmtt.setString(5, text_exemple_e.getText());
          stmtt.setString(6, text_exemple_f.getText());
          stmtt.executeUpdate();
          showdic();
        } catch (Exception e) {
          System.out.println("laaaaaaaa");
        }
    }

    @FXML
    void del(ActionEvent event) throws ClassNotFoundException {
        String delete = "delete from english where ide=?";
        con = connectionbd.connect();
        try {
            stmtt = con.prepareStatement(delete);
          stmtt.setString(1, text_id.getText());
            stmtt.executeUpdate();
            showdic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getdata(MouseEvent event) {
        dictionnaire_english d = tablee.getSelectionModel().getSelectedItem();
        text_id.setText(d.getIde());
        text_word.setText(d.getWord());
        text_traduction.setText(d.getTraduction());
        text_type.setText(d.getType());
        text_exemple_f.setText(d.getExemplee());
        text_exemple_e.setText(d.getExemplef());
    }
    void clear(){
        text_id.setText(null);
        text_word.setText(null);
        text_traduction.setText(null);
        text_type.setText(null);
        text_exemple_e.setText(null);
        text_exemple_f.setText(null);
    }
    @FXML
    void onclear(ActionEvent event) {
        clear();
    }

    @FXML
    void updateper(ActionEvent event) throws ClassNotFoundException {
        String update = "update english set ide =? , word=? ,traduction=?,type=?,exemplee=?,exemplef=? where ide=?";
        con = connectionbd.connect();
        try {
            stmtt = con.prepareStatement(update);
            
            stmtt.setString(1, text_id.getText());
            stmtt.setString(2, text_word.getText());
            stmtt.setString(3, text_traduction.getText());
            stmtt.setString(4, text_type.getText());
            stmtt.setString(5, text_exemple_e.getText());
            stmtt.setString(6, text_exemple_f.getText());
            stmtt.setString(7, text_id.getText());
               stmtt.executeUpdate();
               showdic();
             } catch (SQLException e) {
                e.printStackTrace();
             }
    }





public ObservableList<dictionnaire_english> dic() throws ClassNotFoundException{
    ObservableList<dictionnaire_english> diction = FXCollections.observableArrayList();
    String query = "select * from english";
   con = connectionbd.connect();
   try {
    stmt = con.prepareStatement(query);
    rs = stmt.executeQuery(query);
   
       while(rs.next()){
           dictionnaire_english s =new dictionnaire_english();
           s.setIde(rs.getString("ide"));
            s.setWord(rs.getString("word"));
           s.setTraduction(rs.getString("traduction"));
           s.setType(rs.getString("type"));
           s.setExemplee(rs.getString("exemplef"));
           s.setExemplef(rs.getString("exemplee"));
           diction.add(s);
       }
   
   
   } catch (Exception e) {
      
   }
   return diction;
}
   
   public void showdic() throws ClassNotFoundException{
    ObservableList<dictionnaire_english> list = dic();
    tablee.setItems(list);
 
   idword.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("ide"));
     word.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("word"));
    traduction.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("traduction"));
    type.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("type") );
    exemplee.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("exemplee"));
    exemple.setCellValueFactory(new PropertyValueFactory<dictionnaire_english,String>("exemplef"));
    
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
        Parent root = FXMLLoader.load(getClass().getResource("ad_frensh.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }






}

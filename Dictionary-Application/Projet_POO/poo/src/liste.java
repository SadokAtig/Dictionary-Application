import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class liste {
    Connection con = null;
    PreparedStatement stmt=null;
    PreparedStatement stmt1=null;
    ResultSet rs = null;
    @FXML
    private TextField nbr;
    @FXML
    private RadioButton btn1;

    @FXML
    private RadioButton btn2;
    @FXML
    void bt1check(ActionEvent event)  throws IOException{
        ObservableList<obj> list = Num();
        jTable.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<obj, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_E"));
        expF.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_F"));
        int ch=list.size();
        nbr.setText("N°: \t"+ch);
    
    }

    @FXML
    void bt2check(ActionEvent event)  throws IOException{
        ObservableList<obj> list = Num();
        jTable.setItems(list);
        word.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<obj, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_E"));
        expF.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_F"));
        int ch=list.size();
        nbr.setText("N°: \t"+ch);

    }

    @FXML
    private TableColumn<obj, String> expE;

    @FXML
    private TableColumn<obj, String>expF;

    @FXML
    private TableColumn<obj, String>mot;

    @FXML
    private TableColumn<obj, String>type;

    @FXML
    private TableColumn<obj, String>word;

    @FXML
    private TextField search;
    @FXML
    private TextField fitragetext;

    @FXML
    void filtrage(ActionEvent event) throws IOException, ClassNotFoundException {

        ObservableList<obj> diction = getObjetfiltrer();
        jTable.setItems(diction);
        word.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_E"));
        mot.setCellValueFactory(new PropertyValueFactory<obj, String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<obj, String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_E"));
        expF.setCellValueFactory(new PropertyValueFactory<obj, String>("Expl_F"));




    }
    public  ObservableList<obj> getObjetfiltrer() throws IOException{
        ObservableList<obj> object = FXCollections.observableArrayList();
        File file = new File("filtrage.txt");
        BufferedReader  BufferedReader  =new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(file);
        while((BufferedReader.readLine())!=null){ 
            while(scan.hasNextLine()){
                String line =   scan.nextLine();
                String []details =line.split(",");
                obj ob =new obj();
                for(String det:details){
                   //System.out.println(det);
                    String mot_E=details[0];
                    String mot_F=details[1];
                    String type_E=details[2];
                    String exemple_E=details[3];
                    String exemple_F=details[4];
                    if(( fitragetext.getText().compareTo(mot_E)==0) ||(fitragetext.getText().compareTo(mot_F)==0)){
                
                        ob = new obj(mot_E, mot_F, type_E, exemple_E, exemple_F);
                    
                    }
                
                
                }
                object.add(ob);
            }
        }                
        BufferedReader.close();
        scan.close();
        return object;
    }
    public  ObservableList<obj> Num() throws IOException{
        ObservableList<obj> object = FXCollections.observableArrayList();
        File file = new File("filtrage.txt");
        BufferedReader  BufferedReader  =new BufferedReader(new FileReader(file));
        Scanner scan = new Scanner(file);
        while((BufferedReader.readLine())!=null){ 
            while(scan.hasNextLine()){
                String line =   scan.nextLine();
                String []details =line.split(",");
                obj ob =new obj();
                for(String det:details){
                   //System.out.println(det);
                    String mot_E=details[0];
                    String mot_F=details[1];
                    String type_E=details[2];
                    String exemple_E=details[3];
                    String exemple_F=details[4];

                        ob = new obj(mot_E, mot_F, type_E, exemple_E, exemple_F);

                
                
                }
                object.add(ob);
            }
        }                
        BufferedReader.close();
        scan.close();
        return object;
    }

    public ObservableList<obj> dic() throws ClassNotFoundException{
        ObservableList<obj> diction = FXCollections.observableArrayList();
        String query = "select *  from frensh, english where word=traductionf and wordf=traduction ";
       con = connecte.connect();
       try {
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery(query);
       
           while(rs.next()){
               obj ob = new obj();
               ob.setMot_E(rs.getString("word"));
            ob.setMot_F(rs.getString("wordf")); 
            ob.setType_E(rs.getString("type"));
            ob.setExpl_E(rs.getString("exemplef"));
            ob.setExpl_F(rs.getString("exemplef"));
               diction.add(ob);
           }
       
       
       } catch (Exception e) {
        throw new RuntimeException(e);
       }
       return diction;
    }

    public void showdic() throws ClassNotFoundException{
        ObservableList<obj> list = dic();
        jTable.setItems(list);
    
        word.setCellValueFactory(new PropertyValueFactory<obj,String>("Mot_E"));  
        mot.setCellValueFactory(new PropertyValueFactory<obj,String>("Mot_F"));
        type.setCellValueFactory(new PropertyValueFactory<obj,String>("Type_E"));
        expE.setCellValueFactory(new PropertyValueFactory<obj,String>("Expl_E") );
        expF.setCellValueFactory(new PropertyValueFactory<obj,String>("Expl_F"));
        
        
    }

    @FXML
    private TableView<obj> jTable;

    @FXML
    void select(MouseEvent event) throws Exception {
        
        try {
            ObservableList<obj> list = dic();
            File file = new File("filtrage.txt");
            if (file.exists()) { // Check if file already exists
                file.delete(); // Delete it to start with a fresh file
            }
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" Enter One Time :"+i);
                printWriter.println(list.get(i).AffichageFiltrage()  );
            }
            showdic();
            printWriter.close();
            System.out.println("File written successfully.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

   


}